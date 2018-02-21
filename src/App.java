import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;


import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolViewer;
import org.jmol.util.Logger;
import org.openscience.jmol.app.jmolpanel.console.AppConsole;


public class App extends JFrame 
{
	
	private static int maxScreenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight();
	private static int maxScreenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getWidth();
	
	public static void main(String args[])
	{
	    App ab = new App();
		String filePath = "";
		String filePathicon ="";
		//Get Operating System name.
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		
		
		
        try {
            File tempFile = new File("TempFile");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write("TempFile");
            fileWriter.close();

            filePath = tempFile.getAbsolutePath();
            filePath = filePath.substring(0, filePath.indexOf("TempFile"));
            /*System.out.println(filePath);
            System.out.println();*/

            if (osName.toLowerCase().contains("mac")) {
                filePathicon = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/");
                filePath = filePath.concat("History/");
            } else if (osName.toLowerCase().contains("linux")){
                filePathicon = filePath.concat("icons/");
            	filePath = filePath.concat("History/");
                
                System.out.println(filePath);
            }
            tempFile.delete();
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        //Default file display
        //////////////////////////////Problem - DB00316.mol has nothing in file. Fix that/////////////////////////////////
   try{
        String filePath1 = filePath.concat("DB00316.mol");
		 
        //open the file
        FileInputStream inMessage = new FileInputStream(filePath1);
        
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(inMessage);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<html>");
        
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
              System.out.println (strLine);
              sb.append(strLine + "<br>");
              
              
        }
        sb.append("</html>");
        
        filedisplay.setText(sb.toString());
        
      //Close the input stream
        in.close();
        
    }catch (Exception e)
    {
    	
    	//Catch exception if any
        System.err.println("Error: " + e.getMessage());
        
    }
    
    
    try{
      String imagePath = filePath.concat("DB00316.png");
	  System.out.println(imagePath);
	  
	  ImageIcon icon = new ImageIcon(imagePath); 
	  Image resized = icon.getImage();
	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
	  icon.setImage(resized);
	  
	    imagedisplay.setIcon(icon);
	    
          }catch (Exception e)
          {
          	//Catch exception if any
              System.err.println("Error: " + e.getMessage());
              
          } 
		
	}
	
	
	
