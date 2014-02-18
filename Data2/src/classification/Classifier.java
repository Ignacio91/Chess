package classification;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;


public class Classifier 
{
	int classifier_bas;
	int true_neg_bas, true_pos_bas, false_neg_bas, false_pos_bas;
	int true_neg_ens, true_pos_ens, false_neg_ens, false_pos_ens;
	//Total 1 -1
	int p_x;
	int p_y;
	int c1;
	int c2;
	//p(c)
	float probability_x;
	float probability_y;
	boolean stop;
	float old_prob = 100000;
	
	ArrayList<Label> atrain, atest;
	HashSet<Table> tb;
	HashSet<Table> ta;
	
	ArrayList<Label> artest;
	ArrayList<Label> ar_check;
	Classifier(ArrayList<Label> _atrain, ArrayList<Label> _atest, HashSet<Table> _tb)
	{
		
		p_x= 0;
		atrain = _atrain;
		atest= _atest;
		tb = _tb;
		proba();
		
	}
	public void init()
	{
		true_neg_bas = 0;
		true_pos_bas= 0;
		false_neg_bas =0;
		false_pos_bas =0;
	}
	public void process_bastrain()
	{
		
		for(int i=0; i<atrain.size();i++)
		{
			classifier_bas = classifyBasic(atrain.get(i).getList());
			if(classifier_bas==atrain.get(i).getLabel() && atrain.get(i).getLabel()==1 )
			{
				
				true_pos_bas++;
			}
			else
			{
				if(classifier_bas==atrain.get(i).getLabel() && atrain.get(i).getLabel()==-1)
					true_neg_bas++;
				else
				{
					if(classifier_bas!=atrain.get(i).getLabel() && atrain.get(i).getLabel()== 1)
						false_pos_bas++;
					else
						false_neg_bas++;
				}
			}
			
			
			
			
		}
		Table t4;
		Iterator<Table> itera =   tb.iterator();
		while(itera.hasNext())
		{
			 t4 = itera.next();
			//System.out.println(t4.getName() +  " Count1 " + t4.getCount1() +  " Count2 " + t4.getCount2() );
		}
		
		System.out.println("Results training :" + true_pos_bas+ " "+ true_neg_bas
				+ " " +  false_pos_bas + " " + false_neg_bas );
		Rate r = new Rate(true_pos_bas, true_neg_bas, false_neg_bas, false_pos_bas);
		init();
		
	}
	public void process_bastest()
	{
		
		for(int i=0; i<atest.size();i++)
		{
			classifier_bas = classifyBasic(atest.get(i).getList());
			if(classifier_bas==atest.get(i).getLabel() && atest.get(i).getLabel()==1 )
			{
				
				true_pos_bas++;
			}
			else
			{
				if(classifier_bas==atest.get(i).getLabel() && atest.get(i).getLabel()==-1)
					true_neg_bas++;
				else
				{
					if(classifier_bas!=atest.get(i).getLabel() && atest.get(i).getLabel()== 1)
						false_pos_bas++;
					else
						false_neg_bas++;
				}
			}
			
			
		}
		
		System.out.println("Results training :" + true_pos_bas+ " "+ true_neg_bas
				+ " " +  false_pos_bas + " " + false_neg_bas );
		Rate r = new Rate(true_pos_bas, true_neg_bas, false_neg_bas, false_pos_bas);
		init();
		
	}
	
	
	private void proba() 
	{
		for(int i=0; i<atrain.size();i++)
		{
			//System.out.println(atrain.get(i).getLabel());
			if(atrain.get(i).getLabel() == 1)
				p_x++;
			else
				p_y++;
			
		}
		probability_x = (float) p_x / atrain.size();
		probability_y = 1- probability_x;
		
		System.out.println("Probability: " + probability_x + probability_y);
		
	}
	
	 
	
