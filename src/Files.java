import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Files {
    
    private static String Directory = "scripts/";
    public static String ReadFile(String file, Scanner fileReader){
        StringBuilder dataToReturn = new StringBuilder();
        try {
            File fileToRead = new File(Directory + file);
            fileReader = new Scanner(fileToRead);
            while(fileReader.hasNextLine()){
                dataToReturn.append(fileReader.nextLine()).append("\n");
            }
            fileReader.close();
            return dataToReturn.toString();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error occured reading file: " + e);
            e.printStackTrace();
            return null;
        }

    }
}
