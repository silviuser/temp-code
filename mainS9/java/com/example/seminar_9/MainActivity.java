package com.example.seminar_9;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optiune1) {

            Network network = new Network() {
                @Override
                protected void onPostExecute(InputStream inputStream) {
                    //Toast.makeText(getApplicationContext(),Network.rezultat, Toast.LENGTH_LONG).show();
                    TextView tv = (TextView) findViewById(R.id.txtXML);
                    tv.setText(Network.rezultat);
                    super.onPostExecute(inputStream);
                }
            };
            try {
                //network.execute(new URL("https://bnr.ro/nbrfxrates.xml"));
                network.execute(new URL("https://curs.bnr.ro/nbrfxrates.xml"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            //Toast.makeText(this, "Optiune 1", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.optiune2) {
            //Toast.makeText(this, "Optiune 2", Toast.LENGTH_SHORT).show();
            TextView tv = (TextView) findViewById(R.id.txtXML);
            tv.setText("");
        }
        return true; //super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.tb1));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //getMenuInflater().inflate(R.menu.main_menu, this);
    }
}