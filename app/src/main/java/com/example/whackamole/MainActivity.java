package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private Drawable dogImage;
    private Drawable hedgehogImage;
    private Drawable currentImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    public Handler handler;
    public Button button;
    public UpdateCount update;
    public TextView score;
    public int count;
    public int image;


    public void startPressed(View v){

        handler.postDelayed(update, 1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        dogImage = getDrawable(R.drawable.dog);
        hedgehogImage = getDrawable(R.drawable.hedgehog);
        currentImage = moleImage;
        image = 1;
        imageViews = new ImageView[16];
        rand = new Random();
        update = new UpdateCount();
        button = findViewById(R.id.start);
        handler = new Handler();
        count = 0;
        score = findViewById(R.id.score);
        moleLocation = rand.nextInt(16);
        for (int i = 0; i < 16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if (i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }
    private class UpdateCount implements Runnable {
        @Override
        public void run() {
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(currentImage);
            handler.postDelayed(update, 1000);
        }

    }

    public void scoreBoard(View v){
        if (v==imageViews[moleLocation]) {
            count++;
            score.setText(count + "");
        }

    }

    public void imagePressed(View v){
        Intent i = new Intent(this, ChangeActivity.class);
        i.putExtra("IMAGE", image);
        startActivityForResult(i, 1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        image = data.getIntExtra("IMAGE",1);
        if (image == 1){
            imageViews[moleLocation].setImageDrawable(moleImage);
            currentImage = moleImage;
        }
        else if (image == 2){
            imageViews[moleLocation].setImageDrawable(dogImage);
            currentImage = dogImage;
        }
        else if(image == 3) {
            imageViews[moleLocation].setImageDrawable(hedgehogImage);
            currentImage = hedgehogImage;
        }
    }
}


