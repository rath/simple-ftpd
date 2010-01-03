package rath.tools.ftp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
/**
 *
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
public class Server implements Runnable
{
	private ServerSocketChannel socket;

	public Server()
	{

	}

	public void init() throws IOException
	{
		this.socket = ServerSocketChannel.open();
		this.socket.configureBlocking(true);
		this.socket.socket().bind( new InetSocketAddress(
			Integer.getInteger("ftp.port", 21).intValue() ));
	}

	public void run()
	{
		try
		{
			while(true)
			{
				SocketChannel sc = socket.accept();
				new FTPChannel(sc).start();
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			destroy();
		}
	}

	public void destroy()
	{
		if( this.socket!=null )
		{
			try
			{
				this.socket.close();
			}
			catch( IOException e ) {}
			this.socket = null;
		}
	}
}
