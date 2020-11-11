package mysqlApp;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class StartApp {

    private final Vertx vertx;
    private final boolean debug = true;

    public static void main(final String[] args) {
        final StartApp startApp = new StartApp();
    }

    StartApp() {

        final VertxOptions options = new VertxOptions();
        if (debug) {
            options.setBlockedThreadCheckInterval(Long.MAX_VALUE >> 2);
        }

        vertx = Vertx.vertx(options);
        vertx.deployVerticle(new UserInputVerticle());
        vertx.deployVerticle(new MariadbVerticle());

    }
}
