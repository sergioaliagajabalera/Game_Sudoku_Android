package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowInsetsAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GameMenu extends AppCompatActivity implements View.OnClickListener {
    public static final String MISSATGE_CLAU = "edu.fje.data";
    //private MockSudokus listsudokus= new MockSudokus();
    private int nsudokus=9;
    private Integer[][][][][] listsudokus=new Integer[nsudokus][3][3][3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);
        // setting the image in the layout
        startordened();

        for(int i=0;i<9;i++) {
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
        intent.putExtra(MISSATGE_CLAU, this.listsudokus[view.getId()]);
        startActivity(intent);
    }

    private void ordenedsudokus(String[] item,int nsudoku){
        int count2=0;
        for (int z = 0; z < 9; z++) {
            String[] itemstr = item[z].split(",");
            int[] items = new int[9];
            for (int w = 0; w < 9; w++) items[w] = Integer.parseInt(itemstr[w]);
            int a=0;
            if(z>=3 && z<6) a=1;
            if(z>=6 && z<9) a=2;
            if(count2==3)count2=0;
            for (int b = 0; b < 1; b++) {
                int count=0;
                for (int c = 0; c < 3; c++) {
                    int[] itemss = new int[3];
                    itemss[0] = items[count];
                    itemss[1] = items[count+1];
                    itemss[2] = items[count+2];
                    count = count + 3;
                    for (int d = 0; d < 3; d++) {
                        this.listsudokus[nsudoku][a][count2][c][d] = itemss[d];
                    }
                }
                count2=count2+1;
            }
        }
    }
    private void startordened(){
        String[] items1 = getResources().getStringArray(R.array.sudoku1);
        ordenedsudokus(items1,0);
        items1 = getResources().getStringArray(R.array.sudoku2);
        ordenedsudokus(items1,1);
        items1 = getResources().getStringArray(R.array.sudoku3);
        ordenedsudokus(items1,2);
        items1 = getResources().getStringArray(R.array.sudoku4);
        ordenedsudokus(items1,3);
        items1 = getResources().getStringArray(R.array.sudoku5);
        ordenedsudokus(items1,4);
        items1 = getResources().getStringArray(R.array.sudoku6);
        ordenedsudokus(items1,5);
        items1 = getResources().getStringArray(R.array.sudoku7);
        ordenedsudokus(items1,6);
        items1 = getResources().getStringArray(R.array.sudoku8);
        ordenedsudokus(items1,7);
        items1 = getResources().getStringArray(R.array.sudoku9);
        ordenedsudokus(items1,8);
    }
}