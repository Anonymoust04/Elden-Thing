package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Condition class to check if an actor can spawn in a location.
 *
 * @author Tee Zhi Hong
 */
public class SpawnCondition implements Condition {

    /**
     * Checks if an actor can spawn in its given location, and if not, checks if it can spawn in any of the surrounding
     * locations around it.
     * @param newActor the actor that is to be spawned
     * @param currentLocation the initial location of the actor that is to be spawned
     * @return boolean value indicating if the condition is fulfilled
     */
    @Override
    public boolean isFulfilled(Actor newActor, Location currentLocation) {
        if (!currentLocation.containsAnActor() && currentLocation.canActorEnter(newActor)) {
            currentLocation.addActor(newActor);
            return true;
        }
        for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (!destination.containsAnActor() && destination.canActorEnter(newActor)) {
                destination.addActor(newActor);
                return true;
            }
        }
        return false;
    }
}
