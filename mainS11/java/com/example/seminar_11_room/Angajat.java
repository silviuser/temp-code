package com.example.seminar_11_room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Random;



//@Entity(primaryKeys = {"firstName", "lastName"})
//@Entity(ignoredColumns = "picture")

// index - unique sau nu
// @Entity(indices = {@Index(value = {"first_name", "last_name"},
//        unique = true)})
@Entity(tableName = "ANGAJATI")
public class Angajat {
    @PrimaryKey
    public int cod;

    @ColumnInfo(name = "Nume")
    public String nume;

    @ColumnInfo(name = "Prenume")
    public String prenume;

    String cnp;

    int varsta;

    @Ignore
    int salariu;

    public Angajat(int cod, String nume, String prenume, String cnp, int varsta) {
        this.cod = cod;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.varsta = varsta;
        this.salariu = 1000;
    }

    public Angajat(){
        Random rnd = new Random();
        this.cod = rnd.nextInt(100);
        this.nume = MainActivity.Nume[rnd.nextInt(MainActivity.Nume.length)];
        this.prenume = MainActivity.Prenume[rnd.nextInt(MainActivity.Prenume.length)];
        this.cnp = String.valueOf(rnd.nextInt(1000000));
        this.varsta = rnd.nextInt(100);
        this.salariu = rnd.nextInt();
    }

    @Override
    public String toString() {
        return "" + cod +
                " - " + nume +
                " - " + prenume +
                " - " + cnp +
                " - " + varsta;
    }
}