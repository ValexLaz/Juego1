package com.example.juego1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageButton btn0,btn1,btn2,btn3;
    ImageButton[] tablero =new ImageButton[4];
    Button btnReiniciar;
    TextView txtResultado;

    int[] imagenes;
    int fondo;

    ArrayList<Integer> arrayBarajar;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo=false;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void cargarTablero(){
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        tablero[0]=btn0;
        tablero[1]=btn1;
        tablero[2]=btn2;
        tablero[3]=btn3;

    }
    private void cargarBoton(){
        btnReiniciar=findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();

            }
        });
    }

    private void cargarTxt(){
        txtResultado=findViewById(R.id.txtResultado);
        txtResultado.setText("Resultado: ");
    }

    private void cargarImagenes(){
        imagenes=new int[]{
                R.drawable.incorrecto,
                R.drawable.incorrecto,
                R.drawable.incorrecto,
                R.drawable.correcto
        };
        fondo=R.drawable.interrogacion;
    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result= new ArrayList<Integer>();
        for(int i=0; i<longitud;i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        //System.out.println(Arrays.toString(result.toArray()));
        return result;

    }

    private void comprobar(int i, final ImageButton btn){
        if(primero==null){
            primero = btn;
            String nombreimg= "correcto";
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayBarajar.get(i)]);
            primero.getResources().
            //int id = getResources().getIdentifier("drawable/" + nombreimg, "drawable", getPackageName());

            //System.out.println(id);
            //primero.setEnabled(false);
            numeroPrimero=arrayBarajar.get(i);
            if(primero.getPackageName()==imagenes[R.drawable.correcto]){

                System.out.println("GANASTE");
            }
        }else{
            bloqueo=true;

                System.out.println("Vuelva  a intentar");


        }
    }
    private void init(){
        cargarTablero();
        cargarBoton();
        cargarTxt();
        cargarImagenes();
        arrayBarajar=  barajar(imagenes.length);
        for (int i=0; i<tablero.length;i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayBarajar.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<tablero.length;i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //tablero[i].setImageResource(imagenes[arrayBarajar.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);
        for (int i=0; i<tablero.length;i++){
            final int j=i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo){
                        comprobar(j, tablero[j]);
                    }
                }
            });
        }

    }
}