package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.provider.CalendarContract;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game extends AppCompatActivity {
    private TableLayout tablelayout;
    protected Integer[][][][] sudoku;
    protected Integer[][][][] sudokugame=new Integer[3][3][3][3];
    protected TextView titletext;
    protected int positionsempty=0;
    protected static long gameStartTime;
    protected long score;

    private ScoreDBHelper scoreDBUtil;

    private ContentResolver contentResolver;
    private Set<String> calendars = new HashSet<String>();
    private List<String> events = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameStartTime = System.currentTimeMillis();
        setContentView(R.layout.activity_game);
        titletext=findViewById(R.id.title);
        tablelayout= findViewById(R.id.table_listsudoku);

        contentResolver = getContentResolver();

        scoreDBUtil = ScoreDBHelper.getInstance(this);

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
                            tx.setInputType(InputType.TYPE_NULL);
                            tx.setBackground(Gradientpersonal(3, Color.GRAY,Color.BLACK));
                            tx.setEnabled(false);
                            tx.setTextColor(Color.BLACK);
                        }
                        tx.setTag(a+""+c+""+b+""+d);
                        tx.addTextChangedListener(new CellWatcher(this,tx));
                        tx.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View view) {
                                int[] cell=new int[4];
                                char[] celltag=view.getTag().toString().toCharArray();
                                for(int b=0; b<4; b++) cell[b]=Character.getNumericValue(celltag[b]);
                                setdefaultcolorscells();
                                setinfocolorscells(cell);
                            }
                        });
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

    public void GetSaveScore() {
        long finalGameTime = System.currentTimeMillis();
        score = 2500 - (finalGameTime - gameStartTime) / 100;

        Score sc = new Score();
        sc.time = (finalGameTime - gameStartTime) / 100;
        sc.points = (int) score;
        System.out.print("Score: " + score);
        scoreDBUtil.insertScore(sc);
        addEvent();
        obtenirEvents();
        Log.i(getClass().getName(), calendars.toString());
        Log.i(getClass().getName(), events.toString());
    }

    private void addEvent() {
        ContentValues event = new ContentValues();
        event.put(CalendarContract.Events.CALENDAR_ID, 1);
        event.put(CalendarContract.Events.TITLE, "Ets el guanyador del sudoku amb una puntuació de " + score);
        event.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
        event.put(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis());
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Madrid");
        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, event);

        int id = Integer.parseInt(uri.getLastPathSegment());
        Toast.makeText(getApplicationContext(), "Puntuació afegida al calendari amb id" + id,
                Toast.LENGTH_SHORT).show();
        getCalendars();
    }

    private void getCalendars() {
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = {
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.CALENDAR_COLOR,
                CalendarContract.Calendars.VISIBLE
        };
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        try {
            if(cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    String hideName = cursor.getString(0);
                    String visibleName = cursor.getString(1);
                    String color = cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.CALENDAR_COLOR));
                    Boolean selected = !cursor.getString(3).equals("0");
                    calendars.add(visibleName);
                }
            }
        } catch (AssertionError ex) {}
    }

    protected boolean gameisfinish(){
        return this.positionsempty==0? true:false;
    }

    protected void setdefaultcolorscells(){
        for (int a=0; a<3; a++){
            for (int b=0; b<3; b++) {
                for(int c=0; c<3; c++){
                    for(int d=0; d<3; d++){
                        EditText tx=(EditText) tablelayout.findViewWithTag(a+""+c+""+b+""+d);
                        if(sudokugame[a][c][b][d]!=null){
                            tx.setBackground(Gradientpersonal(3, Color.GRAY,Color.BLACK));
                        }else{
                            tx.setBackground(Gradientpersonal(3, Color.WHITE,Color.BLACK));
                        }
                    }
                }
            }
        }
    }

    private void setinfocolorscells(int[] cells){
        for (int a=0; a<3; a++){
            for (int b=0; b<3; b++) {
                for(int c=0; c<3; c++){
                    for(int d=0; d<3; d++){
                        EditText tx=(EditText) tablelayout.findViewWithTag(a+""+c+""+b+""+d);
                        if(a==cells[0] && c==cells[1] || c==cells[1] && d==cells[3] || a==cells[0] && b==cells[2]){
                            tx.setBackground(Gradientpersonal(3, Color.YELLOW,Color.BLACK));
                        }
                    }
                }
            }
        }
    }

    private void obtenirEvents() {
        Uri uri = CalendarContract.Events.CONTENT_URI;
        String seleccio = String.format("(%s = ?)", CalendarContract.Events.TITLE);
        String[] seleccioArgs = new String[]{"DAM2 Escola del Clot"};
        String[] projeccio = new String[]{
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART
        };
        Cursor cursor = contentResolver.query(uri, projeccio, seleccio, seleccioArgs, null);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String titol = cursor.getString(1);
            events.add(titol);
        }
    }
}

