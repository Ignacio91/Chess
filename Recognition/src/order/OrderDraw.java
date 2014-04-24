package order;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Point.Point;

public class OrderDraw  extends Order
{
	ArrayList<Point> p;
	JSONObject po ;
	String form;
	JSONObject obj ;
	double area_hull;
	
	
	public OrderDraw(String _form, ArrayList<Point> _p)
	{
		init(_form, _p);
		area_hull = 0;
	}

	public OrderDraw(String _form, ArrayList<Point> _p, double _area_hull)
	{
		init(_form, _p);
		
		area_hull = _area_hull;
		System.out.println("El area es" +area_hull);
	}
	private void init(String _form, ArrayList<Point> _p) 
	{
		p = _p;
		form = _form;
		po = new JSONObject();
		obj = new JSONObject();
	}
	
	
	
	public String getOrder(String _form, ArrayList<Point> _p)
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
			if(area_hull!= 0)
			{
				System.out.println(area_hull);
				obj.put("AREA" , area_hull);
			}
			
			for(int i=0; i<p.size(); i++)
			{
				po.put("X", p.get(i).getX());
				po.put("Y", p.get(i).getY());
				
				obj.put("Point" + i, po);
				System.out.println(po);
				po = new JSONObject();
				
			}
			
			System.out.println(obj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public double getArea()
	{
		return area_hull;
	}
	public String getForm() {
		// TODO Auto-generated method stub
		return form;
	}
	public ArrayList<Point> getRectangle() {
		// TODO Auto-generated method stub
		return p;
	}
}
