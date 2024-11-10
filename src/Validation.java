import java.util.Scanner;

class Validation{

    //Check user inputs, this function is run from game menus
    public static String UserInput(Scanner inputScanner){
        
        String userInput;

        while (true) {
            try {
                System.out.print("\nAction: ");
                userInput = inputScanner.nextLine();
                //userInput Has to match set characters
                if (userInput.matches("A|B|C|D|E|a|b|c|d|e")) {
                    break;
                }
                else{
                    break;
                }

            } catch (Exception e) {
                System.out.println("\nError has occured!" + e);
            }
        }
        return userInput.toUpperCase(); //return userInput as uppercase
    }


    //Check user inputs, this function is run from game menus
    public static int UserINput(Scanner myScanner){
        int userInput = -1;
    
        while (true) {
            try {
                System.out.print("\nChoice: ");
                userInput = Integer.parseInt(myScanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("I dont have that item.");
                break;
            }
        }
        return userInput;
    }
}
