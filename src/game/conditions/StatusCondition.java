package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Condition class to check whether the Status enumeration constant is present in the actor.
 *
 * @author Tee Zhi Hong
 */
public class StatusCondition implements Condition {

    private Status status;

    /**
     * Constructor for StatusCondition.
     * @param status Status enumeration constant that represents a status.
     */
    public StatusCondition(Status status){
        this.status = status;
    }

    /**
     * Check whether the status enumeration constant is present in the actor.
     * @param actor The actor to be checked for the presence of the status enumeration constant.
     * @param location The actor current location.
     * @return Boolean that indicates whether the status enumeration constant is present in the actor.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        return actor.hasCapability(status);
    }
}
