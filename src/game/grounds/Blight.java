package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.positions.Ground;
import game.actions.TalismanCureAction;
import game.enums.*;
import game.items.*;

/**
 * A class representing a blight covering the ground of the valley.
 *
 * @author Adrian Kristanto
 * <p> Modified by: Tee Zhi Hong </p>
 */
public class Blight extends Ground implements TalismanCurable {

    /**
     * Blight class constructor.
     */
    public Blight() {
        super('x', "Blight");
        addCapability(Status.CURSED);
    }

    /**
     * Returns an Action List which contains all actions that are allowed inside the Blight class.
     *
     * @param actor     the Actor acting.
     * @param location  the current Location.
     * @param direction the direction of the Blight from the Actor.
     * @return a new collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability((Ability.TALISMAN_CURE))){
            actions.add(new TalismanCureAction(this, location, direction));
        }
        return actions;
    }

    /**
     * Use talisman to cure the Blight ground.
     *
     * @param actor Actor who executes the TalismanCureAction.
     * @param location A location that the Blight to be cured by talisman is present.
     * @return whether the Blight ground can be cured by talisman successfully as a boolean.
     */
    @Override
    public boolean talismanCure(Actor actor, Location location) {

        int requiredStamina = 50;

        if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {

            if (actor.getAttribute(BaseActorAttributes.STAMINA) >= requiredStamina) {
                actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, requiredStamina);
            } else {
                return false;
            }
        }

        location.setGround(new Soil());

        return true;
    }
}
