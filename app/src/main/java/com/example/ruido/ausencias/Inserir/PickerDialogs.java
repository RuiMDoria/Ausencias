package com.example.ruido.ausencias.Inserir;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;


public class PickerDialogs {

    public static class PickerDialogs1 extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DateSettings.DateSettings1 datesettings = new DateSettings.DateSettings1(getActivity());

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog;
            dialog = new DatePickerDialog(getActivity(), datesettings, year, month, day);
            return dialog;
        }
    }

    public static class PickerDialogs2 extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DateSettings.DateSettings2 datesettings2 = new DateSettings.DateSettings2(getActivity());

            Calendar calendar2 = Calendar.getInstance();
            int year2 = calendar2.get(Calendar.YEAR);
            int month2 = calendar2.get(Calendar.MONTH);
            int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog2;
            dialog2 = new DatePickerDialog(getActivity(), datesettings2, year2, month2, day2);
            return dialog2;
        }
    }

}
