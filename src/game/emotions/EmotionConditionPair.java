package game.emotions;

import game.conditions.*;
import game.emotions.Emotion;

/**
 * A class that represents a pair of emotion with its corresponding conditions
 *
 * @author Tee Zhi Hong
 */
public class EmotionConditionPair {

    private Condition condition;

    private Emotion emotion;

    /**
     * EmotionConditionPair class constructor.
     * @param condition A Condition class to set up the emotion.
     * @param emotion An Emotion class for representing emotions.
     */
    public EmotionConditionPair(Condition condition, Emotion emotion){
        this.condition = condition;
        this.emotion = emotion;
    }

    /**
     * Obtains the condition for setting up the emotion.
     * @return Condition for setting up the emotion.
     */
    public Condition getCondition(){
        return condition;
    }

    /**
     * Gets an Emotion class for representing emotions.
     * @return An Emotion class for representing emotions.
     */
    public Emotion getEmotion(){
        return emotion;
    }
}
