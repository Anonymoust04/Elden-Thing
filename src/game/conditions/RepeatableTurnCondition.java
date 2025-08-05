package game.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Condition class that checks if a certain number of turns have passed.
 * This condition can be used to trigger events or actions after a specified number of turns.
 * The condition is repeatable, meaning it can be fulfilled multiple times after the specified number of turns.
 *
 * @author Liew Yi Wei
 */
public class RepeatableTurnCondition implements Condition{

    /**
     * The number of turns after which the condition is fulfilled.
     */
    private int numTurns;

    /**
     * The current number of turns remaining before the condition is fulfilled.
     */
    private int currentNumTurns;

    /**
     * Constructor for RepeatableTurnCondition.
     *
     * @param numTurns the number of turns after which the condition is fulfilled
     */
    public RepeatableTurnCondition(int numTurns){
        this.numTurns = numTurns;
        this.currentNumTurns = numTurns;
    }

    /**
     * Returns a string representation of the condition.
     *
     * @return a string representation of the condition
     */
    @Override
    public boolean isFulfilled(Actor actor, Location location) {
        if (currentNumTurns == 0){
            currentNumTurns = numTurns;
            return true;
        }
        currentNumTurns--;
        return false;
    }
}
