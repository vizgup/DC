import java.util.*;

public class Face {
	HalfEdge he;
	private Vector3d normal;
	double area;
	private Point3d centroid;
	int index;
	int numVerts;
	
	Face next;
	
	static final int VISIBLE = 1;
	static final int NONCONVEX = 2;
	static final int DELETED = 3;
	
	int mark = VISIBLE;
	
	Vertex outside;
	
	public void computeCentroid(Point3d centroid)
	{
		centroid.set(0, 0, 0);
		HalfEdge h = he;
		do
		{
			centroid.add(h.head().pnt);
			h = h.next;
		}
		while (h != he);
		
		centroid.scale(1/(double)numVerts);
	}
	
	public void computeNormal (Vector3d normal)
	{
		HalfEdge he1 = he.next;
		HalfEdge he2 = he1.next;

		Point3d p0 = he.head().pnt;
		Point3d p2 = he1.head().pnt;

		double d2x = p2.x - p0.x;
		double d2y = p2.y - p0.y;
		double d2z = p2.z - p0.z;

		normal.set(0, 0, 0);

		numVerts = 2;

		while (he2 != he)
	    { 
			double d1x = d2x;
			double d1y = d2y;
			double d1z = d2z;

			p2 = he2.head().pnt;
			d2x = p2.x - p0.x;
			d2y = p2.y - p0.y;
			d2z = p2.z - p0.z;

			normal.x += d1y*d2z - d1z*d2y;
			normal.y += d1z*d2x - d1x*d2z;
			normal.z += d1x*d2y - d1y*d2x;

			he1 = he2;
			he2 = he2.next;
			numVerts++;
	    }
		area = normal.norm();
		normal.scale (1/area);
	}

	public static Face createTriangle (Vertex v0, Vertex v1, Vertex v2)
	{
		Face face = new Face();
		HalfEdge he0 = new HalfEdge (v0, face);
		HalfEdge he1 = new HalfEdge (v1, face);
		HalfEdge he2 = new HalfEdge (v2, face);

		he0.prev = he2;
		he0.next = he1;
		he1.prev = he0;
		he1.next = he2;
		he2.prev = he1;
		he2.next = he0;

		face.he = he0;

		return face;
	 }

	public static Face create (Vertex[] vtxArray)
	{
		Face face = new Face();
		HalfEdge hePrev = null;
		for (int i=0; i<vtxArray.length; i++)
	    { 	HalfEdge h = new HalfEdge (vtxArray[i], face); 
	      	if (hePrev != null)
	      	{	h.setPrev (hePrev);
	      		hePrev.setNext (h);
	      	}
	      	else
	      	{ 	face.he = h; 
	      	}
	      	hePrev = h;
	    }
		face.he.setPrev (hePrev);
		hePrev.setNext (face.he);

		return face;	   
	 }

	public Face ()
	{ 
		normal = new Vector3d();
		centroid = new Point3d();
		mark = VISIBLE;
	}

	public HalfEdge getEdge(int i)
	{
		HalfEdge h = he;
		while (i > 0)
	    { 	h = h.next;
	      	i--;
	    }
		while (i < 0)
	    { 	h = h.prev;
	      	i++;
	    }
		return h;
	}

	public HalfEdge getFirstEdge()
	{ 	return he;
	}
	
	public HalfEdge findEdge (Vertex vt, Vertex vh)
	{
		HalfEdge h = he;
		do
	    { 	if (h.head() == vh && h.tail() == vt)
	       	{ 	return h;
	       	}
	      	h = h.next;
	    }
		while (h != he);
		return null;
	}

	
	public double distanceToPlane (Point3d p)
	{	p.x -= centroid.x;
		p.y -= centroid.y;
		p.z -= centroid.z;
		return normal.x*p.x + normal.y*p.y + normal.z*p.z;
	}

	public Vector3d getNormal ()
	{
		return normal;
	}

	public Point3d getCentroid ()
	{
		return centroid;
	}

	public int numVertices()
	{
		return numVerts;
	}

	public void getVertexIndices (int[] idxs)
	{
		HalfEdge h = he;
		int i = 0;
		do
	    {	idxs[i++] = h.head().index;
	      	h = h.next;
	    }
		while (h != he);
	}

	private double areaSquared (HalfEdge hedge0, HalfEdge hedge1)
	{
		// return the squared area of the triangle defined
		// by the half edge hedge0 and the point at the
		// head of hedge1.

		Point3d p0 = hedge0.tail().pnt;
		Point3d p1 = hedge0.head().pnt;
		Point3d p2 = hedge1.head().pnt;

		double dx1 = p1.x - p0.x;
		double dy1 = p1.y - p0.y;
		double dz1 = p1.z - p0.z;

		double dx2 = p2.x - p0.x;
		double dy2 = p2.y - p0.y;
		double dz2 = p2.z - p0.z;

		double x = dy1*dz2 - dz1*dy2;
		double y = dz1*dx2 - dx1*dz2;
		double z = dx1*dy2 - dy1*dx2;

		return x*x + y*y + z*z;	   
	}
	
}