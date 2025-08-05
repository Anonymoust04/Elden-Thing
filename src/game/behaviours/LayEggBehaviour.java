package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npc.Reproduceable;
import game.conditions.Condition;
import game.enums.Attribute;

/**
 * A behaviour that allows an actor to lay an egg.
 *
 * @author Liew Yi Wei
 */
public class LayEggBehaviour implements Behaviour {

    /**
     * The creature that lays eggs.
     */
    private Reproduceable reproduceable;

    /**
     * The condition for laying an egg.
     */
    private Condition layingCondition;

    /**
     * Constructor for LayEggBehaviour.
     *
     * @param creature the creature that lays eggs
     * @param layingCondition the cooldown time for laying an egg
     */
    public LayEggBehaviour(Reproduceable creature, Condition layingCondition) {
        this.reproduceable = creature;
        this.layingCondition = layingCondition;
    }

    /**
     * Returns the action of laying an egg. Based on the countdown timer of the creature that lays eggs.
     *
     * @param actor the actor performing the action
     * @param map   the map the actor is on
     * @return the action of laying an egg, or null if no action is available
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        if(layingCondition.isFulfilled(actor, here)){
            reproduceable.reproduce(actor,here);
            return new DoNothingAction();
        }
        return null;
    }
}
