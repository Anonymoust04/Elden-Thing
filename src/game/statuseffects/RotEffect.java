package game.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Attribute;

/**
 * A StatusEffect child class that provides a rotting effect to rotting creatures.
 *
 * @author Tee Zhi Hong
 */
public class RotEffect extends StatusEffect {

    /**
     * RotEffect class constructor.
     */
    public RotEffect() {
        super("RotEffect");
    }

    /**
     * Inform a rotting effect for rotting creatures of the passage of time.
     * <p> The countdown timer for rotting creatures will be decrement by 1 for each game tick. </p>
     *
     * @param location the location where the actor with the status effect is currently standing.
     * @param actor the actor holding the status effect.
     */
    @Override
    public void tick(Location location, Actor actor) {
        actor.modifyAttribute(Attribute.ROT_COUNTDOWN_TIMER, ActorAttributeOperations.DECREASE, 1);
    }

}