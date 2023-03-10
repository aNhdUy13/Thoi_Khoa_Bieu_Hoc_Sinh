package com.nda.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nda.timetable.Fragment.Calendar.FragmentCalendar;
import com.nda.timetable.Fragment.Timetable.FragmentTimetable;
import com.nda.timetable.Fragment.FragmentUtilities;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    /**
     * Regarding to Fragment
     * */
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private static final int FRAGMENT_TIMETABLE = 0;
    private static final int FRAGMENT_CALENDAR = 1;
    private static final int FRAGMENT_UTILITIES = 2;


    private int mCurrentFragment = 0;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initDB();

        init();
    }
    private void init()
    {
        try {
            setUpNavigationDrawer();

            /**
             Set DEFAULT fragment
             */
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,new FragmentTimetable());
            fragmentTransaction.commit();
        } catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }

    private void setUpNavigationDrawer(){
        /**
         * Setup Navigation Drawer
         * */
        drawerLayout   = (DrawerLayout) findViewById(R.id.drawerLayout);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("Th???i kh??a bi???u");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /**
         * X??? L?? s??? ki???n khi click v??o navigation view
         * Ta ph???i implement (NavigationView.OnNavigationItemSelectedListener)
         * */
        navigationView.setNavigationItemSelectedListener(this);
        // D??ng ????? hi???n fragment hi???n t???i ??ang ??? TRONG NAVIGATION DRAWER
        navigationView.getMenu().findItem(R.id.nav_timetable).setChecked(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_timetable)
        {
            if (mCurrentFragment != FRAGMENT_TIMETABLE)
            {
                toolbar.setTitle("Th???i kh??a bi???u");
                replaceFragment(new FragmentTimetable());
                mCurrentFragment = FRAGMENT_TIMETABLE;
            }

        }
        else if (id == R.id.nav_calendar)
        {
            if (mCurrentFragment != FRAGMENT_CALENDAR)
            {
                toolbar.setTitle("L???ch");
                replaceFragment(new FragmentCalendar());
                mCurrentFragment = FRAGMENT_CALENDAR;
            }

        }
        else if (id == R.id.nav_utilities)
        {
            if (mCurrentFragment != FRAGMENT_UTILITIES)
            {
                toolbar.setTitle("Ti???n ??ch");
                replaceFragment(new FragmentUtilities());
                mCurrentFragment = FRAGMENT_UTILITIES;
            }

        }


        // Logic to close the Drawer
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    private void replaceFragment (Fragment fragment)
    {
        FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void initUI(){
        navigationView = (NavigationView) findViewById(R.id.navigationView);

    }

    private void initDB()
    {
        DataLocalManager.getInstance().dbHandler.createDB();

        if (!DataLocalManager.getFirstTimeGoToApp())
        {
            // First time go to the app
            // => Insert data to database then set boolean = true
            Log.d("mainLog", "*First time go to app");
            DataLocalManager.setFirstTimeGoToApp(true);

            /**
             * Morning
             * */
            // Insert slot 1 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 1", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 1", "S??ng", "");
                }
            }
            // Insert slot 2 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 2", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 2", "S??ng", "");
                }
            }
            // Insert slot 3 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 3", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 3", "S??ng", "");
                }
            }
            // Insert slot 4 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 4", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 4", "S??ng", "");
                }
            }
            // Insert slot 5 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 5", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 5", "S??ng", "");
                }
            }
            // Insert slot 6 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "S??ng", "Ti???t 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 6", "S??ng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 6", "S??ng", "");
                }
            }

            /**
             * Afternoon
             * */
            // Insert slot 1 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler
                            .INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler
                            .INSERT_TIMETABLE("CN", "Ti???t 1", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler
                            .INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 1", "Chi???u", "");
                }
            }
            // Insert slot 2 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 2", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 2", "Chi???u", "");
                }
            }
            // Insert slot 3 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 3", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 3", "Chi???u", "");
                }
            }
            // Insert slot 4 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 4", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 4", "Chi???u", "");
                }
            }
            // Insert slot 5 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 5", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 5", "Chi???u", "");
                }
            }
            // Insert slot 6 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chi???u", "Ti???t 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 6", "Chi???u", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 6", "Chi???u", "");
                }
            }

            /**
             * Night
             * */
            // Insert slot 1 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 1", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 1", "T???i", "");
                }
            }
            // Insert slot 2 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 2", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 2", "T???i", "");
                }
            }
            // Insert slot 3 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 3", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 3", "T???i", "");
                }
            }
            // Insert slot 4 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 4", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 4", "T???i", "");
                }
            }
            // Insert slot 5 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 5", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 5", "T???i", "");
                }
            }
            // Insert slot 6 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "T???i", "Ti???t 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Ti???t 6", "T???i", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Th??? " + (i+1), "Ti???t 6", "T???i", "");
                }
            }
        }
    }
}