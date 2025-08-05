package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.conditions.Condition;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class representing an action to listen to a FarmerListenable object.
 *
 * @author Nigel Wong Wei Lun
 */
public class ListenAction extends Action {

    /**
     * The Actor object to listen to.
     */
    private Actor target;

    /**
     * The direction of the action (only used for display purposes).
     */
    private String direction;

    /**
     * A list of monologues to be spoken by the target.
     */
    private List<String> monologues;

    /**
     * A map of conditional monologues to be spoken by the target.
     * The key is the monologue and the value is the condition that must be fulfilled.
     */
    private Map<String, Condition> conditionalMonologues;

    /**
     * A random number generator for selecting monologues.
     */
    private Random random = new Random();

    /**
     * Constructor for ListenAction.
     *
     * @param target the Actor object to listen to.
     * @param direction the direction of the action (only used for display purposes).
     */
    public ListenAction(Actor target, String direction, List<String> monologues, Map<String, Condition> conditionalMonologues) {
        this.target = target;
        this.direction = direction;
        this.monologues = monologues;
        this.conditionalMonologues = conditionalMonologues;
    }

    /**
     * Constructor for ListenAction.
     *
     * @param target the Actor object to listen to.
     * @param direction the direction of the action (only used for display purposes).
     * @param monologues the list of monologues to be spoken by the target.
     */
    public ListenAction(Actor target, String direction, List<String> monologues) {
        this.target = target;
        this.direction = direction;
        this.monologues = monologues;
    }

    /**
     * Constructor for ListenAction.
     *
     * @param target the FarmerListenable object to listen to.
     * @param direction the direction of the action (only used for display purposes).
     * @param conditionalMonologues the map of conditional monologues to be spoken by the target.
     */
    public ListenAction(Actor target, String direction, Map<String, Condition> conditionalMonologues) {
        this.target = target;
        this.direction = direction;
        this.conditionalMonologues = conditionalMonologues;
    }

    /**
     * Executes the action of listening to the target.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (conditionalMonologues != null){
            for (Map.Entry<String, Condition> entry: conditionalMonologues.entrySet()){
                String monologue = entry.getKey();
                Condition condition = entry.getValue();
                Location actorLocation = map.locationOf(actor);
                if (condition.isFulfilled(actor, actorLocation)){
                    return monologue;
                }
            }
        }

        if (monologues != null){
            String randomMonologue =  monologues.get(random.nextInt(monologues.size()));
            return randomMonologue;
        }
        return "No monologue obtained";
    }

    /**
     * Returns a string describing the action for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + target + " at " + direction;
    }
}
