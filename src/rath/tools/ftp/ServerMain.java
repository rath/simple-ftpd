package rath.tools.ftp;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
public class ServerMain
{
	public ServerMain()
	{

	}

	@SuppressWarnings("unchecked")
	private void loadProperties() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream("ftp.properties");
			prop.load(fis);
		}
		finally
		{
			if( fis!=null )
				fis.close();
		}
		
		for(Enumeration e=prop.keys(); e.hasMoreElements(); )
		{
			String key = (String)e.nextElement();
			System.setProperty(key, prop.getProperty(key));
		}
	}

	public static void main( String[] args ) throws Exception
	{
		out.println( ">>>-------------------<<<" );
		out.println( ">>> Rath FTP Daemon <<<" );
		out.println( ">>>-------------------<<<" );

		ServerMain sm = new ServerMain();

		out.println( "* Load all configuration properties..." );
		sm.loadProperties();

		Server serv = new Server();
		out.println( "* Initialize FTP Daemon... " );
		serv.init();
		out.println( "* Start FTP Daemon..." );
		serv.run();
	}
}
