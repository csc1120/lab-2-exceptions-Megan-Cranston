/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Megan Cranston
 * Last Updated: 9/12/2024
 */
package cranstonm;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class Driver
 */
public class Driver {
    /**
     *
     */
    public final int minDice = 2;
    public final int maxDice = 10;
    public static void main(String[] args) {
        /**
        Get input from the user regarding how many dice will be rolled,
         how many sides the dice will have, and how many times the dice will be rolled.
        Store the results of the user input in an array
        Create the dice described in the user input
        Roll all the dice and store the result as many times as the user has requested
        Find the result that appeared most frequently during all the rolls
        Print the results of the experiment
        **/
        int[] userInput = getInput(); // numDice, numSides, numTimes rolled
        createDie(userInput[0], userInput[1]);

    }

    private static int[] getInput() {
        System.out.print("""
                Please enter the number of dice to roll, how many sides the dice have,
                and how many rolls to complete, separating the values by a space.
                Example: "2 6 1000"
                
                Enter configuration:
                """);
        int[] intInput = new int[3];
        int recieved = 0;
        try (Scanner read = new Scanner(System.in)) {
            String text = read.nextLine();
            String[] strInput = text.split(" ");
            for (int i = 0; i < 3; i++) {
                if (strInput[i] == null) {
                    throw new IllegalArgumentException();
                } else {
                    intInput[i] = Integer.parseInt(strInput[i]);
                    recieved++;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: Expected 3 values but only received " + recieved);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: All values must be whole numbers.");
        }
        return intInput;
    }

    private static Die[] createDie(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private int[] rollDice(Die[] dice, int numSides, int numRolls) {
        /**
         * This method will roll all the dice, total up the values,
         * and add to that value's total.
         * It will do this as many times as the user specifies.
         */
    }
    private int findMax(int[] rolls) {
        /**
         * This method will take the array that
         * contains the rolling statistics
         * and find and return the largest count.
         * Which value has the largest count is not
         * important here, just what the count is.
         */
    }
    private void report(int numDice, int[] rolls, int max) {
        /**
         * This method will print the results as a
         * horizontal bar chart using asterisks.
         * See below for more details
         */
    }
}