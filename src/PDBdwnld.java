import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class PDBdwnld {
	 public void downloadfile(String s,String t, boolean flag2)
		{
						
	        PDBid = s;
	        type = t;
			boolean flag = true;

	        try
			{
	        	/*
	        	 *	URL for fetching being generated 
	        	 *  Exception might occur here(Malformed URLs)
				 */
	        	
	        	////JPGUrl = new URL(JPGdefaultUrl+PDBid.toLowerCase()+"_bio_r_500.jpg");
	        	
	        	//fetching file from  URL
	        	////fetchImage(JPGUrl,createFile (".jpg"));
	        	
	        	if(type.equalsIgnoreCase(".fasta")){
	        		
	        		FastaUrl = new URL(fastaUrl+PDBid.toUpperCase()+"&compressionType=uncompressed");
	        		
	        		//fetching file from  URL
	        		fetchFile(FastaUrl,createFile (type));
	        		
	        		System.out.println("in fasta");
	        		
	        	}else{
	        		
	        		NewUrl = new URL(defaultUrl+PDBid.toUpperCase()+type);
	        		
	        		//fetching file from  URL
	        		fetchFile(NewUrl,createFile (type));
	        		
	        	}
	            
	            
		        

	            /*	
	             *	IOException might occur here
				 */

				
				System.out.println("1");
			}
	        catch(MalformedURLException e)
			{
	        	e.printStackTrace (System.out);
	            flag = false;
	            
	            if(flag2)
	            {
	            	
	            	JOptionPane.showMessageDialog(null,"Exception occurred :( ");
	            
	            }
			}
	     
		    catch (IOException e)
			{
		            e.printStackTrace (System.out);
					flag = false;
					
					if(flag2)
					{
						
						JOptionPane.showMessageDialog (null,"PDBid Number is incorrect OR  server is not responding !");
					
					}

			}

			if (flag)
		    {
				
				System.out.println("2");
				
					if (validatePDBidNumber())
		            {
						
						System.out.println("3");
						
		            	if(flag2)
		            	{
		            		
		            		System.out.println("3.5");

						JOptionPane.showMessageDialog(null,"File downloaded successfully and saved as "+file.getName()); //Add complete filepath in future
		            	

		            	}
					}
		            else
					{
						if(flag2)
						{
							
							JOptionPane.showMessageDialog (null,"PDBid Number is incorrect !");
						
						}
						
						
					}
					
	            
				}
			}
		
	 
	 
	 
	 
	 

		// This method create file with the given PDBid number

		private File createFile(String s)
	    {
			
			System.out.println("creating file");
			
			//Get Operating System name.
			String osName = System.getProperty("os.name");
			
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
			
			File dir = new File(filePath);
			if(!dir.isDirectory())
			{
				
				dir.mkdir();
				
			}
			
			String fname = PDBid;
			fname = fname.concat (s);
			File filename = new File(filePath+fname);
			return filename;
			
		}

		
		
		
		
		
		//	This method download file from the internet based on the PDBid number.
		 

	     private void fetchFile(URL u,File f) throws FileNotFoundException,IOException
	     {
	        
	    	// fetching data from internet by creating input stream 
			// creating output stream for storing fetched data in a file
	    	 
			BufferedInputStream inputS = null;
	        FileOutputStream foutS = null;

			file = f;

			try
	        {
				inputS = new BufferedInputStream(u.openStream());
	            foutS = new FileOutputStream(file);
				byte data[] = new byte[1024];
	            int count;
	            
				while ((count = inputS.read(data, 0, 1024)) != -1)
	            {
					
					foutS.write(data, 0, count);
					
				}
			}
			finally
	        {
				if (inputS != null)
	            {
					
					inputS.close();
					
				}
	            if (foutS != null)
				{
	            	
	            	foutS.close();
	            	
				}
			}
		}
	     
	     
	    
	     
	     
	     

	     private void fetchImage(URL u,File f) throws FileNotFoundException,IOException
	     {
	    	
	        // fetching data from internet by creating input stream 
			// creating output stream for storing fetched data in a file
	    	 
			BufferedInputStream inputS = null;
	        FileOutputStream foutS = null;
	        
	        System.out.println("image1");

			file = f;

			try
	        {
				inputS = new BufferedInputStream(u.openStream());
	            foutS = new FileOutputStream(file);
				byte data[] = new byte[1024];
	            int count;
	            
				while ((count = inputS.read(data, 0, 1024)) != -1)
	            {
					foutS.write(data, 0, count);
				}
				
				System.out.println("image2");
				
			}
			finally
	        {
				if (inputS != null)
	            {
					inputS.close();
				}
	            if (foutS != null)
				{
	            	foutS.close();
				}
	            
	            System.out.println("image3");
	            
			}
		}

	     
		
	     
	     
	     
	     //validating PID
	     
	     private boolean validatePDBidNumber()
	     {
	    	 
	    	 System.out.println("4");
	    	 
			 String content = null;
			 
	        try
			{
	        	System.out.println("5");
	        	
	        	FileInputStream inputS = new FileInputStream(file);
	            DataInputStream data = new DataInputStream(inputS);
				BufferedReader  buffer = new BufferedReader(new InputStreamReader(inputS));

	            buffer.readLine ();           // first line is comment line so ignoring it
	            
				content = buffer.readLine ();
	            inputS.close ();
	            System.out.println("6");
			}
	        catch(FileNotFoundException e)
			{
	        	
	        	System.out.println("false1");
	        	
	            e.printStackTrace (System.out);
	            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
	            
				return false;
				
			}
	        catch(IOException e)
			{
	        	
	        	System.out.println("false2");
	        	
	            e.printStackTrace (System.out);
	            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
	            
				return false;
				
			}
	       
	        return true;
	        
		}

	    //image location
	    //https://cdn.rcsb.org/images/rutgers/gr/3gre/3gre.pdb1-500.jpg
	     //https://cdn.rcsb.org/images/rutgerswhich /ub/1ubq/1ubq.pdb1-500.jpg
	    	 
	    	//Use RESTful Web Services of RCSB 
	     
	    private String PDBid;
	    private String type;
		private String defaultUrl = "http://files.rcsb.org/view/"; //old - "http://www.rcsb.org/pdb/files/";
		private String fastaUrl = "http://www.rcsb.org/pdb/download/viewFastaFiles.do?structureIdList=";//old - "http://www.rcsb.org/pdb/files/fasta.txt?structureIdList=";
		//change below
		private String JPGdefaultUrl = "https://cdn.rcsb.org/images/rutgers/gr/3gre/3gre.pdb1-500.jpg";//old - "http://www.rcsb.org/pdb/images/";
		private URL NewUrl;
		private URL FastaUrl;
		private URL JPGUrl;
		private File file;
		
}
