package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor
 *
 * @author Riordan D. Alfredo
 * <p> Modified by: Tee Zhi Hong </p>
 */
public class Wall extends Ground {

    /**
     * Wall class constructor
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * Returns true if an Actor can enter this location.
     *
     * <p> Actors can enter a location if the terrain is passable and there isn't an Actor there already.
     * Game rule. One actor per location. </p>
     *
     * @param actor the Actor who might be moving.
     * @return true if the Actor can enter this location.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
