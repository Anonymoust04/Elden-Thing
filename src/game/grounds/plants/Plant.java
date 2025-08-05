package game.grounds.plants;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * An interface that represent a plant planted on the Ground.
 *
 * @author Tee Zhi Hong
 */
public interface Plant {

    /**
     * Obtains the effects of plating a plant.
     *
     * @param owner Actor who owns a seed item.
     * @param map current game map.
     * @return whether the plant seed is planted successfully as a boolean.
     */
    boolean plant(Actor owner, GameMap map);
}