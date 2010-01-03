package rath.tools.ftp;

/**
 *
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
public class Authenticator 
{
	public Authenticator()
	{

	}

	public boolean isValidUser( String user, String pass ) 
		throws Exception
	{
		String fileAuth = System.getProperty("ftp.user." + user);
		if( fileAuth!=null && pass.equals(fileAuth) )
			return true;

		return false;
	}
}
