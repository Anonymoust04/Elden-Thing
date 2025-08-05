package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Growable;
/**
 * Class representing an action to grow a boss part.
 *
 * @author Liew Yi Wei
 */
public class GrowAction extends Action {

    /**
     * The Growable boss that will grow its part either Leaf or Branch.
     */
    private Growable boss;

    /**
     * Constructor for GrowAction.
     *
     * @param boss the Growable boss that will grow its part either Leaf or Branch.
     */
    public GrowAction(Growable boss){
        this.boss = boss;
    }

    /**
     * Executes the grow action, let the boss grow.
     *
     * @param actor the actor performing the action
     * @param map current game map
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Display display = new Display();
        boss.grow(display);
        return "The boss now is stronger....";
    }

    /**
     * Provides a description of the action for the menu.
     *
     * @param actor the actor performing the action
     * @return a string describing the action for the menu
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + "leaving causes Bed of Chaos to grow...";
    }
}
