package com.dev.playerauction;

import com.dev.playerauction.exception.ErrorCode;
import com.dev.playerauction.exception.PlayerAuctionException;
import com.dev.playerauction.manager.BootStrapper;
import com.dev.playerauction.manager.PlayersAuctionManager;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.PlayersCollection;
import com.dev.playerauction.validator.ForeignPlayerConstraintValidator;
import com.dev.playerauction.validator.PlayerBuyingConstraintValidator;
import com.dev.playerauction.validator.TeamStrengthAlreadyMetConstraintValidator;
import com.dev.playerauction.validator.TeamUnfulfilledConstraintValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the main method (entry point of the application)
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class Application
{
    private PlayerBuyingConstraintValidator validator;

    public static void main(String [] args) throws PlayerAuctionException
    {
        Application application = new Application();
        application.start();
    }

    private void start() throws PlayerAuctionException{
        setupValidators();
        startTest();
    }

    /**
     * Set up the validators on the line of chain of responsibilities (CoR)
     */
    private void setupValidators() {

        validator = new ForeignPlayerConstraintValidator();
        PlayerBuyingConstraintValidator nextValidator = new TeamStrengthAlreadyMetConstraintValidator();
        nextValidator.setNextValidator(new TeamUnfulfilledConstraintValidator());
        validator.setNextValidator(nextValidator);
    }

    private void startTest() throws PlayerAuctionException {

        BufferedReader br = null;

        try{
            //Lets assume that input is of following form form. First line is number of test cases (say n).
            //Next n lines will be desired strength and a comma separated A,B or B,A. A and B and two bidders
            //who will start bidding as per the ordering of their occurrence.
            /*
                NO_OF_TEST_CASES
                DESIRED_STRENGTH1,A,B
                .
                n-2 cases
                .
                DESIRED_STRENGTH1,B,A
             */
            br = new BufferedReader(new InputStreamReader(System.in));
            int numberOfTestCases = Integer.valueOf(br.readLine());

            for(int i=0; i<numberOfTestCases; i++){

                String inputStringLine = br.readLine();
                String [] input = inputStringLine.split(",");

                int targetStrength = Integer.valueOf(input[0]);

                String firstBuyerName = input[1];
                String secondBuyerName = input[2];

                List<Buyer> listOfBuyers = new ArrayList<Buyer>();
                Buyer buyerOne = new Buyer(firstBuyerName); listOfBuyers.add(buyerOne);
                Buyer buyerTwo = new Buyer(secondBuyerName); listOfBuyers.add(buyerTwo);

                PlayersCollection playersCollection = bootstrap(listOfBuyers.size());

                PlayersAuctionManager.auctionPlayers(listOfBuyers, playersCollection, targetStrength, validator);

                displayTeamStructure(buyerOne);
                displayTeamStructure(buyerTwo);
            }

        }
        catch (IOException exc){

            throw new PlayerAuctionException(ErrorCode.INVALID_DATA_XML, exc.getMessage());
        }
        finally {
            try{
                if(br != null){
                    br.close();
                }
            }
            catch(IOException ioe){/* ignore the exception */}
        }

    }

    private PlayersCollection bootstrap(int buyersCount) throws PlayerAuctionException{

        return BootStrapper.loadXmlInPlayersCollection("sample.xml", buyersCount);
    }

    private void displayTeamStructure(Buyer buyer) {

        System.out.println("\n******************************\n"+buyer);
    }
}
