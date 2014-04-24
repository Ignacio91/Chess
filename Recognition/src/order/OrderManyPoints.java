package order;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Point.Point;



public class OrderManyPoints extends Order
{
	ArrayList<Point> p;
	JSONObject po;
	
	public OrderManyPoints(ArrayList<Point> _p)
	{
		p = _p;
	}
	
	

	@SuppressWarnings("unchecked")
	public String getOrder()
	{
		try 
		{
			obj.put("Order", "MANYPOINTS");
			for(int i=0; i<p.size(); i++)
			{
				po.put("X", p.get(i).getX());
				po.put("Y", p.get(i).getY());
				obj.put("Point" + i, po);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
		
			
		return obj.toString();
	}

}
