package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameMenu extends AppCompatActivity implements View.OnClickListener {
    public static final String MISSATGE_CLAU = "edu.fje.data";
    private MockSudokus listsudokus= new MockSudokus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        // setting the image in the layout


        for(int i=0;i<listsudokus.listsudokus.length;i++) {

            TextView textnumber = new TextView(this);
            textnumber.setText("Level: "+(i+1));
            textnumber.setTextSize(LinearLayout.LayoutParams.WRAP_CONTENT);
            textnumber.setGravity(17);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_sudokulogo_foreground);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setId(i);
            imageView.setOnClickListener(this);

            LinearLayout linearLayout = findViewById(R.id.layo_listsudoku);
            // Add ImageView to LinearLayout{
            linearLayout.addView(textnumber);
            linearLayout.addView(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),Game.class);
        intent.putExtra(MISSATGE_CLAU, listsudokus.listsudokus[view.getId()]);
        startActivity(intent);
    }
}