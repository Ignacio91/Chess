import java.util.ArrayList;


public class Label
{
	int label;
	ArrayList<IntegerM> list;
	
	Label(int _label, ArrayList<IntegerM> _list)
	{
		label = _label;
		list = _list;
	}
	
	public int getLabel(){
		return label;
	}
	public ArrayList<IntegerM> getList(){
		return list;
	}

}
