package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * Class representing a behaviour that attacks actor it finds in an adjacent location.
 * <p>
 * This class is used by NPCs to attack other actors at adjacent locations.
 * </p>
 *
 * @author Nigel Wong Wei Lun
 */
public class AttackBehaviour implements Behaviour {

    /**
     * This method checks for adjacent exits and returns an AttackAction if it finds a target.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return An AttackAction if a target is found, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                if (target.isConscious()) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }
}
