package com.dev.playerauction.validator;

import com.dev.playerauction.constants.Constants;
import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;

/**
 * This class provides the facility to validate the case when players selected do not meet strength criteria
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class TeamUnfulfilledConstraintValidator implements PlayerBuyingConstraintValidator{

    private PlayerBuyingConstraintValidator myNextValidator;

    /**
     *
     * @param buyer buyer who is considering to buy this player
     * @param player Player to be bought
     * @param targetStrength total strength of the party should be
     * @return TEAM_UNFULFILLED of number of players with buyer are maximum numbe of players that can be bought
     * but the team strength has still not been met. Otherwise calls the validate method of the next validator (CoR pattern).
     */
    @Override
    public PlayerBuyEligibilityStatus validate(Buyer buyer, Player player, int targetStrength) {

        PlayerBuyEligibilityStatus result = PlayerBuyEligibilityStatus.ELIGIBLE;

        if((buyer.getBuyedPlayers().size() == Constants.MAX_PLAYERS_TO_BE_SOLD) && (buyer.getCurrentTeamStrength() < targetStrength)){

            result = PlayerBuyEligibilityStatus.TEAM_UNFULFILLED;
        }
        else if(myNextValidator != null){

            result = myNextValidator.validate(buyer, player, targetStrength);
        }

        return result;
    }

    @Override
    public void setNextValidator(PlayerBuyingConstraintValidator nextValidator) {

        myNextValidator = nextValidator;
    }
}
