import java.util.ArrayList;

public class Residue
{
	String name;
	public ArrayList<String> atom;
	public ArrayList<String> atomtype;
	public ArrayList<String> bond_atom1 = new ArrayList<String>();
	public ArrayList<String> bond_atom2 = new ArrayList<String>();
	public ArrayList<Double> charge_atom = new ArrayList<Double>();
	
	Residue(String na, ArrayList<String> atoms, ArrayList<String> types, ArrayList<Double> charges, ArrayList<String> atom1, ArrayList<String> atom2)
	{
		atom = new ArrayList<String>();
		atomtype = new ArrayList<String>();
		bond_atom1 = new ArrayList<String>();
		bond_atom2 = new ArrayList<String>();
		charge_atom = new ArrayList<Double>();
		
		
		name = na;
		for (String ss : atoms)
		{
			atom.add(ss);
		}
		for (String ss :types)
			atomtype.add(ss);
		for (Double dd : charges)
			charge_atom.add(dd);
		for (String ss : atom1)
			bond_atom1.add(ss);
		for (String ss : atom2)
			bond_atom2.add(ss);
	}
	
}
