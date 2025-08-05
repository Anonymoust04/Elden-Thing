package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing an action to attack
 * <p> Note that the attacker must have a weapon, e.g.,
 * an intrinsic weapon or a weapon item.
 * Otherwise, the execute method will throw an error. </p>
 *
 * @author Adrian Kristanto
 * <p> Modified by: Tee Zhi Hong </p>
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked.
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack.
     */
    private Weapon weapon;

    /**
     * AttackAction class constructor with external weapon as default.
     *
     * @param target the Actor to attack.
     * @param direction the direction where the attack should be performed (only used for display purposes).
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * AttackAction class constructor with intrinsic weapon as default.
     *
     * @param target the actor to attack.
     * @param direction the direction where the attack should be performed (only used for display purposes).
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform an attack action to another actor.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Describe what action will be performed if AttackAction is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
