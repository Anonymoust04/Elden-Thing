package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * Interface representing a consumable item.
 *
 * @author Liew Yi Wei
 */
public interface Consumable {

    /**
     * Method to consume the item.
     *
     * @param actor The actor who is consuming the item.
     * @param map The map the actor is currently on.
     * @return A string describing the result of the consumption.
     */
    String consume(Actor actor, GameMap map);
}
