package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A condition class that checks if an actor has any specified entities affliated with specified status around itself.
 *
 * @author Tee Zhi Hong
 */
public class NearStatusCondition implements Condition {

    private Status status;
    private Location targetActorLocation;

    /**
     * Constructor for NearStatusCondition.
     *
     * @param status The status to check for.
     * @param targetActorLocation The location of the actor to check around.
     */
    public NearStatusCondition(Status status, Location targetActorLocation){
        this.status = status;
        this.targetActorLocation = targetActorLocation;
    }

    /**
     * Check if there is any specified entities with specified status around the actor.
     * @param actor
     * @param location
     * @return Boolean value indicating if the condition is fulfilled.
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        for (Exit exit: targetActorLocation.getExits()){
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(status) || destination.containsAnActor() && destination.getActor().hasCapability(status) || checkItemsStatus(destination)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the item on a specified location has a specified status.
     * @param location
     * @return Boolean value indicating if the item has a specified status.
     */
    public boolean checkItemsStatus(Location location){
        for (Item item: location.getItems()){
            if (item.hasCapability(status)){
                return true;
            }
        }
        return false;
    }
}
