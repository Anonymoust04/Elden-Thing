package game.conditions;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;
import game.enums.*;

/**
 * A class that checks whether the affection level is above the required affection level.
 *
 * @author Tee Zhi Hong
 */
public class HighAffectionCondition implements Condition {

    private int requiredAffectionLevel;

    /**
     * Constructor for HighAffectionCondition
     * @param requiredAffectionLevel Required affection level.
     */
    public HighAffectionCondition(Integer requiredAffectionLevel){
        this.requiredAffectionLevel = requiredAffectionLevel;
    }

    /**
     * Checks if the affectionate actor has affection level above the required affection level.
     *
     * @param actor The actor to check the condition for.
     * @param location The location to check the condition for.
     * @return Boolean indicating whether the condition is fulfilled or not.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        Integer affectionLevel = actor.getAttribute(Attribute.AFFECTION_LEVEL);
        return affectionLevel > requiredAffectionLevel;
    }

}
