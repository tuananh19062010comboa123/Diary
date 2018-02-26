package myapplication.example.com.diary;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import myapplication.example.com.entity.Category;
import myapplication.example.com.entity.Diary;
import myapplication.example.com.fragment.FavoritesFragment;
import myapplication.example.com.fragment.FragmentCategory;
import myapplication.example.com.fragment.FragmentDiary;
import myapplication.example.com.sqlite.DBManager;

public class DiaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DBManager dbManager;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        dbManager = new DBManager(this);
        dbManager.open();
        //delete Diaary
     /*   dbManager.deleteAllDiary();*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.addFloatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(DiaryActivity.this,CreateDiaryActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //nav
        /* nav(new FragmentDiary());*/
        //NavigationView
      /* *//* NavigationView naviView = (NavigationView)findViewById(R.id.nav_categories);
        naviView.setNavigationItemSelectedListener(this);*/
      /*  addMenuItemInNavMenuDrawer();*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_option:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //android.app.Fragment currentFragment = getFragmentManager().findFragmentById(R.id.frame_layout);
        if (id == R.id.nav_diary) {
            nav(new FragmentDiary());
        } else if (id == R.id.nav_favorites) {
            nav(new FavoritesFragment());
        } else if (id == R.id.nav_categories) {
         /*   popUpCategory();*/
            nav(new FragmentCategory());
        } else if (id == R.id.nav_trash) {

        } else if (id == R.id.nav_setting) {
            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void nav(Fragment nav){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction  ft= fm.beginTransaction();
        ft.replace(R.id.frame_layout, nav);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
    /*private  void addMenuItemInNavMenuDrawer(MenuItem item) {
    }*/

    private void popUpCategory() {

        String[] colorList = {"Family", "Friends", "Health", "Hobbies", "Poetry", "Travel", "Without category"};
        AlertDialog.Builder alertDialogBuilderForCategory = new AlertDialog.Builder(this);
        alertDialogBuilderForCategory.setTitle("List category")
       .setItems(colorList, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
        alertDialogBuilderForCategory.setPositiveButton("Manage category", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* String categoryNew = categoryName.getText().toString().trim();*/
            }
        });
        alertDialogBuilderForCategory.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilderForCategory.create();
        alertDialog.show();
    }


}
