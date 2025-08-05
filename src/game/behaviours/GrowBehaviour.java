package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.GrowAction;
import game.actors.npc.Growable;
/**
 * A behaviour that allows a Growable boss to grow.
 *
 * @author Liew Yi Wei
 */
public class GrowBehaviour implements Behaviour {

    private Growable Boss;
    /**
     * Constructor for GrowBehaviour.
     *
     * @param boss the Growable boss that will grow its part either Leaf or Branch.
     */
    public GrowBehaviour(Growable boss){
        this.Boss = boss;
    }

    /**
     * Returns the action of growing the boss.
     * If there is an exit that does not contain an actor, it will return a GrowAction.
     * Else, it will return null.
     *
     * @param actor the actor performing the grow action
     * @param map   the current game map
     * @return a GrowAction if there is an exit without an actor, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (!(exit.getDestination().containsAnActor())) {
                return new GrowAction(Boss);
            }
        }
        return null;
    }
}
