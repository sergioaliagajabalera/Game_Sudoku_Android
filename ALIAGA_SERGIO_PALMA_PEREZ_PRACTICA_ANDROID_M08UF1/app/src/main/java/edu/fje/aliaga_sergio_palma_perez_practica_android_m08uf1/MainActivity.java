package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private ImageView logoimage;
    ScoreAdapter scoresAdapter;

    private static final int PERMISSIONS_REQUEST_READ_CALENDARS = 100;
    private static final int PERMISSIONS_REQUEST_WRITE_CALENDARS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calendar
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALENDAR)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        PERMISSIONS_REQUEST_READ_CALENDARS);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CALENDAR)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_CALENDAR},
                        PERMISSIONS_REQUEST_WRITE_CALENDARS);
            }
        }

        //Creation or access to database
        ScoreDBHelper scores = ScoreDBHelper.getInstance(this);
        SQLiteDatabase db = scores.getWritableDatabase();

        //Cursor to take data from db (3 highest)
        Cursor scoreCursor = db.rawQuery("SELECT * FROM score ORDER BY points DESC LIMIT 3", null);

        //Creating listview to show scores and connect the adapter with it.
        ListView scoresLv = findViewById(R.id.scoreLv);
        scoresAdapter = new ScoreAdapter(this,scoreCursor);
        scoresLv.setAdapter(scoresAdapter);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logoimage=findViewById(R.id.sudokulogo);
        animationlogo(logoimage);
    }

    //This method will allow to update the highest scores.
    @Override
    protected void onRestart() {
        super.onRestart();
        ScoreDBHelper scores = ScoreDBHelper.getInstance(this);
        SQLiteDatabase db = scores.getWritableDatabase();

        Cursor scoreCursor = db.rawQuery("SELECT * FROM score ORDER BY points DESC LIMIT 3", null);

        ListView scoresLv = findViewById(R.id.scoreLv);
        scoresAdapter = new ScoreAdapter(this,scoreCursor);
        scoresLv.setAdapter(scoresAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ajustes:
                obrirAjustos();
                return true;
            case R.id.game:
                game();
                return true;
            case R.id.ayuda:
                ayuda();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void obrirAjustos() {
        Snackbar.make(coordinatorLayout, "AJUSTOS", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    private void game() {
        changetoActivity(GameMenu.class);
    }
    private void ayuda() { changetoActivity(HelpWebView.class);; }

    private void animationlogo(ImageView imglogo){
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imglogo.startAnimation(animFadeIn);
    }
    public void changetoActivity(Class classgeneric) {
        Intent intent = new Intent(getApplicationContext(),classgeneric);
        startActivity(intent);
    }

}