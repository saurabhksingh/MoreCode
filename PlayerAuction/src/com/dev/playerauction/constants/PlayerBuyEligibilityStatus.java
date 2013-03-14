package com.dev.playerauction.constants;

/**
 * Enumeration of situation we are in after validating the player for getting auctioned
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public enum PlayerBuyEligibilityStatus {

        ELIGIBLE,
        TEAM_STRENGTH_ALREADY_MET,
        FORIEGN_PLAYER_COUNT_EXCEED,
        TEAM_UNFULFILLED
}
