package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition to check if the actor's inventory is empty.
 *
 * @author Tee Zhi Hong
 */
public class EmptyInventoryCondition implements Condition {

    /**
     * Checks if the actor's inventory is empty.
     *
     * @param actor The actor to check the condition for.
     * @param location The location to check the condition for.
     * @return Boolean indicating whether the inventory is empty or not.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        return actor.getItemInventory().isEmpty();
    }
}
