
public class Word {
	int count;
	String name;
	Word(int _count, String _name)
	{
		name = _name;
		count = _count;
	}
	public void add()
	{
		count++;
	}
	public String getName(){
		return name;
	}
	public int getCount(){
		return count;
	}

}
