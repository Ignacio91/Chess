package classify;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Point.Point;

public class ConvexHull 
{

        public ConvexHull()
        {
        	
        }
        public ArrayList<Point> execute(ArrayList<Point> points) 
        {
                ArrayList<Point> xSorted = (ArrayList<Point>) points.clone();
                Collections.sort(xSorted, new XCompare());
                
                int n = xSorted.size();
                
                Point[] lUpper = new Point[n];
                
                lUpper[0] = xSorted.get(0);
                lUpper[1] = xSorted.get(1);
                
                int lUpperSize = 2;
                
                for (int i = 2; i < n; i++)
                {
                        lUpper[lUpperSize] = xSorted.get(i);
                        lUpperSize++;
                        
                        while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1]))
                        {
                                // Remove the middle point of the three last
                                lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
                                lUpperSize--;
                        }
                }
                
                Point[] lLower = new Point[n];
                
                lLower[0] = xSorted.get(n - 1);
                lLower[1] = xSorted.get(n - 2);
                
                int lLowerSize = 2;
                
                for (int i = n - 3; i >= 0; i--)
                {
                        lLower[lLowerSize] = xSorted.get(i);
                        lLowerSize++;
                        
                        while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1]))
                        {
                                // Remove the middle point of the three last
                                lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
                                lLowerSize--;
                        }
                }
                
                ArrayList<Point> result = new ArrayList<Point>();
                
                for (int i = 0; i < lUpperSize; i++)
                {
                        result.add(lUpper[i]);
                }
                
                for (int i = 1; i < lLowerSize - 1; i++)
                {
                        result.add(lLower[i]);
                }
                
                return result;
        }
        
        private boolean rightTurn(Point a, Point b, Point c)
        {
                return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x) > 0;
        }

        private class XCompare implements Comparator<Point>
        {
                @Override
                public int compare(Point o1, Point o2) 
                {
                        return (new Integer(o1.x)).compareTo(new Integer(o2.x));
                }
        }
        public float area(Point a, Point b, Point c)
        {
        	return Math.abs((float)(a.x*(b.y-c.y) + b.x*(c.y-a.y) + c.x*(a.y-b.y))/2);
        }
        public int[] getMaxTriangle(ArrayList<Point> array)
        {
        	int a=0, b=1, c=2;
        	int bA=a, bB =b, bC=c;
        	int n = array.size();
        	while(true)
        	{
        		while(true)
        		{
        			while(area(array.get(a), array.get(b),  array.get(c))<=area(array.get(a), array.get(b),  array.get((c+1)%n)))
        			{
        				 c =(c+1)%n;
        				
        			}
        			 if(area(array.get(a), array.get(b),  array.get(c))<=area(array.get(a), array.get((b+1)%n),  array.get(c)))
     				{
     					 b =(b+1)%n;
     					 continue;
     				 }
     				 else
     					 break;
     				
        		}
        		if(area(array.get(a), array.get(b),  array.get(c))>area(array.get(bA), array.get(bB),  array.get(bC)))
        		{
        			bA=a; 
        			bB =b;
        			bC=c;
        		}
        		a= (a+1)%n;
        		if (a==b) b = (b+1)%n;
        		if (b==c) c = (c+1)%n;
        		if (a==0)  break;
        	}
        	int [] s = {bA, bB, bC};        	
        	return s;
        }
        
        public int[] getmostRectangle(int j, int n, double s, double c, int mx ,int my, ArrayList<Point> array)
        {
        	int x, y;
        	 
        	int xn= array.get(j).x;
        	int yn= array.get(j).y;
        	double rx = xn*c - yn*s;
        	double ry = xn*s + yn*c;
        	double best = rx*mx + my*ry;
        	while(true){
        		x = xn; y = yn;
        		xn= array.get((j+1)%n).x;
        		yn= array.get((j+1)%n).y;
        		rx = xn*c - yn*s;
        		ry = xn*s + yn*c;
        		if((rx*mx + my*ry) >= best){
        			j = (j+1)%n;
        			best = rx*mx + my*ry;
        		}
        		else
        		{
        			int [] fin = {x, y, j};
        			return  fin;
        		}
        				
        		
        	}
        	
        }
        public double[] getRectangle(ArrayList<Point> array)
        {
        	int il=1, ir=1, ip=1;
        	int xp, yp;
        	int xr, yr;
        	int xl, yl;
        	int n = array.size();
        	int dx, dy;
        	double p1 = 4* Math.atan(1);
        	double theta;
        	double s, c;
        	double yc;
        	double area;
        	int [] p;
        	double minRect [] = {0,0,0,0, 0, 0, 0, 0, 0, 0,0,0, 0};
        	for(int i =0; i< array.size()-1; i++)
        	{
        		dx = array.get(i+1).x - array.get(i).x ;
        		dy = array.get(i+1).y - array.get(i).y ;
        		System.out.println("dx"+ dx + "dy "+ dy );
        		theta = p1 -  Math.atan2(dy, dx);
        		s = Math.sin(theta);
        		c = Math.cos(theta);
        		System.out.println("s "+ s + "c "+ c +"theta "+ theta);
        		yc =  array.get(i).x*s + array.get(i).x*c ;
        		p = getmostRectangle(ip, n, s, c, 0, 1, array);
        		xp = p[0]; yp = p[1]; ip = p[2];
        		if(i ==0) ir =ip;
        		p = getmostRectangle(ir, n, s, c, 1, 0, array);
        		xr = p[0]; yr = p[1]; ir = p[2];
        		p = getmostRectangle(il, n, s, c, -1, 0, array);
        		xl = p[0]; yl = p[1]; il = p[2];
        		System.out.println("il "+ xl + " ir "+ xr +"ip "+ xp);
        		//System.out.println("xl "+ xl + " xr "+ xr +" xp "+ xp);
        		//System.out.println("yl "+ yl + " yr "+ yr + " yp " + yp);
        		area = Math.abs((yp-yc)*(xr-xl));
        		//area = (yp-yc)*(xr-xl);
        		//System.out.println("Area; "+ area + " " + yc);
        		if(area> minRect[0] && area != 0 )
        		{
        			minRect[0] = area; minRect[1] = xr; minRect[2] = yr;
        			minRect[3] = xp; minRect[4] = yp; minRect[5] = xl;
        			minRect[6] = yl ; minRect[7] = array.get(i).x; minRect[8] = array.get(i).y;
        			//System.out.println("i" +minRect[9] + " i "+minRect[10] + " i "+ minRect[11] +"i "+ minRect[12]);
        			minRect[9] = i ; minRect[10] = il; minRect[11] = ip;
        			minRect[12] = ir ; 
        		}
        	}
        	//System.out.println("i" +minRect[9] + " i "+minRect[10] + " i "+ minRect[11] +"i "+ minRect[12]);
        	return minRect;
        }
        public float getPerimeter(ArrayList<Point> array)
        {
        	float perimeter =0;
        	Point p1,p2;
        	for(int i = 0; i < array.size()-1; i++){
        		p1 = array.get(i);
        		p2 = array.get(i+1);
        		perimeter+= (float)Math.pow(Math.pow(p1.x- p2.x,2) + Math.pow(p1.y- p2.y,2), 0.5);
        	}
        	perimeter+= (float)Math.pow(Math.pow(array.get(0).x- array.get(array.size()-1).x,2) + 
        			Math.pow(array.get(0).y- array.get(array.size()-1).y,2), 0.5);
        	return perimeter;
        }
        public float getArea(ArrayList<Point> array){
        	float area = 0;
            int n = array.size();
            int i, j;
            
            for(i = 0; i < n; i++) 
            {   
                j = ((i+1) % n);
                area += (array.get(i).x*array.get(j).y)-(array.get(j).x*array.get(i).y);
                //System.out.println("Area; " + ((array.get(i).x*array.get(j).y)-(array.get(j).x*array.get(i).y)));
             }
            return area/2;
            
        }
		public double[] getRatio(Point2D.Double[] a) 
		{
			//Check
			/*double heigth = Math.max(Math.pow(Math.pow(a[0].x- a[1].x,2) + Math.pow(a[0].y- a[1].y,2), 0.5),
					(Math.pow(Math.pow(a[0].x- a[2].x,2) + Math.pow(a[0].y- a[2].y,2), 0.5)));
			double width = Math.min(Math.pow(Math.pow(a[0].x- a[1].x,2) + Math.pow(a[0].y- a[1].y,2), 0.5),
					(Math.pow(Math.pow(a[0].x - a[2].x,2) + Math.pow(a[0].y- a[2].y,2), 0.5)));*/
			for(int i=0;i< a.length;i++){
				System.out.println(a[i].toString());
				
			}
			
			 double width = Math.pow(Math.pow(a[2].x - a[3].x,2) + Math.pow(a[2].y- a[3].y,2), 0.5);
			 double heigth = Math.pow(Math.pow(a[1].x - a[2].x,2) + Math.pow(a[1].y- a[2].y,2), 0.5);
			 
			 //System.out.println("heigth:" + heigth + "width:" + width );
			 if(width > heigth){
				 double aux = width;
				 width = heigth;
				 heigth = aux;
			 }
			
			double[] re = { width, heigth, width/heigth, width*heigth};
			return re;
			
		}
        
}



