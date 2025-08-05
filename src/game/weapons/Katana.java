package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.items.*;
import game.items.*;
import game.sideeffects.*;

import java.util.*;

/**
 * Class representing a weapon item called Katana.
 * This weapon item deals 50 damage points with a 60% chance
 * to hit the target.
 *
 * @author Tee Zhi Hong
 */
public class Katana extends WeaponItem implements Purchasable {

    /**
     * Katana class constructor.
     */
    public Katana(){
        super("Katana", 'j', 50, "strikes",60);
    }

    /**
     * Give a Katana item from the merchant after a successful purchase.
     * @return a Katana Item from the merchant after a successful purchase.
     */
    @Override
    public Item giveItem() {
        return this;
    }

    /**
     * Gives the side effects when successfully purchasing a Katana.
     * @return A list of SideEffects after successfully purchasing a Katana.
     */
    @Override
    public List<SideEffect> getPurchaseSideEffects() {
        ArrayList<SideEffect> purchaseSideEffects = new ArrayList<>();
        purchaseSideEffects.add(new ActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, 25));
        return purchaseSideEffects;
    }
}
