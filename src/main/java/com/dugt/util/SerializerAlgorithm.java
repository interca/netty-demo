package com.dugt.util;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 序列化枚举类
 */
enum SerializerAlgorithm implements Serializer {
    // Java 实现
    Java {
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            try {
                ObjectInputStream in =
                        new ObjectInputStream(new ByteArrayInputStream(bytes));
                Object object = in.readObject();
                return (T) object;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("SerializerAlgorithm.Java 反序列化错误", e);
            }
        }

        @Override
        public <T> byte[] serialize(T object) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(object);
                byte[] bytes = bos.toByteArray();
                return bytes;
            } catch (IOException e) {
                throw new RuntimeException("SerializerAlgorithm.Java 序列化错误", e);
            }
        }
    },
    // Json 实现(引入了 Gson 依赖)
    Json {
        @Override
        public <T> T deserialize(Class<T> clazz, byte[] bytes) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String json = new String(bytes, StandardCharsets.UTF_8);
            T t = gson.fromJson(json, clazz);
            return t;
        }

        @Override
        public <T> byte[] serialize(T object) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String s = gson.toJson(object);
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            return bytes;
        }
    };


    // 需要从协议的字节中得到是哪种序列化算法
    public static SerializerAlgorithm getByInt(int type) {
        SerializerAlgorithm[] array = SerializerAlgorithm.values();
        if (type < 0 || type > array.length - 1) {
            throw new IllegalArgumentException("超过 SerializerAlgorithm 范围");
        }
        return array[type];
    }
}
// 针对之前报出：不支持 Class类转json的异常 做处理
class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {
    @Override
    public Class<?> deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            String str = json.getAsString();
            return Class.forName(str);

        } catch (ClassNotFoundException e) {

            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Class<?> src, Type type, JsonSerializationContext jsonSerializationContext) {

        // JsonPrimitive 转化基本数据类型
        return new JsonPrimitive(src.getName());
    }
}




