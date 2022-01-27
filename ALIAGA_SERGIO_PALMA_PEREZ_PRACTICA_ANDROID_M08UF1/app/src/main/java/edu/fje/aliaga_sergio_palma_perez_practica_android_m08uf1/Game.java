package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
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
                        EditText tx = generateTxTable();
                        if(sudoku[a][c][b][d]!=null){
                            tx.setText(""+sudoku[a][c][b][d]);
                            tx.setEnabled(false);
                        }
                        tx.setTag(a+""+c+""+b+""+c);

                        tr.addView(tx);
                    }
                }
                tablelayout.addView(tr);
            }
        }
    }

    private EditText generateTxTable(){

        EditText tx= new EditText(this);
        tx.setInputType(InputType.TYPE_CLASS_PHONE);

        // Create a border programmatically
        ShapeDrawable shape = shapestrokepersonal(3);
        // Assign the created border to EditText widget
        tx.setBackground(shape);
        //tx.setBackgroundColor(Color.parseColor("#ffffff"));
        tx.setPadding(20,20,20,20);
        tx.setTextSize(20);
        tx.setGravity(Gravity.CENTER);
        return tx;
    }

    private ShapeDrawable shapestrokepersonal(int stroke){

        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.BLACK);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(stroke);
        return shape;
    }
}

