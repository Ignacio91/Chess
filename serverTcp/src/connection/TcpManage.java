package connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TcpManage extends Thread
{
	Socket connection;
	String clientSentence;
	BufferedReader inFromClient;
	
	public TcpManage(Socket _connection) 
	{
		connection = _connection;
	}
	@Override
	public void run() 
	{
		
		try 
		{
			inFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while(!connection.isClosed())
			{
				clientSentence = inFromClient.readLine();
				if(clientSentence!= null)
					System.out.println("Received: " + clientSentence);
			}
            
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
	            
	}

}
