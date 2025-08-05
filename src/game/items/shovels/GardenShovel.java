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
import game.enums.Ability;
import game.grounds.Soil;
import game.items.Shoveable;

/**
 * Class representing an Ancient Shovel item in the game.
 * Ancient Shovels are used for digging and have special capabilities.
 *
 * @author Nigel Wong Wei Lun
 */
public class GardenShovel extends Item implements Shoveable {
    /**
     * Constructor for Garden Shovel item.
     * Initializes the item with a name, display character, and adds the GARDENING_SHOVEL capability.
     */
    public GardenShovel() {
        super("Garden Shovel", 'd', true);
    }

    /**
     * Checks if the shovel can be used on the given location.
     * A shovel can be used if there is a plant in the surrounding exits.
     *
     * @param location The location to check
     * @return true if the shovel can be used, false otherwise
     */
    public boolean canShovel(Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Ability.PLANT)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shovels the soil at the actor's current location.
     * Requires stamina to perform the action.
     *
     * @param actor The actor performing the action
     * @param map The current game map
     * @return A message indicating the result of the shovelling action
     */
    @Override
    public String shovel(Actor actor, GameMap map) {
        int REQUIRED_STAMINA = 25;

        if (!actor.hasAttribute(BaseActorAttributes.STAMINA) || actor.getAttribute(BaseActorAttributes.STAMINA) < REQUIRED_STAMINA) {
            return actor + " does not have enough stamina to shovel the soil.";
        }

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, REQUIRED_STAMINA);

        Location location = map.locationOf(actor);
        for (Exit exit : location.getExits()){
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Ability.PLANT)) {
                destination.setGround(new Soil());
                return "The plant has been shovelled.";
            }
        }
        return "You cannot use the garden shovel on this ground as it is not suitable for planting.";
    }

    /**
     * List of allowable actions that the garden shovel item can perform to its owner
     * or to the current map while being carried by an actor.
     *
     * @param actor the actor that owns the garden shovel item.
     * @param map the map where the actor is performing the action on.
     * @return an unmodifiable list of Actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = new ActionList();
        Location ownerLocation = map.locationOf(actor);

        if (canShovel(ownerLocation)){
            actions.add(new ShovelAction(this));
        }
        return actions;
    }
}
