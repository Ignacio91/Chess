package classify;



import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import order.Order;
import order.OrderDraw;
import order.OrderSimple;
import order.OrderCircle;


import Point.GeomPoint;
import Point.Point;




public class Classify 
{
	ArrayList<Point> point;
	ConvexHull convex_hull;
	ArrayList<Point> points_hull;
	Order order;
	
	int [] convex_triangle; 
	double [] convex_rectangle;
	
	float regression;
	
	
	
	double ratio_circle;
	//debugging
	boolean debug = false;
	
	double area_hull, perimeter_hull;
	double perim_area_ratio_hull;
	double area_ratio_hull, perim_ratio_hull;
	//Ratios to determine if it is a triangle
	double area_triangle_hull ;
	double perim_triangle_hull;
	
	double triangel_area_ratio_hull,  triangel_perim_ratio_hull;
	//Parameters from the bounding rectangle
	double[] ratio_rectangle_bounding;//0 = width, 1 = height, 2= width/height, 3= width*height
	
	double area_bounding_rectangle;
	double ration_bounding_hull;
	
	double  area_quadrilateral_hull;
	double  perimeter_quadrilateral_hull;
	
	boolean circle;
	boolean recta;
	
	ArrayList<Point> triangel_points ;
	ArrayList<Point> rectangle_boundary;
	ArrayList<Point> rectangle_quadrilateal;
	
	Point circle_center;
	float radius_circle;
	
	double ratio_height_and_width_rectangle;
	
	//corners calibration
	Point[] corners = new Point[4];
	// 
	int boundry_width = 150, boundry_length =150;
	
	Point point_min;
	Point point_max;
	
	public Classify(ArrayList<Point> _point, Point _point_min, Point _point_max)
	{
		point = _point;
		convex_hull = new ConvexHull();
		//remember corners for the points do not fortget!!!!
		corners[0]= new Point(_point_min.x,_point_min.y); corners[2]= new Point(_point_min.x,_point_max.y);
		corners[1]= new Point(_point_max.x,_point_min.y); corners[3]= new Point(_point_max.x,_point_max.y);
		 System.out.println(corners[0]);
		 System.out.println(corners[1]);
		 System.out.println(corners[2]);
		 System.out.println(corners[3]);
		rectangle_boundary = new ArrayList<Point>();
		triangel_points = new ArrayList<Point>();
		
		
		
	}
	
	public Order start()
	{
		Order or; 
		
		if(checkSimple())
		{
			 or = simpleOrder(point.get(0).x, point.get(0).y);
		}
		else// Form recognition
		{

			or = checkDraw();
		}
		
		System.out.println(or.getOrder());
		return or;
		}

