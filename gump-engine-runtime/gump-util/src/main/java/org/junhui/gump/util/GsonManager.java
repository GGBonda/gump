package org.junhui.gump.util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GsonManager {

    private static GsonManager singleton;

    private final Map<Type, Object> TYPE_ADAPTER_MAP = new ConcurrentHashMap<>();

    private Gson gson = new GsonBuilder()
            .setObjectToNumberStrategy(CustomNumberPolicy.INT_OR_LONG_OR_DOUBLE)
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private GsonManager() {}

    public static GsonManager getInstance() {
        if (singleton == null) {
            synchronized (GsonManager.class) {
                if (singleton == null) {
                    singleton = new GsonManager();
                }
            }
        }
        return singleton;
    }

    public GsonManager registerTypeAdapter(Type type, Object typeAdapter) {
        if (!TYPE_ADAPTER_MAP.containsKey(type)) {
            synchronized (this) {
                gson = gson.newBuilder().registerTypeAdapter(type, typeAdapter).create();
                TYPE_ADAPTER_MAP.put(type, typeAdapter);
            }
        }
        return this;
    }

    public String toJSONString(Object obj) {
        return gson.toJson(obj);
    }

    public <T> T parseObject(String json, Class<T> cla) {
        return gson.fromJson(json, cla);
    }

    enum CustomNumberPolicy implements ToNumberStrategy {
        INT_OR_LONG_OR_DOUBLE {
            @Override
            public Number readNumber(JsonReader in) throws IOException {
                String value = in.nextString();
                try {
                    try {
                        return Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        return Long.parseLong(value);
                    }
                } catch (NumberFormatException longE) {
                    try {
                        Double d = Double.valueOf(value);
                        if ((d.isInfinite() || d.isNaN()) && !in.isLenient()) {
                            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + "; at path " + in.getPath());
                        }
                        return d;
                    } catch (NumberFormatException doubleE) {
                        throw new JsonParseException("Cannot parse " + value + "; at path " + in.getPath(), doubleE);
                    }
                }
            }
        }
    }

}
