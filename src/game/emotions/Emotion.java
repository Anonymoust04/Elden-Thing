package game.emotions;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import game.enums.*;

/**
 * A class that represents an emotion
 *
 * @author Tee Zhi Hong.
 */
public class Emotion {

    private Status emotionStatus;
    private StatusEffect emotionStatusEffect;
    private Attribute emotionCountdownTimerAttribute;
    private int emotionCountdownTimer;
    private Display display = new Display();

    /**
     * Emotion class constructor.
     * @param emotionStatus Emotion status to be set on the affecting affectionate actor.
     * @param emotionStatusEffect Status effect of the emotion.
     * @param emotionCountdownTimerAttribute Countdown timer attribute for the emotion status effect.
     * @param emotionCountdownTimer Countdown timer value for the emotion status effect.
     */
    public Emotion(Status emotionStatus, StatusEffect emotionStatusEffect, Attribute emotionCountdownTimerAttribute, Integer emotionCountdownTimer){
        this.emotionStatus = emotionStatus;
        this.emotionStatusEffect = emotionStatusEffect;
        this.emotionCountdownTimerAttribute = emotionCountdownTimerAttribute;
        this.emotionCountdownTimer = emotionCountdownTimer;
    }

    /**
     * Sets the emotion including its status effect if the affectionate actor achieves the required affection level.
     * @param actor Actor that is affected by the emotion status effect.
     */
    public void setEmotion(Actor actor) {
        actor.modifyAttribute(emotionCountdownTimerAttribute, ActorAttributeOperations.UPDATE, emotionCountdownTimer);
        actor.addStatusEffect(emotionStatusEffect);
        actor.addCapability(emotionStatus);
        display.println(actor + " is now " + emotionStatus + " for " + emotionCountdownTimer + " turns.");
    }
}
