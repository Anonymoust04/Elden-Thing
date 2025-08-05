package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * StandardNPCController class implements the NPCController interface.
 * This class is responsible for processing the play turn of an NPC by iterating through its behaviours and returning
 * the first non-null action.
 *
 * @author Tee Zhi Hong
 */
public class StandardNPCController implements NPCController{

    /**
     * StandardNPCController class constructor.
     */
    public StandardNPCController(){}

    /**
     * Processes the play turn of an NPC by iterating through its behaviours and returning the actions that can be performed.
     *
     * @param behaviours a map of behaviours for the NPC
     * @param actor      the NPC actor
     * @param map        the game map
     * @return the action to be performed by the NPC
     */
    @Override
    public Action processPlayTurn(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map){
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
