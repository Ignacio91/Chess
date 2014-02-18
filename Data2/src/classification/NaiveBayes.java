package classification;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NaiveBayes {

	public static void main(String[] args) 
	{
		long startTime = System.currentTimeMillis();
		System.out.println("Start of DATAMINING ASSIGNEMENT");
		System.out.println("FINDING UNIQUE WORDS AND CREATING TITLE");
		ArrayList<Label> train = new ArrayList<Label>();
		ArrayList<Label> test = new ArrayList<Label>();
		//ArrayList<Label> test = new ArrayList<Label>();
		Classifier tr = null, te = null;
		HashSet<Table> tb;
		
		Parse p = new Parse();
		
		
		
	
		
		if(args.length != 0)
		{
			train = p.processtrain(args[0]);
			test = p.processtest(args[1]);}
		else
			{
			train = p.processtrain("a1a.test");
			test = p.processtest("a1a.train");
			}
		
		tb = p.getT();
		//Iterations adjusted for adaboost
		tr = new Classifier(train, test  , tb);
		
		tr.process_bastrain();
		tr.process_bastest();
		tr.classify_Ens(train);
		tr.classify_EnsTest(test);
		
	    
		
	    long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println( "The execution Time was  " +elapsedTime);
	    
		
		
		 

	}

}
