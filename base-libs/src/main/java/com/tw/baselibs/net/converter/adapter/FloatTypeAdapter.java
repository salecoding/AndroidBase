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
public class FloatTypeAdapter extends TypeAdapter<Float> {
    @Override
    public void write(JsonWriter out, Float value) {
        try {
            if (value == null) {
                value = 0F;
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Float read(JsonReader in) {
        try {
            Float value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0F;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                return 0F;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (StringUtils.isEmpty(str))
                    return 0F;
                return Float.parseFloat(str);
            } else {
                String str = in.nextString();
                value = Float.valueOf(str);
            }
            return value;
        } catch (Exception e) {
            Log.e("TypeAdapter", "Not a number", e);
        }
        return 0F;
    }
}
