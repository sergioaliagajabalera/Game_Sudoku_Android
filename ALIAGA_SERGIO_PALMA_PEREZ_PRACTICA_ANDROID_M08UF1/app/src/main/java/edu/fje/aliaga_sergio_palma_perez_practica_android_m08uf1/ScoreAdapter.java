package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context ctx, Cursor c) {
        super(ctx, c, 0);
    }

    @Override
    public View newView(Context ctx, Cursor c, ViewGroup parent) {
        return LayoutInflater.from(ctx).inflate(R.layout.score, parent, false);
    }

    @Override
    public void bindView(View view, Context ctx, Cursor cursor) {
        TextView playerName = (TextView) view.findViewById(R.id.playerName);
        TextView points = (TextView) view.findViewById(R.id.score);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));

        playerName.setText(name);
        points.setText(String.valueOf(score));
    }
}
