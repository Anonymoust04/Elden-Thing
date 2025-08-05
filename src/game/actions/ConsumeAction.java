package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Class representing an action to consume a consumable item.

 * @author Liew Yi Wei
 */
public class ConsumeAction extends Action {

    /**
     * The consumable item that is to be consumed.
     */
    public Consumable consumable;

    /**
     * Constructor for ConsumeAction.
     *
     * @param consumable the consumable item to be eaten.
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Execute the consume action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is currently on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consume(actor, map);
    }

    /**
     * Returns a string describing the action for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action on the menu display.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + consumable;
    }
}
