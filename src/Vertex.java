
public class Vertex {

	Point3d pnt;
	int index;
	Vertex prev;
	Vertex next;
	Face face;
	
	public Vertex()
	{
		pnt = new Point3d();
	}
	
	public Vertex(double x, double y, double z, int idx)
	{
		pnt = new Point3d(x, y, z);
		index= idx;
	}
}
