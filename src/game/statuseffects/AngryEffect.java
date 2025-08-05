package game.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Attribute;
import game.enums.Status;
import game.sideeffects.SideEffect;

import java.util.List;

/**
 * A StatusEffect child class that provides an angry effect to affectionate actors.
 *
 * @author Tee Zhi Hong
 */
public class AngryEffect extends StatusEffect {

    private List<SideEffect> sideEffects;

    private Display display = new Display();

    /**
     * AngryEffect class constructor.
     * @param sideEffects A list of side effects while having the angry status effect.
     */
    public AngryEffect(List<SideEffect> sideEffects) {
        super("Angry Effect");
        this.sideEffects = sideEffects;
    }

    /**
     * Inform an angry effect for angry creatures of the passage of time.
     * <p> The countdown timer for rotting creatures will be decrement by 1 for each game tick. </p>
     *
     * @param location the location where the actor with the status effect is currently standing.
     * @param actor the actor holding the angry effect.
     */
    @Override
    public void tick(Location location, Actor actor) {
        actor.modifyAttribute(Attribute.ANGRY_COUNTDOWN_TIMER, ActorAttributeOperations.DECREASE, 1);
        if (actor.getAttribute(Attribute.ANGRY_COUNTDOWN_TIMER) <= 0){
            actor.modifyAttribute(Attribute.ANGRY_COUNTDOWN_TIMER, ActorAttributeOperations.UPDATE, 0);
            actor.removeStatusEffect(this);
            actor.removeCapability(Status.ANGRY);
            display.println(this + " has been removed from " + actor);
            return;
        }

        display.println(actor + " is currently angry (Countdown Timer: " + actor.getAttribute(Attribute.ANGRY_COUNTDOWN_TIMER) + "/" + actor.getAttributeMaximum(Attribute.ANGRY_COUNTDOWN_TIMER) + ")");

        if (sideEffects == null) return;

        for (SideEffect sideEffect :sideEffects){
            sideEffect.execute(actor, location.map());
        }
    }
}
