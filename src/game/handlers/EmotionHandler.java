package game.handlers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Location;
import game.conditions.*;
import game.emotions.Emotion;
import game.emotions.EmotionConditionPair;
import game.enums.Attribute;
import game.enums.Status;
import java.util.*;

/**
 * A class that sets emotions (Emotion Status Effect) after receiving an affection.
 *
 * @author Tee Zhi Hong
 */
public class EmotionHandler {

    private Map<Status, Attribute> emotionStatusAttributeMap;
    private TreeMap<Integer, EmotionConditionPair> emotionConditions;
    private static final int CLEAR_VALUE = 0;

    /**
     * EmotionHandler class constructor.
     * @param emotionStatusAttributeMap A map value that consists of Status and its countdown timer attribute pairs.
     * @param emotionConditions A tree map value that consists of integer priority and emotion condition pairs.
     */
    public EmotionHandler(Map<Status, Attribute> emotionStatusAttributeMap, TreeMap<Integer, EmotionConditionPair> emotionConditions){
        this.emotionStatusAttributeMap = emotionStatusAttributeMap;
        this.emotionConditions = emotionConditions;
    }

    /**
     * Clears all the emotion statuses (including its status effects and countdown timer) inside an affectionate actor before setting a new emotion status.
     * @param affectionateActor An affectionate actor which its emotion statuses are removed (including its status effects and countdown timer).
     */
    public void clearEmotionStatuses(Actor affectionateActor){
        for (Map.Entry<Status, Attribute> entry: emotionStatusAttributeMap.entrySet()){
            Status status = entry.getKey();
            Attribute countdownAttribute = entry.getValue();
            if (affectionateActor.hasCapability(status)){
                affectionateActor.modifyAttribute(countdownAttribute, ActorAttributeOperations.UPDATE, CLEAR_VALUE);
            }
        }
    }

    /**
     * Clears and set new emotion status (including its status effects and countdown timer) inside an affectionate actor after receiving affection.
     * Removes the "received affection" status after a new emotion status has been set.
     * @param affectionateActor An affectionate actor which its current emotion statuses removed and adds the latest emotion status after receiving affection based on the current affection level.
     * @param location A location whether the affectionate actor is located.
     */
    public void handleEmotion(Actor affectionateActor, Location location){
        clearEmotionStatuses(affectionateActor);
        for (Map.Entry<Integer, EmotionConditionPair> entry: emotionConditions.entrySet()){
            EmotionConditionPair emotionConditionPair = entry.getValue();
            Condition condition = emotionConditionPair.getCondition();
            Emotion emotion = emotionConditionPair.getEmotion();
            if (condition.isFulfilled(affectionateActor, location)){
                emotion.setEmotion(affectionateActor);
                break;
            }
        }
        affectionateActor.removeCapability(Status.RECEIVED_AFFECTION);
    }

}
