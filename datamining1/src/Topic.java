import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Topic 
{
	int i;
	
	File a = new File("word-assignments.txt");
	String find=":" ;
	
	String line;

	
	int index=0;
	int fromIndex=0;
	
	BufferedWriter writer;
	BufferedWriter writer1;
	BufferedWriter writer2;
	BufferedWriter writer3;
	BufferedWriter writer4;
	BufferedReader reader;
	
	boolean checkEnd0;
	boolean checkEnd1;
	boolean checkEnd2;
	boolean checkEnd3;
	boolean checkEnd4;
	
	File b0 = new File("topic-1.txt");
	File b1 = new File("topic-2.txt");
	File b2 = new File("topic-3.txt");
	File b3 = new File("topic-4.txt");
	File b4 = new File("topic-5.txt");
	
	int lines0=1;
	int lines1=1;
	int lines2=1;
	int lines3=1;
	int lines4=1;
	
	ArrayList<Integer> linenumbers= new ArrayList<Integer>();
	
	Topic()
	{	
	}
	
	public void process(){
	try
	{
		
		reader = new BufferedReader(new FileReader(a));
		writer = new BufferedWriter(new FileWriter(b0));
		writer1 = new BufferedWriter(new FileWriter(b1));
		writer2 = new BufferedWriter(new FileWriter(b2));
		writer3 = new BufferedWriter(new FileWriter(b3));
		writer4 = new BufferedWriter(new FileWriter(b4));
		

		while ((line= reader.readLine()) != null) 
		{
			
		    	
		    	
		    	StringTokenizer st= new StringTokenizer(line," ");
		    	String group= st.nextToken();
		    	
		    	resetChecks();
		    	while(st.hasMoreElements())
		    	{
		    		 group= st.nextToken();
		    		String term= group.substring(0,4);
		    		int topic1= Character.getNumericValue(group.charAt(6));
		    		String topic = topic1+"";
		    		
			    	topic.trim();
			    	term.trim();
			    	
			    	if(topic.equals("0"))
			    	{
			    		
			            writer.write(term);
			            writer.write(" ");
			            checkEnd0= true;
			    	}
			    	else if(topic.equals("1"))
			    	{
			    		
			            writer1.write(term);
			            writer1.write(" ");
			            checkEnd1=true;
			            
			    	}
			    	else if(topic.equals("2"))
			    	{
			            writer2.write(term);
			            writer2.write(" ");
			            checkEnd2=true;
			    	}
			    	else if(topic.equals("3"))
			    	{
			            writer3.write(term);
			            writer3.write(" ");
			            checkEnd3=true;
			    	}
			    	else if(topic.equals("4"))
			    	{
			            writer4.write(term);
			            writer4.write(" ");
			            checkEnd4=true;
			    	}
			    	else
			    	{
			    		 System.out.println("hello ");
			    	}
		    		
		    	}
		    	if(checkEnd0==true)
		    	{
		    		writer.write("\n");
		    		lines0++;
		    	}
		    	
		    	if(checkEnd1==true)
		    	{
		    		writer1.write("\n");
		    		lines1++;
		    	}
		    	
		    	if(checkEnd2==true)
		    	{
		    		writer2.write("\n");
		    		lines2++;
		    	}
		    	
		    	if(checkEnd3==true)
		    	{
		    		writer3.write("\n");
		    		lines3++;
		    	}
		    	
		    	if(checkEnd4==true)
		    	{
		    		writer4.write("\n");
		    		lines4++;
		    	}

		    	

		   
		   
		}

		

		
		writer.close();
		writer1.close();
		writer2.close();
		writer3.close();
		writer4.close();
		 reader.close();
	}
    catch (NumberFormatException nfe) { 
        System.out.println("Invalid input "); 
     }
	catch (IOException e)
	{
		System.out.println("Invalid input1"); 
	}
	
	}//from process
	public void resetChecks()
	{
		 checkEnd0=false;//To control that there is a term in that line
		 checkEnd1=false;
		 checkEnd2=false;
		 checkEnd3=false;
		 checkEnd4=false;
	}
	public ArrayList<Integer> getLines()
	{
		linenumbers.add(new Integer(lines0));
		linenumbers.add(new Integer(lines1));
		linenumbers.add(new Integer(lines2));
		linenumbers.add(new Integer(lines3));
		linenumbers.add(new Integer(lines4));
		return linenumbers;
	}
}