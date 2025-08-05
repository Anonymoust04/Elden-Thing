package game.actors.npc;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;
import game.actions.*;
import game.attributes.AffectionAttribute;
import game.behaviours.*;
import game.conditions.Condition;
import game.conditions.HighAffectionCondition;
import game.conditions.StatusCondition;
import game.enums.*;
import game.emotions.EmotionConditionPair;
import game.emotions.Emotion;
import game.handlers.EmotionHandler;
import game.sideeffects.*;
import game.statuseffects.HappyEffect;
import game.weapons.*;

import java.util.*;

/**
 * Class representing the Sellen NPC.
 * <p>
 * This class extends NonPlayerCharacter and implements FarmerListenable.
 * </p>
 *
 * @author Nigel Wong Wei Lun
 * Modified by: Tee Zhi Hong
 */
public class Sellen extends Actor implements NPCControllerSelectable {

    /**
     * The NPC controller for Sellen.
     */
    private NPCController npcController = new StandardNPCController();

    /**
     * A map of behaviours for Sellen.
     * The key is an integer representing the priority of the behaviour.
     * The value is the Behaviour object.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    private static final int HAPPY_AFFECTIONATE_LEVEL = 60;

    private static final int HAPPY_PRIORITY = 1;

    private static final int HAPPY_COUNTDOWN_TIMER_VALUE = 10;

    private static final int WANDER_PRIORITY = 999;

    private static final int BROADSWORD_PRICE = 100;

    private static final int DRAGONSLAYER_PRICE = 1500;

    private static final int KATANA_PRICE = 500;

    private EmotionHandler emotionHandler = new EmotionHandler(getEmotionStatusAttributeMap(), getEmotionConditions());

    /**
     * Constructor for Sellen.
     * <p>
     * Sets the name, display character, and hit points of the NPC and add the WanderBehaviour.
     * The behaviours are stored in a map with their respective priorities where
     * the least priority is WanderBehaviour.
     * </p>
     */
    public Sellen() {
        super("Sellen", 's', 150);
        behaviours.put(WANDER_PRIORITY, new WanderBehaviour());
        addCapability(Ability.RECEIVE_CAPABLE);
        addCapability(Ability.AFFECTIONATE);
        addAttribute(Attribute.AFFECTION_LEVEL, new AffectionAttribute());

        addAttribute(Attribute.HAPPY_COUNTDOWN_TIMER, new BaseActorAttribute(HAPPY_COUNTDOWN_TIMER_VALUE));
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
     * Obtains a Map that consists of emotion status attribute pairs for Sellen.
     * @return a Map that consists of emotion status attribute pairs for Sellen.
     */
    public Map<Status, Attribute> getEmotionStatusAttributeMap(){
        Map<Status, Attribute> emotionStatusAttributeMap = new HashMap<>();
        emotionStatusAttributeMap.put(Status.HAPPY, Attribute.HAPPY_COUNTDOWN_TIMER);
        return emotionStatusAttributeMap;
    }

    /**
     * Obtains a TreeMap that consists of priority and its corresponding emotion and condition pair for Sellen.
     * @return a TreeMap that consists of priority and its corresponding emotion and condition pair for Sellen.
     */
    public TreeMap<Integer, EmotionConditionPair> getEmotionConditions(){
        TreeMap<Integer, EmotionConditionPair> emotionConditions = new TreeMap<>();
        emotionConditions.put(HAPPY_PRIORITY, getHappyConditionPair());
        return emotionConditions;
    }

    /**
     * Obtains the emotion and condition pair for happy emotion for Sellen.
     * @return Emotion and condition pair for happy emotion for Sellen.
     */
    public EmotionConditionPair getHappyConditionPair(){

        Condition happyCondition = new HighAffectionCondition(HAPPY_AFFECTIONATE_LEVEL);
        Emotion happyEmotion = new Emotion(Status.HAPPY, new HappyEffect(), Attribute.HAPPY_COUNTDOWN_TIMER, HAPPY_COUNTDOWN_TIMER_VALUE);

        return new EmotionConditionPair(happyCondition, happyEmotion);
    }

    /**
     * Returns a list of monologues that Sellen can say.
     * <p>
     * This method returns a list of monologues that Sellen can say.
     * </p>
     *
     * @return A list of monologues that Sellen can say.
     */
    public List<String> getMonologues() {
        List<String> monologues = new ArrayList<>();
        monologues.add("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        monologues.add("You sense it too, don’t you? The Glintstone hums, even now.");
        return monologues;
    }

    /**
     * Select and return an action to perform by Sellen on the current turn.
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
     * Returns a list of actions that the Sellen NPC can perform.
     * <p>
     * This method adds the ListenAction to the list of actions if the actor has the FARMER_LISTENABLE capability.
     * </p>
     *
     * @param otherActor The actor performing the action.
     * @param direction  The direction of the action.
     * @param map        The map the actor is on.
     * @return A list of actions that can be performed.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        if(otherActor.hasCapability(Ability.LISTENABLE)){
            actions.add(new ListenAction(this, direction, getMonologues()));
        }

        if(otherActor.hasCapability(Ability.PURCHASE_CAPABLE)) {
            actions.add(new PurchaseAction(new Broadsword(), this, BROADSWORD_PRICE, getDeductionConditions(BROADSWORD_PRICE), direction, sellBroadswordEffect()));
            actions.add(new PurchaseAction(new Dragonslayer(), this, DRAGONSLAYER_PRICE, getDeductionConditions(DRAGONSLAYER_PRICE), direction, sellDragonslayerEffect(otherActor)));
            actions.add(new PurchaseAction(new Katana(), this, KATANA_PRICE, getDeductionConditions(KATANA_PRICE), direction, sellKatanaEffect()));
        }

        return actions;
    }

    /**
     * Obtains a map which consists of condition and corresponding runes deduction value pairs for a merchant called Sellen.
     * @return A map which consists of condition and corresponding runes deduction value pairs for a merchant called Sellen.
     */
    public Map<Condition, Integer> getDeductionConditions(Integer originalPrice){
        Map<Condition, Integer> deductionConditions = new HashMap<>();
        deductionConditions.put(new StatusCondition(Status.HAPPY), originalPrice);
        return deductionConditions;
    }

    /**
     * Gives side effects after selling a Broadsword from a Merchant called Sellen.
     * @return a list of side effects after selling a Broadsword from a Merchant called Sellen.
     */
    public List<SideEffect> sellBroadswordEffect() {
        List<SideEffect> merchantSideEffects = new ArrayList<>();
        merchantSideEffects.add(new MaxActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 20));
        return merchantSideEffects;
    }

    /**
     * Gives side effects after selling a Dragonslayer Greatsword from a Merchant called Sellen.
     * @return a list of side effects after selling a Dragonslayer Greatsword from a Merchant called Sellen.
     */
    public List<SideEffect> sellDragonslayerEffect(Actor buyer) {
        List<SideEffect> merchantSideEffects = new ArrayList<>();
        merchantSideEffects.add(new SpawnEffect(buyer, new GoldenBeetle()));
        return merchantSideEffects;
    }

    /**
     * Gives side effects after selling a Katana from a Merchant called Sellen.
     * @return a list of side effects after selling a Katana from a Merchant called Sellen.
     */
    public List<SideEffect> sellKatanaEffect() {
        List<SideEffect> merchantSideEffects = new ArrayList<>();
        merchantSideEffects.add(new SpawnEffect(this, new OmenSheep()));
        merchantSideEffects.add(new ActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10));
        merchantSideEffects.add(new MaxActorAttributeEffect(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20));
        return merchantSideEffects;
    }

    /**
     * A method for returning the string representation of Sellen.
     * It displays the Sellen's name and its current health (hit points), along with its maximum health (hit points) and also its wallet balance in Runes.
     *
     * @return Sellen details in String
     */
    @Override
    public String toString() {
        return name + " (Health: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", Runes: "+ this.getBalance() + ", Affection Level To Farmer: " + this.getAttribute(Attribute.AFFECTION_LEVEL) + "/" + this.getAttributeMaximum(Attribute.AFFECTION_LEVEL) + ')';
    }
}
