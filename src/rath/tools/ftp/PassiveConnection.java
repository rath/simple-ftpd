package rath.tools.ftp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
/**
 *
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
class PassiveConnection extends DataConnection
{
	private ServerSocketChannel socket;

	PassiveConnection() throws IOException
	{
		InetAddress local = InetAddress.getLocalHost();	
		if( local.isLoopbackAddress() )
			throw new IOException("Can't take local ip address");

		ServerSocket sock = new ServerSocket();	
		int okPort = -1;
		int errorCount = 0;
		while(errorCount < 20)
		{
			int port = 4096 + (int)(Math.random() * 40000.0D);
			try
			{
				sock.bind( new InetSocketAddress(local, port) );
				okPort = port;
			}
			catch( IOException e )
			{
				errorCount++;
				continue;
			}
			break;
		}
		sock.close();

		this.addr = new InetSocketAddress(local, okPort);

		socket = ServerSocketChannel.open();	
		socket.configureBlocking(true);
		socket.socket().setSoTimeout( 1000 * 10 );
		socket.socket().bind(this.addr);
	}
	
	protected void doNegotiate() throws IOException
	{
		super.channel = socket.accept();
	}

	public void stop()
	{
		super.stop();
		if( socket!=null )
		{
			try 
			{
				socket.close();
			}
			catch( IOException e ) {}
			socket = null;
		}

	}
}
