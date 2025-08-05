package game.items.shovels;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ShovelAction;
import game.items.Shoveable;

/**
 * A class that represents a Metal Shovel item.
 * This shovel can be used to attack other actors and requires stamina to use.
 *
 * @author Nigel Wong Wei Lun
 */
public class MetalShovel extends Item implements Shoveable {

    /**
     * Constructor for the Metal Shovel item.
     * Initializes the item with a name, display character, and sets it as portable.
     */
    public MetalShovel() {
        super("Metal Shovel", 'm', true);
    }

    /**
     * Checks if the Metal Shovel can be used on the given location.
     * The Metal Shovel can be used if there is an actor in the adjacent locations.
     *
     * @param actor the actor who is trying to use the shovel
     * @param map the current game map
     * @return String showing the result of the shovel action
     */
    @Override
    public String shovel(Actor actor, GameMap map) {
        int STAMINA_COST = 50; // Stamina cost to use the Metal Shovel
        double STRIKE_CHANCE = 0.25; // 25% chance to hit
        int DAMAGE = 1000; // Damage dealt by the Metal Shovel

        if (!actor.hasAttribute(BaseActorAttributes.STAMINA) || actor.getAttribute(BaseActorAttributes.STAMINA) < STAMINA_COST) {
            return actor + " does not have enough stamina to use the Metal Shovel.";
        }
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, STAMINA_COST);

        Location location = map.locationOf(actor);
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (Math.random() < STRIKE_CHANCE) {
                    target.hurt(DAMAGE);
                    return "Metal shovel strikes " + target + ".";
                } else {
                    return "Metal shovel misses " + target + ".";
                }
            }
        }
        return "Metal shovel can be used around an actor";
    }

    /**
     * Returns a list of actions that can be performed with the Metal Shovel.
     * This includes the ShovelAction for actors in adjacent locations.
     *
     * @param actor the actor who owns the Metal Shovel
     * @param map the current game map
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        Location ownerLocation = map.locationOf(actor);

        for (Exit exit : ownerLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                actions.add(new ShovelAction(this, destination.getActor()));
            }
        }
        return actions;
    }
}
