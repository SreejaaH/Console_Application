package ExceptionHandling;  
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the total marks");
        String totalmark = scanner.nextLine();
        System.out.println("Enter the total students");
        String totalstudent = scanner.nextLine();
        
        int totalMark = Integer.parseInt(totalmark);
        
        try {
            int totalStudent = Integer.parseInt(totalstudent);

            // Check for division by zero
            if (totalStudent == 0) {
                throw new ArithmeticException("Total students cannot be zero");
            }

            int average = totalMark / totalStudent;
            System.out.println("Average mark is: " + average);
        } catch (ArithmeticException exception) {
            System.out.println(exception);
        } catch (NumberFormatException exception) {
            System.out.println(exception);
        } finally {
            System.out.println("\nProgram ended successfully");
        }

        scanner.close();
    }
}
