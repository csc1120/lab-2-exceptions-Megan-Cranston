/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Megan Cranston
 * Last Updated: 9/17/2024
 */
package cranstonm;

import java.util.Random;

/**
 * Class Die
 */
public class Die {
    private int currentValue;
    private int numSides;
    private final Random rand = new Random();

    /**
     * Die Constructor
     * @param numSides number of sides on dice
     */
    public Die(int numSides) {
        try {
            final int maxSides = 10;
            final int minSides = 2;
            if (minSides > numSides || numSides > maxSides) {
                throw new IllegalArgumentException();
            }
            this.numSides = numSides;
        } catch (IllegalArgumentException e) {
            System.out.println("Bad die creation: Illegal number of sides: " + numSides);
            System.err.println(e.getMessage());
        }
    }

    /**
     * getCurrentValue
     * @return tempVal which is the last value of the dice
     */
    public int getCurrentValue() {
        roll();
        int tempVal = 0;
        try {
            if (currentValue > numSides || currentValue < 1) {
                throw new DieNotRolledException();
            }
            tempVal = currentValue;
            currentValue = 0;
        } catch (DieNotRolledException e) {
            System.out.println(e.getMessage());
        }
        return tempVal;
    }

    /**
     * roll the dice for a random value
     */
    public void roll() {
        currentValue = rand.nextInt(numSides) + 1;
    }
}