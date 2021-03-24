package br.com.javaparaweb.medprice.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.faces.context.FacesContext;

public class LoggerUtil {
	public static void escreveLog(String mensagem) throws IOException {
		String caminho = System.getProperty("user.dir");
		File file = new File(caminho + "\\log_medprice.txt");
		file.createNewFile();
		FileWriter fw = null;
		Date date;
		
		try {	
			date = new Date();
			date.getTime();
            fw = new FileWriter(file, true);
            fw.write(date + ": " + mensagem + "\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
        finally {
            // close the streams using close method
            try {
                if (fw != null) {
                	fw.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
 
        }
   
    }
}