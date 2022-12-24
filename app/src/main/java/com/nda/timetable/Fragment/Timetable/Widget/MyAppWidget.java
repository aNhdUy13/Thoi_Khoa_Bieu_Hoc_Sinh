package com.nda.timetable.Fragment.Timetable.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.GridView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.nda.timetable.MainActivity;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    private static final String REFRESH_DATA    = "REFRESH_DATA";
    private static final String MORNING_DATA    = "MORNING_DATA";
    private static final String AFTERNOON_DATA  = "AFTERNOON_DATA";
    private static final String NIGHT_DATA      = "NIGHT_DATA";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Back to main action
        Intent intentActionBackToMain = new Intent(context, MainActivity.class);
        PendingIntent pendingIntentBackToMain = PendingIntent.getActivity(
                context,
                0,
                intentActionBackToMain,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Đổ data vào GridView
        // Set up the intent that starts the StackViewService, which will
        // provide the views for this collection.
        Intent intentDisplayMorningData = new Intent(context, WidgetService.class);
        // intentDisplayData.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intentDisplayMorningData.putExtra("displayTime", "Sáng");

        // Refresh Data
//        Intent updateIntent = new Intent();
//        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(
//                context, 0, updateIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Construct the RemoteViews object
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        rv.setOnClickPendingIntent(R.id.img_backToApp, pendingIntentBackToMain);
        rv.setOnClickPendingIntent(R.id.txt_titleMorning, getPendingSelfIntent(context, MORNING_DATA));
        rv.setOnClickPendingIntent(R.id.txt_titleAfternoon, getPendingSelfIntent(context, AFTERNOON_DATA));
        rv.setOnClickPendingIntent(R.id.txt_titleNight, getPendingSelfIntent(context, NIGHT_DATA));
        rv.setOnClickPendingIntent(R.id.img_refreshData, getPendingSelfIntent(context,REFRESH_DATA));
        rv.setRemoteAdapter(R.id.gridView_widget, intentDisplayMorningData);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, rv);


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        ComponentName watchWidget = new ComponentName(context, MyAppWidget.class);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);

        if (MORNING_DATA.equals(intent.getAction())) {
            Intent intentDisplayMorningData = new Intent(context, WidgetService.class);
            intentDisplayMorningData.putExtra("displayTime", "Sáng");
            intentDisplayMorningData.setData(Uri.parse(intentDisplayMorningData.toUri(Intent.URI_INTENT_SCHEME)));

            rv.setRemoteAdapter(R.id.gridView_widget, intentDisplayMorningData);

            rv.setTextColor(R.id.txt_titleMorning, context.getResources().getColor(R.color.light_blue_widget));
            rv.setTextColor(R.id.txt_titleAfternoon, context.getResources().getColor(R.color.black));
            rv.setTextColor(R.id.txt_titleNight, context.getResources().getColor(R.color.black));


        }
        if (AFTERNOON_DATA.equals(intent.getAction())) {
            Intent intentDisplayAfternoonData = new Intent(context, WidgetService.class);
            intentDisplayAfternoonData.putExtra("displayTime", "Chiều");
            intentDisplayAfternoonData.setData(Uri.parse(intentDisplayAfternoonData.toUri(Intent.URI_INTENT_SCHEME)));
            rv.setRemoteAdapter(R.id.gridView_widget, intentDisplayAfternoonData);

             rv.setTextColor(R.id.txt_titleAfternoon, context.getResources().getColor(R.color.light_blue_widget));
            rv.setTextColor(R.id.txt_titleMorning, context.getResources().getColor(R.color.black));
            rv.setTextColor(R.id.txt_titleNight, context.getResources().getColor(R.color.black));


        }
        if (NIGHT_DATA.equals(intent.getAction())) {
            Intent intentDisplayAfternoonData = new Intent(context, WidgetService.class);
            intentDisplayAfternoonData.putExtra("displayTime", "Tối");
            intentDisplayAfternoonData.setData(Uri.parse(intentDisplayAfternoonData.toUri(Intent.URI_INTENT_SCHEME)));
            rv.setRemoteAdapter(R.id.gridView_widget, intentDisplayAfternoonData);


            rv.setTextColor(R.id.txt_titleNight, context.getResources().getColor(R.color.light_blue_widget));
            rv.setTextColor(R.id.txt_titleMorning, context.getResources().getColor(R.color.black));
            rv.setTextColor(R.id.txt_titleAfternoon, context.getResources().getColor(R.color.black));
        }
        if (REFRESH_DATA.equals(intent.getAction())) {
            Toast.makeText(context, "Để CẬP NHẬT DỮ LIỆU MỚI NHẤT\n=> Bạn cần xóa và tạo lại Widget \n(Xin lỗi vì sự bất tiện này)", Toast.LENGTH_LONG).show();
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(watchWidget, rv);

    }

    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, MyAppWidget.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
    }
}