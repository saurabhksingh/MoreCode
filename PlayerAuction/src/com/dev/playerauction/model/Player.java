package com.dev.playerauction.model;

import com.dev.playerauction.exception.ErrorCode;
import com.dev.playerauction.exception.PlayerAuctionException;
import com.dev.playerauction.util.ArithmeticUtil;

/**
 * Class representing the player who are to be auctioned.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public final class Player {

    private String myName;
    private double myPrice;
    private int myStrength;
    private int myJerseyNumber;
    private boolean isForiegnPlayer;
    private int myMaxRejectCountCount;

    /**
     * Constructor
     * @param name Name of the player
     * @param price Calculated price of the player
     * @param strength Total strength of the player
     * @param jerseyNumber Unique jersey number of the player
     */
    public Player(String name, double price, int strength, int jerseyNumber)
    {
        myName = name;
        myPrice = price;
        isForiegnPlayer = ArithmeticUtil.isPrime(jerseyNumber);
        myStrength = strength;
        myJerseyNumber = jerseyNumber;
    }

    /**
     *
     * @return name of the player
     */
    public String getName() {

        return myName;
    }

    /**
     *
     * @return total price of the player
     */
    public double getPrice() {

        return myPrice;
    }

    /**
     *
     * @return total strength of the player
     */
    public int getStrength() {

        return myStrength;
    }

    /**
     *
     * @return unique jersey number allocated to player
     */
    public int getJerseyNumber() {

        return myJerseyNumber;
    }

    /**
     *
     * @return true if the current player is a foreign player or false otherwise.
     */
    public boolean isForiegnPlayer() {

        return isForiegnPlayer;
    }

    /**
     *
     * @return maximum number of times a player can be rejected
     */
    public int getMaxRejectCount() {

        return myMaxRejectCountCount;
    }

    /**
     *
     * @param maxRejectCount Maximum number of times a player can be marked reh=jected before getting removed from auction.
     * @throws PlayerAuctionException
     */
    public void setMaxRejectCount(int maxRejectCount) throws PlayerAuctionException {

        if(maxRejectCount <= 0){

            throw new PlayerAuctionException(ErrorCode.INVALID_MAXIMUM_REJECT_COUNT_FOR_PLAYER, maxRejectCount+ " is invalid value of reject count, it should be a positive integer");
        }
        myMaxRejectCountCount = maxRejectCount;
    }

    /**
     * mark this player unsold by a buyer
     */
    public void markUnsold(){

        myMaxRejectCountCount--;
    }

    @Override
    public int hashCode(){

        return myJerseyNumber;
    }

    @Override
    public boolean equals(Object other){

        boolean result = false;

        if((other != null)//other object should not be null

                //though this does not seem to be necessary as class is final and
                // next check takes care of class compatibility check. Just doing it
                //as part of *best practices* to do things
                && other instanceof Player

                && other.getClass() == getClass()){


            result = (myJerseyNumber == ((Player) other).getJerseyNumber());
        }

        return result;
    }


    @Override
    public String toString(){

        return "My name is : "+myName+" ,I am a foreign player ? "+ isForiegnPlayer +" mystrength is "+myStrength+", my price is " +myPrice;
    }

}
