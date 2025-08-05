package game.maps;
import edu.monash.fit2099.engine.positions.*;
import game.actors.npc.*;
import game.items.shovels.AncientShovel;
import game.items.shovels.GardenShovel;
import game.items.Talisman;
import game.items.shovels.MetalShovel;

import java.util.*;

/**
 * A class that represents the Valley of Inheritree map in Elden Thing.
 *
 * @author Joel Wong Sing Yue
 * Modified by: Tee Zhi Hong, Nigel Wong Wei Lun
 */
public class ValleyOfInheritree extends GameMap {

    /**
     * ValleyOfInheritree class constructor.
     * @param groundFactory A class that can create different types of Ground based on its character representation.
     */
    public ValleyOfInheritree(FancyGroundFactory groundFactory) {
        super("Valley of the Inheritree", groundFactory, getMapLayout());
    }

    /**
     * Obtains the map layout for Valley of Inheritree.
     * @return map layout for Valley of Inheritree represented as a String List.
     */
    private static List<String> getMapLayout() {
        return Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xA"
        );
    }

    /**
     * Sets up the entities present in the Valley of Inheritree map during its intialisation.
     */
    public void setUp() {
        this.at(24, 11).addItem(new Talisman());
        this.at(23, 11).addActor(new OmenSheep());
        this.at(24,10).addActor(new SpiritGoat());
        this.at(26,6).addActor(new GoldenBeetle());
        this.at(22, 10).addActor(new Sellen());
        this.at(22, 11).addActor(new Kale());
        this.at(24, 8).addActor(new Guts());
        this.at(23,11).addItem(new MetalShovel());
        this.at(23, 12).addItem(new AncientShovel());
        this.at(24, 13).addItem(new GardenShovel());
    }

}