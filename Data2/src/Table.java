
public class Table 
{
	String name;
	float count1, count2;
	Table(String _name)
	{
		name = _name;
		count1 =0;
		count2 =0;
	}
	Table(String _name, float _count1, float _count2)
	{
		name = _name;
		count1 = _count1;
		count2 = _count2;
	}
	public float getCount1(){
		return count1;
	}
	public float getCount2(){
		return count2;
	}
	
	public String getName(){
		return name;
	}
}
