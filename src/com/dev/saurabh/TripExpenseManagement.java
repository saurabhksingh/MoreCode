package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c)
 * User: saurabh
 * Date: 7/13/12
 * Time: 1:10 PM
 * This file is created and owned by Saurabh Kr Singh (saurabh.nsit@gmail.com).
 * The code written here is being released under Apache 2.0 License
 */
public class TripExpenseManagement {

    /**
     * Problem Statement :
     * A group of students are members of a club that travels annually to diﬀerent lo-
     cations. Their destinations in the past have included Indianapolis, Phoenix, Nashville,
     Philadelphia, San Jose, and Atlanta. This spring they are planning a trip to Eindhoven.
     The group agrees in advance to share expenses equally, but it is not practical to share
     every expense as it occurs. Thus individuals in the group pay for particular things, such
     as meals, hotels, taxi rides, and plane tickets. After the trip, each student’s expenses
     are tallied and money is exchanged so that the net cost to each is the same, to within
     one cent. In the past, this money exchange has been tedious and time consuming. Your
     job is to compute, from a list of expenses, the minimum amount of money that must
     change hands in order to equalize (within one cent) all the students’ costs.

     Input :
     Standard input will contain the information for several trips. Each trip consists of a
     line containing a positive integer n denoting the number of students on the trip. This is
     followed by n lines of input, each containing the amount spent by a student in dollars
     and cents. There are no more than 1000 students and no student spent more than
     $10,000.00. A single line containing 0 follows the information for the last trip.

     Output :
     For each trip, output a line stating the total amount of money, in dollars and cents,
     that must be exchanged to equalize the students’ costs.
     Sample Input
     3
     10.00
     20.00
     30.00
     4
     15.00
     15.01
     3.00
     3.01
     0
     Solution
     Step 1 will be to calculate the mean of all expenses. Now anyone who spends more than the mean amount will have to be compensated
     by the people spent more than the mean amount. So the total amount to required by people ho spent more than mean will be the sum
     of difference between the amount they spent and the mean amount.
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        //read the input from the console
        while ((input = br.readLine()) != null && !input.trim().equals("\n") && !input.trim().equals(""))
        {
            int numberOfPeople = Integer.parseInt(input);

            if (numberOfPeople == 0)
            {
                break;
            }
            double [] expenses = new double [numberOfPeople];

            double sum  = 0;
            for(int i=0; i<numberOfPeople; i++)
            {
                input = br.readLine();
                expenses [i] = Double.parseDouble(input);
                sum += expenses[i];
            }

            double avgExpense = sum/numberOfPeople;

            sum = 0;

            for (double expense : expenses)
            {
                if((avgExpense - expense) > 0.01)
                {
                    sum += (avgExpense - expense);
                }
            }

            System.out.println(sum);
        }

    }
}
