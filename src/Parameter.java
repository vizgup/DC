import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

//Take the atom types from the topology file and the parameters from the parameter file

public class Parameter {
	
	private static String parameter = "utilities/parameter.txt";
	private static String topology = "utilities/topology.txt";
	private static File t_file = new File(topology);
	private static File p_file = new File(parameter);
	private static BufferedReader parm = null;
	private static BufferedReader top = null;
	
	private static HashMap<String, String> bondLength = new HashMap<String, String>();
	private static HashMap<String, String> bondAngle = new HashMap<String, String>();
	private static HashMap<String, String> torsion = new HashMap<String, String>();
	private static HashMap<String, String> vdw = new HashMap<String, String>();
	private static HashMap<String, String> electrostatic = new HashMap<String, String>();
	private static HashMap<String, String> atomTypes = new HashMap<String, String>();
	private static String residue = null;
	
	//read the topology file to get the atom types
	
	public static void readFileTop()
	{
		try {
		 	 top = new BufferedReader(new FileReader(t_file));
		  	 String line;
		  	 
		  	 while ((line = top.readLine()) != null)
		  	 {
		  		if (line.toLowerCase().startsWith("resi") || line.toLowerCase().startsWith("pres"))
		  		 {
		  			String[] tmp = line.split(" ");
		  			String[] data = new String[2];
		  			int idx = 0;
		  			for (String ss : tmp)
		  			{
		  				if (ss.length() == 0)
		  					continue;
		  				data[idx++] = ss;
		  				if (idx == 2)
		  					break;
		  			}
		  			residue = data[1];
		  		 }
		  		 if (line.startsWith("ATOM"))
		  		 {
		  			 
		  			 String[] tmp = line.split(" ");
		  			 String[] data = new String[4];
		  			 int idx = 0;
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.equals("!") || ss.startsWith("!"))
		  					 break;
		  				 data[idx++] = ss;
		  			 }
		  			 data[1] = residue + "-" + data[1];
		  			 atomTypes.put(data[1], data[2]);
		  			 electrostatic.put(data[1], data[3]);
		  			 //Arrays.toString(data);
		  		 }
		  	 }
		}
		catch (IOException e) 
		{
	  		e.printStackTrace();
	 	} catch (Exception e) {
	  		e.printStackTrace();
	 	} finally 
		{
	  		try {

	   	//Try to close it, this might throw an exception anyway.
	   top.close();
	   
	  	}
	 catch (Exception ex) {
	   	     ex.printStackTrace();
	  		}
	 	}
	}
	
	public void readFileParm()
	{
		try {
		 	 parm = new BufferedReader(new FileReader(p_file));
		  	 String line;
		  	 int flag = 0;	  	 
		  	 
		  	 while ((line = parm.readLine()) != null)
		  	 {
		  		 if (line.startsWith("BOND"))
		  		 {
		  			 flag = 1;
		  		 }
		  		 else if (line.startsWith("ANGLES"))
		  		 {
		  			 flag = 2;
		  		 }
		  		 else if (line.startsWith("DIHEDRALS"))
		  		 {
		  			 flag = 3;
		  		 }
		  		 else if (line.startsWith("NONBONDED"))
		  		 {
		  			 //System.out.println("start");
		  			 flag = 4;
		  		 }
		  		 else if (line.startsWith("!") || line.startsWith("	"))
		  			 continue;
		  		 else if (flag == 1)
		  		 {
		  			 String[] tmp = line.split(" ");
		  			 String[] data = new String[4];
		  			 int idx = 0;
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.startsWith("!") || ss.equals("!"))
		  					 break;
		  				 data[idx++] = ss;
		  			 }
		  			 //data 2 is kb and data 3 is b0
		  			 if (data[3] != null && data[2] != null)
		  				 bondLength.put(data[0] + " " + data[1], data[2] + " " + data[3]);
		  		 }
		  		 else if (flag == 2)
		  		 {
		  			String[] tmp = line.split(" ");
		  			 String[] data = new String[5];
		  			 int idx = 0;
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.startsWith("!"))
		  					 break;
		  				 data[idx++] = ss;
		  				 if (idx == 5)
		  					 break;
		  			 }
		  			 //data 3 is ktheta and data 4 is theta0
		  			 bondAngle.put(data[0] + " " + data[1] + " " + data[2], data[3] + " " + data[4]);
		  		 }
		  		else if (flag == 3)
		  		{
		  			String[] tmp = line.split(" ");
		  			 String[] data = new String[7];
		  			 int idx = 0;
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.startsWith("!"))
		  					 break;
		  				 data[idx++] = ss;
		  				 if (idx == 7)
		  					 break;
		  			 }
		  			 //System.out.println(data[0] + " " + data[1]);
		  			 //data 4 is kchi and data 5 is n and data 6 is delta
		  			 //formula is Kchi(1 + cos(n(chi)-delta)
		  			 torsion.put(data[0] + " " + data[1] + " " + data[2] + " " + data[3], data[4] + " " + data[5] + " " + data[6]);
		  		}
		  		else if (flag == 4)
		  		{
		  			String[] tmp = line.split(" ");
		  			 String[] data = new String[4];
		  			 int idx = 0;
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.startsWith("!"))
		  					 break;
		  				 data[idx++] = ss;
		  				 if (idx == 4)
		  					 break;
		  			 }
		  			 if (data[0] != null)
		  			 { 	//System.out.println(data[0]);
		  			 //data 2 is epsilon ; data3 is sigmafor (int x = 0; x < vdw.size(); x++)
		  			 	vdw.put(data[0], data[2] + " " + data[3]);
		  			 }
		  		}
		  	 }
		}
		catch (IOException e) 
		{
	  		e.printStackTrace();
	 	} catch (Exception e) {
	  		e.printStackTrace();
	 	} finally 
		{
	  		try {

	   	//Try to close it, this might throw an exception anyway.
	   top.close();
	   
	  	}
	 catch (Exception ex) {
	   	     ex.printStackTrace();
	  		}
	 	}
	}
	
	public String getAtomType(String atom, String residue)
	{
		String type = null;
		String tmp = residue + "-" + atom;
		if (atomTypes.containsKey(tmp))
		{
			type = atomTypes.get(tmp);
		}
		return type;
	}
	
	public double getKb(String atom1, String atom2, String residue1, String residue2)
	{
		//System.out.println(atom1 + " " + residue1 + " " + atom2 + " " + residue2);
		//String w1 = residue1 + "-" + atom1;
		String w2 = residue2 + "-" + atom2;
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		double kb = 0;
		if (bondLength.containsKey(atom1 + " " + atom2))
		{
			String[] tmp = bondLength.get(atom1 + " " + atom2).split(" ");
			//System.out.println(tmp[0]);
			if (tmp[0] != "")
				kb = Double.parseDouble(tmp[0]);
			else
				kb = 0;
		}
		else if (bondLength.containsKey(atom2 + " " + atom1))
		{
			String[] tmp = bondLength.get(atom2 + " " + atom1).split(" ");
			//System.out.println(tmp[0]);
			if (tmp[0] != null)
				kb = Double.parseDouble(tmp[0]);
		}
		return kb;
	}
	
	public double getB0(String atom1, String atom2, String residue1, String residue2)
	{
		
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		double b0 = 0;
		if (bondLength.containsKey(atom1 + " " + atom2))
		{
			String[] tmp = bondLength.get(atom1 + " " + atom2).split(" ");
			b0 = Double.parseDouble(tmp[1]);
		}
		else if (bondLength.containsKey(atom2 + " " + atom1))
		{
			String[] tmp = bondLength.get(atom2 + " " + atom1).split(" ");
			b0 = Double.parseDouble(tmp[1]);
		}
		return b0;
	}
	
	public double getKtheta(String atom1, String atom2, String atom3, String residue1, String residue2, String residue3)
	{
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		atom3 = getAtomType(atom3, residue3);
		double ktheta = 0;
		if (bondAngle.containsKey(atom1 + " " + atom2 + " " + atom3))
		{
			String[] tmp = bondAngle.get(atom1 + " " + atom2 + " " + atom3).split(" ");
			ktheta = Double.parseDouble(tmp[0]);
		}
		else if (bondLength.containsKey(atom3 + " " + atom1 + " " + atom1))
		{
			String[] tmp = bondLength.get(atom3 + " " + atom2 + " " + atom1).split(" ");
			ktheta = Double.parseDouble(tmp[0]);
		}
		return ktheta;
	}
	
	public double getTheta(String atom1, String atom2, String atom3, String residue1, String residue2, String residue3)
	{
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		atom3 = getAtomType(atom3, residue3);
		double theta = 0;
		if (bondAngle.containsKey(atom1 + " " + atom2 + " " + atom3))
		{
			String[] tmp = bondAngle.get(atom1 + " " + atom2 + " " + atom3).split(" ");
			theta = Double.parseDouble(tmp[1]);
		}
		else if (bondLength.containsKey(atom3 + " " + atom1 + " " + atom1))
		{
			String[] tmp = bondLength.get(atom3 + " " + atom2 + " " + atom1).split(" ");
			theta = Double.parseDouble(tmp[1]);
		}
		return Math.toRadians(theta);
	}
	
	public double getKchi(String atom1, String atom2, String atom3, String atom4, String residue1, String residue2, String residue3, String residue4)
	{
		//System.out.println(atom1 + " " + atom2 +  " " + atom3 + " " + atom4);
		//System.out.println(residue1 + " " + residue2 + " " + residue3 + " " + residue4);
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		atom3 = getAtomType(atom3, residue3);
		atom4 = getAtomType(atom4, residue4);
		double kchi = 0;
		if (torsion.containsKey(atom1 + " " + atom2 + " " + atom3 + " " + atom4))
		{
			String[] tmp = torsion.get(atom1 + " " + atom2 + " " + atom3 + " " + atom4).split(" ");
			kchi = Double.parseDouble(tmp[0]);
		}
		else if (torsion.containsKey(atom4 + " " + atom3 + " " + atom2 + " " + atom1))
		{
			String[] tmp = torsion.get(atom4 + " " + atom3 + " " + atom2 + " " + atom1).split(" ");
			kchi = Double.parseDouble(tmp[0]);
		}
		//System.out.println(atom1 + " " + atom2 + " " + atom3 + " " + atom4);
		//System.out.println("kchi: " + kchi);
		return kchi;
	}
	
	public int getN(String atom1, String atom2, String atom3, String atom4, String residue1, String residue2, String residue3, String residue4)
	{
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		atom3 = getAtomType(atom3, residue3);
		atom4 = getAtomType(atom4, residue4);
		int n = 0;
		if (torsion.containsKey(atom1 + " " + atom2 + " " + atom3 + " " + atom4))
		{
			String[] tmp = torsion.get(atom1 + " " + atom2 + " " + atom3 + " " + atom4).split(" ");
			n = Integer.parseInt(tmp[1]);
		}
		else if (torsion.containsKey(atom4 + " " + atom3 + " " + atom2 + " " + atom1))
		{
			String[] tmp = torsion.get(atom4 + " " + atom3 + " " + atom2 + " " + atom1).split(" ");
			n = Integer.parseInt(tmp[1]);
		}
		//System.out.println("n: " + n);
		return n;
	}
	
	public double getDelta(String atom1, String atom2, String atom3, String atom4, String residue1, String residue2, String residue3, String residue4)
	{
		atom1 = getAtomType(atom1, residue1);
		atom2 = getAtomType(atom2, residue2);
		atom3 = getAtomType(atom3, residue3);
		atom4 = getAtomType(atom4, residue4);
		double delta = 0;
		if (torsion.containsKey(atom1 + " " + atom2 + " " + atom3 + " " + atom4))
		{
			String[] tmp = torsion.get(atom1 + " " + atom2 + " " + atom3 + " " + atom4).split(" ");
			delta = Double.parseDouble(tmp[2]);
		}
		else if (torsion.containsKey(atom4 + " " + atom3 + " " + atom2 + " " + atom1))
		{
			String[] tmp = torsion.get(atom4 + " " + atom3 + " " + atom2 + " " + atom1).split(" ");
			delta = Double.parseDouble(tmp[2]);
		}
		//System.out.println("delta: " + Math.toRadians(delta));
		return Math.toRadians(delta);
	}
	
	public double getEpsilon(String atom, String residue)
	{
		atom = getAtomType(atom, residue);
		//atom = residue + "-" + atom;
		double epsilon = 0;
		if (vdw.containsKey(atom))
		{
			String[] tmp = vdw.get(atom).split(" ");
			epsilon = Double.parseDouble(tmp[0]);
			//System.out.println(epsilon);
		}
		return epsilon;
	}
	
	public double getSigma(String atom, String residue)
	{
			
		atom = getAtomType(atom, residue);
		//atom = residue + "-" + atom;
		double sigma = 0;
		if (vdw.containsKey(atom))
		{
			//System.out.println("yes");
			String[] tmp = vdw.get(atom).split(" ");
			sigma = Double.parseDouble(tmp[1]);
			//System.out.println(sigma);
		}
		else if (!vdw.containsKey(atom))
		{
			//System.out.println("hello");
		}
		return sigma;
	}
	
	public double getCharge(String atom, String residue)
	{
		//atom = getAtomType(atom, residue);
		atom = residue + "-" + atom;
		double charge = 0;
		if (electrostatic.containsKey(atom))
		{
			//System.out.println(atom);
			charge = Double.parseDouble(electrostatic.get(atom));
		}
		return charge;
	}

}