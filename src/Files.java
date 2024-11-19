import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**@class Files
 * @description Class contains file reading functions
 */
public class Files {
    
    /**@description contains path to directory where textfiles are */
    private static String Directory = "scripts/";

    /**
     * @description function reads textfiles
     * @param file name of file as String
     * @param start String of word where function starts to read
     * @param end String of word where function will stop reading
     * @return text as string that was read from file
     */
    public static String ReadFile(String file, String start, String end){
        StringBuilder dataToReturn = new StringBuilder(); //create Stringbuilder
        
        try(BufferedReader reader = new BufferedReader(new FileReader(Directory + file))) {
            String line;
            boolean Reading = false; //not writing to dataToReturn

            //If file contains "Datapad", it will be read instantly
            if(file.contains("Datapad") || file.contains("Console")) {
                while((line = reader.readLine()) != null){
                    dataToReturn.append(line).append("\n");
                }
            }
            else{
                while((line = reader.readLine()) != null){
                    //if not writing and line has String start, function will write to dataToReturn
                    if(!Reading && line.equals(start)){
                        Reading = true;
                    }
                    //if writing and line has String end, function will stop writing to dataToReturn
                    if(Reading && line.equals(end)){
                        Reading = false;
                    }
                    if (Reading) {
                        //if line has String start, replace that line with empty space
                        if(line.equals(start)){
                            line = line.replace(start, " ");
                        }
                         //if line has String end, replace that line with empty space
                        else if(line.equals(end)){
                            line = line.replace(start, " ");
                        }
                        dataToReturn.append(line).append("\n"); //write lines to dataToReturn without overwriting
                    }
                }  
            }    
            return dataToReturn.toString(); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
