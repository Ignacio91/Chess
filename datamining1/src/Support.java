
public class Support {
	int[] count;
	int support;
	Support(int[] _count, int _support)
	{
		support = _support;
		count = _count;
	}
	
	public int[] getSec(){
		return count;
	}
	public int getCount()
	{
		return support;
	}

	
}
