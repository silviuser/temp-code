package cts.Serban.Silviu.g1096.Prototype.main;

import cts.Serban.Silviu.g1096.Prototype.model.Bautura;
import cts.Serban.Silviu.g1096.Prototype.model.Cafea;
import cts.Serban.Silviu.g1096.Prototype.model.Ceai;

public class Main {
    public static void main(String[] args) {
        Bautura cafea1 = new Cafea("Latte",230,21);
        Bautura cafea2 = (Bautura) ((Cafea) cafea1).copiere();

        Bautura ceai1 = new Ceai("Ceai de menta",220,14);
        Bautura ceai2 = (Bautura) ((Ceai)ceai1).copiere();

        System.out.println(cafea1.getDetalii());
        System.out.println(cafea2.getDetalii());

        System.out.println(ceai1.getDetalii());
        System.out.println(ceai2.getDetalii());
    }
}
