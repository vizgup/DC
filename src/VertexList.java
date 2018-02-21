
public class VertexList {

	private Vertex head;
	private Vertex tail;
	
	public void clear()
	{
		head = tail = null;
	}
	
	public void add(Vertex vtx)
	{
		if (head ==  null)
		{
			head = vtx;
		}
		
		else
		{
			tail.next = vtx;
		}
		
		vtx.prev = tail;
		vtx.next = null;
		tail = vtx;
	}
	
	public void addAll(Vertex vtx)
	{
		if (head == null)
		{
			head = vtx;
		}
		else
		{
			tail.next = vtx;
		}
		vtx.prev = tail;
		while (vtx.next != null)
		{
			vtx = vtx.next;
		}
		tail = vtx;
	}
	
	public void delete(Vertex vtx)
	{
		if (vtx.prev == null)
		{
			head = vtx.next;
		}
		else
		{
			vtx.prev.next = vtx.next;
		}
		if (vtx.next == null)
		{
			tail = vtx.prev;
		}
		else
		{
			vtx.next.prev = vtx.prev;
		}
	}
	
	//the below function deletes a chain of vertices
	public void delete(Vertex vtx1, Vertex vtx2)
	{
		if (vtx1.prev == null)
		{
			head = vtx2.next;
		}
		else
		{
			vtx1.prev.next = vtx2.next;
		}
		if (vtx2.next == null)
		{
			tail = vtx1.prev;
		}
		else
		{
			vtx2.next.prev = vtx1.prev;
		}
	}
	
	public void InsertBefore(Vertex vtx, Vertex next)
	{
		vtx.prev = next.prev;
		if (next.prev == null)
		{
			head = vtx;
		}
		else
		{
			next.prev.next = vtx;
		}
		vtx.next = next;
		next.prev = vtx;
	}
	
	public Vertex first()
	{
		return head;
	}
	
	public boolean isEmpty()
	{
		return head == null;
	}
}
