package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Integer[][][][] sudoku = (Integer[][][][])getIntent().getSerializableExtra(GameMenu.MISSATGE_CLAU);
        TextView textView = findViewById(R.id.level);
        textView.setTextSize(200);
        textView.setText(""+sudoku);
    }

}