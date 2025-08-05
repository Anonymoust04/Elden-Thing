package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing an item that can be shoveled.
 * This interface defines the method for shoveling an item or the ground.
 * Implementing classes should provide specific behavior for shoveling.
 *
 * @author Nigel Wong Wei Lun
 */
public interface Shoveable {

    /**
     * Shovels the item or the ground.
     *
     * @param actor the actor performing the shoveling action
     * @param map the current game map
     * @return a string describing the result of the shoveling action
     */
    String shovel(Actor actor, GameMap map);
}
