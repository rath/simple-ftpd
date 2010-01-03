package rath.tools.ftp;

/**
 * 
 * @author Jang-Ho Hwang, rath@xrath.com
 * @version 1.0, $Id$ since 2002/09/04
 */
public interface DataConnectionListener
{
	/**
	 * 연결이 성공/실패 했음을 알려준다.
	 */
	public void actionNegoatiated( boolean isOk );

	/**
	 * 전송이 시작되었음을 알려준다.
	 */
	public void transferStarted();

	/**
	 * 전송이 완료(hasError가 켜있을때는 예외로 종료)되었음을 알려준다.
	 */
	public void transferCompleted( boolean hasError );
}
