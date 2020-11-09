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
//            eb.consumer("mariadb", this::handleInsert);
        //Place pool options here.
        return Completable.complete();
    }

}