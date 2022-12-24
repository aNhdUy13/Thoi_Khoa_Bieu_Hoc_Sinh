package com.nda.timetable.Fragment.Calendar;

import android.app.Dialog;
import android.appwidget.AppWidgetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nda.timetable.DataLocalManager;
import com.nda.timetable.Fragment.Timetable.AdapterGridLayout;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCalendar extends Fragment {
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar,container,false);

        initUI();
        initiate();


        return view;
    }

    private void initiate() {

    }

    private void initUI() {

    }

}