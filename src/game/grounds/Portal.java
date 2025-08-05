package game.grounds;
import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.enums.Ability;

import java.util.*;

/**
 * A class that represents a teleportation circle.
 *
 * @author Joel Wong Sing Yue
 * Modified by: Tee Zhi Hong
 */
public class Portal extends Ground {

    private Set<Location> locationSet = new HashSet<>();

    /**
     * Portal class constructor.
     */
    public Portal() {
        super('A', "Portal");
    }

    /**
     * Sets the location set that stores a set of locations where a teleportation cycle is present in each location.
     * @param locationSet A location set that stores a set of locations where a teleportation cycle is present in each location.
     */
    public void setLocationSet(Set<Location> locationSet){
        this.locationSet = locationSet;
    }

    /**
     * Adds a location where the teleportation cycle is present.
     * @param location A location where the teleportation cycle is present there.
     */
    public void addLocation(Location location){
        locationSet.add(location);
    }

    /**
     * Returns a list of possible actions that the Portal can provide to the actor (e.g. Player).
     * @param actor     the Actor acting.
     * @param location  the current Location.
     * @param direction the direction of the Ground from the Actor.
     * @return A list of possible actions that the Portal can provide to the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        GameMap map = location.map();
        Location currentActorLocation = map.locationOf(actor);

        if (locationSet.contains(currentActorLocation))
        {
            for (Location destination : locationSet) {
                if (actor.hasCapability(Ability.TELEPORT) && destination != location) {
                    String directionString = "to " + destination + " through the portal";
                    actions.add(new MoveActorAction(destination, directionString));
                }
            }
        }
        return actions;
    }
}
