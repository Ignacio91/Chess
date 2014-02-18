

import java.util.ArrayList;

public class Regression 
{
	ArrayList<Point> points;
	int total;
	float a, b;
	float r, r_cuadra, r_cuadra_new;
	float sum_x, sum_y;
	float avrg_x, avrg_y;
	float cov_x, cov_y, cov_xy;
	float cov_x_cuad, cov_y_cuad, cov_xy_cuad;
	float SSR, SSE, SST;
	private float cov_new;
	Regression(ArrayList<Point> _points)
	{
		points = _points;
		init();
	}
	private void init() 
	{
		a = 0;
		b = 0;
		r=0; 
		r_cuadra=0;
		r_cuadra_new=0;
		cov_x =0;
		cov_y=0; 
		sum_x =0;
		sum_y=0;
		cov_xy=0;
		cov_x_cuad=0; 
		cov_y_cuad=0; 
		cov_xy_cuad=0;
		SSR=0;
		SSE=0; 
		SST=0;
		
	}
	public void calculate()
	{
		for(int i = 0; i<points.size(); i++)
		{
			total++;
			sum_x+= points.get(i).getX();
			sum_y+= points.get(i).getY();
		}
		System.out.println("Rcuad: " + total);
		avrg_x = sum_x/(float) total;
		avrg_y = sum_y/(float) total;
		
		//System.out.println("Avgx: " + avrg_x + "Avgy: " + avrg_y);
		for(int i = 0; i<points.size(); i++)
		{
			cov_xy += (points.get(i).getX()- avrg_x)*(points.get(i).getY()- avrg_y);
			cov_x_cuad += (float) Math.pow(points.get(i).getX()- avrg_x, 2);
			cov_y_cuad += (float) Math.pow(points.get(i).getY()- avrg_y, 2);
		}
		cov_xy = cov_xy/(float) total;
		System.out.println("Covxy: " + cov_xy );
		cov_x_cuad = cov_x_cuad/(float) total;
		cov_y_cuad = cov_y_cuad/(float) total;
		
		
		cov_new = (float) Math.pow(cov_x_cuad *cov_y_cuad, 0.5);
		
		r = cov_xy/cov_new;
		
		b = cov_xy/cov_x_cuad;
		a = avrg_y- (b*avrg_x);
		System.out.println("r: " + r + "a: " + a  +"b: " + b ); 
		
		for(int i = 0; i<points.size(); i++)
		{
			 SSR += (float) Math.pow((b*points.get(i).getX() + a) - avrg_y,2);
			 SSE += points.get(i).getY() -(b*points.get(i).getX() + a) ;
		}
		SST = SSR + SSE;
		System.out.println("SSR: " + SSR + "SSE: " + SSE  +"SST: " + SST ); 
		if(SST != cov_y_cuad)
			System.out.println("Something went wrong");
		r_cuadra =SSR/SST;
		r_cuadra_new = sign(b)* (float) Math.pow(r_cuadra,0.5);
	}
	private float sign(float b2) {
		if(b2>0)
			return 1;
		else
			return -1;
	}
	public float getA(){
		return a;
	}
	public float getB(){
		return b;
	}
	public float getRcuad()
	{
		return r_cuadra;
	}
	public float getR()
	{
		return r_cuadra;
	}
	
	

}
