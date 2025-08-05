package game.behaviours;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

import java.util.Random;

/**
 * A behaviour that allows non-playable characters to wander around.
 *
 * @author Created by: Riordan D. Alfredo
 * <p> Modified by: Tee Zhi Hong </p>
 */
public class WanderBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ActionList actions = new ActionList();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (actions.size() != 0 ) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}

