import java.util.*;

public class Vector3d {
	
	//variable for setting precision of the double
	static private final double doublePrec = 2.2204460492503131e-16;
	
	public double x;
	public double y;
	public double z;
	
	public Vector3d()
	{
		
	}
	
	public Vector3d(Vector3d v)
	{
		set(v);
	}
	
	public Vector3d(double x, double y, double z)
	{
		set(x, y, z);
	}
	
	public double get(int i)
	{
		switch(i)
		{
			case 0:
			{
				return x;
			}
			case 1:
			{
				return y;
			}
			case 2:
			{
				return z;
			}
			default:
			{
				throw new ArrayIndexOutOfBoundsException(i);
			}
		}
	}
	
	public void set(int i, double v)
	{
		switch(i)
		{
			case 0:
			{
				x = v;
				break;
			}
			case 1:
			{
				y = v;
				break;
			}
			case 2:
			{
				z = v;
				break;
			}
			default:
			{
				throw new ArrayIndexOutOfBoundsException(i);
			}
		}
	}
	
	public void set(Vector3d v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public void set (double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//Operations on Vectors
	
	public void add(Vector3d v1, Vector3d v2)
	{
		x = v1.x + v2.x;
		y = v1.y + v2.y;
		z = v1.z + v2.z;
	}
	
	public void add(Vector3d v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public void sub (Vector3d v1, Vector3d v2)
	{
		x = v1.x - v2.x;
		y = v1.y - v2.y;
		z = v1.z - v2.z;
	}
	
	public void sub (Vector3d v1)
	{
		x -= v1.x;
		y -= v1.y;
		z -= v1.z;
	}
	
	public void scale (double s)
	{
		x = s*x;
		y = s*y;
		z = s*z;
	}
	
	public void scale (double s, Vector3d v1)
	{
		x = s*v1.x;
		y = s*v1.y;
		z = s*v1.z;
	}
	
	public double norm()
	{ 
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public double normSquared()
	{ 
		return x*x + y*y + z*z;
	}
	
	public double distance(Vector3d v)
	{
		double dx = x - v.x;
		double dy = y - v.y;
		double dz = z - v.z;
		return Math.sqrt (dx*dx + dy*dy + dz*dz);
	}
	
	public double dot (Vector3d v1)
	{
		return x*v1.x + y*v1.y + z*v1.z;
	}
	
	public void normalize()
	{
		
		double lenSqr = x*x + y*y + z*z;
		double err = lenSqr - 1;
		if (err > (2*doublePrec) || err < -(2*doublePrec))
		{ 
			double len = Math.sqrt(lenSqr);
			x /= len;
			y /= len;
			z /= len;
		}
	}
	
	public void cross (Vector3d v1, Vector3d v2)
	{
		double tmpx = v1.y*v2.z - v1.z*v2.y;
		double tmpy = v1.z*v2.x - v1.x*v2.z;
		double tmpz = v1.x*v2.y - v1.y*v2.x;

		x = tmpx;
		y = tmpy;
		z = tmpz;
	}
}
