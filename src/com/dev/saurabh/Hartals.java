package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/13/12
 * Time: 1:06 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class Hartals {
    /**
     * Hartals PC/UVa IDs: 110203/10050
     *
     * Problem Statement :

     Political parties in Bangladesh show their muscle by calling for regular hartals
     (strikes), which cause considerable economic damage. For our purposes, each party
     may be characterized by a positive integer h called the hartal parameter that denotes
     the average number of days between two successive strikes called by the given party.
     Consider three political parties. Assume h1 = 3, h2 = 4, and h3 = 8, where hi is
     the hartal parameter for party i. We can simulate the behavior of these three parties
     for N = 14 days. We always start the simulation on a Sunday. There are no hartals on
     either Fridays or Saturdays.
     Given the hartal parameters for several political parties and the value of N , determine
     the number of working days lost in those N days.
     Input
     The ﬁrst line of the input consists of a single integer T giving the number of test cases
     to follow. The ﬁrst line of each test case contains an integer N (7 ≤ N ≤ 3, 650), giving
     the number of days over which the simulation must be run. The next line contains
     another integer P (1 ≤ P ≤ 100) representing the number of political parties. The ith
     of the next P lines contains a positive integer hi (which will never be a multiple of 7)
     giving the hartal parameter for party i (1 ≤ i ≤ P ).

     Output
     For each test case, output the number of working days lost on a separate line.
     Sample Input
     2
     14
     3
     3
     4
     8
     100
     4
     12
     15
     25
     40
     Sample Output
     5
     15
     Solution :

     We will be using bit-vector here of size equal to number of days we are going to monitor the hartal event. We will maintain a counter for recording the total number of hartal days which will incremented  only when the bit is going to be set. If a bit position is already set then the counter will not be incremented.
     */


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int totalNumberOfInputs = Integer.parseInt(input);
        int [] output = new int[totalNumberOfInputs];
        for (int i = 0 ; i < totalNumberOfInputs; i++)
        {
            int numberOfDaysToMonitor = Integer.parseInt(br.readLine());
            BitSet bitSet = new BitSet();
            int numberOfPoliticalParties = Integer.parseInt(br.readLine());
            int [] hartalFreq = new int [numberOfPoliticalParties];
            for(int j = 0; j < numberOfPoliticalParties; j++)
            {
                hartalFreq [j] = Integer.parseInt(br.readLine());
            }
            for (int freq : hartalFreq)
            {
                int currentDay = freq;
                int count = 1;
                while (currentDay <= numberOfDaysToMonitor)
                {
                    count ++;
                    if(!bitSet.get(currentDay) && !(currentDay%7 ==6 || currentDay%7 == 0))
                    {
                        output[i] ++;
                        bitSet.set(currentDay);
                    }
                    currentDay = freq * count;
                }
            }

        }
        System.out.println("Output");
        for(int out : output)
        {
            System.out.println(out);
        }
    }
}
