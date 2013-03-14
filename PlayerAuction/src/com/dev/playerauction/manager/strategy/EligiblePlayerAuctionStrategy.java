package com.dev.playerauction.manager.strategy;

import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;

/**
 * This is a Singleton class (lazy creation singleton pattern) providing the strategy for buying the player. This strategy
 * is executed only in case when player is eligible to be bought by buyer and buyer has the capacity to buy the player
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class EligiblePlayerAuctionStrategy implements PlayerAuctionStrategy{

    private EligiblePlayerAuctionStrategy(){}

    public static EligiblePlayerAuctionStrategy getInstance(){

        return EligiblePlayerAuctionStrategyHolder.getInstance();

    }

    private static class EligiblePlayerAuctionStrategyHolder{

        private static final EligiblePlayerAuctionStrategy instance = new EligiblePlayerAuctionStrategy();

        public static EligiblePlayerAuctionStrategy getInstance(){
            return instance;
        }
    }

    /**
     *
     * @param playersCollection run-time storage of all the players available for auctioning
     * @param buyer is contemplating to buy player in auctioning
     * @param player to be auctioned
     * @param buyerIndex index of the current buyer in buyers list
     * @return index of next buyer to be considered for auction
     */
    @Override
    public int handlePlayerAuction(PlayersCollection playersCollection, Buyer buyer, Player player, int buyerIndex) {
        buyer.addPlayer(player);
        playersCollection.removePlayer(player);
        buyerIndex = buyerIndex+1;

        return buyerIndex;
    }
}