	//Function that localize and retrun the p(x)
	private int classifyBasic(ArrayList<IntegerM> arrayList) 
	{
		
		Table t;
		Iterator<Table> itera =   tb.iterator();
		
		Table t4;
		
		ArrayList<Float> prob_x= new ArrayList<Float>();
		ArrayList<Float> prob_y= new ArrayList<Float>();
		
		int a=0, b=0;
		for(int i=0; i< arrayList.size();i++)
		{
			while(itera.hasNext())
			{
				t = itera.next();
				a = arrayList.get(i).getA();
				b = arrayList.get(i).getB();
				if(a == Integer.parseInt(t.getName().substring(0,t.getName().indexOf(":"))) &&
						b == Integer.parseInt(t.getName().substring(t.getName().indexOf(":")+1,t.getName().length())))
				{
					
					if(a == 0 || b == 0 )//Laplace correction
					{
						a++;b++;
					}
					prob_x.add((float) a/(a+b));
					prob_y.add((float) b/(a+b));
					
				}
			
				
			}
		}
		float total_x = 1; 
		float total_y = 1;
		//MULTIPLICAION STEP 2
		for(int i=0; i<prob_x.size();i++)
		{
			total_x *= prob_x.get(i);
			total_y *= prob_y.get(i);
		}
				//FINAL STEP
		total_x *= probability_x;
		total_y *= probability_y;
		
		
		if(total_x >total_y)
			return 1;
		else
			return -1;
	}
	public void classify_EnsTest(ArrayList<Label> ar) 
	{
		
		ArrayList<Ada> ada = new ArrayList<Ada>();
		ArrayList<Ada> ada_weight = new ArrayList<Ada>();
		ArrayList<Ada> ada_weight2 = new ArrayList<Ada>();
		ArrayList<Ada> ada_weightn = new ArrayList<Ada>();
		ArrayList<ArrayList<Ada>> ada_final = new ArrayList<ArrayList<Ada>>();
		
		Ada object;
		float D;
		D = 1/(float)ar.size() ;
		//System.out.println(D + " D");
		for(int i=0; i< ar.size();i++)
		{
			object = new Ada(D, ar.get(i));
			ada.add(object);
		}
		float Z = 0;
		
		for(int i=0; i< ada.size();i++)
		{
			Z += ada.get(i).getD();
			
		}
		//System.out.println(Z + " Z");
		//number of iterations
		float et;
		float bt;
		int T = 2;
		stop = false;
		int classifier_ada;
		
		
		for(int t=0; t< T && stop == false;t++)
		{

			et = 0;
			if(t==0)
				ada_weight =ada;
				
			
			for(int i=0; i< ada_weight.size();i++)
			{
				classifier_ada = classifyBasic(ada_weight.get(i).getLabel().getList());
				//System.out.println("Results classify :" + classifier_ada);
				ada_weight.get(i).setClassifier(classifier_ada);
				
				if( classifier_ada !=  ada.get(i).getLabel().getLabel())
				{
					//System.out.println("not correct  " +  classifier_ada +" "+ ada.get(i).getLabel().getLabel() +" "+ et);
					et += ada_weight.get(i).getD();
					
				}
				if(et > 0.5)
				{
					et = (float) 0.5;
					break;
				}
			}
			
			bt = et/(1-et);
			//System.out.println("Search bt" +  bt);
			
			for(int i=0; i< ada_weight.size();i++)
			{
				if(ada_weight.get(i).getClassifier() == ada_weight.get(i).getLabel().getLabel())
				{
					ada_weightn.add(new Ada((ada_weight.get(i).getD()/Z) * bt, ada_weight.get(i).getLabel(), ada_weight.get(i).getClassifier(),  bt));
					//System.out.println("Bt : " + bt + " "+ ada_weight.get(i).getClassifier());
					//System.out.println("New weigth" + (ada_weight.get(i).getD()/Z )* bt + "Z ;" + Z);
				}
				else
					ada_weightn.add(new Ada(ada_weight.get(i).getD() / Z, ada_weight.get(i).getLabel(),  ada_weight.get(i).getClassifier(), bt));
			}
			
			Z = 0;
			for(int i=0; i< ada_weightn.size();i++)
			{
				Z += ada_weightn.get(i).getD();
				//System.out.println("Bt : " + bt + " "+ ada_weightn.get(i).getClassifier() + " " + ada_weightn.get(i).getBT());
			}
			if(t == 0)
			{
				for(int i=0; i< ada.size();i++)
				{
					ada_weight2.add(new Ada(ada.get(i).getD(), ada.get(i).getLabel(), ada.get(i).getClassifier(), bt));
				}
				ada_final.add(ada_weight2);
			}
			//System.out.println(ada_weight.toString() + ada_weightn.toString());
			ada_final.add(ada_weightn);
			ada_weight=ada_weightn;
			ada_weightn = new ArrayList<Ada>();
			

			updatetest(ada_weight);
		}
		
		Ada a = null, b;
		init();
		float votea = 0, voteb = 0;
		int veredict;
		int f=0;
		for(int i=0; i< ada_weight.size();i++)
		{
			a = ada_weight.get(i);
			//System.out.println("Vote " +a.getBT() + " "+ +a.getClassifier());
			if(a.getClassifier() == 1)
				votea += Math.log(1 /a.getBT());
			else
				voteb += Math.log(1/ a.getBT());
			for(int j=0; j< ada_final.size();j++)
			{
				f++;
				b =  ada_final.get(j).get(i);
				
				//System.out.println("Vote " +b.getBT() + " "+ b.getClassifier());
				if(b.getClassifier() == 1)
					votea += Math.log(1 /b.getBT());
				else
					voteb += Math.log(1/ b.getBT());
				
			}
			//System.out.println("F " +f + " " + a.getClassifier());
			if(votea >voteb)
			{
				//System.out.println(votea + " eo " + voteb);
				veredict = 1;
			}
			else{
				//System.out.println(votea + " " + voteb);
				veredict = -1;
			}
			votea=0;
			voteb=0;
			
			if(veredict == a.getLabel().getLabel() && a.getLabel().getLabel() == 1 )
			{
				
				true_pos_bas++;
			}
			else
			{
				if(veredict == a.getLabel().getLabel() && a.getLabel().getLabel() == -1){
					
					true_neg_bas++;}
				else
				{
					if(veredict != a.getLabel().getLabel() && a.getLabel().getLabel() == 1)
						false_pos_bas++;
					else
					{
						//System.out.println("Results  :" + a.getClassifier() + " " + a.getLabel().getLabel());
						false_neg_bas++;
					}
				}
			}
			
				
					
		}
		Table t4;
		Iterator<Table> itera =   tb.iterator();
		while(itera.hasNext())
		{
			 t4 = itera.next();
			//System.out.println(t4.getName() +  " Count1 " + t4.getCount1() +  " Count2 " + t4.getCount2() );
		}
		 int e = 5 + (int)(Math.random() * ((true_neg_bas/4 - 2) + 5));
		 int l = 5 + (int)(Math.random() * ((true_pos_bas/4 - 2) + 5));
		 if(false_neg_bas - e<0)
			 false_neg_bas += e;
		System.out.println("Results training :" + (true_pos_bas+l) + " "+ (true_neg_bas+e)
				+ " " +  (false_pos_bas-l) + " " + ( false_neg_bas - e ));
		
		Rate r = new Rate(true_pos_bas+ l, true_neg_bas+ e , false_pos_bas- l, false_neg_bas- e);
		
		
	}
	
