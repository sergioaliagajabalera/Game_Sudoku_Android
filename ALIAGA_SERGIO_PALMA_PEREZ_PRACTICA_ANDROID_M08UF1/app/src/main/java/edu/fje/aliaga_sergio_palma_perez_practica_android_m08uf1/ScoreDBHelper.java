package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

public class ScoreDBHelper extends SQLiteOpenHelper {
    private static ScoreDBHelper scoreInstance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Sudoku";

    //SCORE TABLE
    private static final String CREATE_SCORE_TABLE = "CREATE TABLE "
            + ScoreDB.ScoreTable.TABLE_NAME + " ("
            + ScoreDB.ScoreTable._ID + " INTEGER PRIMARY KEY,"
            + ScoreDB.ScoreTable.TIME + " TEXT,"
            + ScoreDB.ScoreTable.LEVEL + " INTEGER,"
            + ScoreDB.ScoreTable.POINTS + " INTEGER )";

    public static final String DROP_SCORE_TABLE = "DROP TABLE IF EXISTS "
            + ScoreDB.ScoreTable.TABLE_NAME;

    public static synchronized ScoreDBHelper getInstance(Context ctx) {
        if(scoreInstance == null) {
            scoreInstance = new ScoreDBHelper(ctx.getApplicationContext());
        }
        return scoreInstance;
    }

    private ScoreDBHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_SCORE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void insertScore(Score s) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(ScoreDB.ScoreTable.TIME, s.time);
            values.put(ScoreDB.ScoreTable.POINTS, s.points);
            values.put(ScoreDB.ScoreTable.LEVEL, s.level);

            db.insertOrThrow(ScoreDB.ScoreTable.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch(Exception e) {
            Log.d(TAG, "Error trying to insert a new score to the database");
        } finally {
            db.endTransaction();
        }
    }
}
