package com.tw.baselibs.net.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.tw.baselibs.net.BaseHttpResult;
import com.tw.baselibs.net.converter.adapter.DateTypeAdapter;
import com.tw.baselibs.net.converter.adapter.DoubleTypeAdapter;
import com.tw.baselibs.net.converter.adapter.FloatTypeAdapter;
import com.tw.baselibs.net.converter.adapter.IntegerTypeAdapter;
import com.tw.baselibs.net.converter.adapter.LongTypeAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * GsonConverterBodyFactory
 * 自定义 Gson 转换器
 */
public class GsonConverterBodyFactory extends Converter.Factory {

    private final Gson gson;

    public static GsonConverterBodyFactory create() {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                .registerTypeAdapter(int.class, new IntegerTypeAdapter())
                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(Long.class, new LongTypeAdapter())
                .registerTypeAdapter(long.class, new LongTypeAdapter())
                .registerTypeAdapter(Float.class, new FloatTypeAdapter())
                .registerTypeAdapter(float.class, new FloatTypeAdapter())
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .registerTypeAdapter(BaseHttpResult.class, new GsonResponseDeserializer());
        return create(builder);
    }

    public static GsonConverterBodyFactory create(GsonBuilder builder) {
        return new GsonConverterBodyFactory(builder.create());
    }

    public static GsonConverterBodyFactory create(Gson gson) {
        return new GsonConverterBodyFactory(gson);
    }

    private GsonConverterBodyFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("Gson == null");
        }

        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}