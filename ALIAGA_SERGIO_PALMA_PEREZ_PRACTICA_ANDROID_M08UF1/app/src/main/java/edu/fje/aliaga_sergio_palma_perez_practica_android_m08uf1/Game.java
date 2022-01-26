package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private TableLayout tablelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tablelayout= findViewById(R.id.table_listsudoku);

        Intent intent = getIntent();
        Integer[][][][] sudoku = (Integer[][][][])getIntent().getSerializableExtra(GameMenu.MISSATGE_CLAU);
        createSudoku(sudoku);
    }

    private void createSudoku(Integer[][][][] sudoku) {
        TableRow tr = null;
        for (int a=0; a<3; a++){
            for (int b=0; b<3; b++) {
                tr = new TableRow(this);
                for(int c=0; c<3; c++){
                    for(int d=0; d<3; d++){
                        TextView tx = generateTxTable();
                        tx.setText(""+sudoku[a][c][b][d]);
                        tr.addView(tx);
                    }
                }
                tablelayout.addView(tr);
            }
        }
    }

    private TextView generateTxTable(){
        TextView tx= new TextView(this);

        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        tx.setLayoutParams(params);
*/
        tx.setBackgroundColor(Color.parseColor("#ffffff"));
        tx.setPadding(20,20,20,20);
        tx.setTextSize(20);
        tx.setGravity(Gravity.CENTER);
        return tx;
    }
}