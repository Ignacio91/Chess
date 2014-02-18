


import java.util.*;


public class dbscan
{
	public static int e= 8;//mirar distancia correcta
	public static int minpt = 2;// mirar cuantos minpoints
                
	public static Vector<List<Point>> resultList = new Vector<List<Point>>();
                
	public static Vector<Point> pointList ;
	public static Vector<Point> Neighbours ;
	dbscan(Vector<Point> _point)
	{
		pointList = _point;
	}    
	
    public static Vector<List<Point>> applyDbscan()
    {
    	

                        
        int index2 =0;
                      
                                                
         while (pointList.size()>index2)
         {
            Point p =pointList.get(index2);
            System.out.println("iteracion"); 
            if(!Utility.isVisited(p))
            {
                 Utility.Visited(p);
                 Neighbours = Utility.getNeighbours(p);
                 System.out.println(Neighbours);                       
                 if (Neighbours.size()>=minpt)
                 {
                	 System.out.println("entra");
                      int ind=0;
                      while(Neighbours.size()>ind)
                      {
                             Point r = Neighbours.get(ind);
                               if(!Utility.isVisited(r))
                               {
                                 Utility.Visited(r);
                                 Vector<Point> Neighbours2 = Utility.getNeighbours(r);
                                 if (Neighbours2.size() >= minpt)
                                 {
                                     Neighbours=Utility.Merge(Neighbours, Neighbours2);
                                                                
                                 }
                                                        
                               } ind++;
                               System.out.println("iteracion");                
                      }
                                        
                                
                        System.out.println("N"+Neighbours.size());
                         resultList.add(Neighbours);}
                                
                                
                     }index2++;
         }
         return resultList;        
     }
                
}
                


