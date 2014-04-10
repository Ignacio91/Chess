package connection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         //String clientSentence;
         //BufferedReader inFromClient;
         ServerSocket welcomeSocket = new ServerSocket(8603);
         System.out.println("Server started" + welcomeSocket);
         /* System.out.println(checkSimilarity(50, 75));
         System.out.println(checkSimilarity(200, 300));
         
         System.out.println(checkSimilarity(62, 43));
         System.out.println(checkSimilarity(450, 300));
         */
         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println(connectionSocket);
            TcpManage conn = new TcpManage(connectionSocket);
            conn.start();
           // inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //clientSentence = inFromClient.readLine();
            //System.out.println("Received: " + clientSentence);
         }
      }
   private static boolean checkSimilarity(int y, int y2) 
	{
		if(Math.abs(y - y2) + Math.abs(y2+25 - y) == Math.abs(y2+25 - y2)
				|| Math.abs(y - y2-25) + Math.abs(y2 - y) == Math.abs(y2 - (y2- 25)))
			return true;
		else
			return false;
	}
   
   private boolean checkrange(double x, double x2) 
	{
		if(x> x2 + 100 || x< x2 - 100)
		return true;
		else return false;
	}
}
