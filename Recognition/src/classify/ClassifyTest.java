package classify;


import java.util.ArrayList;

import junit.framework.TestCase;

import order.*;
import Point.Point;

import org.json.simple.*;
import org.junit.Test;


public class ClassifyTest extends TestCase {
	@Test
	public void testStart() 
	{
		//TestDraw : 1 = Rectangle,  2 = Triangle , 3 = Line, 4 = Circle
		//TestSimple : 1 = Escape, 2  = Delete , 3 =Move , 4 =Pass
		int option_draw = 1;
		int option_simple =1;
		
		ArrayList<Point> np = getObjects(option_draw);
		ArrayList<Point> np1 = getObjectsSimple(option_simple);
		
		Classify cs = new Classify(np, new Point(0,0), new Point(885, 1920));
		Classify cs1 = new Classify(np1, new Point(0,0), new Point(885, 1920));
		OrderDraw o = (OrderDraw) cs.start();
		OrderSimple os = (OrderSimple) cs1.start();
		JSONObject ob;
		/*try {
			ob = new JSONObject(o.getOrder());
			if(!ob.get("FORM").equals("RECTANGLE"))
				fail("Not yet implemented");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		switch(option_draw)
		{
			case 1: checkResult(o, "RECTANGLE"); break;
			case 2:	checkResult(o, "TRIANGLE"); break;
			case 3:	checkResult(o, "LINE"); break;
			default:
		}
		switch(option_simple)
		{
			case 1: checkResultS(os, "ESCAPE"); break;
			case 2:	checkResultS(os, "DELETE"); break;
			case 3:	checkResultS(os, "MOVE"); break;
			case 4:	checkResultS(os, "PASS"); break;
			default:
		}
		
		
	}

	private void checkResultS(OrderSimple os, String string) {
		if(!os.getName().equals(string))
			fail("Not yet implemented");
		
	}

	private ArrayList<Point> getObjectsSimple(int option_simple) 
	{
		ArrayList<Point> np = new ArrayList<Point>() ;
		
		switch(option_simple)
		{
			case 1: np = getEscape(); break;
			case 2:	np = getDelete(); break;
			case 3:	np = getMove(); break;
			case 4:	np = getPass(); break; 

			default: break;
		}
		return np;
	}

	private ArrayList<Point> getPass() 
	{
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(200, 200));np.add(new Point(204, 203));np.add(new Point(208, 215));
		np.add(new Point(203, 235));np.add(new Point(201, 250));np.add(new Point(198, 270));
		np.add(new Point(215, 283));np.add(new Point(223, 279));
		return np;
	}

	private ArrayList<Point> getDelete() 
	{
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(881, 45));np.add(new Point(792, 14));np.add(new Point(789, 12));
		np.add(new Point(881, 111));np.add(new Point(805, 62));np.add(new Point(883, 21));
		np.add(new Point(841, 121));np.add(new Point(823, 89));
		return np;
	}

	private ArrayList<Point> getMove()
	{
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(21, 1921));np.add(new Point(89, 1921));np.add(new Point(23, 1915));
		np.add(new Point(31, 1888));np.add(new Point(61, 1901));np.add(new Point(78, 1887));
		np.add(new Point(56, 1923));np.add(new Point(68, 1911));
		return np;
	}

	private ArrayList<Point> getEscape()
	{
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(0, 23));np.add(new Point(23, 54));np.add(new Point(23, 45));
		np.add(new Point(72, 78));np.add(new Point(13, 85));np.add(new Point(1, 111));
		np.add(new Point(31, 12));np.add(new Point(1, 44));
		return np;
	}

	private void checkResult(OrderDraw o, String order) 
	{
		
		if(!o.getForm().equals(order))
			fail("Not yet implemented");
	}

	private ArrayList<Point> getObjects(int option) 
	{
		ArrayList<Point> np = new ArrayList<Point>() ;
		switch(option)
		{
			case 1: np = getRectangle(); break;
			case 2:	np = getTriangle(); break;
			case 3:	np = getLine(); break;
			case 4:	np = getCircle(); break; 
			case 5:	//np = getRectangle(); break;
			default: break;
		}
		return np;
	}

	private ArrayList<Point> getRectangle() {
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(200, 200));np.add(new Point(204, 203));np.add(new Point(208, 215));
		np.add(new Point(203, 235));np.add(new Point(201, 250));np.add(new Point(198, 270));
		np.add(new Point(215, 283));np.add(new Point(223, 279));np.add(new Point(235, 285));
		np.add(new Point(255, 281));np.add(new Point(269, 280));np.add(new Point(290, 279));
		np.add(new Point(288, 272));np.add(new Point(281, 256));np.add(new Point(283, 246));
		np.add(new Point(289, 231));np.add(new Point(285, 227));np.add(new Point(280, 210));
		np.add(new Point(283, 231));np.add(new Point(285, 227));np.add(new Point(280, 210));
		np.add(new Point(283, 202));np.add(new Point(275, 198));np.add(new Point(266, 201));
		np.add(new Point(254, 202));np.add(new Point(231, 203));np.add(new Point(221, 201));
		np.add(new Point(211, 202));np.add(new Point(202, 197));
		
		/*np.add(new Point(200, 400));np.add(new Point(800, 400));np.add(new Point(800, 600));
		np.add(new Point(800, 800));np.add(new Point(200, 800));np.add(new Point(200, 600));*/
		return np;
	}

	private ArrayList<Point> getCircle() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Point> getLine() {
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(280, 272));np.add(new Point(275, 268));np.add(new Point(256, 259));
		np.add(new Point(241, 247));np.add(new Point(223, 235));np.add(new Point(218, 221));
		np.add(new Point(205, 207));
		return np;
	}

	private ArrayList<Point> getTriangle() {
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(200, 200));np.add(new Point(204, 203));np.add(new Point(208, 215));
		np.add(new Point(203, 235));np.add(new Point(201, 250));np.add(new Point(198, 270));
		np.add(new Point(215, 283));np.add(new Point(223, 279));np.add(new Point(235, 285));
		np.add(new Point(255, 281));np.add(new Point(269, 280));np.add(new Point(290, 279));
		np.add(new Point(280, 272));np.add(new Point(275, 268));np.add(new Point(256, 259));
		np.add(new Point(241, 247));np.add(new Point(223, 235));np.add(new Point(218, 221));
		np.add(new Point(205, 207));
		
		return np;
	}
	private ArrayList<Point> getCircle() 
	{
		ArrayList<Point> np  =  new ArrayList<Point>() ;
		np.add(new Point(200, 200));np.add(new Point(225, 222));np.add(new Point(250, 250));
		np.add(new Point(269, 274));np.add(new Point(300, 300));np.add(new Point(275, 323));
		np.add(new Point(250, 350));np.add(new Point(225, 375));np.add(new Point(200, 400));
		np.add(new Point(175, 375));np.add(new Point(150, 350));np.add(new Point(125, 325));
		np.add(new Point(100, 300));np.add(new Point(125, 275));np.add(new Point(150, 250));
		np.add(new Point(175, 225));
		
		return np;
	}

}