	private Order checkDraw() 
	{
			OrderDraw or ;
			getRegression();
	
		if(point.size()>3)
		{
			//creates Hull area from cloud of points
			points_hull = convex_hull.execute(point);
			//Calculate The Hull parameters
			initializeHullParameters();
				
			//debugging function	
			debugArrayandConvexHull(points_hull, area_hull, perimeter_hull);
			
			//getLargestRectangle
			//System.out.println("MAX Rectangle");
			//getMaxRectangle();
				
			//get the max Triangle that fits in the convex Hull Area	
			convex_triangle = convex_hull.getMaxTriangle(points_hull);
			initializeTriangle(convex_triangle);
			
			//getMaxRectangle();
			
			convex_rectangle = convex_hull.getRectangle(points_hull);
			
			//Rectangle Ratios
			List<Point> np = new ArrayList<Point>(points_hull);
			Point2D.Double[]  rectangle_bounding= RotatingCalipers.getMinimumBoundingRectangle(np) ;
			initializeRectangle(rectangle_bounding);// initialize all the ratios for the rectangle
	
			ration_bounding_hull  = area_bounding_rectangle/area_hull;
			System.out.println("Rectangle bounding area :" + area_bounding_rectangle );
			System.out.println("Hull area :" + area_hull );
			System.out.println("Bounding area :" + ration_bounding_hull );  
			
			System.out.println("Triangle_RATIO :" + triangel_area_ratio_hull ); 
			System.out.println("triangel_area_ratio_hull :" + triangel_perim_ratio_hull);
			System.out.println();
			//double [] s3 = convex_hull.getRatio(rectangle_bounding);	
			 circle =analyzeCircle();	
		}
		else
		{
				recta = true;
				circle = false;
		}
		
		if(circle)
			{
			
				//OrderCircle circle = new OrderCircle("CIRCLE", getCircleCenter() ,getCircleRadius());
				//System.out.println("Hull area2 :" + area_hull );
				or = new OrderDraw("CIRCLE", points_hull, area_hull);
				
				System.out.println("circulo");
			}
			else
			{
				
			
				if( ratio_height_and_width_rectangle <= 0.3  || recta)
				{
					if(regression > 0.5 || regression <-0.5)
					{
						or = new OrderDraw("LINE",point);
						System.out.println("Recta");
					}
					else
					{		
						or = new OrderDraw("STRANGELINE", point);
						System.out.println("Linea extrana");
					}
				}
				else
				{
					
					if(ratio_height_and_width_rectangle>0.3 && ratio_height_and_width_rectangle<=1 && perim_area_ratio_hull>12 && perim_area_ratio_hull<25 
							&& ration_bounding_hull >= 0.6 && ration_bounding_hull <= 1.4)
					{
						or = new OrderDraw("RECTANGLE", rectangle_boundary);
						System.out.println("Rectangulo");
					}
						//send
					else
					{
						if(triangel_area_ratio_hull>0.9 && triangel_area_ratio_hull<1.25 && triangel_perim_ratio_hull>0.9 && triangel_perim_ratio_hull<1.1)
						{
							or = new OrderDraw("TRIANGLE", triangel_points);
							System.out.println("Triangulo");
						}
						else{
							
								or = new OrderDraw("UNDEFINED", point);
								System.out.println("nada: " + ratio_height_and_width_rectangle +" hull " +perim_area_ratio_hull
										+ " bounding"+ration_bounding_hull);
							}
						
					}
						
				}
			
			}
			return or;
	}

	


	private void getMaxRectangle() 
	{
		MaxRectangle mr = new MaxRectangle();
		for(int i = 0; i<points_hull.size();i++){
			Point p = points_hull.get(i);
			mr.addPointToHull(new GeomPoint(p.x,p.y));
		}
		for(int i = 0; i<mr.size();i++)
		{
			System.out.println("Point init:" + mr.get(i) );
		}
		Vector<?> p = mr.computeLargestRectangle();
		for(int i = 0; i<p.size();i++){
			System.out.println("Point :" + p.get(i) );
		}
	}

	private boolean analyzeCircle() {
		
		ratio_circle = Math.pow(convex_hull.getPerimeter(points_hull), 2)/convex_hull.getArea(points_hull);
		System.out.println(" el ratio circle es" + ratio_circle);
		
		if(ratio_circle > 12 && ratio_circle <= 14.5)
			return true;
		else return false;
	}

	private void initializeHullParameters() 
	{
		area_hull = convex_hull.getArea(points_hull);
		perimeter_hull = convex_hull.getPerimeter(points_hull);
		perim_area_ratio_hull= Math.pow(perimeter_hull, 2)/area_hull;
	}

	private void initializeRectangle(Point2D.Double[] rectangle_bounding) 
	{
		
		for(int i=0;i< rectangle_bounding.length;i++)
		{
			System.out.println(rectangle_bounding[i].toString());
			rectangle_boundary.add(new Point(Math.round( (float)rectangle_bounding[i].x), Math.round( (float)rectangle_bounding[i].y)));
		}
		
		ratio_rectangle_bounding = convex_hull.getRatio(rectangle_bounding);
		ratio_height_and_width_rectangle = ratio_rectangle_bounding[2];
		area_bounding_rectangle = ratio_rectangle_bounding[3];
		
		//if(debug)
			System.out.println("rectangle_boundary size is" +rectangle_boundary.size() + " eo +"+ rectangle_boundary.get(0).getX());
		
		//System.out.println("Rectangle is " +" "+ points.get(s[9]) +" "+ points.get(s[10]) +" " + points.get(s[11]) + " " + points.get(s[12]) );
		//System.out.println("Rectangle is "+" p1: ("+ rectangle_bounding[1] +","+ rectangle_bounding[2]  +" p2: ("+ rectangle_bounding[3] +","+ rectangle_bounding[4] +") p2: (" + rectangle_bounding[5] +"," + rectangle_bounding[6]+ ") p3: ("+" p4: ("+ rectangle_bounding[7] +","+ rectangle_bounding[7]  +") and the area is = "+ rectangle_bounding[0]);
		//System.out.println("Rectangle is "+" p1: ("+ points_hull.get((int) rectangle_bounding[9]) +","+ points_hull.get((int) s2[10])  +" p2: ("+points_hull.get((int) s2[11]) +","+ points_hull.get((int) s2[12]) );
	}

