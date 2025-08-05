package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.conditions.*;
import game.enums.Ability;
import game.attributes.AffectionAttribute;
import game.enums.Attribute;
import game.enums.Status;
import game.emotions.EmotionConditionPair;
import game.emotions.Emotion;
import game.handlers.EmotionHandler;
import game.sideeffects.NearbyActorAttributeEffect;
import game.sideeffects.SideEffect;
import game.statuseffects.AngryEffect;
import game.statuseffects.HappyEffect;
import game.weapons.BareFist;

import java.util.*;

/**
 * Class representing the Guts NPC.
 * <p>
 * This class extends NonPlayerCharacter and implements FarmerListenable.
 * </p>
 *
 * @author Nigel Wong Wei Lun
 * Modified by: Tee Zhi Hong
 */
public class Guts extends Actor implements NPCControllerSelectable {

    /**
     * The NPC controller for Guts.
     */
    private NPCController npcController = new StandardNPCController();

    /**
     * A map of behaviours for Guts.
     * The key is an integer representing the priority of the behaviour.
     * The value is the Behaviour object.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    private static final int HAPPY_AFFECTIONATE_LEVEL = 65;

    private static final int ANGRY_AFFECTIONATE_LEVEL = 30;
    private static final int HAPPY_PRIORITY = 1;
    private static final int ANGRY_PRIORITY = 2;
    private static final int HAPPY_COUNTDOWN_TIMER_VALUE = 5;
    private static final int ANGRY_COUNTDOWN_TIMER_VALUE = 10;
    private static final int ATTACK_PRIORITY = 1;
    private static final int WANDER_PRIORITY = 999;
    private static final int HEAL_HP = 15;
    private static final int HURT_HP = 30;

    private EmotionHandler emotionHandler = new EmotionHandler(getEmotionStatusAttributeMap(), getEmotionConditions());

    /**
     * Constructor for Guts.
     * <p>
     * Sets the name, display character, and hit points of the NPC and adds the AttackBehaviour and WanderBehaviour.
     * The behaviours are stored in a map with their respective priorities where first priority is AttackBehaviour
     * and least priority is WanderBehaviour.
     * </p>
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        behaviours.put(ATTACK_PRIORITY, new AttackBehaviour());
        behaviours.put(WANDER_PRIORITY, new WanderBehaviour());
        addCapability(Ability.RECEIVE_CAPABLE);
        addCapability(Ability.AFFECTIONATE);
        addAttribute(Attribute.AFFECTION_LEVEL, new AffectionAttribute());
        addAttribute(Attribute.HAPPY_COUNTDOWN_TIMER, new BaseActorAttribute(HAPPY_COUNTDOWN_TIMER_VALUE));
        addAttribute(Attribute.ANGRY_COUNTDOWN_TIMER, new BaseActorAttribute(ANGRY_COUNTDOWN_TIMER_VALUE));
    }

    /**
     * Sets the NPCController during the initialisation.
     * @param npcController NPCController selected during the initialisation.
     */
    @Override
    public void selectNPCController(NPCController npcController) {
        this.npcController = npcController;
    }

    /**
     * Obtains a Map that consists of emotion status attribute pairs for Guts.
     * @return a Map that consists of emotion status attribute pairs for Guts.
     */
    public Map<Status, Attribute> getEmotionStatusAttributeMap(){
        Map<Status, Attribute> statusAttributeMap = new HashMap<>();
        statusAttributeMap.put(Status.HAPPY, Attribute.HAPPY_COUNTDOWN_TIMER);
        statusAttributeMap.put(Status.ANGRY, Attribute.ANGRY_COUNTDOWN_TIMER);
        return statusAttributeMap;
    }

    /**
     * Obtains a TreeMap that consists of priority and its corresponding emotion condition pair for Guts.
     * @return a TreeMap that consists of priority and its corresponding emotion condition pair for Guts.
     */
    public TreeMap<Integer, EmotionConditionPair> getEmotionConditions(){
        TreeMap<Integer, EmotionConditionPair> emotionConditions = new TreeMap<>();
        emotionConditions.put(HAPPY_PRIORITY, getHappyConditionPair());
        emotionConditions.put(ANGRY_PRIORITY, getAngryConditionPair());
        return emotionConditions;
    }

