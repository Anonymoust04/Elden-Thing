package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.enums.Status;

import java.util.Random;

/**
 * Class representing weapon items that can be used as a weapon.
 *
 * @author Adrian Kristanto
 * <p> Modified By: Tee Zhi Hong </p>
 */
public abstract class WeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;

    /**
     * WeaponItem class constructor.
     *
     * @param name        name of the weapon item.
     * @param displayChar character to use for display when weapon item is on the ground.
     * @param damage      amount of damage this weapon does.
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps".
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    /**
     * This method is used to define how the weapon attacks its target
     * For example, some weapons deals fire damage when the target is attacked.
     * This method can also be used to determine whether the attack lands (e.g.
     * whether the chance to hit is met), the weapon's damage (e.g. Dragonslayer
     * greatsword deals 100 damage to its target), and the weapon's verb (e.g. slashes,
     * pierces, etc.)
     *
     * @param attacker the actor who performed the attack.
     * @param target   the actor who is the target of the attack.
     * @param map      the map on which the attack was executed.
     * @return the description of what the weapon did when the attack was performed.
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * List of allowable actions that the weapon item can perform to its own or other Actors.
     *
     * @param owner the actor that owns the weapon item
     * @param map the map where the actor is performing the action on
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map){
        Location ownerLocation = map.locationOf(owner);
        ActionList actions = new ActionList();
        for (Exit exit: ownerLocation.getExits()){
            Location exitDestination = exit.getDestination();
            if (exitDestination.containsAnActor() && owner.hasCapability(Status.HOSTILE_TO_ENEMY)){
                Actor target = exitDestination.getActor();
                String direction = exit.getName();
                actions.add(new AttackAction(target, direction, this));
            }
        }
        return actions;
    }
}
