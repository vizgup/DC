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

public class Proteindwnld
{

 	
    public void downloadfile(String key,String t, boolean flag2)
	{
		
        PID = key;
        type = t;
		boolean flag = true;

        try
		{
        	
        	/*
        	 *	URL for fetching being generated 
        	 *  Exception might occur here(Malformed URLs)
			 */
        	
            NewUrl = new URL(defaultUrl+key+type);
            TextUrl = new URL(defaultUrl+key+".txt");
	        

            /*
             *	file being fetched from the generated URL
             *	IOException might occur here
			 */

            fetchText(TextUrl, createTextFile());
			fetch(NewUrl,createFile (type));
			
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
					
					JOptionPane.showMessageDialog (null,"PID is incorrect OR  server is not responding !");
					
				}

		}

		if (flag)
	    {
				if (validatePID())
	            {
	            	if(flag2)
	            	{
	            	//Add complete filepath in future for below line
					JOptionPane.showMessageDialog(null,"File downloaded successfully and saved as "+File.getName());
	            	

	            	}
				}
	            else
				{
					if(flag2)
					{
						
						JOptionPane.showMessageDialog (null,"PID is incorrect !");
						
					}
					
					
				}
				
            
			}
		
		}
	
    
    

	// This method create file with the given PID number

	private File createFile(String s)
    {
		
		
		String filePath = "";
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
		
		String fname = PID;
		fname = fname.concat (s);
		File filename = new File(filePath+fname);
		return filename;
	}
	
	
	
	// this method create file to be write the list of related drug to the protein
	
	private File createTextFile()
    {
		
		String filePath = "";
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
		
		String fname = PID;
		fname = fname.concat (".txt");
		File filename = new File(filePath+fname);
		return filename;
	}

	
	
	//	This method download file from the internet based on the PID.
	 

     private void fetch(URL u,File f) throws FileNotFoundException,IOException
     {


    	// fetching data from internet by creating input stream 
 		// creating output stream for storing fetched data in a file
    	 
		BufferedInputStream inputS = null;
        FileOutputStream foutS = null;

		File = f;

		try
        {
			inputS = new BufferedInputStream(u.openStream());
            foutS = new FileOutputStream(File);
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
     
     
     
     
     
     // 	This method download file from the internet based on the PID.
     
     private void fetchText(URL u,File f) throws FileNotFoundException,IOException
     {


    	// fetching data from internet by creating input stream 
 		// creating output stream for storing fetched data in a file
    	 
		BufferedInputStream inputS = null;
        FileOutputStream foutS = null;

		File = f;

		try
        {
			
			inputS = new BufferedInputStream(u.openStream());
            foutS = new FileOutputStream(File);
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


     
	
     private boolean validatePID()
     {
		String content = null;
        try
		{
        	
        	FileInputStream inputS = new FileInputStream(File);
            DataInputStream data = new DataInputStream(inputS);
			BufferedReader  buffer = new BufferedReader(new InputStreamReader(inputS));

            buffer.readLine ();           // ignore the first empty line in the file
			content = buffer.readLine ();
            inputS.close ();
            
		}
        catch(FileNotFoundException e)
		{
        	
            e.printStackTrace (System.out);
            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
			return false;
			
		}
        catch(IOException e)
		{
        	
            e.printStackTrace (System.out);
            JOptionPane.showMessageDialog(null,"Exception occurred :( ");
			return false;
			
		}
        if ( content.compareTo("Nothing has been found") == 0 )
		{
        	
        	return false;
        	
		}
		else
        {
			
			return true;
			
		}
	}

    private String PID;
    private String type;
	private String defaultUrl = "http://www.uniprot.org/uniprot/";
	private URL NewUrl;
	private URL TextUrl;
	private File File;
    
}