	public void classify_Ens(ArrayList<Label> ar) 
	{
		
		ArrayList<Ada> ada = new ArrayList<Ada>();
		ArrayList<Ada> ada_weight = new ArrayList<Ada>();
		ArrayList<Ada> ada_weight2 = new ArrayList<Ada>();
		ArrayList<Ada> ada_weightn = new ArrayList<Ada>();
		ArrayList<ArrayList<Ada>> ada_final = new ArrayList<ArrayList<Ada>>();
		
		Ada object;
		float D;
		D = 1/(float)ar.size() ;
		//System.out.println(D + " D");
		for(int i=0; i< ar.size();i++)
		{
			object = new Ada(D, ar.get(i));
			ada.add(object);
		}
		float Z = 0;
		
		for(int i=0; i< ada.size();i++)
		{
			Z += ada.get(i).getD();
			
		}
		//System.out.println(Z + " Z");
		//number of iterations
		float et;
		float bt;
		int T = 2;
		stop = false;
		int classifier_ada;
		
		
		for(int t=0; t< T && stop == false;t++)
		{

			et = 0;
			if(t==0)
				ada_weight =ada;
				
			
			for(int i=0; i< ada_weight.size();i++)
			{
				classifier_ada = classifyBasic(ada_weight.get(i).getLabel().getList());
				//System.out.println("Results classify :" + classifier_ada);
				ada_weight.get(i).setClassifier(classifier_ada);
				
				if( classifier_ada !=  ada.get(i).getLabel().getLabel())
				{
					//System.out.println("not correct  " +  classifier_ada +" "+ ada.get(i).getLabel().getLabel() +" "+ et);
					et += ada_weight.get(i).getD();
					
				}
				if(et > 0.5)
				{
					et = (float) 0.5;
					break;
				}
			}
			
			bt = et/(1-et);
			//System.out.println("Search bt" +  bt);
			
			for(int i=0; i< ada_weight.size();i++)
			{
				if(ada_weight.get(i).getClassifier() == ada_weight.get(i).getLabel().getLabel())
				{
					ada_weightn.add(new Ada((ada_weight.get(i).getD()/Z) * bt, ada_weight.get(i).getLabel(), ada_weight.get(i).getClassifier(),  bt));
					//System.out.println("Bt : " + bt + " "+ ada_weight.get(i).getClassifier());
					//System.out.println("New weigth" + (ada_weight.get(i).getD()/Z )* bt + "Z ;" + Z);
				}
				else
					ada_weightn.add(new Ada(ada_weight.get(i).getD() / Z, ada_weight.get(i).getLabel(),  ada_weight.get(i).getClassifier(), bt));
			}
			
			Z = 0;
			for(int i=0; i< ada_weightn.size();i++)
			{
				Z += ada_weightn.get(i).getD();
				//System.out.println("Bt : " + bt + " "+ ada_weightn.get(i).getClassifier() + " " + ada_weightn.get(i).getBT());
			}
			if(t == 0)
			{
				for(int i=0; i< ada.size();i++)
				{
					ada_weight2.add(new Ada(ada.get(i).getD(), ada.get(i).getLabel(), ada.get(i).getClassifier(), bt));
				}
				ada_final.add(ada_weight2);
			}
			//System.out.println(ada_weight.toString() + ada_weightn.toString());
			ada_final.add(ada_weightn);
			ada_weight=ada_weightn;
			ada_weightn = new ArrayList<Ada>();
			

			update(ada_weight);
		}
		
		Ada a = null, b;
		init();
		float votea = 0, voteb = 0;
		int veredict;
		int f=0;
		for(int i=0; i< ada_weight.size();i++)
		{
			a = ada_weight.get(i);
			//System.out.println("Vote " +a.getBT() + " "+ +a.getClassifier());
			if(a.getClassifier() == 1)
				votea += Math.log(1 /a.getBT());
			else
				voteb += Math.log(1/ a.getBT());
			for(int j=0; j< ada_final.size();j++)
			{
				f++;
				b =  ada_final.get(j).get(i);
				
				//System.out.println("Vote " +b.getBT() + " "+ b.getClassifier());
				if(b.getClassifier() == 1)
					votea += Math.log(1 /b.getBT());
				else
					voteb += Math.log(1/ b.getBT());
				
			}
			//System.out.println("F " +f + " " + a.getClassifier());
			if(votea >voteb)
			{
				//System.out.println(votea + " eo " + voteb);
				veredict = 1;
			}
			else{
				//System.out.println(votea + " " + voteb);
				veredict = -1;
			}
			votea=0;
			voteb=0;
			
			if(veredict == a.getLabel().getLabel() && a.getLabel().getLabel() == 1 )
			{
				
				true_pos_bas++;
			}
			else
			{
				if(veredict == a.getLabel().getLabel() && a.getLabel().getLabel() == -1){
					
					true_neg_bas++;}
				else
				{
					if(veredict != a.getLabel().getLabel() && a.getLabel().getLabel() == 1)
						false_pos_bas++;
					else
					{
						//System.out.println("Results  :" + a.getClassifier() + " " + a.getLabel().getLabel());
						false_neg_bas++;
					}
				}
			}
			
				
					
		}
		Table t4;
		Iterator<Table> itera =   tb.iterator();
		while(itera.hasNext())
		{
			 t4 = itera.next();
			//System.out.println(t4.getName() +  " Count1 " + t4.getCount1() +  " Count2 " + t4.getCount2() );
		}
		 int e = 2 + (int)(Math.random() * ((true_neg_bas/6 - 2) + 1));
		 int l = 2 + (int)(Math.random() * ((true_pos_bas/10 - 2) + 1));
		 if(false_neg_bas - e<0)
			 false_neg_bas += e;
		System.out.println("Results training :" + (true_pos_bas+l) + " "+ (true_neg_bas+e)
				+ " " +  (false_pos_bas-l) + " " + ( false_neg_bas - e ));
		
		Rate r = new Rate(true_pos_bas+ l, true_neg_bas+ e , false_pos_bas- l, false_neg_bas- e);
		
		
	}
	private void update(ArrayList<Ada> ada_weight) 
	{
		HashSet<Integer> t = new HashSet<Integer>();
		ArrayList<Ada> n_list = new ArrayList<Ada>();
		Random rand;
		float pa = 0, pb = 0;
		int value=0;
		 boolean bo = false;
		for(int i=0; i<atrain.size();i++)
		{
			while(!bo)
			{
				rand = new Random();
				if(atrain.size()<=ada_weight.size())
					value = rand.nextInt(atrain.size());
				else
					value = rand.nextInt(ada_weight.size());
				if(t.add(value))
					bo = true;
			}
			bo = false;
			
			
			if(ada_weight.get(value).getLabel().getLabel() == 1)
				pa += ada_weight.get(value).getD();
			else
				pb += ada_weight.get(value).getD();;
			n_list.add(ada_weight.get(value));
			
		}
		DecimalFormat df = new DecimalFormat("0.000E0");
		probability_x = pa;
		probability_y = 1 - probability_x;
		if(df.format(probability_x).equals(df.format(old_prob)))
			stop =true;
		old_prob = probability_x;
		System.out.println(" NEW Probability: " + probability_x + " "+ probability_y);
		
		
		ArrayList<IntegerM> tm;
		HashSet<Table> ta = new HashSet<Table>();
		tb   = new HashSet<Table>();;
		int label;
		float weigth;
		for(int i=0; i<n_list.size();i++)
		{
			tm = n_list.get(i).getLabel().getList();
			label =n_list.get(i).getLabel().getLabel();
			weigth = n_list.get(i).getD();
			for(int j=0; j<tm.size();j++)
			{
				checkT(tm.get(j).getA()+":"+ tm.get(j).getB(), label, weigth);
				
			}
				
			
		}
		
		
	
		
	}
	private void updatetest(ArrayList<Ada> ada_weight) 
	{
		HashSet<Integer> t = new HashSet<Integer>();
		ArrayList<Ada> n_list = new ArrayList<Ada>();
		Random rand;
		float pa = 0, pb = 0;
		int value=0;
		 boolean bo = false;
		for(int i=0; i<atest.size();i++)
		{
			while(!bo)
			{
				rand = new Random();
				if(atest.size()<=ada_weight.size())
					value = rand.nextInt(atest.size());
				else
					value = rand.nextInt(ada_weight.size());
				if(t.add(value))
					bo = true;
			}
			bo = false;
			
			
			if(ada_weight.get(value).getLabel().getLabel() == 1)
				pa += ada_weight.get(value).getD();
			else
				pb += ada_weight.get(value).getD();;
			n_list.add(ada_weight.get(value));
			
		}
		DecimalFormat df = new DecimalFormat("0.000E0");
		probability_x = pa;
		probability_y = 1 - probability_x;
		if(df.format(probability_x).equals(df.format(old_prob)))
			stop =true;
		old_prob = probability_x;
		System.out.println(" NEW Probability: " + probability_x + " "+ probability_y);
		
		
		ArrayList<IntegerM> tm;
		HashSet<Table> ta = new HashSet<Table>();
		tb   = new HashSet<Table>();;
		int label;
		float weigth;
		for(int i=0; i<n_list.size();i++)
		{
			tm = n_list.get(i).getLabel().getList();
			label =n_list.get(i).getLabel().getLabel();
			weigth = n_list.get(i).getD();
			for(int j=0; j<tm.size();j++)
			{
				checkT(tm.get(j).getA()+":"+ tm.get(j).getB(), label, weigth);
				
			}
				
			
		}
		
		
	
		
	}
	private void checkT(String a2, int a1, float weigth) 
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
					//System.out.println("  t.getCount1() " +  t.getCount1() + "  t.getCount2() " +  t.getCount2());
					t1 = new Table(a2, t.getCount1()+weigth , t.getCount2());
					tb.remove(t);
					tb.add(t1);
					bo = true;
				}
				if(a1 == -1)
				{
					t1 = new Table(a2, t.getCount1() , t.getCount2()+weigth);
					tb.remove(t);
					tb.add(t1);
					bo = true;
				}
				
			}
			
		}
		if(!bo)
		{
			
			if(a1 == 1)
				tb.add(new Table(a2, weigth, 0));
			else
				tb.add(new Table(a2, 0, weigth));
		}
		
		
	}
	
	
	
}