    /**
     * Obtains the emotion and condition pair for happy emotion for Guts.
     * @return Emotion and condition pair for happy emotion for Guts.
     */
    public EmotionConditionPair getHappyConditionPair(){
        List<SideEffect> happySideEffects = new ArrayList<>();
        happySideEffects.add(new NearbyActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HEAL_HP));

        Condition happyCondition = new HighAffectionCondition(HAPPY_AFFECTIONATE_LEVEL);
        Emotion happyEmotion = new Emotion(Status.HAPPY, new HappyEffect(happySideEffects), Attribute.HAPPY_COUNTDOWN_TIMER, HAPPY_COUNTDOWN_TIMER_VALUE);

        return new EmotionConditionPair(happyCondition, happyEmotion);
    }

    /**
     * Obtains the emotion and condition pair for angry emotion for Guts.
     * @return Emotion and condition pair for angry emotion for Guts.
     */
    public EmotionConditionPair getAngryConditionPair(){
        List<SideEffect> angrySideEffects = new ArrayList<>();
        angrySideEffects.add(new NearbyActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, HURT_HP));

        Condition angryCondition = new LowAffectionCondition(ANGRY_AFFECTIONATE_LEVEL);
        Emotion angryEmotion = new Emotion(Status.ANGRY, new AngryEffect(angrySideEffects), Attribute.ANGRY_COUNTDOWN_TIMER, ANGRY_COUNTDOWN_TIMER_VALUE);

        return new EmotionConditionPair(angryCondition, angryEmotion);
    }

    /**
     * Returns the behaviours of Guts.
     *
     * @return monologues of Guts
     */
    public List<String> getMonologues() {
        List<String> monologues = new ArrayList<>();
        monologues.add("RAAAAGH!");
        monologues.add("I’LL CRUSH YOU ALL!");
        return monologues;
    }

    /**
     * Returns the conditional monologues of Guts.
     * <p>
     * The key is the monologue and the value is the condition that must be fulfilled.
     * When the farmers health below 50, the conoditional monologue will be triggered.
     * </p>
     *
     * @return conditional monologues of Guts
     */
    public Map<String, Condition> getConditionalMonologues() {
        Map<String, Condition> conditionalMonologues = new HashMap<>();
        conditionalMonologues.put("WEAK! TOO WEAK TO FIGHT ME!", new LowHealthCondition(50));
        return conditionalMonologues;
    }

    /**
     * Select and return an action to perform by Guts on the current turn.
     *
     * @param actions    collection of possible Actions for this Player.
     * @param lastAction The Action this Player took last turn. Can do interesting things in conjunction with Action.getNextAction().
     * @param map        the map containing the Player.
     * @param display    the I/O object to which messages may be written.
     * @return the Action to be performed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            display.println(unconscious(map));
            return new DoNothingAction();
        }

        if (hasCapability(Status.RECEIVED_AFFECTION)) {
            emotionHandler.handleEmotion(this, map.locationOf(this));
        }

        return npcController.processPlayTurn(behaviours, this, map);
    }

    /**
     * Returns a list of actions that the Guts NPC can perform.
     * <p>
     * This method adds the ListenAction to the list of actions if the actor has the FARMER_LISTENABLE capability.
     * </p>
     *
     * @param otherActor The actor performing the action.
     * @param direction  The direction of the action.
     * @param map        The map the actor is on.
     * @return A list of actions that the Guts NPC can perform.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        if(otherActor.hasCapability(Ability.LISTENABLE)){
            actions.add(new ListenAction(this, direction, getMonologues(), getConditionalMonologues()));
        }
        return actions;
    }

    /**
     * A method for returning the string representation of Guts.
     * It displays the Gut's name and its current health (hit points), along with its maximum health (hit points).
     *
     * @return Guts details in String
     */
    @Override
    public String toString() {
        return name + " (Health: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", Affection Level To Farmer: " + this.getAttribute(Attribute.AFFECTION_LEVEL) + "/" + this.getAttributeMaximum(Attribute.AFFECTION_LEVEL) + ')';
    }
}
