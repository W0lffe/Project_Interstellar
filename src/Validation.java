import java.util.Scanner;

/******Class contains error handling functions******/
class Validation{

    /*Function is called from different part of code, this makes sure that value returned is INT*/

    public static String UserInput(Scanner inputScanner){
        
        String userInput;

        while (true) {
            try {
                System.out.print("\nAction: ");
                userInput = inputScanner.nextLine();
                
                if (userInput.matches("A|B|C|D|a|b|c|d")) {
                    break;
                }
                else{
                    System.out.println("Unknown action.");
                }

            } catch (Exception e) {
                System.out.println("\nUnknown action.");
            }
        }
        return userInput.toUpperCase();
    }



    public static int UserINput(Scanner myScanner){
        int userInput;
    
        while (true) {
            try {
                System.out.print("\nChoice: ");
                userInput = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("\nUnknown input format! Enter time again!");
            }
        }
        return userInput;
    }
}
