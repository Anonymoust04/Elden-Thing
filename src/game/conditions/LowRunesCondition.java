package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if the actor's balance is below a certain threshold.
 * This condition can be used to trigger events or actions when the actor's balance is low.
 *
 * @author Tee Zhi Hong
 */
public class LowRunesCondition implements Condition{

    private int lowRunesBoundary;

    /**
     * Constructor for LowRunesCondition.
     *
     * @param lowRunesBoundary The balance threshold to check against.
     */
    public LowRunesCondition(int lowRunesBoundary){
        this.lowRunesBoundary = lowRunesBoundary;
    }

    /**
     * Checks if the actor's balance is below the specified threshold.
     *
     * @param actor The actor to check the condition for.
     * @param location The location to check the condition for (not used in this case).
     * @return Boolean indicating whether the condition is fulfilled or not.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        return actor.getBalance() < lowRunesBoundary;
    }
}
