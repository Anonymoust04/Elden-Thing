package game.conditions;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * A Condition Interface, that is used to check if a condition is fulfilled.
 *
 * @author Tee Zhi Hong
 */
public interface Condition {

    /**
     * Check if the condition is fulfilled to perform a specific behaviour.
     *
     * @param actor The actor to check the condition for.
     * @param location The location to check the condition for.
     * @return true if the condition is fulfilled, false otherwise.
     */
    boolean isFulfilled(Actor actor, Location location);

}
