package com.netty.netty.protocol;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 用户拓展序列化,反序列化算法
 *
 * @author : darren
 * @date : 2022/6/3
 */
public interface Serializer {

    /**
     * 反序列化方法
     *
     * @param clazz java对象类型
     * @param bytes 序列化字节数组
     * @param <T>
     * @return
     */
    <T> T deSerialize(Class<T> clazz, byte[] bytes);

    /**
     * 序列化方法
     *
     * @param object 对象
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T object);

    enum Algorithm implements Serializer {

        JAVA {
            @Override
            public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                    ObjectInputStream objectInputStream = new ObjectInputStream(bis);
                    //转换成message
                    return (T) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException("反序列化失败", e);
                }
            }

            @Override
            public <T> byte[] serialize(T object) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    //字节数组
                    return bos.toByteArray();
                } catch (IOException e) {
                    throw new RuntimeException("序列化失败", e);
                }
            }
        },

        JSON {
            @Override
            public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
                Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
                String string = new String(bytes, StandardCharsets.UTF_8);
                return gson.fromJson(string, clazz);
            }

            @Override
            public <T> byte[] serialize(T object) {
                Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
                String json = gson.toJson(object);
                //客户端跟服务器的编码必须一致 不然会有问题
                return json.getBytes(StandardCharsets.UTF_8);
            }
        }
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
