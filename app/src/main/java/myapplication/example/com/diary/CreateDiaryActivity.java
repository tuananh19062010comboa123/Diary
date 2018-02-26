package myapplication.example.com.diary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import myapplication.example.com.entity.Category;
import myapplication.example.com.entity.Diary;
import myapplication.example.com.sqlite.DBManager;

public class CreateDiaryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title, content;
    private Button btnCreate, btnCategory, btnFavorite,btnCancle;
    private Diary diary;
    private DBManager dbManager;
    private int favorites = 0;

    private  RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView;
    private  ArrayList<Diary> data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diary);
        /*dateTime();*/
        dbManager = new DBManager(this);
        dbManager.open();
        diary = new Diary();

        title = findViewById(R.id.createTitle);
        content = findViewById(R.id.createContent);
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnCategory = findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(this);
        btnFavorite = findViewById(R.id.btnfavorite);
        btnFavorite.setOnClickListener(this);
        btnCancle = findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(this);



   /* btnCreate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String titles = title.getText().toString().trim();
            String contents = content.getText().toString().trim();
            String dateTimes = dateTime();

        }
    });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnfavorite:
                favorites = 1;

                Toast.makeText(getApplicationContext(), "save favorite", Toast.LENGTH_SHORT).show();
                String[] colorList = {"Family", "Friends", "Health", "Hobbies", "Poetry", "Travel", "Without category"};
                int colorListLenght = colorList.length;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Choose category")
                        .setSingleChoiceItems(colorList, colorListLenght - 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lw = ((AlertDialog)dialog).getListView();
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                               /* int x = which;*/

                            }
                        });
                alertDialogBuilder.setPositiveButton("New Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogBuilderForCategory();
                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            /*case R.id.btnfavorite:
                break;*/
            case R.id.btnCreate:
              /*  dbManager.deleteAllDiary();*/
                String titles = title.getText().toString().trim();
                String contents = content.getText().toString().trim();
                String dateTimes = dateTime();
                String categorys = "Friends";
                dbManager.insertDiary(titles, contents, dateTimes, categorys, favorites);
                Intent intent = new Intent(CreateDiaryActivity.this, DiaryActivity.class);
                startActivity(intent);
          /*      if (validateCreateDiary()) {
                    String title = edit_title.getText().toString();
                    String startTime = startTimeDate.getText().toString() + " " + startTimeTime.getText().toString();
                    String endTime = endTimeDate.getText().toString() + " " + endTimeTime.getText().toString();
                    String contents = edit_content.getText().toString();
                    dbManager.insertDiary(title, contents, startTime, endTime, realPath);
                    Toast.makeText(getApplicationContext(), "save successed", Toast.LENGTH_SHORT).show();
                    dbManager.fetchDiary();
                    Intent intent = new Intent(SettingDiaryAvtivity.this, ShowDiaryActivity.class);
                    startActivity(intent);
                }
                break;*/
                break;
            case R.id.btnCancle:
                // custom dialog
            /*    AlertDialog.Builder alertDialogs = new AlertDialog.Builder(this);
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.activity_create_diary);
                dialog.setTitle("Title...");

                *//*TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_menu_manage);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
*//*
               *//* recyclerView = (RecyclerView) findViewById(R.id.customDialog);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                dbManager = new DBManager(this);
                dbManager.open();
                data = new ArrayList<Diary>();
                data = dbManager.arrlistFetchDiary();
*//*

      *//*  removedItems = new ArrayList<Integer>();*//*
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);

                dialog.show();
        break;*/
            default:
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.create_action_options) {

            return true;
        }
        if (id == R.id.add_to_favorites) {
            Toast.makeText(getApplicationContext(), "hih ihi ih ", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String dateTime() {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dateNow = dateFormat.format(date);
        SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm");
        String timeNow = timeForm.format(date);

     /*   String[] splitted = dateNow.split("\\s+");
        String monthChar = splitted[0].substring(0, 1);*/
       /* char monthChar = splitted[0].charAt(0);*/
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        String dayAndTimeNow = dayOfTheWeek + ", " + timeNow;
        String dateTime = dateNow + " " + dayAndTimeNow;//save database
        return dateTime;
    }

    public void dialog() {

    }

    public void alertDialogBuilderForCategory() {
        AlertDialog.Builder alertDialogBuilderForCategory = new AlertDialog.Builder(this);
        alertDialogBuilderForCategory.setTitle("New category");

        final EditText categoryName = new EditText(this);
        categoryName.setHint("Category name");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        categoryName.setLayoutParams(lp);
        alertDialogBuilderForCategory.setView(categoryName);

        alertDialogBuilderForCategory.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String categoryNew = categoryName.getText().toString().trim();
                String x = categoryName.toString();
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
   /* private void dialogAdapter(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateDiaryActivity.this);
        builder.setTitle("Select");
        builder.setAdapter(adapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int item) {
                        Toast.makeText(CreateDiaryActivity.this, "You selected: " + items[item],Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }*/
   private void customAdapter(){
       Dialog dialog = new Dialog(this);
   }
}
