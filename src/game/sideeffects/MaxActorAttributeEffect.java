package game.sideeffects;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * A class that modify the maximum values inside the enumeration attributes of an Actor as a side effect.
 *
 * @author Tee Zhi Hong
 */
public class MaxActorAttributeEffect implements SideEffect {

    private Enum<?> attributeName;
    private ActorAttributeOperations operation;
    private int value;
    private Display displayEffects = new Display();

    /**
     * MaxActorAttributeEffect class constructor.
     * @param attributeName An Actor enumeration attribute which its maximum value is to be modified.
     * @param operation An ActorAttributeOperations enumeration constant which can either increase, decrease or update the maximum value of an Actor enumeration attribute.
     * @param value An integer value used for modify the maximum value of the enumeration attribute (increase, decrease or update the Actor enumeration attribute).
     */
    public MaxActorAttributeEffect(Enum<?> attributeName, ActorAttributeOperations operation, int value){
        this.attributeName = attributeName;
        this.operation = operation;
        this.value = value;
    }

    /**
     * Executes the side effect by modifying the maximum value of the actor enumeration attribute.
     *
     * @param mainActor The main actor
     * @param map       Current game map.
     */
    @Override
    public void execute(Actor mainActor, GameMap map) {
        if (!mainActor.hasAttribute(attributeName)){
            displayEffects.println("Does not have the attribute called " + attributeName + " inside " + mainActor);
            return;
        }

        mainActor.modifyAttributeMaximum(attributeName, operation, value);
        displayEffects.println("Successfully " + operation + " the maximum value of an Actor attribute called " + attributeName + " by " + value +  " inside " + mainActor);
    }
}
