package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Shoveable;

/**
 * An action that allows an actor to shovel a target or the ground.
 * This action can be used to interact with shoveable items or the ground itself.
 *
 * @author Nigel Wong Wei Lun
 */
public class ShovelAction extends Action {

    /**
     * The shoveable item that is being used to shovel
     * */
    private Shoveable shoveable;
    /**
     * The target actor that is being shoveled,
     * can be null if shoveling the ground
     * */
    private Actor target;

    /**
     * Constructor for ShovelAction.
     *
     * * @param shoveable the shoveable item to be used for shoveling
     */
    public ShovelAction(Shoveable shoveable) {
        this.shoveable = shoveable;
    }

    /**
     * Constructor for ShovelAction with a target actor.
     *
     * @param shoveable the shoveable item to be used for shoveling
     * @param target the target actor to be shoveled, can be null if shoveling the ground
     */
    public ShovelAction(Shoveable shoveable, Actor target) {
        this.shoveable = shoveable;
        this.target = target;
    }

    /**
     * Executes the shoveling action.
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return shoveable.shovel(actor, map);
    }

    /**
     * Provides a description of the action for the menu.
     * Checks if a target is specified and formats the description accordingly.
     * If no target is specified, it shows shoveling the ground.
     *
     * @param actor the actor performing the action
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        if (target == null) {
            return actor + " shovels the ground with " + shoveable.toString() + ".";
        }
        return actor + " shovels the " + target +  " with " + shoveable.toString() + ".";
    }
}
