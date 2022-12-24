package com.nda.timetable.Fragment.Timetable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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

import com.nda.timetable.DataLocalManager;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTimetable extends Fragment {
    View view;

    GridView gridView_morning, gridView_afternoon, gridView_night;

    List<Timetable> timetableMorningList = new ArrayList<>();
    List<Timetable> timetableAfternoonList = new ArrayList<>();
    List<Timetable> timetableNightList = new ArrayList<>();

    AdapterGridLayout adapterGridLayout, adapterGridLayout2, adapterGridLayout3;

    ImageView img_hintToWidget;

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timetable,container,false);

        initUI();
        initiate();
        displayTimetable();


        return view;
    }

    private void initiate() {


        img_hintToWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHintWidget();
            }
        });

        gridView_morning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Timetable timetable = timetableMorningList.get(i);
                if (timetable.getSlot().equals("TITLE"))
                {

                } else {
                    dialogUpdateTimetable(timetable);
                }
            }
        });

        gridView_afternoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Timetable timetable = timetableAfternoonList.get(i);
                if (timetable.getSlot().equals("TITLE"))
                {

                } else {
                    dialogUpdateTimetable(timetable);
                }
            }
        });

        gridView_night.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Timetable timetable = timetableNightList.get(i);
                if (timetable.getSlot().equals("TITLE"))
                {

                } else {
                    dialogUpdateTimetable(timetable);
                }
            }
        });
    }

    private void dialogHintWidget() {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_hint_widget);

        TextView txt_hintCreateWidget = dialog.findViewById(R.id.txt_hintCreateWidget);
        TextView txt_hintRefreshWidget = dialog.findViewById(R.id.txt_hintRefreshWidget);
        LinearLayout ll_createWidget = dialog.findViewById(R.id.ll_createWidget);
        LinearLayout ll_refreshWidget = dialog.findViewById(R.id.ll_refreshWidget);


        txt_hintCreateWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_createWidget.setVisibility(View.VISIBLE);
                ll_refreshWidget.setVisibility(View.GONE);

                txt_hintCreateWidget.setBackgroundColor(Color.parseColor("#FF01579B"));
                txt_hintRefreshWidget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                txt_hintCreateWidget.setTextColor(Color.parseColor("#FFFFFFFF"));
                txt_hintRefreshWidget.setTextColor(Color.parseColor("#FF000000"));

                txt_hintCreateWidget.setTypeface(txt_hintCreateWidget.getTypeface(), Typeface.BOLD);
                txt_hintRefreshWidget.setTypeface(txt_hintRefreshWidget.getTypeface(), Typeface.NORMAL);

            }
        });
        txt_hintRefreshWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_refreshWidget.setVisibility(View.VISIBLE);
                ll_createWidget.setVisibility(View.GONE);

                txt_hintRefreshWidget.setBackgroundColor(Color.parseColor("#FF01579B"));
                txt_hintCreateWidget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                txt_hintRefreshWidget.setTextColor(Color.parseColor("#FFFFFFFF"));
                txt_hintCreateWidget.setTextColor(Color.parseColor("#FF000000"));

                txt_hintRefreshWidget.setTypeface(txt_hintRefreshWidget.getTypeface(), Typeface.BOLD);
                txt_hintCreateWidget.setTypeface(txt_hintCreateWidget.getTypeface(), Typeface.NORMAL);
            }
        });

        dialog.show();
    }

    private void dialogUpdateTimetable(Timetable timetable) {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_update_timetable);

        TextView txt_updateAddress = dialog.findViewById(R.id.txt_updateAddress);
        EditText edt_inputSubject = dialog.findViewById(R.id.edt_inputSubject);
        CardView cv_update = dialog.findViewById(R.id.cv_update);

        txt_updateAddress.setText(timetable.getTime() + " - " + timetable.getDay() + " - " + timetable.getSlot());

        if (!timetable.getSubject().isEmpty())
        {
            edt_inputSubject.setText(timetable.getSubject());
        }
        cv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSubject = edt_inputSubject.getText().toString().trim();


                DataLocalManager.getInstance().dbHandler.queryData("UPDATE Timetable SET timetableSubject = '"
                        + strSubject + "' WHERE timetableId = '" + timetable.getId() + "'");

                displayTimetable();
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public void displayTimetable() {

        timetableMorningList.clear();
        timetableAfternoonList.clear();
        timetableNightList.clear();

        Cursor cursor = DataLocalManager.getInstance().dbHandler.getData("SELECT * FROM Timetable");

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String day = cursor.getString(1);
            String slot = cursor.getString(2);
            String time = cursor.getString(3);
            String subject = cursor.getString(4);

            if (time.equals("Sáng"))
            {
                timetableMorningList.add(new Timetable(id, day, slot, time, subject));
            }
            else if (time.equals("Chiều"))
            {
                timetableAfternoonList.add(new Timetable(id, day, slot, time, subject));
            } else if (time.equals("Tối"))
            {
                timetableNightList.add(new Timetable(id, day, slot, time, subject));
            }
        }
        adapterGridLayout = new AdapterGridLayout(getContext(), timetableMorningList);
        adapterGridLayout2 = new AdapterGridLayout(getContext(), timetableAfternoonList);
        adapterGridLayout3 = new AdapterGridLayout(getContext(), timetableNightList);

        gridView_morning.setAdapter(adapterGridLayout);
        gridView_afternoon.setAdapter(adapterGridLayout2);
        gridView_night.setAdapter(adapterGridLayout3);
    }



    private void initUI() {
        gridView_morning = view.findViewById(R.id.gridView_morning);
        gridView_afternoon = view.findViewById(R.id.gridView_afternoon);
        gridView_night = view.findViewById(R.id.gridView_night);

        img_hintToWidget = view.findViewById(R.id.img_hintToWidget);

    }

}