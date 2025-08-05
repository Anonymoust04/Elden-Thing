package game.actors.npc;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents an Actor that can reproduce
 *
 * @author Joel Wong Sing Yue
 * Modified by: Tee Zhi Hong
 */
public interface Reproduceable {

    /**
     * Reproduce method for the Reproduceable interface.
     * @param actor the actor that is reproducing
     * @param destination the location where the new entity will spawn
     */
    void reproduce (Actor actor, Location destination);

}
