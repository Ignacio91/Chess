import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class Main {

	
	
	
	
	public static void main(String[] args) 
	{
		
	    
		
		ArrayList<Point> point1 = new ArrayList();
		point1.add(new Point(1,1));
		point1.add(new Point(2,2));
		point1.add(new Point(3,3));
		point1.add(new Point(4,4));
		point1.add(new Point(5,5));
		
		point1.add(new Point(8,8));
		point1.add(new Point(21,26));
		
		Regression rg = new Regression(point1);
		rg.calculate();
		float rCuad = rg.getRcuad();
		System.out.println("Rcuad: " + rCuad);
		System.out.println("Hola");
		dbscan db = new dbscan(new Vector<Point>(point1));
		System.out.println(point1);
		System.out.println("Que haces");
		Vector<List<Point>>  pointlist= dbscan.applyDbscan();
		System.out.println("SE QUEDO");
		System.out.println(pointlist.toString());
		for(int i=0;i< pointlist.size();i++)
		{
			List<Point> p = pointlist.get(i);
			for(int j=0;j< p.size();j++)
			{
				System.out.println(i + "  Punto: " + p.get(j));
			}
		}
		
		/*ConvexHull c = new ConvexHull();
		
		//point1.add(new Point(4,1));
		//point1.add(new Point(3,0));
		//point1.add(new Point(5,1));
		//point1.add(new Point(1,2));
		//point1.add(new Point(2,1));
		
		Regression rg = new Regression(point1);
		rg.calculate();
		float rCuad = rg.getRcuad();
		
		for(int i = 0; i<point1.size(); i++)
		{
			System.out.println(point1.get(i));
		}
		System.out.println("Nuevo");
		if(rCuad <0.3 && rCuad >-0.3)
		{
			System.out.println("Delete");
		}
		else
		{
			if(point1.size()<4){
				
			}
			ArrayList<Point> points = c.execute(point1);
			 double AreaHull = c.getArea(points);
			 double PerimeterHull = c.getPerimeter(points);
			System.out.println("Area: " + c.getArea(points)  + "Perimeter: "  + c.getPerimeter(points));
			
			for(int i = 0; i<points.size(); i++)
			{
				System.out.println(points.get(i));
			}
			List<Point> np = new ArrayList<Point>(points);
			int [] s = c.getMaxTriangle(points);
			ArrayList<Point> triang = new ArrayList<Point>();
			triang.add(points.get(s[0]));triang.add(points.get(s[1]));triang.add(points.get(s[2]));
			double AreaTriang = c.area(points.get(s[0]), points.get(s[1]), points.get(s[2]));
			double PerimeterTrian = c.getPerimeter(triang);
			
			System.out.println("Triangle is " +" "+ s[0] +" "+ s[1] +" " + s[2] + "and the area is = "+ c.area(points.get(s[0]), points.get(s[1]), points.get(s[2])));
			double [] s2 = c.getRectangle(points);
			Double[]  a= RotatingCalipers.getMinimumBoundingRectangle(np) ;
			for(int i=0;i< a.length;i++){
				System.out.println(a[i].toString());
			}
			System.out.println("Ratio" + c.getRatio(a));
			
			//System.out.println("Rectangle is " +" "+ points.get(s[9]) +" "+ points.get(s[10]) +" " + points.get(s[11]) + " " + points.get(s[12]) );
			System.out.println("Rectangle is "+" p1: ("+ s2[1] +","+ s2[2]  +" p2: ("+ s2[3] +","+ s2[4] +") p2: (" + s2[5] +"," + s2[6]+ ") p3: ("+" p4: ("+ s2[7] +","+ s2[7]  +") and the area is = "+ s2[0]);
			System.out.println("Rectangle is "+" p1: ("+ points.get((int) s2[9]) +","+ points.get((int) s2[10])  +" p2: ("+points.get((int) s2[11]) +","+ points.get((int) s2[12]) );
			
			
			if(check(Math.pow(c.getPerimeter(points), 2)/c.getArea(points) ,4*Math.PI))
				System.out.println("circulo");
			else
			{
				double [] s3 = c.getRatio(a);
			
				if( s3[2] <= 0.2)
					System.out.println("Linea extrana");
				else
				{
					if(check(s3[3], c.getArea(points)) )
						System.out.println("Rectangulo");
						//send
					else
					{
						if(check(AreaHull,AreaTriang) || check(PerimeterHull, PerimeterTrian))
						{
							System.out.println("Triangulo");
						}
					}
						
				}
			
			 
	
			}
		}
		*/
	}
	public static boolean check(double a , double b)
	{
		int c = (int) a;
		int d = (int) b;
		if(c == d)
			return true;
			else{
				if(c == d+1 || c == d-1)
					return true;
				else return false;
			}
		
	}

}
