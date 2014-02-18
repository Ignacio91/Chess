import java.util.ArrayList;


public class ClassPurity 
{
	float support;
	ArrayList<Float> countsupport= new ArrayList<Float>();
	String pattern;
	float purity;
	float dt;
	float dtt;
	float max;
	ClassPurity(float _support, ArrayList<Float> count_support, String _pattern, int numlines, int sizetitle)
	{
		support = _support;
		countsupport.addAll(count_support);
		pattern =_pattern;
		dt= numlines;
		dtt= sizetitle;
	}
	
	public float purity()
	{
		float i0=countsupport.get(0);
		float i1=countsupport.get(1);
		float i2=countsupport.get(2);
		float i3=countsupport.get(3);

		float max1= Math.max(i0,i1);
		float max2= Math.max(i2,i3);
		max= Math.max(max1,max2);
		purity =(float) (Math.log10((support/dt))-Math.log10((support+max)/dtt));
		return purity;
	}
}
