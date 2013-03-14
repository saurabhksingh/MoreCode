package com.dev.playerauction.manager.strategy;

import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;

/**
 * Implementation of this interface  will apply the relevant action on the player w.r.t it's current bidder.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public interface PlayerAuctionStrategy {

    /**
     * handle the auctioning of player w.r.t it's buyer
     * @param playersCollection run-time storage of all the players available for auctioning
     * @param buyer is contemplating to buy player in auctioning
     * @param player to be auctioned
     * @param buyerIndex index of the current buyer in buyers list
     * @return return the index of next buyer to consider in list of available buyers
     */
    public int handlePlayerAuction(PlayersCollection playersCollection, Buyer buyer, Player player, int buyerIndex);
}
