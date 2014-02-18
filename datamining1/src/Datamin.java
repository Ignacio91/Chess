import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Datamin {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		long startTime = System.currentTimeMillis();
		System.out.println("Start of DATAMINING ASSIGNEMENT");
		System.out.println("FINDING UNIQUE WORDS AND CREATING TITLE");
		Wordfrecuency word = new Wordfrecuency();
		word.process();
		String s = "";
		
		
		while(!s.equals("y"))
		{
			System.out.println("Run Lda");
			System.out.println("Continue with the process(y/n) ");
			 
			try
			{
			    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    s = bufferRead.readLine();
			    
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		System.out.println("CREATING TOPICS");
		Topic t = new Topic();
		t.process();
		
		for(int i=1;i<6;i++)
		{
			System.out.println("FINDING PATTERNS,  MAX and CLOSED PATTERN FROM TOPIC"+i);
			Apriori ap = new Apriori(i);
			ap = null;
		}
		
		System.out.println("REARRANGING PATTERNS WITH PURITY");
		Purity p = new Purity(t.getLines(), word.getLines());
		p.callprocess();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println( "The execution Time was  " +elapsedTime);
		
	}
	

}
