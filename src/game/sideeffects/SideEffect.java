package game.sideeffects;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * An interface that represents all kinds of side effects.
 * @author Tee Zhi Hong
 */
public interface SideEffect {
    /**
     * Executes the side effect.
     *
     * @param mainActor The main actor
     * @param map       Current game map.
     */
    void execute(Actor mainActor, GameMap map);
}
