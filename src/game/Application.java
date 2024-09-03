package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle());

        List<String> map = Arrays.asList(
                "..........~~~~~~~...~~~~~~~......~...........",
                "~..........~~~~~....~~~~~~...................",
                "~~.........~~~~.....~~~~~~...................",
                "~~~..#####..~~.....~~~~~~~...................",
                "~~~..#___#........~~~~~~~~~..................",
                "~~~..#___#.......~~~~~~.~~~..................",
                "~~~..##_##......~~~~~~.......................",
                "~~~~...........~~~~~~~...........~~..........",
                "~~~~~.........~~~~~~~~.......~~~~~~~.........",
                "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

        GameMap gameMap = new GameMap("Gravesite Plain", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player tarnished = new Player("Tarnished", '@', 150);
        world.addPlayer(tarnished, gameMap.at(7, 4));

        WeaponItem greatKnife = new GreatKnife();
        WeaponItem shortSword = new ShortSword();

        FlaskOfHealing flaskOfHealing = new FlaskOfHealing();
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation();

        gameMap.at(42, 4).addActor(new FurnaceGolem());
        gameMap.at(6, 4).addItem(greatKnife);
        gameMap.at(8, 4).addItem(shortSword);

        gameMap.at(6, 5).addItem(flaskOfHealing);
        gameMap.at(8, 5).addItem(flaskOfRejuvenation);

        world.run();
    }
}
