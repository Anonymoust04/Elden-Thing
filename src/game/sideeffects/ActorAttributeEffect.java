package game.sideeffects;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;

/**
 * A class that modify the values inside the enumeration attributes of an Actor as a side effect.
 *
 * @author Tee Zhi Hong
 */
public class ActorAttributeEffect implements SideEffect {

    private Enum<?> attributeName;
    private ActorAttributeOperations operation;
    private int value;
    private Display displayEffects = new Display();

    /**
     * ActorAttributeEffect class constructor.
     * @param attributeName An Actor enumeration attribute which its value is to be modified.
     * @param operation An ActorAttributeOperations enumeration constant which can either increase, decrease or update the value of an Actor enumeration attribute.
     * @param value An integer value used for modify the value of the enumeration attribute (increase, decrease or update the Actor enumeration attribute).
     */
    public ActorAttributeEffect(Enum<?> attributeName, ActorAttributeOperations operation, int value){
        this.attributeName = attributeName;
        this.operation = operation;
        this.value = value;
    }

    /**Z
     * Executes the side effect by modifying the value of the actor enumeration attribute.
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

        mainActor.modifyAttribute(attributeName, operation, value);
        displayEffects.println("Successfully " + operation + " the value of an Actor attribute called " + attributeName + " by " + value + " inside " + mainActor);
    }
}
