
package gaudrophone.ServiceTechnique;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Toolbox {
    public String readFile(String filename, String initialText)
    {
        String strText=initialText;
        String filePath = new File("").getAbsolutePath();
        try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath.concat(filename)),"ISO-8859-1"))) 
        {

            String strCurrentLine;

            while ((strCurrentLine = bufferReader.readLine()) != null) 
            {
                   strText+=strCurrentLine + "\r\n";
            }

        } catch (IOException e) 
        {
                e.printStackTrace();
        }
        return strText;
    }

}
