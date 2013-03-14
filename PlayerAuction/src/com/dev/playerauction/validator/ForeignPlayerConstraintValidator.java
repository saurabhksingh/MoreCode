package com.dev.playerauction.validator;


import com.dev.playerauction.constants.Constants;
import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;

/**
 * This class provides the API to see if the considered player is a valid foreign player to be considered for team.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class ForeignPlayerConstraintValidator implements PlayerBuyingConstraintValidator{

    PlayerBuyingConstraintValidator myNextValidator;

    @Override
    public void setNextValidator(PlayerBuyingConstraintValidator validator){

        myNextValidator = validator;
    }

    /**
     * @param buyer buyer who is considering to buy this player
     * @param player Player to be bought
     * @param targetStrength total strength of the party should be
     * @return FORIEGN_PLAYER_COUNT_EXCEED if the player being considered here is a foreign player and the quota
     * of foreign players for the current buyer has already exhausted. Otherwise calls the validate of next validator (CoR pattern).
     */
    @Override
    public PlayerBuyEligibilityStatus validate(Buyer buyer, Player player, int targetStrength) {

        PlayerBuyEligibilityStatus result  = PlayerBuyEligibilityStatus.ELIGIBLE;

        if(player.isForiegnPlayer() && buyer.getForeignersCount() == Constants.MAX_ALLOWED_FOREIGN_PLAYERS){

            result = PlayerBuyEligibilityStatus.FORIEGN_PLAYER_COUNT_EXCEED;
        }
        if(myNextValidator != null){

            result = myNextValidator.validate(buyer, player, targetStrength);
        }

        return result;
    }
}
