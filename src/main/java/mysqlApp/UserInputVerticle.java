package mysqlApp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
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

        return Completable.complete();

    }

}
