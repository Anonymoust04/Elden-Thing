    package game.actors.npc;

    import edu.monash.fit2099.engine.actions.Action;
    import edu.monash.fit2099.engine.actions.ActionList;
    import edu.monash.fit2099.engine.actions.DoNothingAction;
    import edu.monash.fit2099.engine.actors.Actor;
    import edu.monash.fit2099.engine.actors.Behaviour;
    import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
    import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
    import edu.monash.fit2099.engine.displays.Display;
    import edu.monash.fit2099.engine.positions.Exit;
    import edu.monash.fit2099.engine.positions.GameMap;
    import edu.monash.fit2099.engine.positions.Location;
    import game.actions.*;
    import game.behaviours.FollowBehaviour;
    import game.behaviours.LayEggBehaviour;
    import game.behaviours.WanderBehaviour;
    import game.conditions.RepeatableTurnCondition;
    import game.conditions.SpawnCondition;
    import game.enums.Ability;
    import game.enums.Status;
    import game.items.Consumable;
    import game.items.eggs.Egg;
    import game.items.eggs.EggHandler;
    import game.items.eggs.EggSelector;

    import java.util.Map;
    import java.util.TreeMap;

    /**
     * Class representing the Golden Beetle
     * <p>
     * This class extends NonPlayerCharacter and implements Consumable, EggHandler, and Reproduceable.
     * </p>
     *
     * @author Tee Zhi Hong
     * Modified By: Liew Yi Wei, Nigel Wong Wei Lun, Joel Wong Sing Yue
     */

    public class GoldenBeetle extends Actor implements Consumable, EggHandler, Reproduceable, NPCControllerSelectable {

        private static final int FOLLOW_PRIORITY = 1;
        private static final int LAY_EGG_PRIORITY = 2;
        private static final int WANDER_PRIORITY = 999;

        private Actor target;
        private NPCController npcController = new StandardNPCController();
        private Map<Integer, Behaviour> behaviours = new TreeMap<>();
        /**
         * Constructor for GoldenBeetle.
         */
        public GoldenBeetle(){
            super("Golden Beetle", 'b', 25);
            behaviours.put(LAY_EGG_PRIORITY,new LayEggBehaviour(this,new RepeatableTurnCondition(5)));
            behaviours.put(WANDER_PRIORITY, new WanderBehaviour());
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
         * Select and return an action to perform by the Golden Beetle at each turn.
         *
         * @param actions    collection of possible Actions for Golden Beetle.
         * @param lastAction The Action this Player took last turn.
         * @param map        the map containing the Player.
         * @param display    the I/O object to which messages may be written.
         * @return the action to be performed by GoldenBeetle
         */
        @Override
        public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
            if (!isConscious()) {
                display.println(unconscious(map));
                return new DoNothingAction();
            }

            setFollowBehvaiour(map);
            return npcController.processPlayTurn(behaviours, this, map);
        }

        /**
         * Sets the follow behaviour for the Golden Beetle.
         * <p>
         * This method checks if the target is still conscious and sets the follow behaviour accordingly.
         * </p>
         *
         * @param map The current GameMap.
         */
        public void setFollowBehvaiour(GameMap map){
            if (target != null && !target.isConscious()){
                target = null;
            }

            if (target == null) {
                Location location = map.locationOf(this);
                for (Exit exit : location.getExits()) {
                    Location destination = exit.getDestination();

                    if (map.isAnActorAt(destination)) {
                        Actor actor = map.getActorAt(destination);
                        if (actor.hasCapability(Ability.FOLLOWABLE)) {
                            target = actor;
                            behaviours.put(FOLLOW_PRIORITY, new FollowBehaviour(target));
                            break;
                        }
                    }
                }
            }
        }

        /**
         * Returns a new collection of the Actions that the otherActor can do to Golden Beetle.
         *
         * @param otherActor the Actor that can perform actions on this Actor.
         * @param direction  String representing the direction of the other Actor.
         * @param map        Current GameMap.
         * @return A collection of Actions.
         */
        @Override
        public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

            ActionList actions = super.allowableActions(otherActor, direction, map);

            if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add(new AttackAction(this, direction));
            }

            actions.add(new ConsumeAction(this));

            return actions;
        }

        /**
         * Returns a string representation of the Golden Beetle.
         *
         * @return A string representation of the Golden Beetle.
         */
        @Override
        public String toString() {
            return name + " (Health: " +
                    this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                    this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ")";
        }

        /**
         * Consume actions for the player to consume the Golden Beetle. Gaining 15 hitPoints and 1000 runes.
         * @param actor actor that consumes the Golden Beetle
         * @param map the current GameMap
         * @return a string describing the result of the consumption.
         */
        @Override
        public String consume(Actor actor, GameMap map){
            actor.heal(15);
            actor.addBalance(1000);
            map.removeActor(this);
            return actor + " eats the " + this + " and gained 15 hit points and 1000 runes";
        }

        /**
         * Reproduce method for the Golden Beetle.
         * <p>
         * This method creates a new Egg object and adds it to the location of the Golden Beetle.
         * </p>
         *
         * @param actor      The actor that is reproducing.
         * @param location   The location where the egg is laid.
         */
        public void reproduce(Actor actor, Location location) {
            EggSelector eggSelector = new EggSelector(location);
            location.addItem(eggSelector.produceEgg());
            target = null;
        }

        /**
         * A hatch method for the Golden Beetle.
         * <p>
         * This method will check if the egg can hatch based on its surrounding, then adds the Golden Beetle to the destination location.
         * </p>
         *
         * @param currentLocation The current location of the egg.
         * @return true if the egg hatches, false otherwise.
         */
        @Override
        public boolean hatch(Location currentLocation) {
            SpawnCondition spawnCondition = new SpawnCondition();
            GoldenBeetle child = new GoldenBeetle();
            child.selectNPCController(npcController);
            return spawnCondition.isFulfilled(child, currentLocation);
        }

        /**
         * A method to get the actor.
         * <p>
         * This method will return the actor.
         * </p>
         *
         * @return the egg that is being handled by the Golden Beetle.
         */
        @Override
        public Actor getActor() {
            return this;
        }

        /**
         * A method to consume the egg.
         * <p>
         * This method will check if the actor has the stamina attribute,
         * then increase the stamina by 20 and remove the egg from the inventory.
         * </p>
         *
         * @param actor The actor that is consuming the egg.
         * @param map   The map where the egg is located.
         * @param egg   The egg that is being consumed.
         * @return a string describing the result of the consumption.
         */
        @Override
        public String consumeEggEffect(Actor actor, GameMap map, Egg egg) {
            if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                actor.modifyAttribute(
                        BaseActorAttributes.STAMINA,
                        ActorAttributeOperations.INCREASE,
                        20
                );
                actor.removeItemFromInventory(egg);
                return "and restore 20 stamina!";
            }
            return "but no stamina increase because the actor does not have stamina attribute.";
        }
    }
