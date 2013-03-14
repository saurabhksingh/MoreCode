package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/13/12
 * Time: 1:11 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class ThreeNPlusOne {

    /**
     * Consider the following algorithm to generate a sequence of numbers. Start with an
     integer n. If n is even, divide by 2. If n is odd, multiply by 3 and add 1. Repeat this
     process with the new value of n, terminating when n = 1. For example, the following
     sequence of numbers will be generated for n = 22:
     22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
     It is conjectured (but not yet proven) that this algorithm will terminate at n = 1 for
     every integer n. Still, the conjecture holds for all integers up to at least 1, 000, 000.
     For an input n, the cycle-length of n is the number of numbers generated up to and
     including the 1. In the example above, the cycle length of 22 is 16. Given any two
     numbers i and j, you are to determine the maximum cycle length over all numbers
     between i and j, including both endpoints.

     Input
     The input will consist of a series of pairs of integers i and j, one pair of integers per
     line. All integers will be less than 1,000,000 and greater than 0.

     Output
     For each pair of input integers i and j, output i, j in the same order in which they
     appeared in the input and then the maximum cycle length for integers between and
     including i and j. These three numbers should be separated by one space, with all three
     numbers on one line and with one line of output for each line of input.
     Sample Input                       Sample Output
     1 10                                  1 10 20

     100 200                               100 200 125
     201 210                               201 210 89

     900 1000                              900 1000 174
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        //read the input from the console
        while ((input = br.readLine()) != null && !input.trim().equals("\n") && !input.trim().equals(""))
        {
            String[] data = input.split("\\s+");
            for(String in : data )
            {
                System.out.print(in + " ");
            }

            int startPoint = Integer.parseInt(data[0]);
            int endPoint = Integer.parseInt(data[1]);
            int maxCycleLength = Integer.MIN_VALUE;

            for(int i = startPoint; i<=endPoint; i++)
            {
                int cycleLength = getCycleLength(i);
                if(maxCycleLength < cycleLength)
                {
                    maxCycleLength = cycleLength;
                }
            }

            System.out.println(maxCycleLength);
        }

    }

    private static int getCycleLength(int n) {

        if (n == 1)
        {
            return 1;
        }
        else if (n%2 == 0)
        {
            return 1 + getCycleLength(n/2);
        }
        else
        {
            return 1 + getCycleLength(n*3 + 1);
        }
    }
}
