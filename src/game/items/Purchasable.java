package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.sideeffects.SideEffect;

import java.util.List;

/**
 * Interface that represents all items that are purchasable.
 *
 * @author Tee Zhi Hong
 */
public interface Purchasable {

    /**
     * Give an item from the merchant after a successful purchase.
     * @return an Item from the merchant after a successful purchase.
     */
    Item giveItem();

    /**
     * Gives the side effects when successfully purchasing an item.
     * @return A list of SideEffects after successfully purchasing an item.
     */
    List<SideEffect> getPurchaseSideEffects();

}
