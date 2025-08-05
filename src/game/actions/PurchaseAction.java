package game.actions;

import game.enums.*;
import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;
import game.conditions.Condition;
import game.handlers.RuneDeductionHandler;
import game.items.*;
import game.sideeffects.*;
import java.util.*;

/**
 * Class representing an action to purchase a purchasable item
 *
 * @author Tee Zhi Hong
 */
public class PurchaseAction extends Action {

    private Purchasable purchasable;
    private Actor merchant;
    private int merchantPrice;
    private String direction;
    private List<SideEffect> merchantSideEffects;
    private RuneDeductionHandler runeDeductionHandler;

    /**
     * PurchaseAction class constructor.
     *
     * @param purchasable           An item that is purchasable.
     * @param merchant              A merchant actor (e.g. Sellen).
     * @param merchantPrice         An integer merchant price for a Purchasable item.
     * @param deductionNConditions  A map that consists rune deduction condition and its deduction integer value pairs.
     * @param direction             The String direction where the purchase should be performed
     * @param merchantSideEffects   A list of side effects from the merchant after buying a purchasable item.
     */
    public PurchaseAction(Purchasable purchasable, Actor merchant, int merchantPrice, Map<Condition, Integer> deductionNConditions, String direction, List<SideEffect> merchantSideEffects){
        this.purchasable = purchasable;
        this.merchant = merchant;
        this.merchantPrice = merchantPrice;
        this.runeDeductionHandler = new RuneDeductionHandler(deductionNConditions);
        this.direction = direction;
        this.merchantSideEffects = merchantSideEffects;
    }

    /**
     * Perform a purchase for a purchasable item from the merchant.
     *
     * @param buyer The actor performing the purchase action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor buyer, GameMap map) {

        merchantPrice = runeDeductionHandler.handleRuneDeduction(merchant, map.locationOf(merchant), merchantPrice);

          if (buyer.getBalance() >= merchantPrice){
              buyer.deductBalance(merchantPrice);
              merchant.addBalance(merchantPrice);

              List<SideEffect> purchaseSideEffects = purchasable.getPurchaseSideEffects();
              purchaseSideEffects.addAll(merchantSideEffects);

              for (SideEffect sideEffect : purchaseSideEffects){
                  sideEffect.execute(buyer, map);
              }

              if (!buyer.isConscious()){
                  buyer.unconscious(map);
                  return buyer + " dies while purchasing an item called " + purchasable + " from " + merchant + " at " + direction;
              }

              buyer.addItemToInventory(purchasable.giveItem());

              return buyer + " successfully purchase the " + purchasable + " from " + merchant + " with a final price of " + merchantPrice + " Runes at " + direction;
          } else{
              return buyer + " fails to purchase the " + purchasable + " from " + merchant + " at " + direction;
          }
    }

    /**
     * Describe what action will be performed if PurchaseAction is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + purchasable + " for " + merchantPrice + " Runes from " + merchant + " at " + direction;
    }
}