	private void initializeTriangle(int[] s) 
	{
		triangel_points = new ArrayList<Point>();
		Point coord1= points_hull.get(s[0]);
		Point coord2= points_hull.get(s[1]);
		Point coord3= points_hull.get(s[2]);
		//add the point to the array triangle
		triangel_points.add(coord1);triangel_points.add(coord2);triangel_points.add(coord3);
		
		//calculate the area and perimeter from the triangle
		area_triangle_hull = convex_hull.area(coord1, coord2, coord3);
		perim_triangle_hull = convex_hull.getPerimeter(triangel_points);
		
		//compare it to the hull area
		triangel_area_ratio_hull = area_hull / area_triangle_hull;
		triangel_perim_ratio_hull = perimeter_hull / perim_triangle_hull;
		
		if(debug)
			System.out.println("Triangle is " +" "+ s[0] +" "+ s[1] +" " + s[2] + "and the area is = "+ area_triangle_hull);
	}

	private void getRegression() 
	{
		Regression rg_obj = new Regression(point);
		rg_obj.calculate();
		regression= rg_obj.getRcuad();
		if(debug)
			System.out.println("r : " + regression);
	}

	private void debugArrayandConvexHull(ArrayList<Point> points,
			double AreaHull, double PerimeterHull) 
	{
		if(debug)				
		{
			for(int i = 0; i<point.size(); i++)
			{
				System.out.println(point.get(i));
			}
			System.out.println("Area: " + AreaHull  + "Perimeter: "  + PerimeterHull);
			for(int i = 0; i<points.size(); i++)
			{
				System.out.println(points.get(i));
			}
		}
	}
		

	private boolean checkSimple() 
	{
		if(checkRange(point.get(0)) && checkRange(point.get(point.size()/2)) && checkRange(point.get(point.size()-1) ))
			return true;
		else
			return false;
	}

	private boolean checkRange(Point point2) {
		if(	   (point2.getX()<corners[0].x +boundry_width && point2.getY()< corners[0].y + boundry_length) 
			|| (point2.getX()>corners[1].x -boundry_width && point2.getY()< corners[1].y + boundry_length) 
			|| (point2.getX()<corners[2].x +boundry_width && point2.getY()> corners[2].y - boundry_length) 
			|| (point2.getX()>corners[3].x -boundry_width && point2.getY()> corners[3].y - boundry_length))
			return true;
		else return false;
	}

	private OrderSimple simpleOrder(int pointX, int pointY) {
		OrderSimple or;
		if(pointX <corners[0].x +boundry_width && pointY<corners[0].y + boundry_length)
		{
			
			or = new OrderSimple("ESCAPE");
			System.out.println("ESCAPE");
		}
		else
		{
			if(pointX >corners[1].x - boundry_width && pointY<corners[1].y + boundry_length)
			{	
				or = new OrderSimple("DELETE");
				System.out.println("DELETE");
			}
			else
			{
				if(pointX <corners[2].x + boundry_width && pointY>corners[2].y - boundry_length)
				{	
					or = new OrderSimple("MOVE");
					System.out.println("MOVE");
				}
				
				else
				{
					or = new OrderSimple("RETURN");
					System.out.println("RETURN");
				}
					
			}
		}
		return or;
	}
	public static boolean check(double a , double b)
	{
		int c = (int) a;
		int d = (int) b;
		if(c == d)
			return true;
			else{
				if(c == d+2500 || c == d-2500)
					return true;
				else return false;
			}
		
	}
	public static ArrayList<Point> clean(ArrayList<Point> point2)
	{
		ArrayList<Point> point_clean = new ArrayList<Point>();
		
		return point_clean;
		
	}
}
