package game.items.shovels;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ShovelAction;
import game.enums.Ability;
import game.grounds.Blight;
import game.items.Diamond;
import game.items.Shoveable;

import java.util.Random;

/**
 * Class representing an Ancient Shovel item in the game.
 * Ancient Shovels are used for digging and have special capabilities.
 *
 * @author Nigel Wong Wei Lun
 */
public class AncientShovel extends Item implements Shoveable {
    /**
     * Constructor for Ancient Shovel item.
     * Initializes the item with a name, display character, and adds the ANCIENT_SHOVEL capability.
     */
    public AncientShovel() {
        super("Ancient Shovel", 'z', true);
    }

    /**
     * Checks if the shovel can be used on the given location.
     * A shovel can be used if the ground has the ANCIENT_SHOVEL capability.
     *
     * @param location The location to check
     * @return true if the shovel can be used, false otherwise
     */
    public boolean canShovel(Location location){
        return location.getGround().hasCapability(Ability.ANCIENT_SHOVEABLE);
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
        Location location = map.locationOf(actor);
        if (!location.getGround().hasCapability(Ability.ANCIENT_SHOVEABLE)){
            return "You cannot use the ancient shovel on this ground.";
        } else {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, REQUIRED_STAMINA);
            Random random = new Random();

            if (random.nextBoolean()) {
                location.setGround(new Blight());
                return "The soil is corrupted by magic and turned into blight.";
            } else {
                location.addItem(new Diamond());
                return "You found a mysterious Diamond under the soil!";
            }
        }
    }

    /**
     * List of allowable actions that the Ancient Shovel item can perform to its owner
     * or to the current map while being carried by an actor.
     *
     * @param actor the actor that owns the Ancient Shovel item.
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
