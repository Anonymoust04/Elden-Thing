package game;

import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;
import game.actors.*;
import game.displays.*;
import game.grounds.*;
import game.grounds.plants.Bloodrose;
import game.grounds.plants.Inheritree;
import game.items.*;
import game.maps.Limveld;
import game.maps.ValleyOfInheritree;

/**
 * The main class to set up and run the game application called Elden Thing.
 *
 * @author Adrian Kristanto
 * <p> Modified By: Tee Zhi Hong, Nigel Wong Wei Lun, Joel Wong Sing Yue </p>
 */
public class Application {

    /**
     * The method that runs the game application called Elden Thing.
     * @param args String input from the command line interface (CLI) or terminal.
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil(), new Portal());

        ValleyOfInheritree valleyMap = new ValleyOfInheritree(groundFactory);
        Limveld limveldMap = new Limveld(groundFactory);

        world.addGameMap(valleyMap);
        valleyMap.setUp();

        world.addGameMap(limveldMap);
        limveldMap.setUp();

        Portal portal = new Portal();
        valleyMap.at(39, 14).setGround(portal);
        valleyMap.at(11, 11).setGround(portal);
        portal.addLocation(valleyMap.at(39, 14));
        portal.addLocation(valleyMap.at(11, 11));

        limveldMap.at(0, 0).setGround(portal);
        limveldMap.at(5, 5).setGround(portal);
        limveldMap.at(7, 0).setGround(portal);
        portal.addLocation(limveldMap.at(0,0 ));
        portal.addLocation(limveldMap.at(5,5 ));
        portal.addLocation(limveldMap.at(7,0 ));

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player farmer = new Player("Farmer", '@', 100, 200);
        farmer.addBalance(2000);
        farmer.addItemToInventory(new Diamond());
        farmer.addItemToInventory(new Rose());
        farmer.addItemToInventory(new RottenTrash());
        farmer.addItemToInventory(new Seed("Inheritree", '*', true, new Inheritree()));
        farmer.addItemToInventory(new Seed("Bloodrose", '*', true, new Bloodrose()));
        world.addPlayer(farmer, valleyMap.at(39, 14));

        world.run();
    }
}