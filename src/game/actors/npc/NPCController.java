package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;

/**
 * Interface for NPC controllers.
 * <p>
 * This interface defines the contract for NPC controllers, which are responsible for processing the play turn of an NPC.
 * </p>
 *
 * @author Tee Zhi Hong
 */
public interface NPCController {

    /**
     * Processes the play turn of an NPC by iterating through its behaviours and returning the first non-null action.
     *
     * @param behaviours a map of behaviours for the NPC
     * @param actor      the NPC actor
     * @param map        the game map
     * @return the action to be performed by the NPC
     */
    Action processPlayTurn(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map);
}
