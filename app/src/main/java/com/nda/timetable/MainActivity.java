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

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nda.timetable.Fragment.Timetable.FragmentTimetable;
import com.nda.timetable.Fragment.FragmentUtilities;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    /**
     * Regarding to Fragment
     * */
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private static final int FRAGMENT_TIMETABLE = 0;
    private static final int FRAGMENT_UTILITIES = 1;


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
        toolbar.setTitle("Thời khóa biểu");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /**
         * Xử Lí sự kiện khi click vào navigation view
         * Ta phải implement (NavigationView.OnNavigationItemSelectedListener)
         * */
        navigationView.setNavigationItemSelectedListener(this);
        // Dùng để hiện fragment hiện tại đang ở TRONG NAVIGATION DRAWER
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
                toolbar.setTitle("Thời khóa biểu");
                replaceFragment(new FragmentTimetable());
                mCurrentFragment = FRAGMENT_TIMETABLE;
            }

        }
        else if (id == R.id.nav_utilities)
        {
            if (mCurrentFragment != FRAGMENT_UTILITIES)
            {
                toolbar.setTitle("Tiện ích");
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
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 1", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 1", "Sáng", "");
                }
            }
            // Insert slot 2 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 2", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 2", "Sáng", "");
                }
            }
            // Insert slot 3 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 3", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 3", "Sáng", "");
                }
            }
            // Insert slot 4 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 4", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 4", "Sáng", "");
                }
            }
            // Insert slot 5 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 5", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 5", "Sáng", "");
                }
            }
            // Insert slot 6 - morning
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Sáng", "Tiết 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 6", "Sáng", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 6", "Sáng", "");
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
                            .INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler
                            .INSERT_TIMETABLE("CN", "Tiết 1", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler
                            .INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 1", "Chiều", "");
                }
            }
            // Insert slot 2 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 2", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 2", "Chiều", "");
                }
            }
            // Insert slot 3 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 3", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 3", "Chiều", "");
                }
            }
            // Insert slot 4 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 4", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 4", "Chiều", "");
                }
            }
            // Insert slot 5 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 5", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 5", "Chiều", "");
                }
            }
            // Insert slot 6 - Afternoon
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Chiều", "Tiết 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 6", "Chiều", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 6", "Chiều", "");
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
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 1");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 1", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 1", "Tối", "");
                }
            }
            // Insert slot 2 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 2");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 2", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 2", "Tối", "");
                }
            }
            // Insert slot 3 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 3");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 3", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 3", "Tối", "");
                }
            }
            // Insert slot 4 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 4");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 4", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 4", "Tối", "");
                }
            }
            // Insert slot 5 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 5");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 5", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 5", "Tối", "");
                }
            }
            // Insert slot 6 - Night
            for (int i = 0; i <= 7; i ++)
            {
                if (i == 0)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("TITLE", "TITLE", "Tối", "Tiết 6");
                }
                else if (i == 7)
                {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("CN", "Tiết 6", "Tối", "");
                }
                else {
                    DataLocalManager.getInstance().dbHandler.INSERT_TIMETABLE("Thứ " + (i+1), "Tiết 6", "Tối", "");
                }
            }
        }
    }
}