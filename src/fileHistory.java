import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class fileHistory
{
 	public fileHistory(int size,int flag)
    {
		maxSize = size;
        fileFlag = flag;
		updateFileList();
	}

	/*******************************************************************************
	 *	This method uses the flag provided in the constuctor and calls the
	 *	appropriate update file list method.
	*******************************************************************************/

	protected void updateFileList()
    {
		if (fileFlag == 1)
        {
			updateFetchedFileList();
		}
    }


	/*******************************************************************************
	 *	This method refresh the internal array of files in the fetched
	 *	files' directory, sort them according to time created or modified
	 *	and then add latest allowable number of files in the array
	 *******************************************************************************/

    protected void updateFetchedFileList()
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
		history = new ArrayList<File>();
    	if (dir.listFiles () != null )
        {

			File[] files = dir.listFiles();

            Pair[] pairs = new Pair[files.length];
			for (int i = 0; i < files.length; i++)
            {
            	//System.out.println(files[i].getName());
				pairs[i] = new Pair(files[i]);
			}

			// Sort them by timestamp.
            Arrays.sort(pairs);

			/*******************************************************************************
			 *	Loop variable initializes with total number of files at location,
			 *	decrements by one at every iteration till value 0 is hit and
			 *	adds the file in such way that the most recent files are at the
			 *	top of the array
			 *******************************************************************************/

			for (int i = files.length - 1; i >= 0; i--)
			{
            	// Take the sorted pairs and extract only the file part, discarding the timestamp.
                files[i] = pairs[i].f;
				addElement (files[i],files.length - 1 - i);
			}
		}
	}

	

	/*******************************************************************************
	 *	This methid can change the maximum allowable number of files in
	 *	history. This count is applicable for encoded and transformed file list
	 *	as well.
	 *******************************************************************************/

	protected void changeSize(int a)
	{
    	maxSize = a;
    	for(int i=maxSize;i<history.size();i++)
    	{
    		history.get(i).delete();
    	}
        updateFileList ();
	}

	/*******************************************************************************
	 *	This method adds file  f in the history at given index if size of the history
	 *	doesnot exceed threshold.
	 *******************************************************************************/

	private  void addElement(File f,int index)
    {
		if (index < maxSize )
        {
			if ( history.size () < maxSize )
            {
				// simply add an element at the end of the array
                if (index < history.size ())
				{
                	history.remove (index);
				}
                history.add(index,f);
			}
            else
			{
            	// remove a first inserted element, shift entire array by one position then add the new element at the end
                history.remove(0);
				if (index < history.size ())
                {
                    history.remove (index);
				}
                history.add(index,f);
			}
		}
	}

	/*******************************************************************************
	 *	This method prints the current content of the array that is list of
	 *	files that exist in the history.
	 *******************************************************************************/

	protected void printArray()
	{
    	for(int i=0;i<history.size ();i++)
        {
			System.out.println(history.get(i).getName());
		}
	}

	/*******************************************************************************
	 *	This method returns all the elements of the array that is list of files
	 *	in a new array of strings.
	 *******************************************************************************/

	protected String[] getArray()
	{
    	if (history.size() != 0)
        {
			String[] list = new String[history.size ()];

			for(int j=0;j<history.size ();j++)
            {
				File f = history.get(j);
                list[j] = f.getName ();
			}
            return list;
		}
        else
		{
        	String[] list = new String[1];
            list[0] = "List is empty";
			return list;
		}
	}

	/*******************************************************************************
	 *	This method returns current size of the history
	*******************************************************************************/

    protected int getSize()
	{
    	return this.history.size();
	}

	/*******************************************************************************
	 *	This method returns maximum size allowed in the history
	 *******************************************************************************/

    protected int getMaxSize()
	{
    	return this.maxSize;
	}

	/*******************************************************************************
	 *	This method returns element of the array that is a file located at the
	 *	index provided as parameter.
	 *******************************************************************************/

	protected File getElement(int index)
	{
		if (index<maxSize)
        {
			File f = history.get(index);
            return f;
		}
        else
		{
        	return null;
		}
	}



	/*******************************************************************************
	 *	This is an inner class that sorts the file list according to
	 *	date added or modified.
	 *******************************************************************************/

    class Pair implements Comparable
	{
    	public Pair(File file)
        {
			f = file;
            t = file.lastModified ();
		}

        public int compareTo(Object o)
		{
        	long u = ((Pair) o).t;

			/*******************************************************************************
			 *	This condition compares t with u and returns -1
			 *	if t<u 0 if both are equal and 1 other wise.
			 *******************************************************************************/

             return t < u ? -1 : t == u ? 0 : 1;
		}

        public long t;
		public File f;
	}

    private int fileFlag;
	private int maxSize;
    private ArrayList<File> history = new ArrayList<File>();
    
}
	