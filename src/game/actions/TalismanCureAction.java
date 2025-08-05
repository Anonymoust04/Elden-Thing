package game.actions;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;
import game.items.TalismanCurable;

/**
 * Class representing an action to cure things using talisman.
 *
 * @author Tee Zhi Hong
 */
public class TalismanCureAction extends Action {

    private TalismanCurable talismanCurable;
    private Location location;
    private String direction;

    /**
     * TalismanCureAction class constructor.
     *
     * @param talismanCurable the entity that is curable by talisman.
     * @param location the location where the entity to be cured by talisman is located.
     * @param direction the direction from the actor where the entity to be cured by talisman is located.
     */
    public TalismanCureAction(TalismanCurable talismanCurable, Location location, String direction) {
        this.talismanCurable = talismanCurable;
        this.location = location;
        this.direction = direction;
    }

    /**
     * Perform the cure action for entities that are curable by talisman.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing who has dropped which item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.locationOf(actor) == location){
            return "Error: cannot cure the blight where the performing actor stands on it";
        }
        boolean cured = talismanCurable.talismanCure(actor, location);
        if (!cured){
            return "Error: Unable to cure the " + talismanCurable;
        }
        return talismanCurable + " has been cured by " + actor + " using talisman";
    }

    /**
     * Describe what action will be performed if TalismanCureAction is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " cures the " + talismanCurable + " at " + direction + " with Talisman";
    }

}
