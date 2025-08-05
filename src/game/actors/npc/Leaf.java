package game.actors.npc;

import edu.monash.fit2099.engine.displays.Display;

import java.util.Collections;
import java.util.List;

/**
 * Leaf is a part of the Bed of Chaos boss that can heals the boss.
 * It implements the BossPart interface.
 *
 * @author Liew Yi Wei
 * Modified by Tee Zhi Hong
 */
public class Leaf implements BossPart{

    private static final int LEAF_DAMAGE = 1;
    private static final int LEAF_HEAL = 5;
    private int damage = LEAF_DAMAGE;
    private int heal = LEAF_HEAL;
    private boolean grown = false;

    /**
     * Constructor for Leaf.
     * Initializes the damage and heal valuesof the boss.
     */
    public Leaf(){}

    /**
     * Returns the damage value of the Leaf.
     * @return the damage value
     */
    @Override
    public int getDamage() {
        return damage;
    }
    /**
     * This method is called when the Bed of Chaos boss grows.
     * It gets the effect of the Leaf on the boss.
     */
    @Override
    public void grow(BedOfChaos boss, Display display) {
        getEffect(boss,display);
    }

    /**
     *
     * This method applies the healing effect of the Leaf on the Bed of Chaos boss.
     */
    @Override
    public void getEffect(BedOfChaos boss, Display display) {
        boss.heal(heal);
    }

    /**
     * Returns an empty list as Leaf does not have any growable parts.
     * @return An empty list as Leaf does not have any growable parts.
     */
    @Override
    public List<BossPart> getGrowPart() {
        return Collections.emptyList();
    }


    /**
     * Resets the growth state of the Leaf.
     * Since Leaf does not grow into other parts, this method simply resets the grown state.
     */
    public void resetGrowth() {
        this.grown = false;
    }
}
