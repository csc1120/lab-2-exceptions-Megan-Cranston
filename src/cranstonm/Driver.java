/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Megan Cranston
 * Last Updated: 9/12/2024
 */
package cranstonm;

import java.util.Scanner;

/**
 * Class Driver
 */
public class Driver {

    public static void main(String[] args) {
        int[] userInput = getInput();
        Die[] userDice = createDie(userInput[0], userInput[1]);
        int[] rollingDice = rollDice(userDice, userInput[1], userInput[2]);
        int largest = findMax(rollingDice);
        report(userInput[0], rollingDice, largest);
    }

    private static int[] getInput() {
        boolean validate = false;
        int[] intInput = new int[3];
        do {
            System.out.print("""
                    Please enter the number of dice to roll, how many sides the dice have,
                    and how many rolls to complete, separating the values by a space.
                    Example: "2 6 1000"
                    
                    Enter configuration:""");
            Scanner read = new Scanner(System.in);
            try {
                // read values into String array separated by " "
                String text = read.nextLine();
                String[] strInput = text.split(" ");
                // check if 3 values were entered
                if (strInput.length != 3) {
                    throw new IllegalArgumentException("Invalid input: " +
                            "Expected 3 values but only received " + strInput.length);
                }
                // turn string to int
                for (int i = 0; i < 3; i++) {
                    intInput[i] = Integer.parseInt(strInput[i]);
                }
                final int minDice = 2;
                final int maxDice = 10;
                if (intInput[0] < minDice || intInput[0] > maxDice) {
                    throw new IllegalArgumentException("Bad die creation: " +
                            "Illegal number of dice: " + intInput[0]);
                }
                // no errors thrown, data has been validated
                validate = true;
            } catch (NumberFormatException e){
                System.err.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            read.reset();
        } while (!validate);
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