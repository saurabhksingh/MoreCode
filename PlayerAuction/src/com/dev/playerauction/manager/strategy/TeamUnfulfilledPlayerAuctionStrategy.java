package com.dev.playerauction.manager.strategy;

import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;

/**
 * This is a Singleton class proi the strategy for checking of team-unfulfilled condition.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class TeamUnfulfilledPlayerAuctionStrategy implements PlayerAuctionStrategy{

    private TeamUnfulfilledPlayerAuctionStrategy(){}

    public static TeamUnfulfilledPlayerAuctionStrategy getInstance(){

        return TeamUnfulfilledPlayerAuctionStrategyHolder.getInstance();

    }

    private static class TeamUnfulfilledPlayerAuctionStrategyHolder{

        private static final TeamUnfulfilledPlayerAuctionStrategy instance = new TeamUnfulfilledPlayerAuctionStrategy();

        public static TeamUnfulfilledPlayerAuctionStrategy getInstance(){
            return instance;
        }
    }

    @Override
    public int handlePlayerAuction(PlayersCollection playersCollection, Buyer buyer, Player player, int buyerIndex) {

        boolean playerSwapped = false;
        for(Player currentPlayer : buyer.getBuyedPlayers()){

            if(currentPlayer.getStrength() < player.getStrength()){
                buyer.removePlayer(currentPlayer);
                buyer.addPlayer(player);
                playerSwapped = true;
                break;
            }
        }
        if(!playerSwapped) {

            player.markUnsold();
        }

        return buyerIndex;
    }
}
