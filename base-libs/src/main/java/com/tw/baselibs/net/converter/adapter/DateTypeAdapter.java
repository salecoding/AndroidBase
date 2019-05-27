package com.tw.baselibs.net.converter.adapter;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2019/2/21
 */
public class DateTypeAdapter extends TypeAdapter<Date> {
    private static final List<String> formarts = new ArrayList<>(8);

    static {
        formarts.add("yyyy/MM");
        formarts.add("yyyy/MM/dd");
        formarts.add("yyyy/MM/dd hh:mm");
        formarts.add("yyyy/MM/dd hh:mm:ss");

        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public void write(JsonWriter out, Date value) {
        try {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(TimeUtils.date2String(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Date read(JsonReader in) {
        try {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                return null;
            }
            if (in.peek() == JsonToken.STRING) {
                String source = in.nextString();
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                source = source.replaceAll("T"," ");
                if (source.matches("^\\d{4}/\\d{1,2}$")) {
                    return parseDate(source, formarts.get(0));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
                    return parseDate(source, formarts.get(1));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                    return parseDate(source, formarts.get(2));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                    return parseDate(source, formarts.get(3));
                } else if (source.matches("^\\d{4}-\\d{1,2}$")) {
                    return parseDate(source, formarts.get(4));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                    return parseDate(source, formarts.get(5));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                    return parseDate(source, formarts.get(6));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                    return parseDate(source, formarts.get(7));
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    public Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }
}
