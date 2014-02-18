package classification;

public class Rate 
{
	int b1, b2,  m1,  m2;
	int n;
	float acc, error, sensivity, specifity, precision, recall, f1, fa, fb;
	Rate(int _b1, int _b2, int _m1, int _m2)
	{
		b1= _b1;
		b2= _b2;
		m1= _m1;
		m2= _m2;
		process();
	}
	public void process()
	{
		n = b1 + b2 + m1 + m2;
		acc = (float) (b1+b2)/(float)n;
		error = 1- acc;
		sensivity =  (float)b1/(float)(b1+m1);
		specifity = (float)b2/ (float)(b2+m2);
		precision = (float)b1/(float)(b1+b2);
		recall = (float)b2/(float)(b1+b2);
		f1 = (2 * precision * recall)/ (precision + recall);
		fa = ((float)1.25 * precision * recall) / ((float)0.25*(precision + recall));
		fb = (5 * precision * recall)/ (4*(precision + recall));
		System.out.println(" acc: " + acc + " error: " + error + " sensivity: " + sensivity +
				" specifity: " + specifity + " precision: " + precision +
				" recall: " + recall + " f1: " + f1 + " fa: " + fa +
				" fb: " + fb );
		
	}
}
