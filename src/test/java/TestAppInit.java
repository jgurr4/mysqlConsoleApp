import io.vertx.reactivex.core.*;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import mysqlApp.Mysql;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static mysqlApp.BusEvent.*;


@ExtendWith(VertxExtension.class)
public class TestAppInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAppInit.class);

    //This tests if UserInputVerticle successfully deploys.
    //TODO: Redo all unit tests to not include vertx verticles.

    @Test
    void failStartAdventure() throws Throwable {
        try {
            Boolean didIntro = false;
            if (didIntro == false) {
                System.out.println("\nWelcome Johan!\n Prepare for adventure!");
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
            //TODO: Find out how to test with Scanner.
            int choice = 15;

            if (choice == 1) {
                System.out.println("biome1");
            } else if (choice == 2) {
                System.out.println("biome2");
            } else if (choice == 3) {
                System.out.println("biome3");
            } else if (choice == 4) {
                System.out.println("biome4");
            } else {
                System.out.println("\nThat is invalid option.\n");
            }
        } catch (Exception e) {
            LOGGER.debug("error running TestAppInit.startAdventure()" + e.getMessage());
        }
    }
}

        /*
    void queryMysql() throws Throwable {
        new Mysql.handleInput("Underground");
            .subscribe(e -> {
                    vertx.eventBus().rxRequest(mariadbRetrieve.name(), "select * from monsters where biome = 'Underground'")
                        .subscribe(ar -> {
                                LOGGER.debug("Test.queryCouchbase received reply : " + ar.body());
                                context.completeNow();
                            },
                            err -> {
                                LOGGER.debug("Communication between Test.queryCouchbase error : " + err.getMessage());
                                context.failNow(err);
                            });
                },
                err -> {
                    LOGGER.debug("TestCrawlInit.queryCouchbase issue deploying verticle : " + err.getMessage());
                    context.failNow(err);
                });
    }

    @Test
    void retrieveMonsterList(Vertx vertx, VertxTestContext context) throws Throwable {

    }

}
    /*
    @Test
    void insertMysql(Vertx vertx, VertxTestContext context) throws Throwable {
        vertx.rxDeployVerticle(new MariadbVerticle())
            .subscribe(e -> {
                    vertx.eventBus().rxRequest(mariadbCreate.name(), "{\"key\":\"value\",\"key\":\"value\"}")
                        .subscribe(ar -> {
                                if (ar.body() == null) {
                                    LOGGER.debug("Couchbase successfully inserted document.");
                                } else {
                                    LOGGER.debug("Couchbase failed to insert document : " + ar.body());
                                }
                                context.completeNow();
                            },
                            err -> {
                                LOGGER.debug("Communication between Test.queryCouchbase error : " + err.getMessage());
                                context.failNow(err);
                            });
                },
                err -> {
                    LOGGER.debug("TestCrawlInit.insertCouchbase issue communicating with " +
                        "couchbase verticle. : " + err.getCause());
                    context.failNow(err);
                });
    }
*/

