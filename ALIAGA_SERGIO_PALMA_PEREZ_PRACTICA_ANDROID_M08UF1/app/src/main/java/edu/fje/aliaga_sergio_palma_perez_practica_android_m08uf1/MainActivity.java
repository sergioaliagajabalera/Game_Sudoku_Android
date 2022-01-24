package edu.fje.aliaga_sergio_palma_perez_practica_android_m08uf1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;


/**
 * Classe que implementa un menu a la barra d'acció
 *
 * @author sergi.grau@fje.edu
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private ImageView logoimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logoimage=findViewById(R.id.sudokulogo);
        animationlogo(logoimage);
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