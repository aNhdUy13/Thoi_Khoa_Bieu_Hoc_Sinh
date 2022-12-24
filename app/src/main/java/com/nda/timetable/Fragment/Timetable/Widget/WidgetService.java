package com.nda.timetable.Fragment.Timetable.Widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends RemoteViewsService{


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(this.getApplicationContext(), intent);
    }
}

class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory{
    static SQLiteDatabase dbBroadcast;

    private Context context;

    List<Timetable> timetableList = new ArrayList<>();
    private int appWidgetId;

    String passedSignal, refreshSignal;

    public WidgetRemoteViewFactory(Context context, Intent intent) {

        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        passedSignal = intent.getStringExtra("displayTime");
        refreshSignal = intent.getStringExtra("displayTime");

    }

    @Override
    public void onCreate() {
        dbBroadcast = context.openOrCreateDatabase("Timetable.sqlite", 0, null);
        Cursor cursor = null;
        if (passedSignal.equals("Sáng"))
        {
            cursor = dbBroadcast.rawQuery("SELECT * FROM Timetable WHERE timetableTime = 'Sáng'", null);
        }
        else if (passedSignal.equals("Chiều"))
        {
            cursor = dbBroadcast.rawQuery("SELECT * FROM Timetable WHERE timetableTime = 'Chiều'", null);
        }
        else if (passedSignal.equals("Tối"))
        {
            cursor = dbBroadcast.rawQuery("SELECT * FROM Timetable WHERE timetableTime = 'Tối'", null);
        }
        else{
            cursor = dbBroadcast.rawQuery("SELECT * FROM Timetable", null);
        }


        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String day = cursor.getString(1);
            String slot = cursor.getString(2);
            String time = cursor.getString(3);
            String subject = cursor.getString(4);

            timetableList.add(new Timetable(id,day,slot,time,subject));

        }


    }
    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.item_cell_timetable_widget);

        if (timetableList.get(i).getSubject().isEmpty())
        {
            rv.setTextViewText(R.id.txt_subject, "_");

        } else {
            rv.setTextViewText(R.id.txt_subject, timetableList.get(i).getSubject());

        }

        return rv;
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        // 7 ngày trong tuần , 6 tiết/ngày, thêm 6 title => 7x6+4 = 48 (1 buổi sáng/chiều/tối)
        return 48;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
