package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.Math;
import java.util.Arrays;

public class Game extends AppCompatActivity {
    private TableLayout tablelayout;
    protected Integer[][][][] sudoku;
    protected Integer[][][][] sudokugame=new Integer[3][3][3][3];
    protected TextView titletext;
    protected int positionsempty=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        titletext=findViewById(R.id.title);
        tablelayout= findViewById(R.id.table_listsudoku);

        Intent intent = getIntent();
        this.sudoku = (Integer[][][][])getIntent().getSerializableExtra(GameMenu.MISSATGE_CLAU);
        copysudoku();
        DeletePositions(20);
        positionsempty=20;
        createSudoku(this.sudokugame);

    }

    private void createSudoku(Integer[][][][] sudoku) {
        TableRow tr = null;
        for (int a=0; a<3; a++){
            for (int b=0; b<3; b++) {
                tr = new TableRow(this);
                for(int c=0; c<3; c++){
                    for(int d=0; d<3; d++){
                        EditText tx = generateTxTable();
                        if(sudoku[a][c][b][d]!=null){
                            tx.setText(""+sudoku[a][c][b][d]);
                            tx.setBackground(Gradientpersonal(3, Color.GRAY,Color.BLACK));
                            tx.setEnabled(false);
                            tx.setTextColor(Color.BLACK);
                        }
                        tx.setTag(a+""+c+""+b+""+d);
                        tx.addTextChangedListener(new CellWatcher(this,tx));
                        tr.addView(tx);
                    }
                }
                tablelayout.addView(tr);
            }
        }
    }

    private EditText generateTxTable(){

        EditText tx= new EditText(this);
        tx.setInputType(InputType.TYPE_CLASS_NUMBER);
        // Create a border programmatically
        GradientDrawable shape = Gradientpersonal(3, Color.WHITE,Color.BLACK);
        // Assign the created border to EditText widget
        tx.setBackground(shape);
        //tx.setBackgroundColor(Color.parseColor("#ffffff"));
        tx.setPadding(20,20,20,20);
        tx.setTextSize(20);
        tx.setGravity(Gravity.CENTER);
        return tx;
    }

    protected GradientDrawable Gradientpersonal(int stroke, int color1, int color2){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color1);
        gd.setStroke(stroke, color2);
        return gd;
    }

    private void DeletePositions(int nPositions){
        int[] positionsacbd=new int[4];
        int i2=0;
        for(; i2<nPositions; i2++) {
            for (int i = 0; i < 4; i++) positionsacbd[i] = (int) (Math.random() * 3);
            if (this.sudokugame[positionsacbd[0]][positionsacbd[1]][positionsacbd[2]][positionsacbd[3]] != null)
                this.sudokugame[positionsacbd[0]][positionsacbd[1]][positionsacbd[2]][positionsacbd[3]] = null;
            else i2--;
        }
    }

    private void copysudoku(){
        for (int a=0; a<3; a++){
            for (int b=0; b<3; b++) {
                for(int c=0; c<3; c++){
                    System.arraycopy(this.sudoku[a][b][c],0,this.sudokugame[a][b][c],0,this.sudokugame.length);
                }
            }
        }
    }

    protected boolean gameisfinish(){
        return this.positionsempty==0? true:false;
    }
}

