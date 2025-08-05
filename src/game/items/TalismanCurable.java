package game.items;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * An interface representing the things that can be cured by talisman.
 *
 * @author Tee Zhi Hong
 */
public interface TalismanCurable {

    /**
     * Use talisman to cure things.
     *
     * @param actor Actor who executes the TalismanCureAction.
     * @param location A location that the entity to be cured by talisman is present.
     * @return whether the thing can be cured by talisman successfully as a boolean.
     */
    boolean talismanCure(Actor actor, Location location);

}
