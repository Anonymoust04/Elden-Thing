package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.npc.BedOfChaos;

import java.util.Random;
/**
 * Represents the weapon of the Bed of Chaos boss.
 *
 * @author Liew Yi Wei
 */
public class BossFist extends IntrinsicWeapon {

    private BedOfChaos boss;
    /**
     * Constructor for BossFist, initializes the weapon with a damage value and a hit rate.
     *
     * @param boss the Bed of Chaos boss that uses this weapon
     */
    public BossFist (BedOfChaos boss){
        super(25, "smacks", 75);
        this.boss = boss;
    }

    /**
     * Returns the total damage of the boss's weapon, which is the accumulated damage of all its parts plus the base damage of the weapon.
     *
     * @return the total damage of the weapon
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }
        int totalDamage = 25 + boss.getTotalDamage();
        target.hurt(totalDamage);

        return String.format("%s %s %s for %d damage", attacker, verb, target, totalDamage);
    }

}
