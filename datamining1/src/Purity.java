import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Purity 
{
	BufferedWriter bufferedWriter;
	BufferedWriter bufferedWriter1;
	BufferedWriter bufferedWriter2;
	BufferedWriter bufferedWriter3;
	BufferedWriter bufferedWriter4;
	
	BufferedReader bufferedReader;
	BufferedReader bufferedReader1;
	
	int valuep;
	int x=0;
	int frec;
	int index=0;
	
	File pattern0;
	File pattern1;
	File pattern2;
	File pattern3;
	File pattern4;
	
	File purity0;
	File purity1;
	File purity2;
	File purity3;
	File purity4;
	
	String line;
	String find = "\t";
	
	ArrayList<Integer> al0;
	ArrayList<Integer> al1;
	ArrayList<Integer> al2;
	ArrayList<Integer> al3;
	ArrayList<Integer> al4;
	
	ArrayList<SupportAndPurityCombined> ar;
	ArrayList<SupportAndPurityCombined> ar1;
	ArrayList<SupportAndPurityCombined> ar2;
	ArrayList<SupportAndPurityCombined> ar3;
	ArrayList<SupportAndPurityCombined> ar4;
	
	
	
	ArrayList<Integer> as=new ArrayList<Integer>();
	Purity (ArrayList<Integer> _as, int _valuep)
	{
		valuep = _valuep;
		as.addAll(_as);
		initialize();
	}
	
	private void initialize() 
	{

		 pattern0 = new File("pattern/pattern-1.txt");
		 pattern1 = new File("pattern/pattern-2.txt");
		 pattern2 = new File("pattern/pattern-3.txt");
		 pattern3 = new File("pattern/pattern-4.txt");
		 pattern4 = new File("pattern/pattern-5.txt");
		
		 purity0 = new File("purity/purity-1.txt");
		 purity1 = new File("purity/purity-2.txt");
		 purity2 = new File("purity/purity-3.txt");
		 purity3 = new File("purity/purity-4.txt");
		 purity4 = new File("purity/purity-5.txt");
		
		al0 = new ArrayList<Integer>();
		al1 = new ArrayList<Integer>();
		al2 = new ArrayList<Integer>();
		al3 = new ArrayList<Integer>();
		al4 = new ArrayList<Integer>();

		
		ar= new ArrayList<SupportAndPurityCombined>();
		ar1= new ArrayList<SupportAndPurityCombined>();
		ar2= new ArrayList<SupportAndPurityCombined>();
		ar3= new ArrayList<SupportAndPurityCombined>();
		ar4= new ArrayList<SupportAndPurityCombined>();

		
	}

	
	
	
	public void callprocess()
	{		
		
		al0.addAll(as);
		al1.addAll(as);
		al2.addAll(as);
		al3.addAll(as);
		al4.addAll(as);

		process(pattern0);
		process(pattern1);
		process(pattern2);
		process(pattern3);
		process(pattern4);
			
	}

	
	public void process(File x){
	try
	{
		
		bufferedReader = new BufferedReader(new FileReader(x));
		bufferedWriter = new BufferedWriter(new FileWriter(purity0));
		bufferedWriter1 = new BufferedWriter(new FileWriter(purity1));
		bufferedWriter2 = new BufferedWriter(new FileWriter(purity2));
		bufferedWriter3 = new BufferedWriter(new FileWriter(purity3));
		bufferedWriter4 = new BufferedWriter(new FileWriter(purity4));
		

		while ((line= bufferedReader.readLine()) != null) 
		{
	    	
			
	    		index=line.indexOf(find);
				String support= line.substring(0,index); 
				
				int frecPatternThisTopic =Integer.parseInt(support);
				String pattern= line.substring(index+1,line.length());
				
				
				if(x.getName().equals("pattern-1.txt"))
				{
					
					int f1 = otherTopic(pattern1,pattern);
					int f2 = otherTopic(pattern2,pattern);
					int f3 = otherTopic(pattern3,pattern);
					int f4 = otherTopic(pattern4,pattern);
					
					
					ArrayList<Float> fpot0= new ArrayList<Float>();
					
					
					int numlines0 =(int) al0.get(0);
					int numlines3 =(int) al0.get(3);
					int numlines1 =(int) al0.get(1);
					int numlines2 =(int) al0.get(2);
					int numlines4 =(int) al0.get(4);
					
					fpot0.add(new Float(f3/(float)numlines3));
					fpot0.add(new Float(f1/(float)numlines1));
					fpot0.add(new Float(f2/(float)numlines2));
					fpot0.add(new Float(f4/(float)numlines4));

					
					ClassPurity pu= new ClassPurity((frecPatternThisTopic/(float)numlines0),fpot0,pattern,numlines0,valuep);
					float purity0= pu.purity();
					
					ar.add(new SupportAndPurityCombined(purity0*frecPatternThisTopic, pattern,purity0));
				}
				else if(x.getName().equals("pattern-2.txt"))
				{
					int f0 = otherTopic(pattern0,pattern);
					int f2 = otherTopic(pattern2,pattern);
					int f3 = otherTopic(pattern3,pattern);
					int f4 = otherTopic(pattern4,pattern);
					
					
					ArrayList<Float> fpot1= new ArrayList<Float>();
					
					
					int numlines1 =(int) al1.get(1);
					int numlines3 =(int) al1.get(3);
					int numlines0 =(int) al1.get(0);
					int numlines2 =(int) al1.get(2);
					int numlines4 =(int) al1.get(4);
					
					fpot1.add(new Float(f0/(float)numlines0));
					fpot1.add(new Float(f2/(float)numlines2));
					fpot1.add(new Float(f3/(float)numlines3));
					fpot1.add(new Float(f4/(float)numlines4));
					
					ClassPurity pu= new ClassPurity((frecPatternThisTopic/(float)numlines1),fpot1,pattern,numlines1,valuep);
					float purity1= pu.purity();
					ar1.add(new SupportAndPurityCombined(purity1*frecPatternThisTopic, pattern,purity1));
				}
				else if(x.getName().equals("pattern-3.txt"))
				{
					int f0 = otherTopic(pattern0,pattern);
					int f1 = otherTopic(pattern1,pattern);
					int f3 = otherTopic(pattern3,pattern);
					int f4 = otherTopic(pattern4,pattern);
					
					
					ArrayList<Float> fpot2= new ArrayList<Float>();
					
					
					int numlines2=(int) al2.get(2);
					int numlines3 =(int) al2.get(3);
					int numlines0 =(int) al2.get(0);
					int numlines1 =(int) al2.get(1);
					int numlines4 =(int) al2.get(4);
					
					fpot2.add(new Float(f0/(float)numlines0));
					fpot2.add(new Float(f1/(float)numlines1));
					fpot2.add(new Float(f3/(float)numlines3));
					fpot2.add(new Float(f4/(float)numlines4));
					

					ClassPurity pu= new ClassPurity((frecPatternThisTopic/(float)numlines2),fpot2,pattern,numlines2,valuep);
					float purity2= pu.purity();
					ar2.add(new SupportAndPurityCombined(purity2*frecPatternThisTopic, pattern,purity2));
				}
				else if(x.getName().equals("pattern-4.txt"))
				{
					int f0 = otherTopic(pattern0,pattern);
					int f1 = otherTopic(pattern1,pattern);
					int f2 = otherTopic(pattern2,pattern);
					int f4 = otherTopic(pattern4,pattern);
					
					
					ArrayList<Float> fpot3= new ArrayList<Float>();
					
					int numlines3 =(int) al3.get(3);
					int numlines2 =(int) al3.get(2);
					int numlines0 =(int) al3.get(0);
					int numlines1 =(int) al3.get(1);
					int numlines4 =(int) al3.get(4);
					
					fpot3.add(new Float(f0/(float)numlines0));
					fpot3.add(new Float(f1/(float)numlines1));
					fpot3.add(new Float(f2/(float)numlines2));
					fpot3.add(new Float(f4/(float)numlines4));
				
					
					ClassPurity pu= new ClassPurity((frecPatternThisTopic/(float)numlines3),fpot3,pattern,numlines3,valuep);
					float purity3= pu.purity();
					ar3.add(new SupportAndPurityCombined(purity3*frecPatternThisTopic, pattern,purity3));
				}
				else if(x.getName().equals("pattern-5.txt"))
				{
					int f0 = otherTopic(pattern0,pattern);
					int f1 = otherTopic(pattern1,pattern);
					int f2 = otherTopic(pattern2,pattern);
					int f3 = otherTopic(pattern3,pattern);
					
					
					ArrayList<Float> fpot4= new ArrayList<Float>();
					
					int numlines4 =(int) al4.get(4);
					int numlines2 =(int) al4.get(2);
					int numlines0 =(int) al4.get(0);
					int numlines1 =(int) al4.get(1);
					int numlines3 =(int) al4.get(3);
					
					fpot4.add(new Float(f0/(float)numlines0));
					fpot4.add(new Float(f1/(float)numlines1));
					fpot4.add(new Float(f2/(float)numlines2));
					fpot4.add(new Float(f3/(float)numlines3));
					
					ClassPurity pu= new ClassPurity((frecPatternThisTopic/(float)numlines4),fpot4,pattern,numlines4,valuep);
					float purity4= pu.purity();
					ar4.add(new SupportAndPurityCombined(purity4*frecPatternThisTopic, pattern,purity4));
				}
	    	
			   
		}
		bufferedReader.close();
		sort(ar);
		sort(ar1);
		sort(ar2);
		sort(ar3);
		sort(ar4);
		
		write(ar,bufferedWriter);
		write(ar1,bufferedWriter1);
		write(ar2,bufferedWriter2);
		write(ar3,bufferedWriter3);
		write(ar4,bufferedWriter4);
		
		bufferedWriter.close();
		bufferedWriter1.close(); 
		bufferedWriter2.close(); 
		bufferedWriter3.close(); 
		bufferedWriter4.close(); 
		

	}
    catch (NumberFormatException nfe) { 
        System.out.println("NumberFormatException"); 
     }
	catch (IOException e)
	{
		System.out.println("IOException"); 
	}
	
	}
	public int otherTopic(File x, String y)
	{
		try
		{
			
			bufferedReader1 = new BufferedReader(new FileReader(x));
			
	
			while ((line= bufferedReader1.readLine()) != null) 
			{
				index=line.indexOf(find);
				
				if((line.substring(index+1,line.length())).equals(y))
				{
					String support= line.substring(0,index); //(support)
					frec =Integer.parseInt(support);
				}
					

			}	
			bufferedReader1.close();	
		}
	    catch (NumberFormatException nfe) { 
	        System.out.println("NumberFormatException"); 
	     }
		catch (IOException e)
		{
			System.out.println("IOException"); 
		}
		return frec;
	}
	
	
	public void sort(ArrayList<SupportAndPurityCombined> sortar)
	{
		for(int i= 0; i<sortar.size()-1;i++){
			for(int j= 0; j<sortar.size()-1-i;j++)
			{
				if(sortar.get(j).getCombine()<sortar.get(j+1).getCombine())
				{
					SupportAndPurityCombined aux= sortar.get(j);
					sortar.set(j, sortar.get(j+1));
					sortar.set(j+1, aux);
				}
				
			}
		}
	}
	public void write(ArrayList<SupportAndPurityCombined> sortar,BufferedWriter bf)
	{
		for(int i=0; i<sortar.size();i++)
		{
			Float f = new Float(sortar.get(i).getPurity());
			try {
				bf.write(f.toString());
				bf.write(" ");
				bf.write(sortar.get(i).getPattern());
				bf.write("\n");
			} catch (NumberFormatException nfe) { 
		        System.out.println("NumberFormatException"); 
		     }
			catch (IOException e)
			{
				System.out.println("IOException"); 
			}
	
		}
		
	}
	
}
