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

public class UserInputVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputVerticle.class);

    @Override
    public Completable rxStart() {

        final EventBus eb = vertx.eventBus();

        Scanner input = new Scanner(System.in);
        String proceed = "y";
        while (proceed == "y") {
            sendInput(eb, input);
            System.out.println("Do you have any more queries? y/n ");
            proceed = input.next();
        }
        return Completable.complete();

    }

    private void sendInput(EventBus eb, Scanner input) {
        eb.rxRequest("mariadb", input)
            .subscribe(e -> {
                LOGGER.debug("UserInputVerticle received reply: " + e.body());
                System.out.println(e.body());
            });
    }

}
