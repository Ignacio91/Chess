package order;

import org.json.JSONException;
import org.json.JSONObject;

import Point.Point;

public class OrderCircle 
{
	
	JSONObject po ;
	String form;
	JSONObject obj ;
	
	Point center;
	float radius;
	
	public OrderCircle(String _form, Point _center, float _radius)
	{
		center = _center;
		radius = _radius;
		po = new JSONObject();
		obj = new JSONObject();
	}
	public String getOrder()
	{
	
		return obj.toString();
	}
	public JSONObject getOrderJson()
	{
		return obj;
	}

	public void parseToJson() 
	{
		try 
		{
			System.out.println("que haces");
			obj.put("ORDER", "DRAW");
			obj.put("FORM", form);
			
			obj.put("X", center.x);
			obj.put("Y", center.y);
			obj.put("RADIUS", radius);
			
			obj.put("CIRCLE", po);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getForm() {
		// TODO Auto-generated method stub
		return form;
	}

}
