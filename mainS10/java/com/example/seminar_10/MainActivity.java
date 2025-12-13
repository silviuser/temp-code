package com.example.seminar_10;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtNume, txtPrenume;
    TextView txtProgress;
    Spinner nbrVarsta;
    SharedPreferences sp;

    ProgressBar pb;

    int progress = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNume = findViewById(R.id.txtNume);
        txtPrenume = findViewById(R.id.txtPrenume);
        nbrVarsta = findViewById(R.id.nbrVarsta);
        txtProgress = findViewById(R.id.txtProgress);

        // populate the spinner
        List ageS = new ArrayList<Integer>();
        for(int i=0; i<100; i++)
            ageS.add(i);

        // array adapter
        ArrayAdapter<Integer> aaI = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ageS);
        aaI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nbrVarsta.setAdapter(aaI);

        sp = getSharedPreferences("APP_S_10", MODE_PRIVATE);


        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
                //Toast.makeText(MainActivity.this, "SharedPreferences just UPDATED!!!", Toast.LENGTH_SHORT).show();
                Log.d("###", "SharedPreferences just UPDATED!!!");
            }
        };
        sp.registerOnSharedPreferenceChangeListener(listener);

        pb = findViewById(R.id.progBAR);
        // loading options
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pb.setMin(0);
        }
        pb.setMax(100);
        pb.setProgress(0);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // saving SP
        SharedPreferences.Editor editor = sp.edit();

        String nume = txtNume.getText().toString().trim().isEmpty() ? "???" : txtNume.getText().toString().trim();
        String prenume = txtPrenume.getText().toString().trim().isEmpty() ? "???" : txtPrenume.getText().toString().trim();
        int varsta = Integer.parseInt(nbrVarsta.getSelectedItem().toString());

        Log.e("###", nume + prenume + varsta);

        editor.putString("nume", nume);
        editor.putString("prenume", prenume);
        editor.putInt("varsta", varsta);

        editor.apply(); // commit intoarce si rezultat insa pe nimeni nu intereseaza
    }

    @Override
    protected void onResume() {
        super.onResume();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                progress = pb.getProgress();
//                Log.i("#####", String.valueOf(progress));
//                while (progress < 100)
//                    progress += 1;
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        pb.setProgress(progress);
//                        Log.i("#####", String.valueOf(progress));
//                    }
//                });
//
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        txtNume.setVisibility(View.INVISIBLE);
        txtPrenume.setVisibility(View.INVISIBLE);
        nbrVarsta.setVisibility(View.INVISIBLE);
        findViewById(R.id.lblNume).setVisibility(View.INVISIBLE);
        findViewById(R.id.lblPrenume).setVisibility(View.INVISIBLE);
        findViewById(R.id.lblVarsta).setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
        txtProgress.setVisibility(View.VISIBLE);

        progress = 0;
        setProgressValue(progress);
    }

    private void setProgressValue(int progress) {

        // set the progress
        pb.setProgress(progress);
        // thread is used to change the progress value
        Thread threadX = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 5);
                txtProgress.setText("Loading SP Data... " + String.valueOf(progress) + "%");

                Log.e("#####", String.valueOf(progress));

                if (progress > 100)
                    return;

            }
        });
        if (progress > 100) {
            threadX.interrupt();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getDatSP();
                }
            });
        }
        else
        {
            threadX.start();
        }

    }

    private void getDatSP() {
        // show controls
        pb.setVisibility(View.INVISIBLE);
        txtProgress.setVisibility(View.INVISIBLE);
        txtNume.setVisibility(View.VISIBLE);
        txtPrenume.setVisibility(View.VISIBLE);
        nbrVarsta.setVisibility(View.VISIBLE);
        findViewById(R.id.lblNume).setVisibility(View.VISIBLE);
        findViewById(R.id.lblPrenume).setVisibility(View.VISIBLE);
        findViewById(R.id.lblVarsta).setVisibility(View.VISIBLE);

        // resuming SP
        String nume = sp.getString("nume", "***");
        String prenume = sp.getString("prenume", "***");
        int varsta = sp.getInt("varsta", 100);

        Log.w("###", nume + prenume + varsta);

        txtNume.setText(nume);
        txtPrenume.setText(prenume);

//        ArrayAdapter myAdapter = (ArrayAdapter) nbrVarsta.getAdapter();
//        int spinnerPosition = myAdapter.getPosition(String.valueOf(varsta));
        nbrVarsta.setSelection(varsta);
    }
}

