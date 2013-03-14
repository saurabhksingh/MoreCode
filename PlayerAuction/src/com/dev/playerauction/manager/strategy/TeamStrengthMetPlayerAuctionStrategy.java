package com.dev.playerauction.manager.strategy;

import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;

/**
 * This is a Singleton class (lazy creation singleton pattern) providing the strategy for checking the condition if team has already achieved the desired strength.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class TeamStrengthMetPlayerAuctionStrategy implements PlayerAuctionStrategy{

    private TeamStrengthMetPlayerAuctionStrategy(){}

    public static TeamStrengthMetPlayerAuctionStrategy getInstance(){

        return TeamStrengthMetPlayerAuctionStrategyHolder.getInstance();

    }

    private static class TeamStrengthMetPlayerAuctionStrategyHolder{

        private static final TeamStrengthMetPlayerAuctionStrategy instance = new TeamStrengthMetPlayerAuctionStrategy();

        public static TeamStrengthMetPlayerAuctionStrategy getInstance(){
            return instance;
        }
    }


    @Override
    public int handlePlayerAuction(PlayersCollection playersCollection, Buyer buyer, Player player, int buyerIndex) {

        player.markUnsold();
        if(player.getMaxRejectCount() <= 0){
            System.out.println("removing player : "+player+" for buyer : "+buyer);
            playersCollection.removePlayer(player);
        }
        buyerIndex++;

        return buyerIndex;
    }
}
