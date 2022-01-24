package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameMenu extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        // setting the image in the layout


        for(int i=1;i<12;i++){
            TextView textnumber= new TextView(this);
            textnumber.setText("Level: "+i);
            textnumber.setTextSize(LinearLayout.LayoutParams.WRAP_CONTENT);
            textnumber.setGravity(17);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_sudokulogo_foreground);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout linearLayout = findViewById(R.id.layo_listsudoku);
            // Add ImageView to LinearLayout{
            linearLayout.addView(textnumber);
            linearLayout.addView(imageView);
        }
    }
}