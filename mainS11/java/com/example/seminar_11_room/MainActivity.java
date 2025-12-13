package com.example.seminar_11_room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDB db;
    List<Angajat>  listaAngajati= new ArrayList<>();

    static String Nume[] = {"Ionescu", "Popescu", "Georgesu", "Dumitrescu", "Enescu", "Marinescu", "Stefanescu", "Mateescu"};
    static String Prenume[] = {"Ana", "Ioana", "Elena", "Maria", "George", "Adrian", "Flavius", "Ion", "Petru", "Rares"};



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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "appDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        UserDAO userDao = db.userDao();
        listaAngajati.add(new Angajat(1, "Ionescu", "Ion", "112233", 20));
        listaAngajati.add(new Angajat(2, "Popescu", "Popa", "334455", 30));
        listaAngajati.add(new Angajat());
        userDao.insertAll(listaAngajati);
        userDao.insert(new Angajat(3, "Georgescu", "George", "889900", 40));
        userDao.insert(new Angajat());

        listaAngajati = userDao.getAll();

        updateList();
    }

    public void doCodSort(View view) {
        UserDAO userDao = db.userDao();
        listaAngajati = userDao.getCodSorted();

        updateList();
    }

    public void doAgeSort(View view) {
        UserDAO userDao = db.userDao();
        listaAngajati = userDao.getAgeSorted();

        updateList();
    }

    public void doAgeFilter(View view) {
        UserDAO userDao = db.userDao();
        listaAngajati = userDao.getAgeFiltered(50);

        updateList();
    }

    public void updateList(){
        ListView listData = (ListView) findViewById(R.id.listDATA);
        ArrayAdapter<Angajat> arr;
        arr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAngajati);
        listData.setAdapter(arr);
    }

    public void doAddNew(View view) {
        UserDAO userDao = db.userDao();
        userDao.insert(new Angajat());

        listaAngajati = userDao.getAll();
        updateList();
    }

    public void doDelAll(View view) {
        UserDAO userDao = db.userDao();
        userDao.deleteAll();

        listaAngajati = userDao.getAll();
        updateList();
    }
}

