package mysqlApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

import static mysqlApp.BusEvent.*;

public class StartApp {

    private String playerName;
    private int playerHP;
    private int playerLvl;
    private Scanner myScanner = new Scanner(System.in);
    private static final Logger LOGGER = LoggerFactory.getLogger(StartApp.class);

    public static void main(final String[] args) {

        StartApp dublin;
        dublin = new StartApp();
        dublin.playerSetup();
        dublin.startAdventure();

    }

    private void playerSetup() {
        playerHP = 100;
        playerLvl = 1;
        System.out.println("Your HP: " + playerHP);
        System.out.println("Your level: " + playerLvl);

        System.out.println("\nPlease enter your name:");
        playerName = myScanner.nextLine();
    }

    private void startAdventure() {
        try {
            Boolean didIntro = false;
            if (didIntro == false) {
                System.out.println("\nWelcome " + playerName + "!" + "\n Prepare for adventure!");
                didIntro = true;
            }
            System.out.println("\n-----------------------------------------------------------\n");
            System.out.println("You walk into a crossroads with 4 signs.");
            System.out.println("Which road do you take?");
            System.out.println("");
            System.out.println("1: Lothlorien Mines");
            System.out.println("2: Brigandine Forest");
            System.out.println("3: Frost Caves");
            System.out.println("4: Femori Dungeon Keep");
            System.out.println("\n-----------------------------------------------------------\n");
            int choice = myScanner.nextInt();

            if (choice == 1) {
                String monsterList = Mysql.handleInput("'Underground'");
                System.out.println(monsterList);
            } else if (choice == 2) {
                String monsterList = Mysql.handleInput("'Forest'");
                System.out.println(monsterList);
            } else if (choice == 3) {
                String monsterList = Mysql.handleInput("'Tundra'");
                System.out.println(monsterList);
            } else if (choice == 4) {
                String monsterList = Mysql.handleInput("'Urban'");
                System.out.println(monsterList);
            } else {
                System.out.println("\nThat is invalid option.\n");
            }
        } catch (Exception e) {
            LOGGER.debug("error running startAdventure() : " + e.getCause());
        }
    }

}
            /*
            (e -> {
                    if (e.body() != null) {
                        result.append(e.body());
                        LOGGER.debug("UserInputVerticle received reply: " + e.body());
                    } else {
                        LOGGER.debug("Mysql Query failure for : " + biome);
                    }
                },
                err -> {
                    LOGGER.debug("Verticle Failed to communicate " + err.getMessage());
                }
            );
        return result.toString();
    }



             */


/*
//This was my query test.
        final EventBus eb = vertx.eventBus();

        final Scanner input = new Scanner(System.in);
        String proceed = "y";
        while (proceed == "y") {
            System.out.println("Enter your first query: EXAMPLES: select * from monsters limit 100;");
            String query = input.nextLine();
            sendInput(eb, query);
            System.out.println("Do you have any more queries? y/n ");
            proceed = input.next();
        }
*/
