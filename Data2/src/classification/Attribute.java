package classification;

public class Attribute {

	int value;
	int index;
	Attribute(int _index)
	{
		index = _index;
		//value = _value;
	}
	
	public int getIndex(){
		return index;
	}
	public int getValue(){
		return value;
	}

}



