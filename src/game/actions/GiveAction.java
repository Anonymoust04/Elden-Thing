package game.actions;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;
import game.enums.*;
import game.items.*;

/**
 * Class representing an action to give a "giveable" item.
 *
 * @author Tee Zhi Hong
 */
public class GiveAction extends Action {

    /**
     * The Actor that is to receive the item.
     */
    private Actor receiver;

    /**
     * The item that is capable of being given.
     */
    private GiveableItem giveableItem;

    /**
     * The direction where the giving action should be performed.
     */
    private String direction;

    /**
     * GiveAction class constructor.
     * @param receiver      Receiving affectionate actor.
     * @param giveableItem  An item that is capable of giving.
     * @param direction     The String direction where the giving should be performed.
     */
    public GiveAction(Actor receiver, GiveableItem giveableItem, String direction) {
        this.receiver = receiver;
        this.giveableItem = giveableItem;
        this.direction = direction;
    }

    /**
     * Perform a giving action from a giving actor to the receiving affectionate actor.
     *
     * @param giver The actor performing the purchase action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor giver, GameMap map) {
        giveableItem.giveToReceiver(giver, receiver);
        giveableItem.modifyAffectionLevel(receiver);
        receiver.addCapability(Status.RECEIVED_AFFECTION);
        return giver + " successfully gives the " + giveableItem + " to " + receiver + " at " + direction;
    }

    /**
     * Describe what action will be performed if GiveAction is chosen in the menu.
     *
     * @param giver The actor performing the action.
     * @return the action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor giver) {
        return giver + " gives " + giveableItem + " to " + receiver + " at " + direction;
    }
}
