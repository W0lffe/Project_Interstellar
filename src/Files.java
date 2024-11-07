import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Files {
    
    //Directory where the files are
    private static String Directory = "scripts/";

    //Function reads files, function gets file name, string where to start and where to end as parameters
    public static String ReadFile(String file, String start, String end){
        StringBuilder dataToReturn = new StringBuilder(); //create Stringbuilder
        
        try(BufferedReader reader = new BufferedReader(new FileReader(Directory + file))) {
            String line;
            boolean Reading = false; //not writing to dataToReturn

            //If file contains "Datapad", it will be read instantly
            if(file.contains("Datapad")) {
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
