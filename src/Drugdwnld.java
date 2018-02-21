import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

public class Drugdwnld
{

 	
    public void downloadfile(String key,String t, boolean flag2)
	{
		
        accession = key;
        type = t;
		boolean flag = true;

        try
		{

  
        	/*
        	 *	URL for fetching being generated 
        	 *  Exception might occur here(Malformed URLs)
			 */
        	
            NewUrl = new URL(defaultUrl+key+type);
            PNGUrl = new URL(PNGdefaultUrl+key+"/image.png");
	        

            /*
             *	file and image being fetched from the generated URL
             *	IOException might occur here
			 */

			fetchImage(PNGUrl,createFile (".png"));
			fetchFile(NewUrl,createFile (type));
			
			//System.out.println("1");
			
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
					
					
					JOptionPane.showMessageDialog (null,"Accession Number is incorrect OR  server is not responding !");
					
				
				}

		}

		if (flag)
	    {
			
			//System.out.println("2");
			
			
				if (validateAccessionNumber())
	            {
					
					//System.out.println("3");
					
	            	if(flag2)
	            	{
	            		
	            		
	            		//System.out.println("3.5");

					JOptionPane.showMessageDialog(null,"File downloaded successfully and saved as "+file.getName()); //Add complete filepath in future
	            	

	            	}
				}
	            else
				{
	            	
					if(flag2)
					{
						
						
						JOptionPane.showMessageDialog (null,"Accession Number is incorrect !");
					
					}
					
					
				}
				
            
			}
		}
	

    
    
    
    
	// This method create file with the given accession number

	private File createFile(String s)
    {
		
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
		
		//System.out.println("creating file");
		String fname = accession;
		fname = fname.concat (s);
		
		File filename = new File(filePath+fname);
		return filename;
	}

	
	
	
	
	//	This method download file from the internet based on the accession number.
	 

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

     
     
     
     
 	//	This method download image from the internet based on the accession number.
     
     private void fetchImage(URL u,File f) throws FileNotFoundException,IOException
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

     
     
     
     
    //validating Accession no. 
	
     private boolean validateAccessionNumber()
     {
    	 //System.out.println("4");
		String content = null;
        try
		{
        	
        	//System.out.println("5");
        	FileInputStream inputS = new FileInputStream(file);
            DataInputStream dataS = new DataInputStream(inputS);
			BufferedReader  buffer = new BufferedReader(new InputStreamReader(inputS));

            buffer.readLine ();           // ignore the first empty line in the file
			content = buffer.readLine ();
            inputS.close ();
            //System.out.println("6");
            
		}
        catch(FileNotFoundException e)
		{
        	
        	//System.out.println("false1");
            e.printStackTrace (System.out);
            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
            
			return false;
			
		}
        catch(IOException e)
		{
        	
        	//System.out.println("false2");
            e.printStackTrace (System.out);
            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
            
			return false;
		}
       
        return true;
	}

     
     
     
    private String accession;
    private String type;
	private String defaultUrl = "http://www.drugbank.ca/drugs/";
	private String PNGdefaultUrl = "http://structures.wishartlab.com/molecules/";
	private URL NewUrl;
	private URL PNGUrl;
	private File file;
    
}


