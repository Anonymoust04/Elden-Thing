package game.items;

import edu.monash.fit2099.engine.actors.attributes.*;

/**
 * Class representing a "giveable" item called Rotten Trash.
 * This item will decrease the affection level of any affectionate actors (e.g. Guts) by 30.
 *
 * @author Tee Zhi Hong
 */
public class RottenTrash extends GiveableItem {

    /**
     * RottenTrash Class Constructor
     */
    public RottenTrash() {
        super("Rotten Trash", 'h', ActorAttributeOperations.DECREASE, 30);
    }

}
