package com.tw.baselibs.net.converter.adapter;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2019/2/21
 */
public class DoubleTypeAdapter extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter out, Double value) {
        try {
            if (value == null) {
                value = 0D;
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double read(JsonReader in) {
        try {
            Double value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0D;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                return 0D;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (StringUtils.isEmpty(str))
                    return 0D;
                return Double.parseDouble(str);
            } else {
                value = in.nextDouble();
                return value;
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", e.getMessage(), e);
        }
        return 0D;
    }
}