    public App() 
	{
		initComponents();
	}

    
    
    	
	/**
	 * 
	 */
	public void initComponents() 
	{
		
		
		menubar = new JMenuBar();
		About = new JButton();
		ForceField= new JButton();
		menuHelp  = new JMenu("Help");
		hdoc = new JButton("     User Manual      ");
		softUpdate = new JButton("  Software Update ");
		video = new JButton("    Product Demo    ");
		menuTool  = new JMenu("Tool");
		displayFile = new JButton("           Display File          ");
		button15 = new JButton();
		feedback = new JButton("Product Feedback ");
		tabbedPane1 = new JTabbedPane();
		PubChem = new JToolBar();
		xml = new JButton();
		sdf = new JButton();
		asn1 = new JButton();
		SearchAccessionNo = new JToolBar();
		button4 = new JButton();
		button3 = new JButton();
		button5 = new JButton();
		button6 = new JButton();
		SearchUniprotID = new JToolBar();
		button8 = new JButton();
		button9 = new JButton();
		button12 = new JButton();
		SearchPDB_ID = new JToolBar();
		button7 = new JButton();
		button10 = new JButton();
		button11 = new JButton();
		button14 = new JButton();
		textField1 = new JTextField();
		button13 = new JButton();
		pharmaKB = new JButton();
		filedisplay = new JLabel(); 
		Rfiledisplay = new JLabel();
		imagedisplay = new JLabel();
		displaypane = new JScrollPane(filedisplay);
		relationaldisplay = new JScrollPane(Rfiledisplay);
		scrollPane1 = new JScrollPane();
		internalFrame5 = new JFrame();
		/*kaValue = new JTextField();
		inputKa = new JButton("inputKa");
		kbValue = new JTextField();
		inputKb = new JButton("inputKb");*/
		calculate = new JButton("calculate");
		internalFrame6 = new JInternalFrame();
		pngPanel = new JPanel();
		frame = new JFrame();
		
		
		
		

		
		
		//======== this ========
		//setAlwaysOnTop(true);
		setName("this");
		setTitle("DrugCalc");
		Container contentPane = getContentPane(); 
		
		
		theHandler handler = new theHandler();

		//======== menubar ========
		{
			menubar.setName("menubar");


			

			//---- Help ----
			About.setText("            About            ");
			About.setName("About");
			About.setHorizontalTextPosition(SwingConstants.CENTER);
			hdoc.setHorizontalTextPosition(SwingConstants.CENTER);
			softUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
			video.setHorizontalTextPosition(SwingConstants.CENTER);
			menuHelp.add(hdoc);
			menuHelp.add(softUpdate);
			menuHelp.add(video);
			menuHelp.add(feedback);
			menuHelp.add(About);
			feedback.addActionListener(handler);
	        hdoc.addActionListener(handler);
	        About.addActionListener(handler);
	        softUpdate.addActionListener(handler);
	        video.addActionListener(handler);
	        menubar.add(menuTool);
			menubar.add(menuHelp);
			
			
			
		//---- Tool -------
			
		    //---- Delete History -----
			button15.setText("       Delete History        ");
			button15.setName("Delete History");
			button15.setHorizontalTextPosition(SwingConstants.CENTER);
			button15.addActionListener(handler);
			menuTool.add(button15);

			
			//---- Display File-----
			menuTool.add(displayFile);
			displayFile.addActionListener(handler);
			
			
			//---- ForceField ----
			ForceField.setText("Force Field Calculator ");
			ForceField.setName("Force Field Calculator");
			ForceField.setHorizontalTextPosition(SwingConstants.CENTER);
			ForceField.addActionListener(handler);
			menuTool.add(ForceField);
			
			
		}

		//======== tabbedPane1 ========
		{
			tabbedPane1.setName("tabbedPane1");
			
			//======== PubChem ===============
			{
				PubChem.setName("PubChem");
				PubChem.setToolTipText("Displays Drug from PubChem database using CID in formats selcted by the user from tabbed pane");

				

				//---- SDF ----
				sdf.setText("SDF");
				sdf.setName("sdf");
				sdf.addActionListener(handler);
				PubChem.add(sdf);
				

				//---- XML ----
				xml.setText("XML");
				xml.setName("xml");
				xml.addActionListener(handler);
				PubChem.add(xml);


	 			//---- ASN1 ----
				asn1.setText("ASN1");
				asn1.setName("asn1");
				asn1.addActionListener(handler);
				PubChem.add(asn1);

		
			}
			tabbedPane1.addTab("PubChem", PubChem);
			

			//======== DrugBank ========
			{
				SearchAccessionNo.setName("DrugBank");
				SearchAccessionNo.setToolTipText("Displays Drug from Drugbank database using AccessionNo in formats selcted by the user from tabbed pane");


				//---- button4 ----
				button4.setText("MOL");
				button4.setName("button4");
				button4.addActionListener(handler);
				SearchAccessionNo.add(button4);

				//---- button3 ----
				button3.setText("SDF");
				button3.setName("button3");
				button3.addActionListener(handler);
				SearchAccessionNo.add(button3);

				//---- button5 ----
				button5.setText("SMILES");
				button5.setName("button5");
				button5.addActionListener(handler);
				SearchAccessionNo.add(button5);

	 			//---- button6 ----
				button6.setText("InChi");
				button6.setName("button6");
				button6.addActionListener(handler);
				SearchAccessionNo.add(button6);

		
			}
			tabbedPane1.addTab("DrugBank", SearchAccessionNo);


			//======== SearchUniprotID ========
			{
				SearchUniprotID.setName("Uniprot");
				SearchUniprotID.setToolTipText("Displays Protein from Uniprot database using UniprotID in formats selcted by the user from tabbed pane");
 


				//---- button8 ----
				button8.setText("Fasta");
				button8.setName("button8");
				button8.addActionListener(handler);
				SearchUniprotID.add(button8);

				//---- button9 ----
				button9.setText("XML");
				button9.setName("button9");
				button9.addActionListener(handler);
				SearchUniprotID.add(button9);


				//---- button12 ----
				button12.setText("Text");
				button12.setName("button12");
				button12.addActionListener(handler);
				SearchUniprotID.add(button12);  
			}
			tabbedPane1.addTab("Uniprot", SearchUniprotID);
			
			//======== SearchPDB_ID ========
			{
				SearchPDB_ID.setName("RSCB_PDB");
				SearchPDB_ID.setToolTipText("Displays Protein from SwisProt database using SearchPDB_ID in formats selcted by the user from tabbed pane");
				
				//---- button7 ----
				button7.setText("PDB");
				button7.setName("button7");
				button7.addActionListener(handler);
				SearchPDB_ID.add(button7); 
				
				//---- button10 ----
				button10.setText("FASTA");
				button10.setName("button10");
				button10.addActionListener(handler);
				SearchPDB_ID.add(button10);

				//---- button11 ----
				button11.setText("CIF");
				button11.setName("button11");
				button11.addActionListener(handler);
				SearchPDB_ID.add(button11); 

				//---- button14 ----
				button14.setText("XML");
				button14.setName("button14");
				button14.addActionListener(handler);
				SearchPDB_ID.add(button14); 
				
			}	
			
			tabbedPane1.addTab("RSCB_PDB", SearchPDB_ID);

		}

		//---- textField1 ----
		textField1.setName("textField1");
		textField1.setToolTipText("Enter PubChem ID/Accession No./Uniprot ID/PDB ID");
		textField1.addActionListener(handler);

		//---- button13 ----
		
		button13.setText("Fetch");
		button13.setName("button13");
		button13.addActionListener(handler);
		
		//---- pharmaKB -----------
		
		pharmaKB.setText("viewGene");
		pharmaKB.setName("pharmaKB");
		pharmaKB.addActionListener(handler);
		
		//==========Displaypane=============
		

		
		displaypane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		displaypane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		displaypane.setToolTipText("FILE DISPLAY");
		displaypane.setBorder (new LineBorder(Color.BLACK,2,true));
        displaypane.getViewport().setBackground (Color.WHITE);
        displaypane.setPreferredSize(new Dimension(600, 250));
        

		/*//======== internalFrame1 ========
		{
			internalFrame1.setVisible(true);
			internalFrame1.setName("internalFrame1");
			internalFrame1.setTitle("Result space");
			Container internalFrame1ContentPane = internalFrame1.getContentPane();

			//---- scrollBar2 ----
			scrollBar2.setName("scrollBar2");

			GroupLayout internalFrame1ContentPaneLayout = new GroupLayout(internalFrame1ContentPane);
			internalFrame1ContentPane.setLayout(internalFrame1ContentPaneLayout);
			internalFrame1ContentPaneLayout.setHorizontalGroup(
				internalFrame1ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, internalFrame1ContentPaneLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(filedisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollBar2, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE))
			);
			internalFrame1ContentPaneLayout.setVerticalGroup(
				internalFrame1ContentPaneLayout.createParallelGroup()
				    .addComponent(filedisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollBar2, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
			);
		} */

		
  

        //======== relationaldisplay ========
        
        relationaldisplay.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		relationaldisplay.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		relationaldisplay.setToolTipText("Drugs targeting this protein (or basically, outputs)");
		relationaldisplay.setBorder (new LineBorder(Color.BLACK,2,true));
		relationaldisplay.getViewport().setBackground (Color.WHITE);
		relationaldisplay.setPreferredSize(new Dimension(350, 300));
   
		
        
        
		

		//======== pngPanel ========
		{
			//pngPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//pngPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			pngPanel.setBorder (new LineBorder(Color.BLACK,2,true));
			pngPanel.setBackground (Color.WHITE);
			pngPanel.setName("View structure in 2D");
			pngPanel.setToolTipText("molecular structure in 2D");
			pngPanel.add(imagedisplay);
			pngPanel.setPreferredSize(new Dimension(400, 300));
			pngPanel.setBackground (Color.WHITE);
			

			
		}
		
	/*	//======== internalFrame6 ========
		{
			internalFrame6.setVisible(true);
			internalFrame6.setName("internalFrame6");
			internalFrame6.setTitle("View structure in 2D");
			internalFrame6.getContentPane().add(pngPanel);
			Container internalFrame6ContentPane = internalFrame6.getContentPane();

			GroupLayout internalFrame6ContentPaneLayout = new GroupLayout(internalFrame6ContentPane);
			internalFrame6ContentPane.setLayout(internalFrame6ContentPaneLayout);
			internalFrame6ContentPaneLayout.setHorizontalGroup(
				internalFrame6ContentPaneLayout.createParallelGroup()
					.addGap(0, 400, Short.MAX_VALUE)
			);
			internalFrame6ContentPaneLayout.setVerticalGroup(
				internalFrame6ContentPaneLayout.createParallelGroup()
					.addGap(0, 250, Short.MAX_VALUE)
			);
		} */

		
		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button13, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addComponent(pharmaKB, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(35, 35, 35))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(relationaldisplay)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(pngPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
								.addComponent(displaypane, GroupLayout.Alignment.LEADING))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
								.addComponent(menubar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4, 4, 4))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(menubar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button13)
								.addComponent(pharmaKB))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(displaypane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(relationaldisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pngPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(4, 4, 4)
							.addGroup(contentPaneLayout.createParallelGroup()))
						.addComponent(scrollPane1)))
						
		);
		
		String filePath = "";
		String filePathicon ="";
		//Get Operating System name.
		String osName = System.getProperty("os.name");
		
		
		
        try {
            File tempFile = new File("TempFile");
            FileWriter fileWriter = new FileWriter(tempFile);
            fileWriter.write("TempFile");
            fileWriter.close();

            filePath = tempFile.getAbsolutePath();
            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

            if (osName.toLowerCase().contains("mac")) {
                filePathicon = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/");
                filePathicon = filePathicon.concat("icons/");
                
            } else if (osName.toLowerCase().contains("linux")){
                filePathicon = filePath.concat("icons/");
               
                
                //System.out.println(filePath);
            }
            tempFile.delete();
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Exception occurred.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        
        ImageIcon ImageIcon = new ImageIcon(filePathicon + "logo.jpg");
        Image Image = ImageIcon.getImage();
        this.setIconImage(Image);
        
  
		
		this.setBounds(maxScreenWidth/4,maxScreenHeight/4,maxScreenWidth/2+295,maxScreenHeight/2);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.pack();
		
		setLocationRelativeTo(getOwner());
		// End of component initialization 
	}

	
	private void setVerticalScrollBarPolicy(int verticalScrollbarAsNeeded) 
	{
		// TODO Auto-generated method stub
		
	}


	private JFrame frame;
	private JMenuBar menubar;
	private JButton About;
	private JMenu menuHelp;
	private JMenu menuTool;
	private JButton hdoc;
	private JButton softUpdate;
	private JButton video;
	private JButton about;
	private JButton displayFile;
	private JButton feedback;
	private JButton button15;
	private JButton ForceField;
	private JTabbedPane tabbedPane1;
	private JToolBar PubChem;
	private JButton xml;
	private JButton sdf;
	private JButton asn1;
	private JToolBar SearchAccessionNo;
	private JButton button4;
	private JButton button3;
	private JButton button5;
	private JButton button6;
	private JToolBar SearchUniprotID;
	private JButton button8;
	private JButton button9;
	private JButton button12;
	private JToolBar SearchPDB_ID;
	private JButton button7;
	private JButton button10;
	private JButton button11;
	private JButton button14;
	private JTextField textField1;
	private JButton button13;
	private JButton pharmaKB;
	private static JLabel filedisplay;
	private static JLabel Rfiledisplay;
	private static JLabel imagedisplay;
 	private JScrollPane displaypane;
	private JScrollPane relationaldisplay;
	private JScrollPane scrollPane1;
	private JFrame internalFrame5;
    private JTextField kaValue;
    private JTextField kbValue;
    private JButton inputKa;
    private JButton inputKb;
    private JButton calculate;
	private JInternalFrame internalFrame6;
	private static JPanel pngPanel;
	private static fileHistory fetchedHistory = new fileHistory(50,1);
	private static String fileName;
	private static int flag;
	//  End of variables declaration  
	
	
 	
 	  static class JmolPanel extends JPanel 
	  {
 	
 	    JmolViewer viewer;
 	    
 	    private final Dimension currentSize = new Dimension();
 	    
 	    JmolPanel() 
		{
 	      viewer = JmolViewer.allocateViewer(this, new SmarterJmolAdapter(), 
 	          null, null, null, null, null);
        }
 	
 	    @Override
 	    public void paint(Graphics g) 
		{
 	      getSize(currentSize);
 	      viewer.renderScreenImage(g, currentSize.width, currentSize.height);
 	    }
 	  }
 	  
 
 		
	
	public class theHandler implements ActionListener
	{
		
		
		//Get Operating System name.
		String osName = System.getProperty("os.name");
		
		boolean searchAccession = false;
		boolean searchUniprot = false;
		boolean searchPDB = false;
		boolean searchCID = false;
		String string = "";
		String accessionNo = "";
		String PID = "";
		String PDBid = "";
		String phKB = "";
		String CID = "";
		
		public void makeNewFile(final String aFileName,final String bFileName) 
		{
			  
	    	 //Make a new file.
	    	 File file = new File(aFileName);
	    	 File fileb = new File(bFileName);

	    	 //Declare the reader outside the scope so we can use it
	    	 //in the finally block.
	    	 BufferedReader reader = null;
	    	
	    	 
	    	 


	    	 try {
	    	 	 reader = new BufferedReader(new FileReader(file));
	    	 	 
	    	  	 String line;
	    		 
				 String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/output.txt");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/output.txt");
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
							
							PrintWriter writer = new PrintWriter(filePath, "UTF-8");
							
	    		 
	    	     //Read one line at a time, printing it.
	    	      while ((line = reader.readLine()) != null) {
	    	      	if(line.startsWith("DR   DrugBank; "))
	    				{
	    	      		
	    					String s1=new String(line.substring(15,21));
	    					String s2=new String(line.substring(24));
	    					s2=s2.substring(0,s2.length()-2);
	    					writer.println(s1+" "+s2);
	    					//System.out.println("in makefile");
	    					
	    				}
	    	  		}
	    	      
	    			writer.close();
	    			
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
		
		public void actionPerformed(ActionEvent event)
		{
			

		//-------Tabbedpane event handeling-----------	
			
			if(event.getSource()== button4){
				
				string = String.format(".mol");
				searchAccession = true;
				
			}else if(event.getSource()== button3)
			{
				
				
				string = String.format(".sdf");
				//System.out.println(string);
				searchAccession = true;
				
			}else if(event.getSource()== button5)
			{
				
				string = String.format(".smiles");
				searchAccession = true;
				
			}else if(event.getSource()== button6)
			{
				
				string = String.format(".inchi");
				searchAccession = true;
				
			}else if(event.getSource()== button8)
			{
				
				string = String.format(".fasta");
				searchUniprot = true;
				
			}else if(event.getSource()== button9)
			{
				
				string = String.format(".xml");
				searchUniprot = true;
				
			}else if(event.getSource()== button12){
				
				string = String.format(".txt");
				searchUniprot = true;	
				
			}else if(event.getSource()== button7)
			{
				
				string = String.format(".pdb");
				searchPDB = true;
				
			}else if(event.getSource()== button10)
			{
				
				string = String.format(".fasta");
				searchPDB = true;
				
			}else if(event.getSource()== button11)
			{
				string = String.format(".cif");
				searchPDB = true;
				
			}else if(event.getSource()== button14)
			{
				
				string = String.format(".xml");
				searchPDB = true;
				
				
			}else if(event.getSource()== sdf)
			{
				
				string = String.format(".sdf");
				searchCID = true;
				
				
			}else if(event.getSource()== xml)
			{
				
				string = String.format(".xml");
				searchCID = true;
				
				
			}else if(event.getSource()== asn1)
			{
				
				string = String.format(".asnt");
				searchCID = true;
				
				
			}
			
			
				
			//System.out.println(string);
			Drugdwnld dwnld = new Drugdwnld();
			Proteindwnld prd = new Proteindwnld();
			PDBdwnld pdb = new PDBdwnld();
			PubChemdwnld cid = new PubChemdwnld();
			
			
			
			
		//---------event handling of search---------------
			
			
			if(event.getSource()== button13)
			{
				Rfiledisplay.setText("");
				//ImageIcon iconN = new ImageIcon("../1.jpg");
				//imagedisplay.setIcon(iconN);
				
				if(searchAccession)
				{
					
					accessionNo = String.format("%s", textField1.getText());
					
				     //System.out.println(event.getSource());
					 dwnld.downloadfile(accessionNo, string,(event.getSource() == button13)); 
					 try{
						 
						 String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/");
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
					        
					        filePath = filePath.concat(accessionNo+string);
						 
			                //open the file
			                FileInputStream inMessage = new FileInputStream(filePath);
			                
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html>");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null)   {
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                      
			                      
			                }
			                sb.append("</html>");
			                
			                filedisplay.setText(sb.toString());
			                
			              //Close the input stream
			                in.close();
			                
			            }catch (Exception e)
			            {
			            	
			            	//Catch exception if any
			                System.err.println("Error: " + e.getMessage());
			                
			            }
			            
			      try{
			    	  
			    	  String filePath = "";
						
						
						
				        try {
				            File tempFile = new File("TempFile");
				            FileWriter fileWriter = new FileWriter(tempFile);
				            fileWriter.write("TempFile");
				            fileWriter.close();

				            filePath = tempFile.getAbsolutePath();
				            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

				            if (osName.toLowerCase().contains("mac")) {
				                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/");
				            } else if (osName.toLowerCase().contains("linux")){
				                filePath = filePath.concat("History/");
				                
				                //System.out.println(filePath);
				            }
				            tempFile.delete();
				        } catch (IllegalArgumentException e) {
				            e.printStackTrace(System.out);
				            JOptionPane.showMessageDialog(null, "Exception occurred.",
				                    "Error!", JOptionPane.INFORMATION_MESSAGE);
				        } catch (IOException e) {
				            e.printStackTrace(System.out);
				            JOptionPane.showMessageDialog(null, "Exception occurred.",
				                    "Error!", JOptionPane.INFORMATION_MESSAGE);
				        }
			    	  String imagePath = filePath.concat(accessionNo +".png");
			    	  System.out.println(imagePath);
			    	  
			    	  ImageIcon icon = new ImageIcon(imagePath); 
			    	  Image resized = icon.getImage();
			    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
			    	  icon.setImage(resized);
			    	  
			    	    imagedisplay.setIcon(icon);
			    	    
				            }catch (Exception e)
				            {
				            	//Catch exception if any
				                System.err.println("Error: " + e.getMessage());
				                
				            } 
				            
				            if(string.equalsIgnoreCase(".mol")){ 
				            	
				            	final  String strXyzHOH = "3\n" 
						    	    + "water\n" 
						    	    + "O  0.0 0.0 0.0\n"
						    	    + "H  0.76923955 -0.59357141 0.0\n" 
						    	    + "H -0.76923955 -0.59357141 0.0\n";
						    	
						    	final String strScript = "delay; move 360 0 0 0 0 0 0 0 4;";
						    	
					            JFrame frame = new JFrame("3-D viewer");
					            
					    	    //frame.addWindowListener(new ApplicationCloser());
					    	    frame.setSize(410, 700);
					    	    Container contentPane = frame.getContentPane();
					    	    JmolPanel jmolPanel = new JmolPanel();
					    	    jmolPanel.setPreferredSize(new Dimension(400, 400));
					    	
					    	    // main panel -- Jmol panel on top
					    	
					    	    JPanel panel = new JPanel();
					    	    panel.setLayout(new BorderLayout());
					    	    panel.add(jmolPanel);
					    	    
					    	    // main panel -- console panel on bottom
					    	    
					    	    JPanel panel2 = new JPanel();
					    	    panel2.setLayout(new BorderLayout());
					    	    panel2.setPreferredSize(new Dimension(400, 300));
					    	    AppConsole console = new AppConsole(jmolPanel.viewer, panel2,
					    	        "History State Clear");
					    	    
					    	    // You can use a different JmolStatusListener or JmolCallbackListener interface
					    	    // if you want to, but AppConsole itself should take care of any console-related callbacks
					    	    jmolPanel.viewer.setJmolCallbackListener(console);
					    	    
					    	    panel.add("South", panel2);
					    	    
					    	    contentPane.add(panel);
					    	    frame.setVisible(true);
					    	    
					    	    String filePath = "";
								
								
								
						        try {
						            File tempFile = new File("TempFile");
						            FileWriter fileWriter = new FileWriter(tempFile);
						            fileWriter.write("TempFile");
						            fileWriter.close();

						            filePath = tempFile.getAbsolutePath();
						            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

						            if (osName.toLowerCase().contains("mac")) {
						                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/");
						            } else if (osName.toLowerCase().contains("linux")){
						                filePath = filePath.concat("History/");
						                
						                //System.out.println(filePath);
						            }
						            tempFile.delete();
						        } catch (IllegalArgumentException e) {
						            e.printStackTrace(System.out);
						            JOptionPane.showMessageDialog(null, "Exception occurred.",
						                    "Error!", JOptionPane.INFORMATION_MESSAGE);
						        } catch (IOException e) {
						            e.printStackTrace(System.out);
						            JOptionPane.showMessageDialog(null, "Exception occurred.",
						                    "Error!", JOptionPane.INFORMATION_MESSAGE);
						        }
					    	
						        filePath = filePath.concat( accessionNo + string);
					    	    // sample start-up script
					    	    
					    	    
					    	    String strError = jmolPanel.viewer
					    	        .openFile(filePath);
					    	    
					    	    //viewer.openStringInline(strXyzHOH);
					    	    if (strError == null)
					    	      jmolPanel.viewer.evalString(strScript);
					    	    else
					    	      Logger.error(strError);
					           }
				            
				      searchAccession = false;        
			              
			                    
				}else if(searchUniprot)
				{
					
					Rfiledisplay.setText("");
					PID = String.format("%s", textField1.getText());
					 prd.downloadfile(PID, string,(event.getSource() == button13)); 
					 try{
						 
						   String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/");
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
			                //open the file
			                FileInputStream inMessage = new FileInputStream(filePath+PID+string);
			                
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html>");
			                sb.append("<head>File dispaly</head> ");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null) 
			                {
			                	
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                  
			                }
			                
			                sb.append("</html>");
			                filedisplay.setText(sb.toString());
			                in.close();
			                
			            }catch (Exception e)
			            {
			            	
			            	//Catch exception if any
			                System.err.println("Error: " + e.getMessage());
			                
			            }
			            
			            
			            
			        //======to display drugs whose target protein is PID=======
			        try{    
			        	
			        	    //System.out.println("reached");
                            String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History");
					            } else if (osName.toLowerCase().contains("linux")){
					                
					            	filePath = filePath.concat("History/");
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
					        
					        
						   	this.makeNewFile(filePath+PID+".txt","output.txt");
						   	FileInputStream inMessage = new FileInputStream(filePath+"output.txt");
						   	
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                //Read File Line By Line
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html><body>");
			                sb.append("<h2>Drugs whose target protein is ");
			                sb.append(PID + ".txt</h2><br>");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null) 
			                {
			                	
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                      //System.out.println("reached1");
			                  
			                }
			                
			                sb.append("</body></html>");
			                //System.out.println("reached2");
			                Rfiledisplay.setText(sb.toString());
			                in.close();
			                
			          
					}
					catch(Exception e)
					{
						
						e.printStackTrace();
						
					}
					
					searchUniprot = false;
				}else if(searchPDB)
				{
					
					
					PDBid = String.format("%s", textField1.getText());
					
				     //System.out.println(event.getSource());
					 pdb.downloadfile(PDBid, string,(event.getSource() == button13)); 
					 try{
						  
						    String filePath = "";
			                //open the file
							try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/"+ PDBid + string);
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/"+ PDBid + string);
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
			                FileInputStream inMessage = new FileInputStream(filePath);
			                
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html>");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null) 
			                {
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                      
			                      
			                }
			                sb.append("</html>");
			                filedisplay.setText(sb.toString());
			                
			              //Close the input stream
			                in.close();
			                
			            }catch (Exception e)
			            {
			            	//Catch exception if any
			                System.err.println("Error: " + e.getMessage());
			                
			            }
			            
			      try{
			    	  
			    	                
					  				String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/"+ PDBid +".jpg");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/"+ PDBid +".jpg");
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
			    	  //System.out.println(filePath);
			    	  
			    	  ImageIcon icon = new ImageIcon(filePath); 
			    	  Image resized = icon.getImage();
			    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
			    	  icon.setImage(resized);
			    	  
			    	    imagedisplay.setIcon(icon);
			    	    
				            }catch (Exception e)
				            {
				            	//Catch exception if any
				                System.err.println("Error: " + e.getMessage());
				                
				            }
				           
				            
				        if(!string.equalsIgnoreCase(".fasta") && !string.equalsIgnoreCase(".xml"))
				        {  
				        	
				            final  String strXyzHOH = "3\n" 
					    	    + "water\n" 
					    	    + "O  0.0 0.0 0.0\n"
					    	    + "H  0.76923955 -0.59357141 0.0\n" 
					    	    + "H -0.76923955 -0.59357141 0.0\n";
					    	
					    	final String strScript = "delay; move 360 0 0 0 0 0 0 0 4;";
				            
				            JFrame frame = new JFrame("3-D viewer");
				            
				    	    //frame.addWindowListener(new ApplicationCloser());
				    	    frame.setSize(410, 700);
				    	    Container contentPane = frame.getContentPane();
				    	    JmolPanel jmolPanel = new JmolPanel();
				    	    jmolPanel.setPreferredSize(new Dimension(400, 400));
				    	
				    	    // main panel -- Jmol panel on top
				    	
				    	    JPanel panel = new JPanel();
				    	    panel.setLayout(new BorderLayout());
				    	    panel.add(jmolPanel);
				    	    
				    	    // main panel -- console panel on bottom
				    	    
				    	    JPanel panel2 = new JPanel();
				    	    panel2.setLayout(new BorderLayout());
				    	    panel2.setPreferredSize(new Dimension(400, 300));
				    	    AppConsole console = new AppConsole(jmolPanel.viewer, panel2,
				    	        "History State Clear");
				    	    
				    	    // You can use a different JmolStatusListener or JmolCallbackListener interface
				    	    // if you want to, but AppConsole itself should take care of any console-related callbacks
				    	    jmolPanel.viewer.setJmolCallbackListener(console);
				    	    
				    	    panel.add("South", panel2);
				    	    
				    	    contentPane.add(panel);
				    	    frame.setVisible(true);
				    	
				    	    // sample start-up script
				    	    
				    	    try
			 				{
			 					
			 					File temp1 = new File("check");
			 					FileWriter fr = new FileWriter(temp1);
			 					fr.write("check");
			 					fr.close();
			 					String path = temp1.getAbsolutePath();
			 					//System.out.println(path);
			 					path = path.substring(0,path.indexOf("check"));
			 					if (osName.toLowerCase().contains("mac")) {
					                path = path.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/"+ PDBid + string);
					                String strError = jmolPanel.viewer
					    	        .openFile(path);
					                //viewer.openStringInline(strXyzHOH);
						    	    if (strError == null)
						    	      jmolPanel.viewer.evalString(strScript);
						    	    else
						    	      Logger.error(strError);
						           
					            } else if (osName.toLowerCase().contains("linux")){
					                path = path.concat("History/"+ PDBid + string);
					                String strError = jmolPanel.viewer
					    	        .openFile(path);
					                //viewer.openStringInline(strXyzHOH);
						    	    if (strError == null)
						    	      jmolPanel.viewer.evalString(strScript);
						    	    else
						    	      Logger.error(strError);
						           }
					                //System.out.println(path);
					            
			 					
			 					
			 					temp1.delete();
			 					
			 				}
			 	            catch(IllegalArgumentException e)
			 				{
			 	            	
			 	            	e.printStackTrace(System.out);
			 	            	JOptionPane.showMessageDialog(null,"Exception occurred :( ");
			 	            	
			 				}
			 	            catch(IOException e)
			 				{
			 	            	
			 	            	e.printStackTrace(System.out);
			 	            	JOptionPane.showMessageDialog(null,"Exception occurred :( ");
			 	            	
			 				}
				    	    
				    	    
				    	  
				        
				    	    searchPDB = false;
				    	  
				    	
				    	 
				    	
				
				        }
				}else if(searchCID)
				{
					Rfiledisplay.setText("");
					CID = String.format("%s", textField1.getText());
					 cid.downloadfile(CID, string,(event.getSource() == button13)); 
					 try{
						 
						   String filePath = "";
							
							
							
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("History/");
					                
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
			                //open the file
			                FileInputStream inMessage = new FileInputStream(filePath+CID+string);
			                
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html>");
			                sb.append("<head>File dispaly</head> ");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null) 
			                {
			                	
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                  
			                }
			                
			                sb.append("</html>");
			                filedisplay.setText(sb.toString());
			                in.close();
			                
			            }catch (Exception e)
			            {
			            	
			            	//Catch exception if any
			                System.err.println("Error: " + e.getMessage());
			                
			            }
			            
			            
			            try{
					    	  
	    	                
			  				String filePath = "";
					
					
					
			        try {
			            File tempFile = new File("TempFile");
			            FileWriter fileWriter = new FileWriter(tempFile);
			            fileWriter.write("TempFile");
			            fileWriter.close();

			            filePath = tempFile.getAbsolutePath();
			            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

			            if (osName.toLowerCase().contains("mac")) {
			                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/History/"+ CID +".jpg");
			            } else if (osName.toLowerCase().contains("linux")){
			                filePath = filePath.concat("History/"+ CID +".jpg");
			                
			                //System.out.println(filePath);
			            }
			            tempFile.delete();
			        } catch (IllegalArgumentException e) {
			            e.printStackTrace(System.out);
			            JOptionPane.showMessageDialog(null, "Exception occurred.",
			                    "Error!", JOptionPane.INFORMATION_MESSAGE);
			        } catch (IOException e) {
			            e.printStackTrace(System.out);
			            JOptionPane.showMessageDialog(null, "Exception occurred.",
			                    "Error!", JOptionPane.INFORMATION_MESSAGE);
			        }
	    	  //System.out.println(filePath);
	    	  
	    	  ImageIcon icon = new ImageIcon(filePath); 
	    	  Image resized = icon.getImage();
	    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
	    	  icon.setImage(resized);
	    	  
	    	    imagedisplay.setIcon(icon);
	    	    
		            }catch (Exception e)
		            {
		            	//Catch exception if any
		                System.err.println("Error: " + e.getMessage());
		                
		            }
			            
				}

			}	
			if(event.getSource() == pharmaKB)
			{
					
					phKB = String.format("%s", textField1.getText());
					
					Desktop desktop = Desktop.getDesktop();
					try
					{
						URI updateLink = new URI("http://www.pharmgkb.org/gene/" + phKB);
						desktop.browse(updateLink);
					}
					catch (URISyntaxException e1)
					{
						e1.printStackTrace(System.out);
						JOptionPane.showMessageDialog(null,"Exception occurred :( ");
					}
					catch (IOException e2)
					{
						e2.printStackTrace(System.out);
						JOptionPane.showMessageDialog(null,"Exception occurred :( ");
					}
					
					
			}else if(event.getSource()== hdoc)
			{
					
					Desktop desktop = Desktop.getDesktop();
	 				try
	 				{
	 					
	 					File temp1 = new File("check");
	 					FileWriter fr = new FileWriter(temp1);
	 					fr.write("check");
	 					fr.close();
	 					String path = temp1.getAbsolutePath();
	 					//System.out.println(path);
	 					path = path.substring(0,path.indexOf("check"));
	 					if (osName.toLowerCase().contains("mac")) {
			                path = path.concat("DrugCalc.app/Contents/Resources/DrugCalc/Help/User Manual.pdf");
			            } else if (osName.toLowerCase().contains("linux")){
			                path = path.concat("Help/UserManual.pdf");
			              
			                //System.out.println(path);
			            }
	 					
	 					
	 					desktop.open(new File(path));
	 					temp1.delete();
	 					
	 				}
	 	            catch(IllegalArgumentException e)
	 				{
	 	            	
	 	            	e.printStackTrace(System.out);
	 	            	JOptionPane.showMessageDialog(null,"Exception occurred :( ");
	 	            	
	 				}
	 	            catch(IOException e)
	 				{
	 	            	
	 	            	e.printStackTrace(System.out);
	 	            	JOptionPane.showMessageDialog(null,"Exception occurred :( ");
	 	            	
	 				}
	 			
					
			}else if(event.getSource()== button15)
			{	
					
					File fc=new File("ok");
					String path = fc.getAbsolutePath();
					//System.out.println(path);
					path = path.substring(0,path.indexOf("ok"));
					if (osName.toLowerCase().contains("mac")) {
		                path = path.concat("DrugCalc.app/Contents/Resources/DrugCalc/History");
		            } else if (osName.toLowerCase().contains("linux")){
		                
					     path = path.concat("History/");
					     //System.out.println(path);
		            }
					File f1 = new File(path);
					fc.delete();
					boolean d =false;
					if (f1.isDirectory())
					{
						
					 File[] flist = f1.listFiles();
					//FileWriter fr = new FileWriter(flist);
					//fr.close();
					
					 for (int i = 0; i < flist.length; i++)
						{
							
						    //System.out.println("1");
							boolean success =flist[i].delete();
							d = true;
							
							if (!success)
							{
					        	d = false;
							}
						}
					
					
					
					if(d)
					{
						
						JOptionPane.showMessageDialog(null,"History cleared successfully.");
						
					}
					else
					{
						
						JOptionPane.showMessageDialog(null,"History not cleared.Some file in History Folder is being accessed by another application. Close that file and try again.");
						
					}
					
					f1.mkdir();
			            
					}
			}else if(event.getSource()== video)
			{
					
					Desktop desktop = Desktop.getDesktop();
					try
					{
						
						URI updateLink = new URI("file:///C:/Users/hritik%20bhardwaj/Desktop/code/download.htm");
						desktop.browse(updateLink);
						
					}
					catch (URISyntaxException e1)
					{
						
						e1.printStackTrace(System.out);
						JOptionPane.showMessageDialog(null,"Exception occurred :( ");
						
					}
					catch (IOException e2)
					{
						
						e2.printStackTrace(System.out);
						JOptionPane.showMessageDialog(null,"Exception occurred :( ");
						
					}
			}else if(event.getSource()== About)
			{
					
					//System.out.println("about");
					JDialog dialog = new JDialog(null,"About DrugCalc",JDialog.ModalityType.DOCUMENT_MODAL);
					JButton credits = new JButton("Credits");
					JButton homepage = new JButton("Homepage");
					credits.setBorder(new LineBorder(Color.BLACK,2,false));
					credits.addActionListener (new ActionListener()
		        	{
						
						public void actionPerformed(ActionEvent ex)
						{
							
							Desktop desktop = Desktop.getDesktop();
							
							String filePath = "";
							
							
							
							// Get the credits file from the Help folder.
					        try {
					            File tempFile = new File("TempFile");
					            FileWriter fileWriter = new FileWriter(tempFile);
					            fileWriter.write("TempFile");
					            fileWriter.close();

					            filePath = tempFile.getAbsolutePath();
					            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

					            if (osName.toLowerCase().contains("mac")) {
					                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/Help/Credits.pdf");
					            } else if (osName.toLowerCase().contains("linux")){
					                filePath = filePath.concat("Help/Credits.pdf");
					                desktop.open(new File(filePath));
					                //System.out.println(filePath);
					            }
					            tempFile.delete();
					        } catch (IllegalArgumentException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } catch (IOException e) {
					            e.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        }
		                	
						}
					});

					homepage.addActionListener (new ActionListener()
		        	{
						
		        		public void actionPerformed(ActionEvent ex)
						{
		        			
							Desktop desktop = Desktop.getDesktop();
							try
							{
								
								URI updateLink = new URI("file:///C:/Users/hritik%20bhardwaj/Desktop/code/frontpage.htm");
								desktop.browse(updateLink);
								
							}
							catch (URISyntaxException e)
							{
								
								e.printStackTrace(System.out);
								JOptionPane.showMessageDialog(null,"Exception occurred :( ");
								
							}
							catch (IOException e)
							{
								
								e.printStackTrace(System.out);
								JOptionPane.showMessageDialog(null,"Exception occurred :( ");
								
							}
						}
		        	});

					String version = "<html><center><h1>DrugCalc</h1> <br/> Version : 1.0<br/>"+
						"Copyright \u00a9 2013 Manish K. Gupta </center></html>";
					
					String filePath = "";
					
					
					
					// Get the LOGO from the icons folder.
			        try {
			            File tempFile = new File("TempFile");
			            FileWriter fileWriter = new FileWriter(tempFile);
			            fileWriter.write("TempFile");
			            fileWriter.close();

			            filePath = tempFile.getAbsolutePath();
			            filePath = filePath.substring(0, filePath.indexOf("TempFile"));

			            if (osName.toLowerCase().contains("mac")) {
			                filePath = filePath.concat("DrugCalc.app/Contents/Resources/DrugCalc/calc/logo.jpg");
			            } else if (osName.toLowerCase().contains("linux")){
			                filePath = filePath.concat("icons/logo.jpg");
			                //System.out.println(filePath);
			            }
			            tempFile.delete();
			        } catch (IllegalArgumentException e) {
			            e.printStackTrace(System.out);
			            JOptionPane.showMessageDialog(null, "Exception occurred.",
			                    "Error!", JOptionPane.INFORMATION_MESSAGE);
			        } catch (IOException e) {
			            e.printStackTrace(System.out);
			            JOptionPane.showMessageDialog(null, "Exception occurred.",
			                    "Error!", JOptionPane.INFORMATION_MESSAGE);
			        }
					JLabel label1 = new JLabel(version,new ImageIcon(filePath),JLabel.CENTER);
                     
					label1.setBorder(new BevelBorder(BevelBorder.RAISED));
					label1.setOpaque(true);
					label1.setBackground (Color.WHITE);

					JPanel jp = new JPanel(new GridLayout(1,2));

					credits.setMaximumSize(new Dimension(45,60));
					credits.setMinimumSize(new Dimension(45,60));
					credits.setBackground(Color.white);
					credits.setBorder(new BevelBorder(BevelBorder.RAISED));

					homepage.setMaximumSize(new Dimension(45,60));
					homepage.setMinimumSize(new Dimension(45,60));
					homepage.setBackground(Color.white);
					homepage.setBorder(new BevelBorder(BevelBorder.RAISED));

					jp.add(credits);
					jp.add(homepage);
					
					int maxScreenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight();
					int maxScreenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getWidth();

					dialog.add(jp, BorderLayout.SOUTH);
					dialog.add(label1,BorderLayout.CENTER);
		            dialog.setBounds(maxScreenWidth/3,maxScreenHeight/3,400,200);
		            dialog.setResizable(false);
					dialog.setVisible(true);
					
			}else if(event.getSource()== ForceField)
			{
					
				//======== internalFrame5 ========
				
					internalFrame5.setVisible(true);
					internalFrame5.setName("internalFrame5");
					internalFrame5.setTitle("Force Feild Calculator");
					Container internalFrame5ContentPane = internalFrame5.getContentPane();
					
					
					JPanel panel0 = new JPanel();
			        JPanel panel1 = new JPanel();
			        
			        panel0.setLayout(new GridLayout(2, 2));
			        /*panel0.add(kaValue);
			        panel0.add(inputKa);
			        //inputKa.addActionListener(handler);
			        panel0.add(kbValue);
			        panel0.add(inputKb);*/
			        //inputKb.addActionListener(handler);
			        
			        
			        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
			        panel1.add(calculate);
			        
			        calculate.addActionListener (new ActionListener()
		        	{
						
						public void actionPerformed(ActionEvent ex)
						{
							if( !PDBid.matches("") && string.matches(".pdb"))
							  { 
								   
								/*double ka = Double.parseDouble(kaValue.getText());
								double kb = Double.parseDouble(kaValue.getText());*/
								String path = "History/"+ PDBid + string;
								//String top = "utilities/topology.txt";
								//String parm = "utilities/parameter.txt";
								
								ForceFieldCalculator ffc = new ForceFieldCalculator(path);
								double bondLength = ffc.getBondLengthEnergy();
								Rfiledisplay.setText("BondLength - " + bondLength);
								
								double bondAngle = ffc.getBondAngleEnergy();
								Rfiledisplay.setText("BondAngle - " + bondAngle);
								
								double torsion = ffc.getTorsionEnergy();
								Rfiledisplay.setText("Torsional - " + torsion);
								
								double vdw = ffc.getVdwEnergy();
								Rfiledisplay.setText("Van der Waal - " + vdw);
								
								double electrostatic = ffc.getElectrostaticEnergy();
								Rfiledisplay.setText("Electrostatic - " + electrostatic);
								
								double energy = bondLength + bondAngle + torsion + vdw + electrostatic;
								Rfiledisplay.setText("Total - " + energy);
								
								String s = "Potential Energy between Bonded Atom is " + energy +" Kcal/mol";
								Rfiledisplay.setText(s);
								
								
							   
							  }
						}
					});
			        
			        internalFrame5ContentPane.add(panel0, BorderLayout.NORTH);
			        internalFrame5ContentPane.add(panel1, BorderLayout.SOUTH);
			        internalFrame5.pack();
				    
			}else if(event.getSource() == displayFile)
			{
				
				fetchedHistory.updateFileList();
				

				final JButton okButton=new JButton("OK");
				final JButton cancelButton=new JButton("Cancel");
				final JDialog dialog=new JDialog();
				dialog.setTitle("Display fetched sequence from History");
				final JPanel panel1=new JPanel();
				JLabel label=new JLabel("Select File for displaying:");
				
				final JComboBox fetchedfiles;

				
				fetchedfiles = new JComboBox(fetchedHistory.getArray());
				


				fetchedfiles.addActionListener (new ActionListener()
				{
	            	public void actionPerformed(ActionEvent e)
	                {
						JComboBox temp = (JComboBox) e.getSource ();
	                    flag = temp.getSelectedIndex();
	                    System.out.println(fileName);
					}
				});

	            okButton.addActionListener (new ActionListener()
				{
	            	public void actionPerformed(ActionEvent e)
	                {
	                	
 
	     			      try{  
	     			    	  
	     			    	  filedisplay.setText("");
	     			    	  Rfiledisplay.setText("");
	     			    	  imagedisplay.setText("");
	     			    	  if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".png")||fetchedHistory.getElement(flag).getAbsolutePath().contains(".jpg"))
	     			    	  {
	     			    		 
	     			    		  String filePath = fetchedHistory.getElement(flag).getAbsolutePath();
	     			    		  ImageIcon icon = new ImageIcon(filePath); 
	     				    	  Image resized = icon.getImage();
	     				    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
	     				    	  icon.setImage(resized);
	     				    	  
	     				    	    imagedisplay.setIcon(icon);
	     			    	  }else
	     			    	  {
	     			        FileInputStream inMessage = new FileInputStream(fetchedHistory.getElement(flag));
			                
			                // Get the object of DataInputStream
			                DataInputStream in = new DataInputStream(inMessage);
			                BufferedReader br = new BufferedReader(new InputStreamReader(in));
			                String strLine;
			                
			                StringBuilder sb = new StringBuilder();
			                
			                sb.append("<html>");
			                
			                //Read File Line By Line
			                while ((strLine = br.readLine()) != null) 
			                {
			                      // Print the content on the console
			                      //System.out.println (strLine);
			                      sb.append(strLine + "<br>");
			                      
			                      
			                }
			                sb.append("</html>");
			                filedisplay.setText(sb.toString());
			                
			                if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".mol"))
			                {
			                
			                 try{	
			                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
				                path = path.substring(0,path.indexOf("mol"));
				                path = path.concat("png");
				                
				                ImageIcon icon = new ImageIcon(path); 
						    	  Image resized = icon.getImage();
						    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
						    	  icon.setImage(resized);
						    	  
						    	  
						    	    imagedisplay.setIcon(icon);

					        } catch (IllegalArgumentException el) {
					            el.printStackTrace(System.out);
					            JOptionPane.showMessageDialog(null, "Exception occurred.",
					                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					        } 
			    	  
			    	
			                }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".sdf"))
			                {
				                
				                 try{	
				                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
					                path = path.substring(0,path.indexOf("sdf"));
					                path = path.concat("png");
					                
					                ImageIcon icon = new ImageIcon(path); 
							    	  Image resized = icon.getImage();
							    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
							    	  icon.setImage(resized);
							   
							    	    imagedisplay.setIcon(icon);

						        } catch (IllegalArgumentException el) {
						            el.printStackTrace(System.out);
						            JOptionPane.showMessageDialog(null, "Exception occurred.",
						                    "Error!", JOptionPane.INFORMATION_MESSAGE);
						        }  
			                
	     			    	 
			              //Close the input stream
			                in.close();
	     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".smiles"))
				                {
					                
					                 try{	
					                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
						                path = path.substring(0,path.indexOf("smiles"));
						                path = path.concat("png");
						                
						                ImageIcon icon = new ImageIcon(path); 
								    	  Image resized = icon.getImage();
								    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
								    	  icon.setImage(resized);
								   
								    	    imagedisplay.setIcon(icon);

							        } catch (IllegalArgumentException el) {
							            el.printStackTrace(System.out);
							            JOptionPane.showMessageDialog(null, "Exception occurred.",
							                    "Error!", JOptionPane.INFORMATION_MESSAGE);
							        }  
				                
		     			    	 
				              //Close the input stream
				                in.close();
		     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".inchi"))
				                {
					                
					                 try{	
					                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
						                path = path.substring(0,path.indexOf("inchi"));
						                path = path.concat("png");
						                
						                ImageIcon icon = new ImageIcon(path); 
								    	  Image resized = icon.getImage();
								    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
								    	  icon.setImage(resized);
								   
								    	    imagedisplay.setIcon(icon);

							        } catch (IllegalArgumentException el) {
							            el.printStackTrace(System.out);
							            JOptionPane.showMessageDialog(null, "Exception occurred.",
							                    "Error!", JOptionPane.INFORMATION_MESSAGE);
							        }  
				                
		     			    	 
				              //Close the input stream
				                in.close();
		     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".pdb"))
					                {
						                
						                 try{	
						                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
							                path = path.substring(0,path.indexOf("pdb"));
							                path = path.concat("jpg");
							                
							                ImageIcon icon = new ImageIcon(path); 
									    	  Image resized = icon.getImage();
									    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
									    	  icon.setImage(resized);
									   
									    	    imagedisplay.setIcon(icon);

								        } catch (IllegalArgumentException el) {
								            el.printStackTrace(System.out);
								            JOptionPane.showMessageDialog(null, "Exception occurred.",
								                    "Error!", JOptionPane.INFORMATION_MESSAGE);
								        }  
					                
			     			    	 
					              //Close the input stream
					                in.close();
			     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".fasta"))
						                {
							                
							                 try{	
							                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
								                path = path.substring(0,path.indexOf("fasta"));
								                path = path.concat("jpg");
								                
								                ImageIcon icon = new ImageIcon(path); 
										    	  Image resized = icon.getImage();
										    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
										    	  icon.setImage(resized);
										   
										    	    imagedisplay.setIcon(icon);

									        } catch (IllegalArgumentException el) {
									            el.printStackTrace(System.out);
									            JOptionPane.showMessageDialog(null, "Exception occurred.",
									                    "Error!", JOptionPane.INFORMATION_MESSAGE);
									        }  
						                
				     			    	 
						              //Close the input stream
						                in.close();
				     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".cif"))
							                {
								                
								                 try{	
								                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
									                path = path.substring(0,path.indexOf("cif"));
									                path = path.concat("jpg");
									                
									                ImageIcon icon = new ImageIcon(path); 
											    	  Image resized = icon.getImage();
											    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
											    	  icon.setImage(resized);
											   
											    	    imagedisplay.setIcon(icon);

										        } catch (IllegalArgumentException el) {
										            el.printStackTrace(System.out);
										            JOptionPane.showMessageDialog(null, "Exception occurred.",
										                    "Error!", JOptionPane.INFORMATION_MESSAGE);
										        }  
							                
					     			    	 
							              //Close the input stream
							                in.close();
					     			    	  }else if(fetchedHistory.getElement(flag).getAbsolutePath().contains(".xml"))
								                {
									                
									                 try{	
									                	String path = fetchedHistory.getElement(flag).getAbsolutePath();
										                path = path.substring(0,path.indexOf("xml"));
										                path = path.concat("jpg");
										                
										                ImageIcon icon = new ImageIcon(path); 
												    	  Image resized = icon.getImage();
												    	  resized = resized.getScaledInstance(pngPanel.getWidth(), pngPanel.getHeight(), java.awt.Image.SCALE_SMOOTH);
												    	  icon.setImage(resized);
												   
												    	    imagedisplay.setIcon(icon);

											        } catch (IllegalArgumentException el) {
											            el.printStackTrace(System.out);
											            JOptionPane.showMessageDialog(null, "Exception occurred.",
											                    "Error!", JOptionPane.INFORMATION_MESSAGE);
											        }  
								                
						     			    	 
								              //Close the input stream
								                in.close();
						     			    	  }
	     			      
	                }
				}catch(OutOfMemoryError ev)
				{
					
					Runtime rt = Runtime.getRuntime();
					//rt.gc();
					ev.printStackTrace(System.out);
					//JOptionPane.showMessageDialog(app,"Heap Memory exhausted. Current operation will be aborted :(\n"+
						//"If software seems to be hung after pressing OK, minize & maximize :)");
					dialog.dispose();

				}catch(Exception el)
				{
					el.printStackTrace(System.out);
				}
				}
	            	
				
		       });

				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						dialog.dispose();
					}
				});

				panel1.setLayout (new GridLayout(2,2));
	            panel1.add(label);
	            panel1.add(fetchedfiles);
	            panel1.setBackground (Color.WHITE);
	            //panel1.setBorder (new EtchedBorder(EtchedBorder.RAISED));
	            panel1.add(okButton);
	            panel1.add(cancelButton);
	            dialog.getContentPane ().add(panel1);
	            dialog.setBounds(maxScreenWidth/3,maxScreenHeight/3,400,100);
	            dialog.setDefaultCloseOperation (JDialog.DISPOSE_ON_CLOSE);
	            dialog.setAlwaysOnTop(true);
	            dialog.setVisible(true);
			}else if(event.getSource() == feedback)
			{
				Desktop desktop = Desktop.getDesktop();
				try
				{
					
					URI updateLink = new URI("https://docs.google.com/forms/d/1YGu_I9z7Z56oAP1enGByBahqs-ItHbLqnBwCoJouOro/viewform");
					desktop.browse(updateLink);
					
				}
				catch (URISyntaxException e)
				{
					
					e.printStackTrace(System.out);
					JOptionPane.showMessageDialog(null,"Exception occurred :( ");
					
				}
				catch (IOException e)
				{
					
					e.printStackTrace(System.out);
					JOptionPane.showMessageDialog(null,"Exception occurred :( ");
					
				}
			}
			
			   
	 
	    }

	
    }
   
   
}
