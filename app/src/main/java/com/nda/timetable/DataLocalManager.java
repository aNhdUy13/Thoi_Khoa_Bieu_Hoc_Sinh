package com.nda.timetable;

import android.content.Context;

public class DataLocalManager {
    private static DataLocalManager instance;

    MySharedPreferences mySharedPreferences;
    public dbHandler dbHandler;

    private static final String PREF_FIRST_TIME_GOTO_APP = "PREF_FIRST_TIME_GOTO_APP";


    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
        instance.dbHandler = new dbHandler(context, "Timetable.sqlite", null, 1);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }

        return instance;
    }

    /**
     * First time go to the app
     * */
    public static void setFirstTimeGoToApp(boolean isFirstTime)
    {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(isFirstTime, PREF_FIRST_TIME_GOTO_APP);
    }

    public static boolean getFirstTimeGoToApp()
    {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_FIRST_TIME_GOTO_APP);
    }
}
