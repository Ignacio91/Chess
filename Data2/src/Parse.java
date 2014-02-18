import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Iterator;

public class Parse 
{
	int label ,b,c;
	String a;
	HashSet<Table> tb;
	Parse(){
		
	}
	
	public ArrayList<Label> processtrain(String file)
	{
		String aa;
		ArrayList<Label> data = new ArrayList<Label>();
		//ArrayList<Attribute> attribute = new ArrayList<Attribute>();
		ArrayList<IntegerM> attribute = new ArrayList<IntegerM>();
		tb = new HashSet<Table>();
		File inputFile = new File(file);
		int i =0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line;
	        while((line = br.readLine()) != null)
	        {
	        	if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))
	        		continue;
        		//System.out.println(line.charAt(0));
	        	if((line.charAt(0)+"").equals("+")){
	        		//System.out.println("a");
	        		label= Integer.parseInt(line.substring(1,2));}
	        	else
	        		label= Integer.parseInt(line.substring(0,2));
	        	
	        	StringTokenizer st = new StringTokenizer(line, " ");
	        	aa = st.nextToken();
	        	i++;
	        	while(st.hasMoreElements()){
	        		
	        		a = st.nextToken();
	        		//System.out.println(a);
	        		int b = Integer.parseInt(a.substring(0,a.indexOf(":")));
	        		int c = Integer.parseInt(a.substring(a.indexOf(":")+1,a.length()));
	        		check(a , label);
	        		
	        		//System.out.println(c);
	        		//attribute.add(new Attribute(b));
	        		attribute.add(new IntegerM(b, c));
	        		
	        	}
	        	
	        	
	        	data.add(new Label(label, attribute));
	        	attribute =new ArrayList<IntegerM>();
	        	
	        }
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
		
	}
	public ArrayList<Label> processtest(String file)
	{
		ArrayList<Label> data = new ArrayList<Label>();
		//ArrayList<Attribute> attribute = new ArrayList<Attribute>();
		ArrayList<IntegerM> attribute = new ArrayList<IntegerM>();
		
		File inputFile = new File(file);
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line;
	        while((line = br.readLine()) != null)
	        {
	        	if(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))
	        		continue;
        		//System.out.println(line.charAt(0));
	        	if((line.charAt(0)+"").equals("+")){
	        		//System.out.println("a");
	        		label= Integer.parseInt(line.substring(1,2));}
	        	else
	        		label= Integer.parseInt(line.substring(0,2));
	        	
	        	StringTokenizer st = new StringTokenizer(line, " ");
	        	st.nextToken();
	        	
	        	while(st.hasMoreElements())
	        	{
	        		
	        		a = st.nextToken();
	        		//System.out.println(a);
	        		int b = Integer.parseInt(a.substring(0,a.indexOf(":")));
	        		int c = Integer.parseInt(a.substring(a.indexOf(":")+1,a.length()));
	        		//System.out.println(c);
	        		//attribute.add(new Attribute(b));
	        		attribute.add(new IntegerM(b, c));
	        		
	        	}
	        	
	        	
	        	data.add(new Label(label, attribute));
	        	attribute =new ArrayList<IntegerM>();
	        	
	        }
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}

	private void check(String a2, int a1) 
	{
		
		HashSet<Table> ta = new HashSet<Table>(tb);
		Table t, t1;
		Iterator<Table> itera =   ta.iterator();
		
		boolean bo = false;
		while(itera.hasNext())
		{
			t = itera.next();
			if(a2.equals(t.getName()))
			{
				if(a1 == 1)
				{
					t1 = new Table(a2, t.getCount1()+1 , t.getCount2());
					tb.remove(t);
					tb.add(t1);
					bo = true;
				}
				if(a1 == -1)
				{
					t1 = new Table(a2, t.getCount1() , t.getCount2()+1);
					tb.remove(t);
					tb.add(t1);
					bo = true;
				}
				
			}
			
		}
		if(!bo)
		{
			
			if(a1 == 1)
				tb.add(new Table(a2, 1, 0));
			else
				tb.add(new Table(a2, 0, 1));
		}
		
		
	}
	
	public HashSet<Table> getT()
	{
		/*Iterator<Table> itera =   tb.iterator();
		Table t ;
		boolean bo = false;
		while(itera.hasNext())
		{
			t =  itera.next();
			System.out.println(t.getName()+ " "+ t.getCount1() +" "+t.getCount2());
		}*/
		return tb;
	}

}
