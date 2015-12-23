package com.example.colin.retrofit_test2;

import com.example.colin.Utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by colin on 15-12-23.
 */
public class ResponseTypeAdapterFactory implements TypeAdapterFactory {
    private static final String CODE= "code";
    private static final String SUCCESS = "0";
    private static final String DATA = "data";
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> jsonElementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegateAdapter.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = jsonElementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has(CODE)) {
                        if (jsonObject.get(CODE).getAsString().equals(SUCCESS)) {
                            if (jsonObject.has(DATA) && jsonObject.get(DATA).isJsonObject()) {
                                jsonElement = jsonObject.get(DATA);
                            }
                        } else {
                            //异常类
                            LogUtil.e("code");
                        }
                    }
                }
                return delegateAdapter.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
