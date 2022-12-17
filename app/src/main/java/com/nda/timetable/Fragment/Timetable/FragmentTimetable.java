package com.nda.timetable.Fragment.Timetable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nda.timetable.DataLocalManager;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class FragmentTimetable extends Fragment {
    View view;

    GridView gridView_morning, gridView_afternoon, gridView_night;

    List<Timetable> timetableMorningList = new ArrayList<>();
    List<Timetable> timetableAfternoonList = new ArrayList<>();
    List<Timetable> timetableNightList = new ArrayList<>();

    AdapterGridLayout adapterGridLayout, adapterGridLayout2, adapterGridLayout3;

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


    private void dialogUpdateTimetable(Timetable timetable) {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_update_timetable);

        TextView txt_updateAddress = dialog.findViewById(R.id.txt_updateAddress);
        EditText edt_inputSubject = dialog.findViewById(R.id.edt_inputSubject);
        CardView cv_update = dialog.findViewById(R.id.cv_update);

        txt_updateAddress.setText(timetable.getDay() + " - " + timetable.getTime() + " - " + timetable.getSlot());

        cv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSubject = edt_inputSubject.getText().toString().trim();

                if (strSubject.isEmpty())
                {
                    Toast.makeText(getContext(), "Error : nhập môn học" , Toast.LENGTH_SHORT).show();
                    return;
                }

                DataLocalManager.getInstance().dbHandler.queryData("UPDATE Timetable SET timetableSubject = '"
                        + strSubject + "' WHERE timetableId = '" + timetable.getId() + "'");

                displayTimetable();
                dialog.dismiss();
                Toast.makeText(getContext(), "Cập nhật môn học thành công !" , Toast.LENGTH_SHORT).show();

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

    }

}