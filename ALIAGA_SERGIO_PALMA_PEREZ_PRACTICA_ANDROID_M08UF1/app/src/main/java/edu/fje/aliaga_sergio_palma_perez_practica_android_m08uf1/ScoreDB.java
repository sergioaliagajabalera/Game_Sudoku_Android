package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.provider.BaseColumns;

public class ScoreDB {
    public ScoreDB(){}

    static abstract class ScoreTable implements BaseColumns {
        static final String TABLE_NAME = "score";
        static final String PLAYER_NAME = "playerName";
        static final String POINTS = "points";
    }
}
