import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Apriori extends Observable {

	File filepat, filepat2, filepat3 ;
	int topic;
	List<int[]> frequentCandidates;
	ArrayList<String> pattern;
	ArrayList<String> maxfrec;
	ArrayList<String> maxclose;
	
	HashSet<Support> hashpat;
	HashSet<Support> hashmax;
	HashSet<Support> hashclose;
	
	BufferedWriter bw1, bw2, bw3;
    
    private List<int[]> itemsets ;
    private String transaFile; 
    private int numItems; 
    private int numTransactions; 
    private double minSup; 
    
    private boolean usedAsLibrary = false;

    public  Apriori(int _topic) 
    {
    	pattern = new ArrayList();
    	maxfrec = new ArrayList();
    	maxclose = new ArrayList();
    	
    	hashpat = new HashSet();
    	hashmax = new HashSet();
    	hashclose = new HashSet();
    	
		topic = _topic;
		filepat = new File("pattern/pattern-"+ topic + ".txt" );
		filepat2 = new File("max/maxpat-"+ topic + ".txt" );
		filepat3 = new File("closed/closepat-"+ topic + ".txt" );
		transaFile = "topic-"+topic+ ".txt";
		
			setup();
	        process();
	        sort();
	        
		
        
    }

    private void sort() {
    	try
		{
			bw1 = new BufferedWriter(new FileWriter(filepat));
			
			String line;
			
			for(int i=0;i<pattern.size()-1;i++) {
	            for(int j=0;j<pattern.size()-1-i;j++) {
	                if ( Integer.parseInt((pattern.get(j).substring(0, pattern.get(j).indexOf("\t") ))) <
	                		Integer.parseInt((pattern.get(j+1).substring(0, pattern.get(j+1).indexOf("\t") )))) 
	                {
	                	
	                    String aux;
	                    aux=pattern.get(j);
	                    pattern.set(j, pattern.get(j+1));
	                    pattern.set(j+1, aux);
	                }
	            }
	        }
			
			for(int i=0;i<pattern.size();i++) 
			{
				bw1.write(pattern.get(i));
			}
			maxpattern();
			maxclose();
			
			bw1.close();
		
    	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
    	}
		
	}

	private void maxpattern() {
		
		int[] word1;
		int[]  word2;
		Support supor;
		Support supor2;
		Boolean no_max ;
		
		
	Iterator<Support> itera =  hashpat.iterator();
	while(itera.hasNext()){
		Iterator<Support> itera2 =  hashpat.iterator();
		no_max = false;
		
		supor = itera.next();
		word1 = supor.getSec();
		while(itera2.hasNext())
		{
			supor2 = itera2.next();
			word2 = supor2.getSec();
			
			if(word2.length > word1.length && contains(word1, word2) )
			{
				
				no_max =true;
				
				
				break;
			}
			
			
		}
		if(!no_max)
        {
			hashmax.add(supor);
        }
		
	}
	write(hashmax, "max");
	
		
	
}
private void maxclose() {
		
		int[] word1;
		int[]  word2;
		Support supor;
		Support supor2;
		
		Boolean no_close ;
		
	Iterator<Support> itera =  hashpat.iterator();
	while(itera.hasNext()){
		Iterator<Support> itera2 =  hashpat.iterator();
		
		no_close= false;
		supor = itera.next();
		word1 = supor.getSec();
		while(itera2.hasNext())
		{
			supor2 = itera2.next();
			word2 = supor2.getSec();
			
			if(word2.length > word1.length && contains(word1, word2) && supor2.getCount()== supor.getCount() )
			{
				
				no_close=true;
				
				break;
			}
			
			
		}
		
		if(!no_close)
        {
			hashclose.add(supor);
        }
	}
	write(hashclose, "closed");
		
	
}


	private void write(HashSet<Support> hashmax, String string) 
	{
		ArrayList<Support> ar = new ArrayList(hashmax);
		try
		{
			if(string.equals("max"))
				bw2 = new BufferedWriter(new FileWriter(filepat2));
			else
				bw2 = new BufferedWriter(new FileWriter(filepat3));
			
			for(int i=0;i<ar.size()-1;i++) {
	            for(int j=0;j<ar.size()-1-i;j++) {
	            	if ((ar.get(j)).getCount() < ar.get(j+1).getCount() )
	            	{
	            		Support aux;
                    	aux = ar.get(j);
                    	ar.set(j, ar.get(j+1));
                    	ar.set(j+1, aux);
	            		
	            	}
	            		
	            }
			}
			for(int i=0;i<ar.size();i++) 
			{
				bw2.write(ar.get(i).getCount() + "" + Arrays.toString(ar.get(i).getSec())+ "\n");
			}
			
			bw2.close();
			
			
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
    	}
		
	}

	private String replace(String word1) {
		word1 = word1.substring(word1.indexOf("\t")+1, word1.length());
		System.out.println(word1);
		word1 = word1.replace("[", "");
		System.out.println(word1);
		word1 = word1.replace("\n", "");
		System.out.println(word1);
		word1 = word1.replaceAll("]", "");
		System.out.println(word1);
		word1 = word1.replaceAll(",", "");
		System.out.println(word1);
		
		return word1;
	}

	private boolean contains(int[] word1, int[] word2) {
		
		 Boolean bool;
		 int i, j;
	    /*for (i=0,j=0; i<word1.length && j<word2.length;) {
	        if (word1[i] < word2[j]) {
	        	System.out.println("mayor");
	            ++i;
	        } else if (word1[i] == word2[j]) 
	        {
	        	System.out.println("igual");
	            ++i; ++j;
	            System.out.println("igual "+j);
	        } else {
	        	
	        	++j;
	        	System.out.println("nada" + j);
	        }
	    }
	    // make sure there are no elements left in sub
	    if(i == word1.length )
	    	return true;*/
		 for(i =0;i< word1.length; i++)
		 {
			 bool =false;
			 for(j =0;j<word2.length;j++)
			 {
				 if(word1[i] == word2[j])
				 {
					 bool = true;
					 break;
				 }
				 
			 }
			 if(!bool){
				 return false;
			 }
		 }
	    
	    return true;
		
	}

	
    private void process() 
    {
        

        
        createItemsetsOfSize1();        
        int itemsetNumber=1; 
        int nbFrequentSets=0;
        
        while (itemsets.size()>0)
        {

            calculateFrequentItemsets();

            if(itemsets.size()!=0)
            {
                nbFrequentSets+=itemsets.size();
                create_new_items();
            }

            itemsetNumber++;
        } 
 
    }

    private void foundFrequentItemSet(int[] itemset, int support)
    {
    	if (usedAsLibrary) 
    	{
            this.setChanged();
            notifyObservers(itemset);
    	}
    	else 
    	{
    		pattern.add(support +  "\t" + Arrays.toString(itemset)+ "\n");
    		hashpat.add(new Support(itemset, support));
			
    	}
    }


    private void setup() 
    {        
        // setting transafile
        
    	
    	// setting minsupport
    	 minSup = .001;// by default

    	
    	// processing thourgh the file to compute numItems and  numTransactions
    	numItems = 0;
    	numTransactions=0;
    	
    	BufferedReader data_in;
		try {
			data_in = new BufferedReader(new FileReader(transaFile));
			while (data_in.ready()) {    		
	    		String line=data_in.readLine();
	    		if (line.matches("\\s*")) continue; // be friendly with empty lines
	    		numTransactions++;
	    		StringTokenizer t = new StringTokenizer(line," ");
	    		while (t.hasMoreTokens()) {
	    			int x = Integer.parseInt(t.nextToken());
	    		
	    			if (x+1>numItems) numItems=x+1;
	    		}    		
	    	}  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }


	private void createItemsetsOfSize1() {
		itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
        	int[] cand = {i};
        	itemsets.add(cand);
        }
	}
			

    private void create_new_items()
    {
    	
    	int currentSizeOfItemsets = itemsets.get(0).length;
    	
    	HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>(); //temporary candidates
    	
        // compare each pair of itemsets of size n-1
        for(int i=0; i<itemsets.size(); i++)
        {
            for(int j=i+1; j<itemsets.size(); j++)
            {
                int[] X = itemsets.get(i);
                int[] Y = itemsets.get(j);

                assert (X.length==Y.length);
                
                //make a string of the first n-2 tokens of the strings
                int [] newCand = new int[currentSizeOfItemsets+1];
                for(int s=0; s<newCand.length-1; s++) {
                	newCand[s] = X[s];
                }
                    
                int ndifferent = 0;
                // then we find the missing value
                for(int s1=0; s1<Y.length; s1++)
                {
                	boolean found = false;
                	
                    for(int s2=0; s2<X.length; s2++) {
                    	if (X[s2]==Y[s1]) { 
                    		found = true;
                    		break;
                    	}
                	}
                	if (!found){ // Y[s1] is not in X
                		ndifferent++;
                		// we put the missing value at the end of newCand
                		newCand[newCand.length -1] = Y[s1];
                	}
            	
            	}
                
                // we have to find at least 1 different, otherwise it means that we have two times the same set in the existing candidates
                assert(ndifferent>0);
                
                
                if (ndifferent==1) {
                    // HashMap does not have the correct "equals" for int[] :-(
                    // I have to create the hash myself using a String :-(
                	// I use Arrays.toString to reuse equals and hashcode of String
                	Arrays.sort(newCand);
                	tempCandidates.put(Arrays.toString(newCand),newCand);
                }
            }
        }
        
        //set the new itemsets
        itemsets = new ArrayList<int[]>(tempCandidates.values());
    	

    }



    private void line2booleanArray(String line, boolean[] trans) {
	    Arrays.fill(trans, false);
	    StringTokenizer stFile = new StringTokenizer(line, " "); //read a line from the file to the tokenizer
	    //put the contents of that line into the transaction array
	    while (stFile.hasMoreTokens())
	    {
	    	
	        int parsedVal = Integer.parseInt(stFile.nextToken());
			trans[parsedVal]=true; //if it is not a 0, assign the value to true
	    }
    }

    
    private void calculateFrequentItemsets() 
    {
    	
        

        frequentCandidates = new ArrayList<int[]>(); //the frequent candidates for the current itemset

        boolean match; //whether the transaction has all the items in an itemset
        int count[] = new int[itemsets.size()]; //the number of successful matches, initialized by zeros


		// load the transaction file
		BufferedReader data_in;
		try 
		{
			data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transaFile)));
			boolean[] trans = new boolean[numItems];
			
			// for each transaction
			for (int i = 0; i < numTransactions; i++) 
			{

				
				String line = data_in.readLine();
				line2booleanArray(line, trans);

				// check each candidate
				for (int c = 0; c < itemsets.size(); c++) 
				{
					match = true; 
					int[] cand = itemsets.get(c);
					
					for (int pos_cand : cand) 
					{
						if (trans[pos_cand] == false) 
						{
							match = false;
							break;
						}
					}
					if (match) 
					{ 
						count[c]++;
						
					}
				}
			}
			data_in.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			for (int i = 0; i < itemsets.size(); i++) {
				
				if ((count[i] / (double) (numTransactions)) >= minSup) {
					foundFrequentItemSet(itemsets.get(i),count[i]);
					frequentCandidates.add(itemsets.get(i));
				}
				
			}
	        
	        itemsets = frequentCandidates;
	        
		
		
    }
}