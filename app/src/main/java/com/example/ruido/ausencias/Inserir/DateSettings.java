package com.example.ruido.ausencias.Inserir;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;


public class DateSettings {

    public static class DateSettings1 implements DatePickerDialog.OnDateSetListener {
        public static String date;


        Context context;

        public DateSettings1(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            int month = monthOfYear+1;
            date = year + "-" + month + "-" + dayOfMonth;
            Toast.makeText(context, date, Toast.LENGTH_LONG).show();
            InserirActivity.DateStart.setText(DateSettings1.date);

        }

    }
    public static class DateSettings2 implements DatePickerDialog.OnDateSetListener{
        public static String date2;


        Context context;

        public DateSettings2(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth){
            int month = monthOfYear+1;
            date2 = year + "-" + month + "-" + dayOfMonth;
            Toast.makeText(context, date2, Toast.LENGTH_LONG).show();
            InserirActivity.DateFinish.setText(DateSettings2.date2);

        }

    }
}
