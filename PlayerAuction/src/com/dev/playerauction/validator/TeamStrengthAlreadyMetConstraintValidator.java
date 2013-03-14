package com.dev.playerauction.validator;

import com.dev.playerauction.constants.Constants;
import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;

/**
 * This class provides the facility to validate the case when player is not needed to be added to the squad
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class TeamStrengthAlreadyMetConstraintValidator implements PlayerBuyingConstraintValidator{

    PlayerBuyingConstraintValidator myNextValidator;

    @Override
    public void setNextValidator(PlayerBuyingConstraintValidator validator){

        myNextValidator = validator;
    }

    /**
     *
     * @param buyer buyer who is considering to buy this player
     * @param player Player to be bought
     * @param targetStrength total strength of the party should be
     * @return TEAM_STRENGTH_ALREADY_MET if team has already achieved the required strength. Otherwise
     * it calls the validate method of next validator (CoR pattern)
     */
    @Override
    public PlayerBuyEligibilityStatus validate(Buyer buyer, Player player, int targetStrength) {

        PlayerBuyEligibilityStatus result  = PlayerBuyEligibilityStatus.ELIGIBLE;

        if((buyer.getBuyedPlayers().size() == Constants.MAX_PLAYERS_TO_BE_SOLD)
                && (buyer.getCurrentTeamStrength() >= targetStrength) ){

            result = PlayerBuyEligibilityStatus.TEAM_STRENGTH_ALREADY_MET;
        }
        else if(myNextValidator != null){

            result = myNextValidator.validate(buyer, player, targetStrength);
        }

        return result;
    }
}
