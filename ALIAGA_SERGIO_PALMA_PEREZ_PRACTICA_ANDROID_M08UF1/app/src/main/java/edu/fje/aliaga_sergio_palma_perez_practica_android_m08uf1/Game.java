package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LinearLayout linearLayout= findViewById(R.id.layo_listsudoku);

        Intent intent = getIntent();
        Integer[][][][] sudoku = (Integer[][][][])getIntent().getSerializableExtra(GameMenu.MISSATGE_CLAU);

    }

    public void createSudoku(Integer[][][][] sudoku){

        //linearLayout.addView();

    }
}