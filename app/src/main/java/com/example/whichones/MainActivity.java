package com.example.whichones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    LinearLayout easyLevel1,easyLevel2,normalLevel1,normalLevel2,hardLevel1,hardLevel2;
    ImageView changeLevelModeForEasy1,changeLevelModeForEasy2,changeLevelModeForNormal1,changeLevelModeForNormal2,changeLevelModeForHard1,changeLevelModeForHard2;
    SQLiteDatabase database;
    List<Level> getData;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(this,R.raw.menu);
        getData=new ArrayList<>();
        database=this.openOrCreateDatabase("Levels",MODE_PRIVATE,null);
        setStatusBarColor();
        createDatabase();
        checkDatabase();


        initializeLinearLayout();
        initializeImageView();

        for(Level level:getData){
            if(level.getLevelPermission().equals("yes")){
                switch (level.getLevel()){
                    case "easylevelone":
                        System.out.println("LEVEL 1 AÇIK");
                        easyLevel1.setClickable(true);
                        changeLevelModeForEasy1.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;
                    case "easyleveltwo":
                        System.out.println("LEVEL 2 AÇIK");
                        easyLevel2.setClickable(true);
                        changeLevelModeForEasy2.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;
                    case "normallevelone":
                        System.out.println("LEVEL 3 AÇIK");
                        normalLevel1.setClickable(true);
                        changeLevelModeForNormal1.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;
                    case "normalleveltwo":
                        System.out.println("LEVEL 4 AÇIK");
                        normalLevel2.setClickable(true);
                        changeLevelModeForNormal2.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;
                    case "hardlevelone":
                        System.out.println("LEVEL 5 AÇIK");
                        hardLevel1.setClickable(true);
                        changeLevelModeForHard1.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;
                    case "hardleveltwo":
                        System.out.println("LEVEL 6 AÇIK");
                        hardLevel2.setClickable(true);
                        changeLevelModeForHard2.setBackgroundResource(R.drawable.main_menu_level_open);
                        break;


                }
            }

        }



    }
    public void easyLevel1(View view){
        mediaPlayer.start();
        Intent intent=new Intent(MainActivity.this,EasyLevelOne.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    public void easyLevel2(View view){
        mediaPlayer.start();
        Intent intent=new Intent(MainActivity.this,EasyLevelTwo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void normalLevel1(View view){
        Intent intent=new Intent(MainActivity.this,NormalLevelOne.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void normalLevel2(View view){
        Intent intent=new Intent(MainActivity.this,NormalLevelTwo.class);
        startActivity(intent);
    }
    public void hardLevel1(View view){
        Intent intent=new Intent(MainActivity.this,HardLevelOne.class);
        startActivity(intent);
    }
    public void hardLevel2(View view){
        Intent intent=new Intent(MainActivity.this,HardLevelTwo.class);
        startActivity(intent);
    }

    public void initializeLinearLayout(){
        easyLevel1=findViewById(R.id.easyLevel1);
        easyLevel2=findViewById(R.id.easyLevel2);
        normalLevel1=findViewById(R.id.normalLevel1);
        normalLevel2=findViewById(R.id.normalLevel2);
        hardLevel1=findViewById(R.id.hardLevel1);
        hardLevel2=findViewById(R.id.hardLevel2);

        easyLevel1.setClickable(false);
        easyLevel2.setClickable(false);
        normalLevel1.setClickable(false);
        normalLevel2.setClickable(false);
        hardLevel1.setClickable(false);
        hardLevel2.setClickable(false);
    }

    public void initializeImageView(){
        changeLevelModeForEasy1=findViewById(R.id.changeLevelModeForEasy1);
        changeLevelModeForEasy2=findViewById(R.id.changeLevelModeForEasy2);
        changeLevelModeForNormal1=findViewById(R.id.changeLevelModeForNormal1);
        changeLevelModeForNormal2=findViewById(R.id.changeLevelModeForNormal2);
        changeLevelModeForHard1=findViewById(R.id.changeLevelModeForHard1);
        changeLevelModeForHard2=findViewById(R.id.changeLevelModeForHard2);
    }
    public void createDatabase(){
        try{

            database.execSQL("CREATE TABLE IF NOT EXISTS levels(id INTEGER PRIMARY KEY,level VARCHAR, levelpermission VARCHAR)");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('easylevelone','yes')");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('easyleveltwo','no')");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('normallevelone','no')");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('normalleveltwo','no')");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('hardlevelone','no')");
            database.execSQL("INSERT INTO levels (level,levelpermission) VALUES ('hardleveltwo','no')");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void checkDatabase(){


        try{

            database.execSQL("DELETE FROM levels WHERE id=7");
            database.execSQL("DELETE FROM levels WHERE id=8");
            database.execSQL("DELETE FROM levels WHERE id=9");
            database.execSQL("DELETE FROM levels WHERE id=10");
            database.execSQL("DELETE FROM levels WHERE id=11");
            database.execSQL("DELETE FROM levels WHERE id=12");

            Cursor cursor=database.rawQuery("SELECT * FROM levels",null);

            int levelIx=cursor.getColumnIndex("level");
            int levelPermissionIx=cursor.getColumnIndex("levelpermission");
            while ((cursor.moveToNext())){
                String level=cursor.getString(levelIx);
                String levelPermission=cursor.getString(levelPermissionIx);
                //System.out.println(level+" "+levelPermission);
                Level level1=new Level(level,levelPermission);
                getData.add(level1);
            }
            cursor.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setStatusBarColor(){
        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.gri));
    }


}