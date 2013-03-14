package com.dev.playerauction.util;

/**
 * Utility class which provides the API for doing application related arithmetic operations
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class ArithmeticUtil {

    /**
     * API to test if a number is prime or not.
     * @param number to be verified if prime or not.
     * @return true if number given as input is a prime number
     */
    public static boolean isPrime(int number)
    {
        boolean result = true;
        if(number == 2)
        {
            result = true;
        }
        else if(number ==1 || number%2 == 0)  //lets take 1 as non-prime
        {
            result = false;
        }
        else
        {
            int numberToCheckTill = (int) Math.sqrt(number);
            for (int i = 3; i <= numberToCheckTill; i = i + 2) {

                if ((number % i) == 0) {
                    result = false;
                    //number is not prime
                    break;
                }
            }
        }

        return result;
    }


    /**
     * API to calculate the price of the player based onn the strength of the player
     * @param battingStrength Strength in batting (0 to 10)
     * @param bowlingStrength Strength in bowling (0 to 10)
     * @param fieldingStrength Strength in fielding (0 to 10)
     * @param keepStrength Strength in keeping (0 or 10)
     * @return estimated price of player based on the strength of skill-sets given
     */
    public static double getPlayerPrice(int battingStrength, int bowlingStrength, int fieldingStrength, int keepStrength){

        double price = 1000000 * (0.45 * battingStrength +  0.35 * bowlingStrength + 0.20 * fieldingStrength) + 500000 * keepStrength;

        return price;
    }
}
