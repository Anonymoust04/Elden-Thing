package game.maps;
import edu.monash.fit2099.engine.positions.*;
import game.actors.npc.*;

import java.util.*;

/**
 * A class that represents the Limveld map in Elden Thing.
 *
 * @author Joel Wong Sing Yue
 * Modified by: Tee Zhi Hong
 */
public class Limveld extends GameMap {

    /**
     * Limveld class constructor.
     * @param groundFactory A class that can create different types of Ground based on its character representation.
     */
    public Limveld(FancyGroundFactory groundFactory) {
        super("Limveld", groundFactory, getMapLayout());
    }

    /**
     * Obtains the map layout for Limeveld.
     * @return map layout for Limeveld represented as a String List.
     */
    private static List<String> getMapLayout() {
        return Arrays.asList(
                "A............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx"
        );
    }

    /**
     * Sets up the entities present in the Limeveld map during its intialisation.
     */
    public void setUp() {

        OmenSheep omenSheep = new OmenSheep();
        omenSheep.selectNPCController(new RandomNPCController());

        SpiritGoat spiritGoat = new SpiritGoat();
        spiritGoat.selectNPCController(new RandomNPCController());

        GoldenBeetle goldenBeetle = new GoldenBeetle();
        goldenBeetle.selectNPCController(new RandomNPCController());

        this.at(10, 9).addActor(new OmenSheep());
        this.at(10,12).addActor(new SpiritGoat());
        this.at(10,6).addActor(new GoldenBeetle());
        this.at(9,6).addActor(new BedOfChaos());
    }

}

