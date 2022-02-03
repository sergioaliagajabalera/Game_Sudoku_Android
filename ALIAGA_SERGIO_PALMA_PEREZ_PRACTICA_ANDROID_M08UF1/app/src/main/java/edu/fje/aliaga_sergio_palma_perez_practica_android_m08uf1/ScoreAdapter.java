package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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
        TextView game_date = (TextView) view.findViewById(R.id.game_date);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView points = (TextView) view.findViewById(R.id.score);
        TextView levels = (TextView) view.findViewById(R.id.level);

        String date_game = cursor.getString(cursor.getColumnIndexOrThrow("game_date"));
        long duration = cursor.getInt(cursor.getColumnIndexOrThrow("time"));
        long score = cursor.getInt(cursor.getColumnIndexOrThrow("points"));
        long level = cursor.getInt(cursor.getColumnIndexOrThrow("level"));

        game_date.setText(String.valueOf(date_game));
        time.setText(String.valueOf(duration));
        points.setText(String.valueOf(score));
        levels.setText(String.valueOf(level));
    }
}
