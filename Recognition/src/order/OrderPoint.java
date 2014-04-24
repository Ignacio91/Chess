package order;

import org.json.JSONException;
import org.json.JSONObject;

import Point.Point;

public class OrderPoint extends Order
{
	Point p;
	JSONObject po;
	public OrderPoint(Point _p)
	{
		p = _p;
	}
	@SuppressWarnings("unchecked")
	public String getOrder()
	{
		try {
			obj.put("Order", "POINT");
			po.put("X", p.getX());
			po.put("Y", p.getY());
			obj.put("Point", po);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return obj.toString();
	}
}
