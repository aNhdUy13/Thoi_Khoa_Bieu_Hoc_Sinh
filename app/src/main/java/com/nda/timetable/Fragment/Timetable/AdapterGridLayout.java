package com.nda.timetable.Fragment.Timetable;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;


import com.nda.timetable.DataLocalManager;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterGridLayout extends ArrayAdapter {

    List<Timetable> timetableList;
    LayoutInflater layoutInflater;

    TextView txt_subject;
    LinearLayout ll_item;
    ImageView img_add;


    public AdapterGridLayout(@NonNull Context context, List<Timetable> timetableList)
    {
        super(context, R.layout.item_single_cell_timetable);

        this.timetableList = timetableList;

        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Timetable timetable = timetableList.get(position);

        View view = convertView;

        if (view == null)
        {
            view = layoutInflater.inflate(R.layout.item_single_cell_timetable, parent, false);

            txt_subject = view.findViewById(R.id.txt_subject);
            ll_item = view.findViewById(R.id.ll_item);
            img_add = view.findViewById(R.id.img_add);
        }

        if (timetable.getDay().equals("Thứ 2") || timetable.getDay().equals("Thứ 4")
                || timetable.getDay().equals("Thứ 6") || timetable.getDay().equals("CN"))
        {
            ll_item.setBackgroundResource(R.drawable.shape_normal_solid);
        }

        if (timetable.getSubject().equals(""))
        {
            img_add.setVisibility(View.VISIBLE);
            txt_subject.setVisibility(View.GONE);
        } else {
            txt_subject.setText(timetable.getSubject());
        }

        return view;

    }



    @Override
    public int getCount() {
        return timetableList.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return timetableList.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return timetableList.get(position);

    }
}
