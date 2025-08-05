package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.GiveAction;
import game.enums.Ability;
import game.enums.Attribute;

/**
 * An abstract class that represents "giveable" items (items that is capable of giving).
 *
 * @author Tee Zhi Hong
 */
public abstract class GiveableItem extends Item {

    private ActorAttributeOperations operation;

    private int affectionLevelVal;

    private Display display = new Display();

    /**
     * GiveableItem class constructor.
     * @param name Name of the item that is capable of giving.
     * @param displayChar Display character of the item.
     * @param operation An enumeration constant from ActorAttributeOperations that has increase, decrease or update.
     * @param affectionLevelVal A magnitude that shows the affection level of the item that is capable of giving.
     */
    public GiveableItem(String name, char displayChar, ActorAttributeOperations operation, int affectionLevelVal) {
        super(name, displayChar, true);
        this.operation = operation;
        this.affectionLevelVal = affectionLevelVal;
    }

    /**
     * List the possible actions that can perform on its owner and other actors.
     *
     * @param owner the actor that owns the "giveable" item.
     * @param map the map where the actor is performing the action on.
     * @return an unmodifiable list of Actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        Location ownerLocation = map.locationOf(owner);
        ActionList actions = new ActionList();
        for (Exit exit: ownerLocation.getExits()){
            Location exitDestination = exit.getDestination();
            Actor target = exitDestination.getActor();
            if (target != null && target.hasCapability(Ability.RECEIVE_CAPABLE) && target.hasCapability(Ability.AFFECTIONATE) && owner.hasCapability(Ability.GIVE_AFFECTION)){
                String direction = exit.getName();
                actions.add(new GiveAction(target, this, direction));
            }
        }
        return actions;
    }

    /**
     * Gives the "giveable" item from the giving actor to the receiving actors.
     * @param giver Giving actor (e.g. Player).
     * @param receiver Receiving actor (usually affectionate actors such as Guts).
     */
    public void giveToReceiver(Actor giver, Actor receiver){
        giver.removeItemFromInventory(this);
        receiver.addItemToInventory(this);
    }

    /**
     * Modifies the affection level of the affectionate actor.
     * @param affectionateActor Affectionate actor (e.g. Guts).
     */
    public void modifyAffectionLevel(Actor affectionateActor){
        affectionateActor.modifyAttribute(Attribute.AFFECTION_LEVEL, operation, affectionLevelVal);
        display.println("The affectionate level of the " + affectionateActor + " has been " + operation + " by " + affectionLevelVal);
    }
}
