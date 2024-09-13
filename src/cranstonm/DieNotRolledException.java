/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Megan Cranston
 * Last Updated: 9/12/2024
 */
package cranstonm;

/**
 * Class DieNotRolledException
 */
public class DieNotRolledException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Value not in expected range.";
    }
}
