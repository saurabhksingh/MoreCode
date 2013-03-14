package com.dev.playerauction.manager.strategy;


import com.dev.playerauction.constants.Constants;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;

/**
 * This is a Singleton class (lazy creation singleton pattern) providing the strategy for handling the buying of foreign player.
 * This strategy does the handling of this foreign player given the condition that buyer has already reached the maximum foreign player
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class ForeignPlayerLimitationAuctionStrategy implements  PlayerAuctionStrategy{

    private ForeignPlayerLimitationAuctionStrategy(){}

    public static ForeignPlayerLimitationAuctionStrategy getInstance(){

        return ForeignPlayerAuctionStrategyHolder.getInstance();

    }

    private static class ForeignPlayerAuctionStrategyHolder{

        private static final ForeignPlayerLimitationAuctionStrategy instance = new ForeignPlayerLimitationAuctionStrategy();

        public static ForeignPlayerLimitationAuctionStrategy getInstance(){
            return instance;
        }
    }

    @Override
    public int handlePlayerAuction(PlayersCollection playersCollection, Buyer buyer, Player player, int buyerIndex) {

        int result = buyerIndex;

        if(buyer.getForeignersCount() == Constants.MAX_ALLOWED_FOREIGN_PLAYERS && player.isForiegnPlayer()){
            player.markUnsold();
            result++;
        }
        if(player.getMaxRejectCount() <= 0){
            System.out.println("removing foreign player : "+player+" for buyer : "+buyer);
            playersCollection.removePlayer(player);
        }
        return result;
    }
}
