package mysqlApp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.reactivex.core.Vertx.*;
import io.reactivex.*;
import io.reactivex.annotations.Nullable;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.*;
import io.vertx.reactivex.core.*;
import io.vertx.reactivex.core.http.*;
import io.vertx.reactivex.ext.web.*;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.core.eventbus.EventBus;
import java.util.Scanner;

public class UserInputVerticle extends AbstractVerticle {

    private String playerName;
    private int playerHP;
    private int playerLvl;
    private Scanner myScanner = new Scanner(System.in);
    private Scanner enterScanner = new Scanner(System.in);

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputVerticle.class);

    @Override
    public Completable rxStart() {

        UserInputVerticle dublin;
        dublin = new UserInputVerticle();
        dublin.playerSetup();
        dublin.startAdventure();

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
        return Completable.complete();

    }

    private void playerSetup() {
    }

    private void startAdventure() {

    }

    private void sendInput(EventBus eb, String query) {
        eb.rxRequest("mariadb", query)
            .subscribe(e -> {
                    LOGGER.debug("UserInputVerticle received reply: " + e.body());
                    System.out.println(e.body());
                },
                err -> {
                    LOGGER.debug("Verticle Failed to communicate " + err.getMessage());

                }
            );
    }

}
