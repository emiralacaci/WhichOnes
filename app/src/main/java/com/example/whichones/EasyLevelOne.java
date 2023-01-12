package com.example.whichones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EasyLevelOne extends AppCompatActivity {
    Animation animation;


    SQLiteDatabase database;
    int a,b,c;
    int inputCounter;
    int time;
    int deneme,setMax;
    int stepOfLevelCounter;

    ProgressBar progressBar;

    Boolean isWin;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView stepOfLevel;


    TextView[] textViewArray;
    Boolean[] textViewBoolean;
    Button button,button2,button3;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level_one);
        setStatusBarColor();


        database=this.openOrCreateDatabase("Levels",MODE_PRIVATE,null);


        get5Box();
        stepOfLevelCounter=1;
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blabla);


        inputCounter=0;
        isWin=true;
        button2.setEnabled(false);
        button2.setVisibility(View.INVISIBLE);
        button3.setEnabled(false);
        button3.setVisibility(View.INVISIBLE);

        progressBar=findViewById(R.id.progressbar);
        deneme=0;
        time=5000;
        setMax=300;
        progressBar.setMax(setMax);
        progressBar.setVisibility(View.INVISIBLE);


        for(TextView textView: textViewArray){

            textView.setTextColor(R.color.yesil);
        }

        Random random = new Random();
        a = random.nextInt(9);
        b = random.nextInt(9);
        c = random.nextInt(9);


        while(a==b || a==c || b==c ) {
            a = random.nextInt(9);
            b = random.nextInt(9);
            c = random.nextInt(9);

        }
    }

    public void start(View view){
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        new CountDownTimer(time,10){
            @Override
            public void onTick(long l) {
                button.setEnabled(false);
                deneme+=1;
                progressBar.setProgress(deneme);


                textViewArray[a].setBackgroundResource(R.drawable.button_bg_yesil);

                textViewArray[b].setBackgroundResource(R.drawable.button_bg_yesil);

                textViewArray[c].setBackgroundResource(R.drawable.button_bg_yesil);


                for(TextView textView:textViewArray){
                    textView.setClickable(false);
                }



            }

            @Override
            public void onFinish() {
                for(TextView textView:textViewArray){
                    textView.setClickable(true);
                }

                textViewArray[a].setBackgroundResource(R.drawable.button_bg_kirmizi);
                textViewArray[b].setBackgroundResource(R.drawable.button_bg_kirmizi);
                textViewArray[c].setBackgroundResource(R.drawable.button_bg_kirmizi);

                progressBar.setVisibility(View.INVISIBLE);
                //button.setVisibility(View.VISIBLE);


                for(TextView textVieww:textViewArray){
                    textVieww.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View view) {
                            int getIndex = 0;
                            for(int i=0;i<textViewArray.length;i++){
                                if(textViewArray[i]==textVieww){
                                    getIndex=i;
                                }
                            }
                            boolean isClicked=textViewBoolean[getIndex];
                            if(isClicked==false){
                                textVieww.startAnimation(animation);
                                textVieww.setBackgroundResource(R.drawable.button_bg_orange);
                                textVieww.setTextColor(R.color.orange);
                                inputCounter++;
                                //textVieww.setClickable(false);
                                textViewBoolean[getIndex]=true;
                            }else{
                                textVieww.startAnimation(animation);
                                textVieww.setBackgroundResource(R.drawable.button_bg_kirmizi);
                                textVieww.setTextColor(R.color.yesil);
                                inputCounter--;
                                //textVieww.setClickable(false);
                                textViewBoolean[getIndex]=false;
                            }


/*
                                    textVieww.startAnimation(animation);
                                    textVieww.setBackgroundResource(R.drawable.button_bg_orange);
                                    textVieww.setTextColor(R.color.orange);
                                    inputCounter++;
                                    textVieww.setClickable(false);

 */


                            if(inputCounter==3){
                                for(TextView textViewww:textViewArray){
                                    //textViewww.setClickable(false);
                                    button2.setEnabled(true);
                                    button2.setVisibility(View.VISIBLE);
                                }
                            }else{
                                button2.setEnabled(false);
                                button2.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        }.start();
    }

    @SuppressLint("ResourceAsColor")
    public void control(View view){
        for(TextView textView:textViewArray){
            textView.setClickable(false);
        }
        button2.setEnabled(false);
        button2.setVisibility(View.INVISIBLE);
        textViewArray[a].setBackgroundResource(R.drawable.button_bg_yesil);
        textViewArray[a].setTextColor(R.color.yesil);
        textViewArray[a].startAnimation(animation);

        textViewArray[b].setBackgroundResource(R.drawable.button_bg_yesil);
        textViewArray[b].setTextColor(R.color.yesil);
        textViewArray[b].startAnimation(animation);

        textViewArray[c].setBackgroundResource(R.drawable.button_bg_yesil);
        textViewArray[c].setTextColor(R.color.yesil);
        textViewArray[c].startAnimation(animation);



        for(TextView textView: textViewArray){

            if(textView.getCurrentTextColor()==R.color.orange){
               //Toast.makeText(this, "Kaybettin", Toast.LENGTH_LONG).show();
                button3.setText("restart");
                button3.setEnabled(true);
                button3.setVisibility(View.VISIBLE);
                isWin=false;
                time=5000;
                progressBar.setMax(300);
                setMax=300;
                break;
            }
        }
        if(isWin){

            button3.setText("Next Level");
            button3.setEnabled(true);
            button3.setVisibility(View.VISIBLE);
            //Toast.makeText(this, "Kazandın", Toast.LENGTH_SHORT).show();
            if(time<=5000 && time>2000){
                time-=1000;
                setMax=(time*setMax)/(time+1000);
                progressBar.setMax(setMax);
                // System.out.println(time);


            }
            else if(time<=2000 && time>500){
                time-=500;
                setMax=(time*setMax)/(time+500);
                progressBar.setMax(setMax);
                //  System.out.println(time);
            }
            else if(time<=500 && time>100){
                time-=100;
                setMax=(time*setMax)/(time+100);
                progressBar.setMax(setMax);
                // System.out.println(time);
            }
            else if(time<=100 && time>10){
                time-=10;
                setMax=(time*setMax)/(time+10);
                progressBar.setMax(setMax);
                // System.out.println(time);
            }
            else if(time==10){
                Toast.makeText(this, "EN İYİSİ SENSİN", Toast.LENGTH_SHORT).show();
                database.execSQL("UPDATE levels SET levelpermission ='yes' WHERE level='easyleveltwo'");

            }
        }

    }

    @SuppressLint("ResourceAsColor")
    public void restart(View view){

        if(isWin){
            stepOfLevelCounter++;
            stepOfLevel.setText(stepOfLevelCounter+"/20");
        }else{
            stepOfLevelCounter=1;
            stepOfLevel.setText(stepOfLevelCounter+"/20");
        }

        inputCounter=0;
        button2.setEnabled(false);
        button2.setVisibility(View.INVISIBLE);
        button3.setEnabled(false);
        button3.setVisibility(View.INVISIBLE);
        isWin=true;
        progressBar.setProgress(0);
        deneme=0;

        for(int j=0;j< textViewBoolean.length;j++){
            textViewBoolean[j]=false;
        }


        for(TextView textView:textViewArray){
            textView.setBackgroundResource(R.drawable.button_bg_kirmizi);
            textView.clearAnimation();
            textView.setTextColor(R.color.yesil);
        }
        Random random = new Random();
        a = random.nextInt(9);
        b = random.nextInt(9);
        c = random.nextInt(9);

        while(a==b || a==c || b==c ) {
            a = random.nextInt(9);
            b = random.nextInt(9);
            c = random.nextInt(9);

        }

        button.setEnabled(true);
        button.setVisibility(View.VISIBLE);
        button.setText("START");



    }

    public void get5Box(){
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);

        stepOfLevel=findViewById(R.id.stepOfLevel);


        textViewArray=new TextView[]{textView1,textView2,textView3,textView4,textView5
                ,textView6,textView7,textView8,textView9};

        textViewBoolean=new Boolean[]{false,false,false,false,false
                ,false,false,false,false};
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

    }

    public void goMainPage(View view){
        Intent intent=new Intent(EasyLevelOne.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }






    public void setStatusBarColor(){
        Window window = EasyLevelOne.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(EasyLevelOne.this, R.color.easy));
    }
}