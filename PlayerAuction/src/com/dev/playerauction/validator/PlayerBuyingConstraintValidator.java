package com.dev.playerauction.validator;

import com.dev.playerauction.constants.PlayerBuyEligibilityStatus;
import com.dev.playerauction.model.Buyer;
import com.dev.playerauction.model.Player;

/**
 * This class is designed keeping Chain-of-Responsibility pattern (CoR)
 * pattern in mind. Validations wil happen soonImplementations of this interface will need to implement declared methods.
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public interface PlayerBuyingConstraintValidator {

    /**
     * API to validate the player
     * @param buyer buyer who is considering to buy this player
     * @param player Player to be bought
     * @param targetStrength total strength of the party should be
     * @return status of player if its eligible to be bought buyer or not
     */
    public PlayerBuyEligibilityStatus validate(Buyer buyer, Player player, int targetStrength);

    /**
     *
     * @param nextValidator  the next validator to be invoked
     */
    public void setNextValidator(PlayerBuyingConstraintValidator nextValidator);
}
