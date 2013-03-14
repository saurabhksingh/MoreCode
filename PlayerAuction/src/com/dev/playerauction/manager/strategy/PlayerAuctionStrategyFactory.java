package com.dev.playerauction.manager.strategy;

import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;

/**
 * Factory class for giving the strategy implementation based on the input
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class PlayerAuctionStrategyFactory {

    private PlayerAuctionStrategyFactory() {
        // never instantiate; only use static factory methods
    }

    /**
     * @param playerBuyEligibilityStatus status of player based on the validations done on player w.r.t it's current buyer
     * @return the strategy algorithm for handling the auctioning of player
     */
    public static PlayerAuctionStrategy getPlayerAuctionStrategy(PlayerBuyEligibilityStatus playerBuyEligibilityStatus){

        switch (playerBuyEligibilityStatus){

            case ELIGIBLE:
                return EligiblePlayerAuctionStrategy.getInstance();

            case FORIEGN_PLAYER_COUNT_EXCEED:
                return ForeignPlayerLimitationAuctionStrategy.getInstance();

            case TEAM_UNFULFILLED:
                return TeamUnfulfilledPlayerAuctionStrategy.getInstance();

            case TEAM_STRENGTH_ALREADY_MET:
                return TeamStrengthMetPlayerAuctionStrategy.getInstance();

            default:
                return EligiblePlayerAuctionStrategy.getInstance();
        }
    }
}
