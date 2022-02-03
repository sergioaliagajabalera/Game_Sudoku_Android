package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.EditText;

import java.util.Arrays;

public class CellWatcher implements TextWatcher {
    private Game game;
    private EditText mEditText;


    public CellWatcher(Game game, EditText text){
        this.game=game;
        this.mEditText=text;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void  onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(this.mEditText.getText().length()!=0) {
            char[] valuec = this.mEditText.getText().toString().toCharArray();
            int[] cell = new int[4];
            char[] celltag = this.mEditText.getTag().toString().toCharArray();
            for (int b = 0; b < 4; b++) cell[b] = Character.getNumericValue(celltag[b]);
            if (Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9').contains(valuec[0])) {
                int text = Integer.parseInt(String.valueOf(valuec[0]));
                for (int b = 0; b < 4; b++) cell[b] = Character.getNumericValue(celltag[b]);
                Integer valueposition = this.game.sudoku[cell[0]][cell[1]][cell[2]][cell[3]];
                if (valueposition == text) {
                    mEditText.setBackground(this.game.Gradientpersonal(3, Color.GRAY, Color.BLACK));
                    mEditText.setEnabled(false);
                    mEditText.setTextColor(Color.BLACK);
                    this.game.sudokugame[cell[0]][cell[1]][cell[2]][cell[3]] = text;
                    this.game.positionsempty = this.game.positionsempty - 1;
                } else {
                    mEditText.setText("");
                    mEditText.setBackground(this.game.Gradientpersonal(3, Color.WHITE, Color.RED));
                    this.game.gameErrors += 1;
                }
                if (this.game.gameisfinish()) {
                    this.game.titletext.setText(this.game.titletext.getText() + " FINISH");
                    this.game.GetSaveScore();
                }
            } else mEditText.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
