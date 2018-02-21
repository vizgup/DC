import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ForceFieldCalculator {

	//These ArrayLists store the data from ATOM record of PDB
	
	private static ArrayList<String> name = new ArrayList<String>();
	private static ArrayList<String> resSeq = new ArrayList<String>();
	private static ArrayList<Character> chainID = new ArrayList<Character>();
	private static ArrayList<String> resName = new ArrayList<String>();
	private static ArrayList<Double> x = new ArrayList<Double>();
	private static ArrayList<Double> y = new ArrayList<Double>();
	private static ArrayList<Double> z = new ArrayList<Double>();
	private static ArrayList<Double> occupancy = new ArrayList<Double>();
	private static ArrayList<Double> tempFactor = new ArrayList<Double>();
	private static ArrayList<Character> altLoc = new ArrayList<Character>();
	
	private static String initial = "";
	private static int count = 0;
	
	//Constants
	private static double k = 332; //check this value again
	private static double switchin = 10;
	private static double switchout = 12;
	private static double coulomb = 332.0636;
	
	private static Parameter p = new Parameter();
	private static Topology t = new Topology();
	private static int no_atoms = 0;
	
	//ArrayLists for preparing bond list
	private static ArrayList<Integer> bond_atom1 = new ArrayList<Integer>();
	private static ArrayList<Integer> bond_atom2 = new ArrayList<Integer>();
	
	//ArrayList for preparing angle list
	private static ArrayList<Integer> angle_atom1 = new ArrayList<Integer>();
	private static ArrayList<Integer> angle_atom2 = new ArrayList<Integer>();
	private static ArrayList<Integer> angle_atom3 = new ArrayList<Integer>();
	
	//ArrayList for preparing dihedral list
	private static ArrayList<Integer> dihedral_atom1 = new ArrayList<Integer>();
	private static ArrayList<Integer> dihedral_atom2 = new ArrayList<Integer>();
	private static ArrayList<Integer> dihedral_atom3 = new ArrayList<Integer>();
	private static ArrayList<Integer> dihedral_atom4 = new ArrayList<Integer>();
	
	
	private static double getDist(double x1, double x2, double y1, double y2, double z1, double z2) {
		double x = x1-x2;
		double y = y1-y2;
		double z = z1-z2;
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	private static double getAngle(double x1, double x2, double x3, double y1, double y2, double y3, double z1, double z2, double z3) {
		double dot = ((x1-x2)*(x3-x2) + (y1-y2)*(y3-y2) + (z1-z2)*(z3-z2))/(getDist(x1, x2, y1, y2, z1, z2)*getDist(x3, x2, y3, y2, z3, z2));
		double radians = Math.acos(dot);
		return radians;
	}
	
	private static double swit(double dist)
	{
		if (dist < switchin)
			return 1;
		else if (dist > switchout)
			return 0;
		else
			return Math.pow(Math.pow(switchout, 2) - Math.pow(dist, 2), 2) * (Math.pow(switchout, 2) + (2*Math.pow(dist, 2)) - (3*Math.pow(switchin, 2))) / Math.pow(Math.pow(switchout, 2) - Math.pow(switchin, 2), 3);
	}
		
	private static double getPhi(double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4, double z1, double z2, double z3, double z4)
	{
		DecimalFormat df = new DecimalFormat("#.######");
		
		//define vector a
		double xa = x2-x1;
		double ya = y2-y1;
		double za = z2-z1;
		
		//define vector b
		double xb = x3-x2;
		double yb = y3-y2;
		double zb = z3-z2;
		
		//define vector c
		double xc = x4-x3;
		double yc = y4-y3;
		double zc = z4-z3;
		
		//define vector axb
		double xaxb = zb*ya - yb*za;
		double yaxb = zb*xa - xb*za;
		double zaxb = yb*xa - xb*ya;
		
		//define vector bxc
		double xbxc = zc*yb - yc*zb;
		double ybxc = zc*xb - xc*zb;
		double zbxc = yc*xb - xc*yb;
		
		double dot = ((xaxb*xbxc + yaxb*ybxc + zaxb*zbxc) / (Math.sqrt(xaxb*xaxb + yaxb*yaxb + zaxb*zaxb)*Math.sqrt(xbxc*xbxc + ybxc*ybxc + zbxc*zbxc)));
		dot = Double.parseDouble(df.format(dot));
		double radians = Math.acos(dot);
		double phi = Math.toDegrees(radians);
		
		return radians;
	}
	
	private static double getEpsilon2(double epsilon1, double epsilon2)
	{
		return Math.sqrt(epsilon1 * epsilon2);
	}
	
	private static double getSigma2(double sigma1, double sigma2)
	{
		return (sigma1 + sigma2); //Note that sigma values passed are already divided in 2
	}
	
	private static void fileReader(String aFileName)
	{
		File file = new File (aFileName);
		
		BufferedReader reader = null;
		try{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		try {
		 	 reader = new BufferedReader(new FileReader(file));
		  	 String line;
		  	 
		  	 while ((line = reader.readLine())!= null) {
		  		 if (line.startsWith("ATOM")) {
		  			 
		  			 String s1 = new String(line.substring(12, 16).replace(" ", "")); //atom name
		  			 String s3 = new String(line.substring(17, 20).replace(" ", "")); //residue name
		  			 String s2 = new String(line.substring(22, 26).replace(" ", "")); //residue seq num
		  			 String s4 = new String(line.substring(31, 38).replace(" ", "")); //x
		  			 String s5 = new String(line.substring(39, 46).replace(" ", "")); //y
		  			 String s6 = new String(line.substring(47, 54).replace(" ", "")); //z
		  			 
		  			 altLoc.add(line.charAt(16));
		  			 chainID.add(line.charAt(21));
		  			 name.add(s1);
		  			 no_atoms++;
		  			 resName.add(s3);
		  			 x.add(Double.parseDouble(s4));
		  			 y.add(Double.parseDouble(s5));
		  			 z.add(Double.parseDouble(s6));
		  			 
		  			 //System.out.println(s2);
		  			 resSeq.add(s2);
		  			
		  		 }
		  	 }
		  	 
		}catch (IOException e) 
		{
	  		e.printStackTrace();
	 	} catch (Exception e) {
	  		e.printStackTrace();
	 	} finally 
		{
	  		try {

	   	//Try to close it, this might throw an exception anyway.
	   reader.close();
	   
	  	}
	 catch (Exception ex) {
	   	     ex.printStackTrace();
	  		}
	 	}
		
	}
	
	public static int prepareBondList()
	{
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int flag;
		for (int i = 0; i < name.size(); i++)
		{
			if (name.get(i).startsWith("H") || altLoc.get(i).equals("B"))
				continue;
			flag = 1;
			
			for (int j = i+1; j < name.size(); j++)
			{
				if (altLoc.get(j).equals("B"))
					continue;
				if (!resSeq.get(i).equals(resSeq.get(j)))
					flag = 0;
				//change below
				if (resSeq.get(i).equals(resSeq.get(j)) && flag == 1)
				{
					
					boolean bool = t.isBond(name.get(i), name.get(j), resName.get(i));
					if (bool)
					{
						//System.out.println(element.get(i) + " " + element.get(j));
						bond_atom1.add(i);
						bond_atom2.add(j);
						//System.out.println("1: " + (i+1) + " " + (j+1) + " " + resName.get(i) + " " + name.get(i) + " " + name.get(j));
						count++;
						count1++;
					}
				}
				else if (name.get(i).startsWith("C") && name.get(j).startsWith("N"))
				{
					if (getDist(x.get(i), x.get(j), y.get(i), y.get(j), z.get(i), z.get(j)) < 2.0)
					{
						bond_atom1.add(i);
						bond_atom2.add(j);
						//System.out.println("2: " + (i+1) + " " + (j+1));
						
						count++;
						count2++;
					}
				}
				else if (name.get(i).startsWith("S") && name.get(j).startsWith("S"))
				{
					if (getDist(x.get(i), x.get(j), y.get(i), y.get(j), z.get(i), z.get(j)) < 3.0)
					{
						bond_atom1.add(i);
						bond_atom2.add(j);
						//System.out.println("3: " + (i+1) + " " + (j+1));
						count++;
						count3++;
					}
				}
			}
		}
		System.out.println("bond1: " + count1);
		System.out.println("bond2: " + count2);
		System.out.println("bond3: " + count3);
		System.out.println("#bond : " + count);
		return count;
	}
	
	public static boolean exists(int q, int j, int k)
	{
		for (int i = 0; i < angle_atom1.size(); i++)
		{
			if (angle_atom1.get(i) == q)
			{
				if (angle_atom2.get(i) == j)
				{
					if (angle_atom3.get(i) == k)
						return true;
				}
			}
		}
		return false;
	}
	
	public static int prepareAngleList()
	{
		int count = 0;
		
		for (int i = 0; i < bond_atom1.size(); i++)
		{
			for (int j = i+1; j < bond_atom1.size(); j++)
			{
				if (bond_atom1.get(i).equals(bond_atom1.get(j))) //Case 1
				{
					if(!exists(bond_atom2.get(j), bond_atom1.get(i), bond_atom2.get(i)))
					{	angle_atom1.add(bond_atom2.get(i));
						angle_atom2.add(bond_atom1.get(i));
						angle_atom3.add(bond_atom2.get(j));
						count++;
//						System.out.println((bond_atom2.get(i)+1) + " " + (bond_atom1.get(i)+1) + " " + (bond_atom2.get(j)+1));
					}
				}
				else if (bond_atom2.get(i).equals(bond_atom1.get(j))) // Case 3
				{
					if(!exists(bond_atom2.get(j), bond_atom2.get(i), bond_atom1.get(i)))
					{	angle_atom1.add(bond_atom1.get(i));
						angle_atom2.add(bond_atom2.get(i));
						angle_atom3.add(bond_atom2.get(j));
						count++;
//						System.out.println((bond_atom1.get(i)+1) + " " + (bond_atom2.get(i)+1) + " " + (bond_atom2.get(j)+1));
					}
				}
				else if (bond_atom2.get(i).equals(bond_atom2.get(j))) // Case 4
				{
					if(!exists(bond_atom1.get(j), bond_atom2.get(i), bond_atom1.get(i)))
					{	angle_atom1.add(bond_atom1.get(i));
						angle_atom2.add(bond_atom2.get(i));
						angle_atom3.add(bond_atom1.get(j));
						count++;
//						System.out.println((bond_atom1.get(i)+1) + " " + (bond_atom2.get(i)+1) + " " + (bond_atom1.get(j)+1));
					}
				}
				else if (bond_atom1.get(i).equals(bond_atom2.get(j))) //Case 2
				{
					if(!exists(bond_atom1.get(j), bond_atom1.get(i), bond_atom2.get(i)))
					{	angle_atom1.add(bond_atom2.get(i));
						angle_atom2.add(bond_atom1.get(i));
						angle_atom3.add(bond_atom1.get(j));
						count++;
//						System.out.println((bond_atom2.get(i)+1) + " " + (bond_atom1.get(i)+1) + " " + (bond_atom1.get(j)+1));
					}
				}
			}
			//System.out.println(i);
		}
//		System.out.println("Yes : " + superCount);
		System.out.println("#angle : " + count);
		return count;
	}
	
	public static boolean diexists(int i, int j, int k, int l)
	{
		for (int a = 0; a < dihedral_atom1.size(); a++)
		{
			if (dihedral_atom1.get(a) == l)
			{
				if (dihedral_atom2.get(a) == k)
				{
					if (dihedral_atom3.get(a) == j)
					{
						if (dihedral_atom4.get(a) == i)
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public static int prepareDihedralList()
	{
		int count = 0;
		
		for (int i = 0; i < angle_atom1.size(); i++)
		{
			for (int j = i+1; j < angle_atom1.size(); j++)
			{
				if (angle_atom2.get(i).equals(angle_atom1.get(j)))
				{
					if (angle_atom1.get(i).equals(angle_atom2.get(j)))
					{
						if (!diexists(angle_atom3.get(j), angle_atom2.get(j), angle_atom1.get(j), angle_atom3.get(i)))
						{
							dihedral_atom1.add(angle_atom3.get(i));
							dihedral_atom2.add(angle_atom1.get(j));
							dihedral_atom3.add(angle_atom2.get(j));
							dihedral_atom4.add(angle_atom3.get(j));
							count++;
						}
					}
					
					else if (angle_atom3.get(i).equals(angle_atom2.get(j)))
					{
						if (!diexists(angle_atom3.get(j), angle_atom2.get(j), angle_atom1.get(j), angle_atom1.get(i)))
						{
							dihedral_atom1.add(angle_atom1.get(i));
							dihedral_atom2.add(angle_atom1.get(j));
							dihedral_atom3.add(angle_atom2.get(j));
							dihedral_atom4.add(angle_atom3.get(j));
							count++;
						}
					}
				}
				else if (angle_atom2.get(i).equals(angle_atom3.get(j)))
				{
					if (angle_atom1.get(i).equals(angle_atom2.get(j)))
					{
						if (!diexists(angle_atom1.get(j), angle_atom2.get(j), angle_atom3.get(j), angle_atom3.get(i)))
						{
							dihedral_atom1.add(angle_atom3.get(i));
							dihedral_atom2.add(angle_atom3.get(j));
							dihedral_atom3.add(angle_atom2.get(j));
							dihedral_atom4.add(angle_atom1.get(j));
							count++;
						}
					}
					else if (angle_atom3.get(i).equals(angle_atom2.get(j)))
					{
						if (!diexists(angle_atom1.get(j), angle_atom2.get(j), angle_atom3.get(j), angle_atom1.get(i)))
						{
							dihedral_atom1.add(angle_atom1.get(i));
							dihedral_atom2.add(angle_atom3.get(j));
							dihedral_atom3.add(angle_atom2.get(j));
							dihedral_atom4.add(angle_atom1.get(j));
							count++;
						}
					}
				}
			}
		}
		System.out.println("#dihedral : " + count);
		return count;
	}
	
	
	public static double getBondLengthEnergy()
	{
		
		double energy = 0.0;
		for (int i = 0; i < bond_atom1.size(); i++)
		{
			double dist = getDist(x.get(bond_atom1.get(i)), x.get(bond_atom2.get(i)), y.get(bond_atom1.get(i)), y.get(bond_atom2.get(i)), z.get(bond_atom1.get(i)), z.get(bond_atom2.get(i)));
			double kb = p.getKb(name.get(bond_atom1.get(i)), name.get(bond_atom2.get(i)), resName.get(bond_atom1.get(i)), resName.get(bond_atom2.get(i)));
			double b0 = p.getB0(name.get(bond_atom1.get(i)), name.get(bond_atom2.get(i)), resName.get(bond_atom1.get(i)), resName.get(bond_atom2.get(i)));
			if (dist > 2*b0)
			{	//System.out.println(bond_atom1.get(i) + " " + bond_atom2.get(i) + " " + name.get(bond_atom1.get(i)) + " " + name.get(bond_atom2.get(i)) + " " + b0 + " " + dist + " " + energy);
				//System.out.println(x.get(bond_atom1.get(i)) + " " + x.get(bond_atom2.get(i)));
				//System.out.println(y.get(bond_atom1.get(i)) + " " + y.get(bond_atom2.get(i)));
				//System.out.println(z.get(bond_atom1.get(i)) + " " + z.get(bond_atom2.get(i)));
			}
			energy += kb*(Math.pow(dist - b0, 2));
			//System.out.println(energy);
		}
		return energy;
	}
	
	public static double getBondAngleEnergy()
	{
		double energy = 0.0;
		for (int i = 0; i < angle_atom1.size(); i++)
		{
			energy += p.getKtheta(name.get(angle_atom1.get(i)), name.get(angle_atom2.get(i)), name.get(angle_atom3.get(i)), resName.get(angle_atom1.get(i)), resName.get(angle_atom2.get(i)), resName.get(angle_atom3.get(i))) * Math.pow(getAngle(x.get(angle_atom1.get(i)), x.get(angle_atom2.get(i)), x.get(angle_atom3.get(i)), y.get(angle_atom1.get(i)), y.get(angle_atom2.get(i)),  y.get(angle_atom3.get(i)), z.get(angle_atom1.get(i)), z.get(angle_atom2.get(i)), z.get(angle_atom3.get(i))) - p.getTheta(name.get(angle_atom1.get(i)), name.get(angle_atom2.get(i)), name.get(angle_atom3.get(i)), resName.get(angle_atom1.get(i)), resName.get(angle_atom2.get(i)), resName.get(angle_atom3.get(i))), 2);
		}
		return energy;
	}
	
	public static double getTorsionEnergy()
	{
		double energy = 0;
		for (int i = 0; i < dihedral_atom1.size(); i++)
		{
			energy += p.getKchi(name.get(dihedral_atom1.get(i)), name.get(dihedral_atom2.get(i)), name.get(dihedral_atom3.get(i)), name.get(dihedral_atom4.get(i)), resName.get(dihedral_atom1.get(i)), resName.get(dihedral_atom2.get(i)), resName.get(dihedral_atom3.get(i)), resName.get(dihedral_atom4.get(i))) * (1 + Math.cos(p.getN(name.get(dihedral_atom1.get(i)), name.get(dihedral_atom2.get(i)), name.get(dihedral_atom3.get(i)), name.get(dihedral_atom4.get(i)), resName.get(dihedral_atom1.get(i)), resName.get(dihedral_atom2.get(i)), resName.get(dihedral_atom3.get(i)), resName.get(dihedral_atom4.get(i))) * getPhi(x.get(dihedral_atom1.get(i)), x.get(dihedral_atom2.get(i)), x.get(dihedral_atom3.get(i)), x.get(dihedral_atom4.get(i)), y.get(dihedral_atom1.get(i)), y.get(dihedral_atom2.get(i)), y.get(dihedral_atom3.get(i)), y.get(dihedral_atom4.get(i)), z.get(dihedral_atom1.get(i)), z.get(dihedral_atom2.get(i)), z.get(dihedral_atom3.get(i)), z.get(dihedral_atom4.get(i))) - p.getDelta(name.get(dihedral_atom1.get(i)), name.get(dihedral_atom2.get(i)), name.get(dihedral_atom3.get(i)), name.get(dihedral_atom4.get(i)), resName.get(dihedral_atom1.get(i)), resName.get(dihedral_atom2.get(i)), resName.get(dihedral_atom3.get(i)), resName.get(dihedral_atom4.get(i)))));
		}
		return energy;
	}
	
	public static double getVdwEnergy()
	{
		double energy = 0;
		
		for (int i = 0; i < name.size(); i++)
		{
			
			for (int j = i+1; j < name.size(); j++)
			{
				
					double switchVal = 1, dSwitchVal = 0;
					double dist = getDist(x.get(i), x.get(j), y.get(i), y.get(j), z.get(i), z.get(j));
					double dist_1 = 1/dist;
					double dist_2 = dist_1*dist_1;
					double dist_6 = dist_2*dist_2*dist_2;
					double dist_12 = dist_6*dist_6;
					if (dist > 1.0)
					{
						double c1 = 2;
						double c3 = 4*c1;
						double c2 = 1.5 - dist;
						double c4 = c2*(1.5 + 2*dist - 3*1.0);
						switchVal = c2*c4*c1;
						//dSwitchVal = 
					}
					//System.out.println(element.get(i) + " " + element.get(j));
					double sig2 = p.getSigma(name.get(i), resName.get(i)) + p.getSigma(name.get(j), resName.get(j));
					
					double eps = Math.sqrt(p.getEpsilon(name.get(i), resName.get(i)) * p.getEpsilon(name.get(j), resName.get(j)));
					energy += swit(dist) * 4 * eps *(Math.pow(sig2/dist, 12) - ((Math.pow(sig2/dist, 6))));
				
			}
		}
		
		return energy;
	}
	
 	public static double getElectrostaticEnergy()
	{
		double energy = 0;
		for (int i = 0; i < name.size()-1; i++)
		{
			double kq = coulomb * p.getCharge(name.get(i), resName.get(i));
			for (int j = i+1; j < name.size(); j++)
			{
				//if (element.get(i).startsWith("H") && element.get(j).startsWith("H"))
					//continue;
				double dist = getDist(x.get(i), x.get(j), y.get(i), y.get(j), z.get(i), z.get(j));
				if (dist > 1.5)
					continue;
				double kqq = kq * p.getCharge(name.get(j), resName.get(j));
				double efac = 1.0 - dist/1.5;
				double prefac = kqq * (1.0/dist) * efac;
				energy += prefac * efac;
			}
		}
		return energy;
	}
	
	public ForceFieldCalculator(String aFileName) {
		
		String file = aFileName;
		fileReader(file);
		
		p.readFileTop();
		p.readFileParm();
		
		t.fileReader();
				
		prepareBondList();
		prepareAngleList();
		prepareDihedralList();
		
		/*int count = 0;
		for (int i = 0; i < name.size(); i++)
		{
			if (name.get(i).equals("SG"))
				count++;
		}
		System.out.println(count);
		
		String ini = "";
		count = 0;
		for (int i = 0; i < resSeq.size(); i++)
		{
			if (!ini.equals(resSeq.get(i)))
			{
				ini = resSeq.get(i);
				count++;
			}
		}
		System.out.println(count);*/
		
		/*String r = null;
		int count = 0;
		int cys = 0;
		ArrayList<String> added = new ArrayList<String>();
		for (int i = 0; i < element.size(); i++)
		{
			if (!residue.get(i).equals(r))
			{
				if (added.contains(r))
					continue;
				else
				{	
					//added.add(r);
					r = residue.get(i);
					if (r.equals("CYS"))
						cys++;
					//count += t.res(r);
					System.out.println(r + " " + t.res(r));
				}
			}
		}
		//System.out.println(count + " " + cys);
*/		
		double bondLength = getBondLengthEnergy();
		System.out.println("BondLength - " + bondLength);
		
		double bondAngle = getBondAngleEnergy();
		System.out.println("BondAngle - " + bondAngle);
		
		double torsion = getTorsionEnergy();
		System.out.println("Torsional - " + torsion);
		
		double vdw = getVdwEnergy();
		System.out.println("Van der Waal - " + vdw);
		
		double electrostatic = getElectrostaticEnergy();
		System.out.println("Electrostatic - " + electrostatic);
		
		double energy = bondLength + bondAngle + torsion + vdw + electrostatic;
		System.out.println("Total - " + energy);
		
	}
}