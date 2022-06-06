package com.netty.netty.test;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author : darren
 * @date : 2022/6/5
 */
public class TestGson {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
        System.out.println(gson.toJson(String.class));
    }

    static class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

        @Override
        public JsonElement serialize(Class<?> src, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(src.getName());
        }

        @Override
        public Class<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String asString = jsonElement.getAsString();
            try {
                return Class.forName(asString);
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
