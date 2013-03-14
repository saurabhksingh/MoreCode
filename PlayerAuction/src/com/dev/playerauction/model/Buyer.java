package com.dev.playerauction.model;

import java.util.*;

/**
 * This class represents the buyers who are participating to buy players in auction.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class Buyer {

    private static final Random ourRandomGenerator = new Random();

    private String myName;
    private Integer myId;
    private int myForeignersCount = 0;
    private TreeSet<Player> myBoughtPlayerSet;
    private int myTotalCurrentStrength = 0;
    private int myTotalCostOfBuildignTeam = 0;

    /**
     * Constructor
     * @param name of the buyer
     */
    public Buyer(String name){

        myName = name;
        myBoughtPlayerSet = new TreeSet<Player>(new Comparator<Player>() {

            @Override
            public int compare(Player playerOne, Player playerTwo) {

                return Double.compare(playerTwo.getPrice(), playerOne.getPrice());
            }
        });
        myId = ourRandomGenerator.nextInt();
    }

    /**
     *
     * @param player to be added to the bought list of players
     */
    public void addPlayer(Player player){

        myBoughtPlayerSet.add(player);
        myTotalCurrentStrength += player.getStrength();
        myTotalCostOfBuildignTeam += player.getPrice();
        if(player.isForiegnPlayer()) {
            myForeignersCount++;
        }
    }

    private void adjustValuesForPlayerDeparture(Player player) {

        myTotalCurrentStrength -= player.getStrength();
        myTotalCostOfBuildignTeam -= player.getPrice();
        if(player.isForiegnPlayer()){
            myForeignersCount--;
        }
    }

    /**
     *
     * @return unique id of the buyer
     */
    public Integer getId(){

        return myId;
    }

    /**
     *
     * @return number of foreign players in current squad
     */
    public int getForeignersCount() {

        return myForeignersCount;
    }

    /**
     *
     * @return Set of the currently bought players
     */
    public Set<Player> getBuyedPlayers(){

        return myBoughtPlayerSet;
    }

    /**
     *
     * @return The current sum of all of player's strength
     */
    public int getCurrentTeamStrength(){

        return myTotalCurrentStrength;
    }

    /**
     *
     * @param player Player to be returned back to auctioneer
     */
    public void removePlayer(Player player){

        myBoughtPlayerSet.remove(player);
        adjustValuesForPlayerDeparture(player);
    }

    @Override
    public String toString(){

        StringBuilder out = new StringBuilder("Buyer ").append(myName).append(" has total strength of ")
                                .append(myTotalCurrentStrength).append(" and has paid ").append(myTotalCostOfBuildignTeam)
                                .append(" for building this team").append(". I have ").append(myForeignersCount).append(" foreign players");

        out.append("\nMy selected players are :\n");
        for(Player player : myBoughtPlayerSet){
            out.append(player).append("\n");
        }

        return out.toString();

    }
}
