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
     * final int minDice is 2
     */
    public static final int MIN_DICE = 2;
    /**
     * final int maxDice is 10
     */
    public static final int MAX_DICE = 10;

    public static void main(String[] args) {
        int[] userInput = getInput();
        Die[] userDice = createDie(userInput[0], userInput[1]);
        int[] rollingDice = rollDice(userDice, userInput[1], userInput[2]);
        int largest = findMax(rollingDice);
        report(userInput[0], rollingDice, largest);
    }

    private static int[] getInput() {
        int[] intInput = new int[3];
        System.out.print("""
                Please enter the number of dice to roll, how many sides the dice have,
                and how many rolls to complete, separating the values by a space.
                Example: "2 6 1000"
                
                Enter configuration:""");
        try (Scanner read = new Scanner(System.in)) {
            // read values into String array separated by " "
            String text = read.nextLine();
            String[] strInput = text.split(" ");
            // check for 3 arguments, turn string to int
            for (int i = 0; i < 3; i++) {
                if (strInput[i] == null) {
                    // not 3 arguments, throw exception
                    throw new IllegalArgumentException("Invalid input: " +
                            "Expected 3 values but only received " + i);
                } else {
                    // 3 arguments, convert string to int
                    intInput[i] = Integer.parseInt(strInput[i]);
                }
            }
            // check if dice value is valid
            if (intInput[0] < MIN_DICE || intInput[0] > MAX_DICE) {
                throw new IllegalArgumentException("Bad die creation: " +
                        "Illegal number of dice: " + intInput[0]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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

    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] value = new int[numSides*dice.length - (dice.length-1)];
        int total = 0;
        for (int i = 0; i < numRolls; i++) {
            for (Die die : dice) {
                total += die.getCurrentValue();
            }
            value[total-dice.length]++;
            total = 0;
        }
        return value;
    }

    private static int findMax(int[] rolls) {
        int longest = rolls[0];
        for (int i = 0; i < rolls.length-1; i++) {
            int roll = rolls[i];
            if (roll > longest) {
                longest = roll;
            }
        }
        return longest;
    }

    private static void report(int numDice, int[] rolls, int max) {
        final int scale = max/10;
        int numStars;
        for (int i = 0; i < rolls.length; i++) {
            System.out.printf("%-2s:%-9s", numDice+i, rolls[i]);
            numStars = rolls[i] / scale;
            for (int j = 0; j < numStars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}