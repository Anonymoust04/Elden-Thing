package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;


/**
 * A class representing the soil in the valley
 *
 * @author Adrian Kristanto
 * <p> Modified By: Tee Zhi Hong, Nigel Wong Wei Lun </p>
 */
public class Soil extends Ground {

    /**
     * Soil class constructor.
     */
    public Soil() {
        super('.', "Soil");
        addCapability(Ability.PLANT_SUITABLE);
        addCapability(Ability.ANCIENT_SHOVEABLE);
    }
}
