package game.sideeffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that modifies the value of an attribute for a nearby actor in the main actor's surroundings.
 *
 * @author Tee Zhi Hong
 */
public class NearbyActorAttributeEffect implements SideEffect{

    private Enum<?> attributeName;
    private ActorAttributeOperations operation;
    private int value;
    private Display displayEffects = new Display();

    /**
     * NearbyActorAttributeEffect class constructor.
     * @param attributeName An Actor enumeration attribute which its value is to be modified.
     * @param operation An ActorAttributeOperations enumeration constant which can either increase, decrease or update the value of an Actor enumeration attribute.
     * @param value An integer value used for modify the enumeration attribute (increase, decrease or update the Actor enumeration attribute).
     */
    public NearbyActorAttributeEffect(Enum<?> attributeName, ActorAttributeOperations operation, int value){
        this.attributeName = attributeName;
        this.operation = operation;
        this.value = value;
    }

    /**
     * Executes the side effect by modifying the nearby actor enumeration attribute in the main actor's surroundings.
     *
     * @param mainActor The main actor
     * @param map       Current game map.
     */
    @Override
    public void execute(Actor mainActor, GameMap map) {
        Location currentLocation = map.locationOf(mainActor);
        for (Exit exit :currentLocation.getExits()){
            Location destination = exit.getDestination();
            Actor target = destination.getActor();
            if(target != null && target.hasAttribute(attributeName)){
                target.modifyAttribute(attributeName, operation, value);
                displayEffects.println("Successfully " + operation + " the value of an Actor attribute called " + attributeName + " by " + value + " to a nearby actor called " + target);
                return;
            }
        }

        displayEffects.println("Fails to " + operation + " the value of an Actor attribute called " + attributeName + " as there are no nearby actors around the current actor called " + mainActor);
    }
}
