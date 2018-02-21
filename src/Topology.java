import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Topology {

	private static String txt = "utilities/topology.txt";
	private static File file = new File(txt);
	private static BufferedReader reader = null;
	private static String name = null;
	ArrayList<Residue> residue = new ArrayList<Residue>();
	ArrayList<String> temp_atoms = new ArrayList<String>();
	ArrayList<String> temp_atom_types = new ArrayList<String>();
	ArrayList<String> temp_bond_atom1 = new ArrayList<String>();
	ArrayList<String> temp_bond_atom2 = new ArrayList<String>();
	ArrayList<Double> temp_charge = new ArrayList<Double>();
	
	ArrayList<String> bond1 = new ArrayList<String>();
	ArrayList<String> bond2 = new ArrayList<String>();
	ArrayList<String> r = new ArrayList<String>();
	
	
	public void fileReader()
	{
		try {
		 	 reader = new BufferedReader(new FileReader(file));
		  	 String line;
		  	 while ((line = reader.readLine()) != null)
		  	 {
		  		 if (line.startsWith("!"))
		  			 continue;
		  		 if (line.toLowerCase().startsWith("end"))
		  		 {
		  			 if (name!=null)
		  			 residue.add(new Residue(name, temp_atoms, temp_atom_types, temp_charge, temp_bond_atom1, temp_bond_atom2));
		  			 name = null;
		  			 temp_atoms.clear();
		  			 temp_atom_types.clear();
		  			 temp_bond_atom1.clear();
		  			 temp_bond_atom2.clear();
		  			 temp_charge.clear();
		  		 }
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
		  			name = data[1];
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
		  				 data[idx++] = ss;
		  				 if (idx == 4 || ss.startsWith("!"))
		  					 break;
		  			 }
		  			 temp_atoms.add(data[1]);
		  			 temp_atom_types.add(data[2]);
		  			 temp_charge.add(Double.parseDouble(data[3]));
		  		 }
		  		 if (line.toUpperCase().startsWith("BOND") || line.toUpperCase().startsWith("DOUBLE"))
		  		 {
		  			 String[] tmp = line.split(" ");
		  			 ArrayList<String> data = new ArrayList<String>();
		  			 for (String ss : tmp)
		  			 {
		  				 if (ss.length() == 0)
		  					 continue;
		  				 else if (ss.startsWith("!"))
		  					 break;
		  				 data.add(ss);
		  			 }
		  			 //System.out.println(data);
		  			 for (int i = 1; i < data.size(); i+=2)
		  			 {
		  				 temp_bond_atom1.add(data.get(i));
		  			 }
		  			 for (int i = 2; i < data.size(); i+=2)
		  			 {
		  				 temp_bond_atom2.add(data.get(i));
		  			 }
		  			 data.clear();
		  		 }
		  	 }
		  		   	 
		} catch (IOException e) 
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
	
	public int getBondCount(String resi)
	{
		int bcount = 0;
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).name.equals(resi))
				bcount = residue.get(i).bond_atom1.size();
		}
		
		return bcount;
	}
	
	public int getResidueCount()
	{
		return residue.size();
	}
	
	public int res(String r)
	{
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).name.equals(r))
			{
				return residue.get(i).bond_atom1.size();
			}
		}
		return 0;
	}
	
	//residue bonds copy. edit below
	
	public ArrayList<String> rBond1(String res)
	{
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).equals(res))
			{
				return residue.get(i).bond_atom1;
			}
		}
		return null;
	}
	
	public ArrayList<String> rBond2(String res)
	{
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).equals(res))
			{
				return residue.get(i).bond_atom2;
			}
		}
		return null;
	}
	
	public int resSize(String res)
	{
		int minus = 0;
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).name.equals(res))
			{
				for (String ss : residue.get(i).atom)
				{
					if (ss.startsWith("S"))
						minus = 1;
				}
				return residue.get(i).atom.size() - minus;
			}
		}
		return 0;
	}
	
	public boolean isBond(String atom1, String atom2, String res)
	{
		for (int i = 0; i < residue.size(); i++)
		{
			if (residue.get(i).name.equals(res))
			{
				for (int j = 0; j < residue.get(i).bond_atom1.size(); j++)
				{
					if (residue.get(i).bond_atom1.get(j).equals(atom1) && residue.get(i).bond_atom2.get(j).equals(atom2))
						return true;
					else if (residue.get(i).bond_atom1.get(j).equals(atom2) && residue.get(i).bond_atom2.get(j).equals(atom1))
						return true;
				}
			}
		}
		
		return false;
	}
}