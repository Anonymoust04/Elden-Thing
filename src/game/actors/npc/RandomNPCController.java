package game.actors.npc;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.positions.*;

import java.util.*;

/**
 * RandomNPCController class implements the NPCController interface.
 * This class is responsible for processing the play turn of an NPC by selecting a behaviour by random and
 * returns an action if it fulfills the condition and DoNothingAction otherwise.
 *
 * @author Joel Wong Sing Yue
 * Modified by: Tee Zhi Hong
 */
public class RandomNPCController implements NPCController {
    private final Random random = new Random();

    /**
     * Process the play turn and selects the behaviour by random. Returns an action if the behaviour has been fulfilled and DoNothingAction otherwise.
     * @param behaviours a map of behaviours for the NPC
     * @param actor      the NPC actor
     * @param map        the game map
     * @return An action if the behaviour has been fulfilled and DoNothingAction otherwise.
     */
    @Override
    public Action processPlayTurn(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        List<Behaviour> behaviourList = new ArrayList<>(behaviours.values());
        if (behaviourList.isEmpty()) {
            return new DoNothingAction();
            }
        Behaviour chosen = behaviourList.get(random.nextInt(behaviourList.size()));
        Action action = chosen.getAction(actor, map);
        if (action != null) {
            return action;
        } else {
            return new DoNothingAction();
        }
    }
}