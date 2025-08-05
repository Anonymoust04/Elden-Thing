package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A condition that checks if an actor's health is below a certain threshold.
 *
 * @author Tee Zhi Hong
 */
public class LowHealthCondition implements Condition{

    private int lowHealthBoundary;

    /**
     * Constructor for LowHealthCondition.
     *
     * @param lowHealthBoundary The health threshold to check against.
     */
    public LowHealthCondition(int lowHealthBoundary){
        this.lowHealthBoundary = lowHealthBoundary;
    }

    /**
     * Checks if the actor's health is below the specified threshold.
     *
     * @param actor The actor to check the condition for.
     * @param location The location to check the condition for (not used in this case).
     * @return Boolean indicating whether the condition is fulfilled or not.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        return actor.getAttribute(BaseActorAttributes.HEALTH) < lowHealthBoundary;
    }
}
