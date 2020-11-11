package mysqlApp;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.reactivex.mysqlclient.MySQLPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.Tuple;
import io.vertx.sqlclient.PoolOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.*;
import io.vertx.reactivex.core.*;
import io.vertx.reactivex.core.eventbus.EventBus;
import io.vertx.reactivex.core.eventbus.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MariadbVerticle extends AbstractVerticle {


    private static final Logger LOGGER = LoggerFactory.getLogger(MariadbVerticle.class);

    public Completable rxStart() {
        final EventBus eb = vertx.eventBus();
        eb.consumer("mariadb", this::handleInput);
        eb.consumer("xdevapi", this::documentHandler);
        //Place pool options here.
        return Completable.complete();
    }

    private void handleInput(Message<String> message) {
        LOGGER.debug("MariadbVerticle received message : " + message.body());
        final MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setPort(3306)
            .setHost("localhost")
            .setDatabase("monster")
            .setUser("jared")
            .setPassword("super03");

        PoolOptions poolOptions = new PoolOptions();

        MySQLPool client = MySQLPool.pool(vertx, connectOptions, poolOptions);

        client
            .query(message.body())
            .execute(ar -> {
                if (ar.succeeded()) {
                    RowSet<Row> result = ar.result();
                    LOGGER.debug("Got " + result.size() + " rows ");
                    message.reply(result);
                } else {
                    LOGGER.debug("Failure: " + ar.cause().getMessage());
                    message.reply("failure");
                }

                client.close();
            });
    }

    private void documentHandler(Message<String> message) {

    }
}
