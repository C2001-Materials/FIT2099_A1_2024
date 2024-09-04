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
        world.addPlayer(tarnished, gameMap.at(7, 8));

        WeaponItem greatKnife = new GreatKnife();
        WeaponItem shortSword = new ShortSword();

        FlaskOfHealing flaskOfHealing = new FlaskOfHealing();
        FlaskOfRejuvenation flaskOfRejuvenation = new FlaskOfRejuvenation();

        ShadowtreeFragment stF1 = new ShadowtreeFragment();
        ShadowtreeFragment stF2 = new ShadowtreeFragment();
        ShadowtreeFragment stF3 = new ShadowtreeFragment();
        ShadowtreeFragment stF4 = new ShadowtreeFragment();
        ShadowtreeFragment stF5 = new ShadowtreeFragment();

        gameMap.at(7, 9).addActor(new FurnaceGolem());
        gameMap.at(6, 7).addItem(greatKnife);
        gameMap.at(8, 8).addItem(shortSword);

        gameMap.at(6, 5).addItem(flaskOfHealing);
        gameMap.at(8, 5).addItem(flaskOfRejuvenation);

        gameMap.at(16, 4).addItem(stF1);
        gameMap.at(17, 5).addItem(stF2);
        gameMap.at(18, 6).addItem(stF3);
        gameMap.at(19, 7).addItem(stF4);
        gameMap.at(20, 8).addItem(stF5);

        world.run();
    }
}
