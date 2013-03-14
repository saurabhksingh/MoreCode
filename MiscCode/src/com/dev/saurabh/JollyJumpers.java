package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/13/12
 * Time: 1:09 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class JollyJumpers {

    /**
     * Problem Statement :
     * A sequence of n > 0 integers is called a jolly jumper if the absolute values of the
     differences between successive elements take on all possible values 1 through n − 1. For
     instance,
     1 4 2 3
     is a jolly jumper, because the absolute diﬀerences are 3, 2, and 1, respectively. The
     deﬁnition implies that any sequence of a single integer is a jolly jumper. Write a program
     to determine whether each of a number of sequences is a jolly jumper.

     Input
     Each line of input contains an integer n < 3, 000 followed by n integers representing the
     sequence.

     Output
     For each line of input generate a line of output saying “Jolly” or “Not jolly”.

     Sample Input
     4 1 4 2 3
     5 1 4 2 -1 6
     Sample Output
     Jolly
     Not jolly

     Solution :
     We need to concentrate on the fact that difference in consecutive numbers should cover all number from 1 to n-1.
     The heart of solution is in selecting a data structure who's value at an index corresponding to the i (1 <= i < n) will be set
     if absolute value of difference between consecutive numbers is i. Absolute values of differences will fail to cover
     the range of 1 to n-1 if any of the difference value is repeated or any of the difference will fall out side the range
     of 1 to n-1 and there will be no need to check for the numbers further.
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        //read the input from the console
        while ((input = br.readLine()) != null && !input.trim().equals("\n") && !input.trim().equals(""))
        {
            String[] data = input.split("\\s+");

            int numberOfInputs = Integer.parseInt(data[0]);

            if ((data.length - 1) != numberOfInputs)
            {
                System.out.println("Wrong input. Exiting....");
                System.exit(1);
            }
            BitSet bitSet = new BitSet(numberOfInputs);

            boolean isJolly = true;

            for(int i = 1; i< data.length-1; i++)
            {
                int diff = Math.abs(Integer.parseInt(data[i]) - Integer.parseInt(data[i+1]));
                if (diff < 1 || diff >= numberOfInputs || bitSet.get(diff))
                {
                    System.out.println("Not Jolly");
                    isJolly = false;
                    break;
                }
                bitSet.set(diff);
            }
            if(isJolly)
            {
                System.out.println("Jolly");
            }
        }
    }
}
