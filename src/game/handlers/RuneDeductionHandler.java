package game.handlers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.conditions.Condition;
import java.util.*;

/**
 * A class that handles Rune deduction.
 *
 * @author Tee Zhi Hong
 */
public class RuneDeductionHandler {

    private Map<Condition, Integer> deductionNConditions;

    private static final int NO_RUNES = 0;

    private Display display = new Display();


    /**
     * RuneDeductionHandler class constructor.
     * @param deductionNConditions A map that consists rune deduction condition and its deduction integer value pairs.
     */
    public RuneDeductionHandler(Map<Condition, Integer> deductionNConditions){
        this.deductionNConditions = deductionNConditions;
    }

    /**
     * Handles the rune deduction based on the conditions given in the rune deduction.
     * @param merchant  Merchant actor (e.g. Sellen).
     * @param location  Current location of the merchant actor.
     * @param originalPrice Original price of an item.
     * @return Integer final price after rune deduction.
     */
    public int handleRuneDeduction(Actor merchant, Location location, Integer originalPrice){
        int currentPrice = originalPrice;
        for (Map.Entry<Condition, Integer> entry: deductionNConditions.entrySet()){
            Condition condition = entry.getKey();
            Integer deduction = entry.getValue();
            if (condition.isFulfilled(merchant, location)){
                currentPrice -= deduction;
                currentPrice = Math.max(currentPrice, NO_RUNES);
                display.println("The price has been deducted to " + currentPrice + " Runes with " + deduction + " Runes deduction");
            }
        }
        return currentPrice;
    }
}
