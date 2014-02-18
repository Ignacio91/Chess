
public class Vocab 
{
	int count;
	String name;
	Vocab(int _count, String _name)
	{
		name = _name;
		count = _count;
	}
	
	public String getName(){
		return name;
	}
	public int getCount(){
		return count;
	}
}
