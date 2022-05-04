/*
 * Isaak Nijakowski
 * CS1181-06L
 * Practice 4
 * 2/10/2022
 */

import java.util.InputMismatchException;
import java.util.Scanner;

class Driver {

    public static int fibonacci(int n) throws InvalidArgumentException {
        //Initializes variables to calculate the fibonacci sequence
        int x = 1;
        int y = 1;
        int z = 0;

        try {
            if (n < 0) {
                throw new InvalidArgumentException();
            } else {
                //Calculates fibonacci sequence (and throws exception if negative number)
                //i starts at 2 because the first two numbers are already calculated and are equal to 1
                for (int i = 2; i < n; i++) {
                    z = x + y;
                    x = y;
                    y = z;
                }
            }
        } catch (InvalidArgumentException e) {
            //Standard error for negative numbers
            System.err.printf("Error: %d is not a positive number", n);
        }
        //Check if n is 1 or 2 and set z to 1 as they are the first numbers of the fibonacci sequence
        //I didnt make it include 0 because i really dont know what to do in case of 0, so it will return 0 in that scenario
        z = (n < 3 && n != 0) ? 1 : z;
        return z;
    }
    public static void main(String[] args) throws InvalidArgumentException {
        Scanner read = new Scanner(System.in);
        int response = 0;

        System.out.printf("Enter the disred Fibonacci number n: ");
        try {
            response = read.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Error: you must enter a number");
        }

        read.close();
        System.out.println(fibonacci(response));
    }
}