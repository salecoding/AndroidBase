package com.tw.baselibs.net;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2019/2/21
 */
public class LongTypeAdapter extends TypeAdapter<Long> {
    @Override
    public void write(JsonWriter out, Long value) throws IOException {
        try {
            if (value == null) {
                value = 0L;
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long read(JsonReader in) {
        try {
            Long value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0L;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                return 0L;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (StringUtils.isEmpty(str))
                    return 0L;
                return Long.parseLong(str);
            } else {
                value = in.nextLong();
                return value;
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", "Not a number", e);
        }
        return 0L;
    }
}
