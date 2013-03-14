package com.dev.playerauction.manager;

import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;
import com.dev.playerauction.manager.strategy.PlayerAuctionStrategyFactory;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;
import com.dev.playerauction.model.PlayersCollection;
import com.dev.playerauction.validator.PlayerBuyingConstraintValidator;

import java.util.List;


/**
 * This is the central point of managing the auctioning of players with the given constraints.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class PlayersAuctionManager {

    /**
     * This API triggers the process of auction of the players.
     * @param buyerList is the list of the buyers bidding for the players
     * @param playersCollection is a wrapper class of a min heap. This stores the players data with increasing ordering of their prices
     * @param targetTeamStrength is the value of total team strength which buyers are looking to buy
     * @param validator is the API to test if player can be included in the squad and if not then why
     */
    public static void auctionPlayers(List<Buyer> buyerList, PlayersCollection playersCollection, int targetTeamStrength, PlayerBuyingConstraintValidator validator) {

        int buyerIndex = 0;

        while(!playersCollection.isEmpty()){

            Player player = playersCollection.getLowestPricedPlayer();

            Buyer buyer = buyerList.get(buyerIndex);

            PlayerBuyEligibilityStatus status = validator.validate(buyer, player, targetTeamStrength);

            buyerIndex = PlayerAuctionStrategyFactory.getPlayerAuctionStrategy(status).handlePlayerAuction(playersCollection, buyer,player,buyerIndex);

            buyerIndex = buyerIndex % buyerList.size();
        }
    }
}
