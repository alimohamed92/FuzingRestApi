package org.esir.AliMax.FuzingRestApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String readTemplate(){
		String res ="";
		try {
		       BufferedReader br = new BufferedReader(new FileReader("tamplate.html"));
		       String thisLine;
			while ((thisLine = br.readLine()) != null) { 
		         res+=thisLine+"\n";
		       } 
		     } 
		     catch (IOException e) {
		       System.err.println("Error: " + e);
		     }
		return res;
	}
	public static void generatehtml(String rap,String name)
	{   
		String str =readTemplate();
		System.out.println( "Hello World!" );
		File f = new File (name);
		try
		{
			FileWriter fw = new FileWriter (f);
			fw.write (str+"\n"+rap);
			fw.write ("</tbody></table></div</div></section></body></html>");
			fw.close();
		}
		catch (IOException exception)
		{
			System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}
}
