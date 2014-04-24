package order;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderSimple extends Order
{
	
	public OrderSimple(String _name)
	{
		name = _name;
	}
	
	public String getOrder()
	{
		
		return obj.toString();
		
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public JSONObject getOrderJson(){
		return obj;
	}
	@SuppressWarnings("unchecked")
	public void parseToJson()
	{
		try 
		{
			obj.put("ORDER", "SIMPLE");
			obj.put("ACTION", name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
