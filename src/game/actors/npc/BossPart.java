package game.actors.npc;

import edu.monash.fit2099.engine.displays.Display;

import java.util.List;

/**
 * Interface for parts of the Bed of Chaos boss that can grow and provide special effects.
 *
 * @author Liew Yi Wei
 */
public interface BossPart {

    /**
     * Gets the damage value of this boss part.
     *
     * @return the damage
     */
    int getDamage();

    /**
     * Grows the boss part, applying its effects to the Bed of Chaos.
     *
     * @param boss the Bed of Chaos boss
     * @param display the display to show messages
     */
    void grow(BedOfChaos boss, Display display);

    /**
     * Applies the effect of this boss part to the Bed of Chaos.
     *
     * @param boss the Bed of Chaos boss
     * @param display the display to show messages
     */
    void getEffect(BedOfChaos boss, Display display);

    /**
     * Gets all the parts that can grow from this boss part.
     *
     * @return a list of BossPart objects that can grow
     */
    List<BossPart> getGrowPart();

    /**
     * Resets the growth state of this boss part and its children.
     */
    void resetGrowth();
}
