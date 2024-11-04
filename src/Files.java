import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Files {
    
    private static String Directory = "scripts/";
    public static String ReadFile(String file, String start, String end){
        StringBuilder dataToReturn = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(Directory + file))) {
            String line;
            boolean Reading = false;
            if(file.contains("Datapad")) {
                while((line = reader.readLine()) != null){
                    dataToReturn.append(line).append("\n");
                }
            }
            else{
                while((line = reader.readLine()) != null){
                    if(!Reading && line.contains(start)){
                        Reading = true;
                    }
                    if(Reading && line.contains(end)){
                        Reading = false;
                    }
                    if (Reading) {
                        dataToReturn.append(line).append("\n");
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
