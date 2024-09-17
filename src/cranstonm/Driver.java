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
        // Int array: i[0]numDice, i[1]numSides, i[2]numTimes
        int[] userInput = getInput();
        int numDice = userInput[0];
        int numSides = userInput[1];
        int numTimes = userInput[2];
        // Die array length numDice: Die(numSides)
        Die[] userDice = createDie(numDice, numSides);
        // Rolls the dice, sums their value, repeat for numRolls, return array of rolled values
        int[] rollingDice = rollDice(userDice, numSides, numTimes);
        // largest value rolled in set of values
        int largest = findMax(rollingDice);
        // print out results of Dice Rolls
        report(userInput[0], rollingDice, largest);

    }

    private static int[] getInput() {
        System.out.print("""
                Please enter the number of dice to roll, how many sides the dice have,
                and how many rolls to complete, separating the values by a space.
                Example: "2 6 1000"
                
                Enter configuration:""");
        int[] intInput = new int[3];
        int received = 0;
        try (Scanner read = new Scanner(System.in)) {
            String text = read.nextLine();
            String[] strInput = text.split(" ");
            for (int i = 0; i < 3; i++) {
                if (strInput[i] == null) {
                    throw new IllegalArgumentException();
                } else {
                    intInput[i] = Integer.parseInt(strInput[i]);
                    received++;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: Expected 3 values but only received " + received);
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
        int numStars = 0;
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