package com.example.administrador.numerosv11;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private Button btnVer,btnLimpiar,btnSalir;
    private EditText numero,vista;

    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero=(EditText)findViewById(R.id.editText);
        vista=(EditText)findViewById(R.id.editText2);

        tts=new TextToSpeech(this,this);

        //boton de pasar numeros a letras
        btnVer=(Button)findViewById(R.id.button);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n = Float.parseFloat(numero.getText().toString());
                String n2 = numero.getText().toString();
                //logica de negocio***

                tts.setLanguage(new Locale("spa","ESP"));
                hablar(vista.getText().toString());
            }

        });

        //boton de limpiar
        btnLimpiar=(Button)findViewById(R.id.button2);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero.setText("");
                vista.setText("");
            }
        });

        //boton de salida
        btnSalir=(Button)findViewById(R.id.button3);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void hablar(String s) {
        tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
        tts.setSpeechRate(0.0f);
        tts.setPitch(0.0f);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED){
            Toast.makeText(MainActivity.this,"Problemas con el hardware de sonido o perdida de datos",Toast.LENGTH_SHORT).show();
            Log.e("TTS_Numero","Problemas con el hardware de sonido o perdida de datos");
        }
    }

    @Override
    protected void onDestroy(){
        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

