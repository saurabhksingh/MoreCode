package com.dev.playerauction.model;

import java.util.*;

/**
 * Wrapper data-structure over the min-heap based collection of players data.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class PlayersCollection {

    private PriorityQueue <Player> myPlayerQueue;

    /**
     * Constructor
     */
    public PlayersCollection(){

        myPlayerQueue = new PriorityQueue<Player>(30, new Comparator<Player>() {
            @Override
            public int compare(Player playerOne, Player playerTwo) {

                return Double.compare(playerOne.getPrice(), playerTwo.getPrice());
            }
        });
    }

    /**
     *
     * @param player Player to be added to the collection of to-be auctioned players
     */
    public void addPlayer(Player player){

        myPlayerQueue.add(player);
    }

    /**
     *
     * @param player Player to be removed from the auction
     */
    public void removePlayer(Player player){

        myPlayerQueue.remove(player);
    }

    /**
     *
     * @return the lowest priced player
     */
    public Player getLowestPricedPlayer() {
        return myPlayerQueue.poll();
    }

    /**
     *
     * @return true if no more players are left to be considered for the auction
     */
    public boolean isEmpty(){

        return myPlayerQueue.isEmpty();
    }
}
