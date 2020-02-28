package com.example.whackamole;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whackamole.R;

public class ChangeActivity extends AppCompatActivity {

    private RadioButton oneButton;
    private RadioButton twoButton;
    private RadioButton threeButton;
    private int image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        Intent i = getIntent();
        int image = i.getIntExtra("IMAGE", 1);
        if (image == 1)
            oneButton.setChecked(true);
        else if (image == 2)
            twoButton.setChecked(true);
        else if(image == 3)
            threeButton.setChecked(true);

    }
    @Override
    public void onBackPressed() {
        if (oneButton.isChecked())
            image = 1;
        else if (twoButton.isChecked())
            image = 2;
        else
            image = 3;
        Intent i = new Intent();
        i.putExtra("IMAGE", image);
        setResult(RESULT_OK, i);
        finish();
    }

}
