package rath.tools.ftp;

import java.io.IOException;
import java.nio.channels.SocketChannel;
/**
 *
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
class ActiveConnection extends DataConnection
{
	ActiveConnection() throws IOException
	{

	}
	
	protected void doNegotiate() throws IOException
	{
		channel = SocketChannel.open();
		channel.configureBlocking(true);
		channel.connect( this.addr );
	}
}
