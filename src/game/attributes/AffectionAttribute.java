package game.attributes;

import edu.monash.fit2099.engine.actors.attributes.ActorAttribute;

/**
 * Affection attribute (Mainly used in Affection Level Value)
 *
 * @author Tee Zhi Hong
 */
public class AffectionAttribute implements ActorAttribute<Integer> {

    private static final int INITIAL_AFFECTION_LEVEL = 50;
    private static final int MINIMUM_AFFECTION_LEVEL = 0;
    private static final int MAXIMUM_AFFECTION_LEVEL = 100;

    private int affectionLevel = INITIAL_AFFECTION_LEVEL;

    /**
     * Overwrites the current level of the attribute with the given level.
     * @param level the level to overwrite the current level of the attribute
     */
    @Override
    public void update(Integer level) {
        affectionLevel = level;
    }

    /**
     * Increases the current level of the attribute by the given level.
     * This will add to the existing level of an attribute without overwriting the level.
     * @param level the level to increase the current level of the attribute
     */
    @Override
    public void increase(Integer level) {
        affectionLevel += level;
        affectionLevel = Math.min(affectionLevel, MAXIMUM_AFFECTION_LEVEL);
    }

    /**
     * Decreases the current level of the attribute by the given level.
     * This will subtract from the existing level of an attribute without overwriting the level.
     * @param level the level to decrease the current level of the attribute
     */
    @Override
    public void decrease(Integer level) {
        affectionLevel -= level;
        affectionLevel = Math.max(affectionLevel, MINIMUM_AFFECTION_LEVEL);
    }

    /**
     * Getter for the current level of the attribute.
     * @return the current level of the attribute
     */
    @Override
    public Integer get() {
        return affectionLevel;
    }

    /**
     * Getter for the maximum level of the attribute.
     * @return the maximum level of the attribute
     */
    @Override
    public Integer getMaximum() {
        return MAXIMUM_AFFECTION_LEVEL;
    }
}
