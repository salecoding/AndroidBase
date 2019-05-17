package com.tw.baselibs.widget;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.tw.baselibs.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2018/12/13
 */
@SuppressLint("AppCompatCustomView")
public class DateTimeTextView extends TextView implements View.OnClickListener {
    private int mType;  // 1 yyyy-MM-dd HH:mm   2yyyy-MM-dd   3 HH:mm

    public DateTimeTextView(Context context) {
        super(context);
    }

    public DateTimeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typed = context.obtainStyledAttributes(attrs,
                R.styleable.DateTimeEditView);
        mType = typed.getInt(R.styleable.DateTimeEditView_type, 1);
        typed.recycle();
        init();
    }

    private void init() {
        this.setFocusable(false);
        this.setFocusableInTouchMode(false);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (mType) {
            case 1:
                choiceDateTime(getContext(), this);
                break;
            case 2:
                choiceDate(getContext(), this);
                break;
            case 3:
                choiceTime(getContext(), this);
                break;
            case 4:
                choiceMonth(getContext(), this);
                break;
        }
    }

    public Date getDateTimeValue() {
        if (StringUtils.isEmpty(this.getText().toString())) return null;
        if (mType == 1) {
            return TimeUtils.string2Date(this.getText().toString(), new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        } else if (mType == 2) {
            return TimeUtils.string2Date(this.getText().toString(), new SimpleDateFormat("yyyy-MM-dd"));
        } else if (mType == 3) {
            return TimeUtils.string2Date(this.getText().toString(), new SimpleDateFormat("HH:mm"));
        } else {
            return TimeUtils.string2Date(this.getText().toString(), new SimpleDateFormat("yyyy-MM"));
        }
    }

    public void setDateTimeValue(Date date) {
        if (date == null) return;
        if (mType == 1) {
            this.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM-dd HH:mm")));
        } else if (mType == 2) {
            this.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM-dd")));
        } else if (mType == 3) {
            this.setText(TimeUtils.date2String(date, new SimpleDateFormat("HH:mm")));
        } else {
            this.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy-MM")));
        }
    }

    @SuppressLint("WrongConstant")
    private void choiceMonth(Context context, final TextView tv) {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                String date = new SimpleDateFormat("yyyy-MM").format(new Date(calendar.getTimeInMillis()));
                tv.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @SuppressLint("WrongConstant")
    private void choiceDate(Context context, final TextView tv) {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(calendar.getTimeInMillis()));
                tv.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @SuppressLint("WrongConstant")
    private void choiceDateTime(final Context context, final TextView tv) {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(calendar.getTimeInMillis()));
                        tv.setText(date);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @SuppressLint("WrongConstant")
    private void choiceTime(final Context context, final TextView tv) {
        final Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                String date = new SimpleDateFormat("HH:mm").format(new Date(calendar.getTimeInMillis()));
                tv.setText(date);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }
}
