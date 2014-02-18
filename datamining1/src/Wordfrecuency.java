import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Iterator;




public class Wordfrecuency {
	Word word;
	HashSet<String> vocab;
	HashSet<Word> frecuen;
	HashSet<Vocab> hashvoc;
	ArrayList<HashSet> file;
	int num_titleline;
	Wordfrecuency()
	{
		vocab = new HashSet<String>();
		frecuen = new HashSet<Word>();
		hashvoc = new HashSet<Vocab>();
		file = new ArrayList();
		num_titleline =0;
	}
	public void process()
	{
		
		File inputFile = new File("paper.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line;
	        while((line = br.readLine()) != null){
	        	
	        	line = line.substring(line.indexOf("\t")+1, line.length());
	        	
	        	StringTokenizer st = new StringTokenizer(line, " ");
	        	while(st.hasMoreElements()){
	        		String a = st.nextToken();
	        		processWord(a);
	        		
	        	}
	        	
	        	file.add(frecuen);
	        	frecuen = new HashSet();
	        	
	        }
	        write();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	private void write() 
	{
		File filevoc = new File("vocab.txt");
		File filefre = new File("title.txt");
		try 
		{
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(filevoc));
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(filefre));
			int num = 0;
			String wor;
			Iterator<String> itera =  vocab.iterator();
			while(itera.hasNext())
			{
				wor = itera.next();
				
				hashvoc.add(new Vocab(num, wor+"\t"+num));
				bw1.write(wor+"\t"+num+ "\n");
				num++;
			}
			bw1.close();
			Iterator<HashSet> iter1 =  file.iterator();
			
			while(iter1.hasNext()){
				HashSet a = iter1.next();
				Iterator<Word> iter2=  a.iterator();
				bw2.write(a.size()+" ");
				while(iter2.hasNext())
				{
					Word x =iter2.next();
					bw2.write(checking(x.getName())+":"+ x.getCount()+ " ");
					
					
				}
				bw2.write("\n");
				num_titleline++;
			}
			bw2.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private String checking(String name) 
	{
		Iterator<Vocab> iter2=  hashvoc.iterator();
		while(iter2.hasNext())
		{
			String line2 = iter2.next().getName();
			String line = line2.substring(0, line2.indexOf("\t"));
			//System.out.println(line2.substring(line.indexOf("\t")+1, line.length()));
        	if(line.equals(name)){
        		return line2.substring(line2.indexOf("\t")+1, line2.length());
        	}
		}
		File inputFile = new File("vocab.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			String line;
	        while((line = br.readLine()) != null){
	        	String line2 = line.substring(0, line.indexOf("\t"));
	        	if(line2.equals(name)){
	        		return line.substring(line.indexOf("\t"), line.length());
	        	}
	        }
	        br.close();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Not found";
	}
	private void processWord(String word) 
	{
		
		vocab.add(word);
		
		if(!check(word)){
			
			frecuen.add(new Word(1, word));
		}
		
		
	}
	private boolean check(String word) {
		Word checking;
		
		Iterator<Word> itera =  frecuen.iterator();
		while(itera.hasNext()){
			checking = itera.next();
			if(checking.getName().equals(word))
			{
				
				checking.add();
				frecuen.remove(checking);
				frecuen.add(checking);
				return true;
			}
			
		}
		return false;
	}
	public int getLines(){
		return num_titleline;
	}

}
