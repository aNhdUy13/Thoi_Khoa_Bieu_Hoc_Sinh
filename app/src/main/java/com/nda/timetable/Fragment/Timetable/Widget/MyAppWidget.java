package com.nda.timetable.Fragment.Timetable.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.GridView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.nda.timetable.MainActivity;
import com.nda.timetable.Models.Timetable;
import com.nda.timetable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {


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
        Intent intentDisplayData = new Intent(context, WidgetService.class);
        intentDisplayData.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intentDisplayData.setData(Uri.parse(intentDisplayData.toUri(Intent.URI_INTENT_SCHEME)));




        // Construct the RemoteViews object
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        rv.setOnClickPendingIntent(R.id.img_backToApp, pendingIntentBackToMain);
        rv.setRemoteAdapter(R.id.gridView_morning_widget, intentDisplayData);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, rv);


    }

}