/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Megan Cranston
 * Last Updated: 9/12/2024
 */
package cranstonm;

import java.util.Random;

/**
 * Class Die
 */
public class Die {
    /**
     * minimum number of sides on the dice
     */
    public final int MIN_SIDES = 2;
    /**
     * maximum number of sides on the dice
     */
    public final int MAX_SIDES = 10;
    private int currentValue;
    private int numSides;
    private Random rand = new Random();

    /**
     * Die Constructor
     * @param numSides number of sides on dice
     */
    public Die(int numSides) {
        try {
            if (MIN_SIDES > numSides || numSides > MAX_SIDES) {
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