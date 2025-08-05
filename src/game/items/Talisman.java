package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TalismanCureAction;
import game.enums.Ability;

/**
 * A class representing a Talisman item
 *
 * @author Adrian Kristanto
 * <p> Modified By: Tee Zhi Hong </p>
 */
public class Talisman extends Item {

    /**
     * Talisman class constructor.
     */
    public Talisman() {
        super("Talisman", 'o', true);
        addCapability(Ability.TALISMAN_CURE);
    }
}
