package cts.Serban.Silviu.g1096.SimpleFactory.main;

import cts.Serban.Silviu.g1096.SimpleFactory.model.Bautura;
import cts.Serban.Silviu.g1096.SimpleFactory.model.BauturaFactory;
import cts.Serban.Silviu.g1096.SimpleFactory.model.BauturaType;

public class Main {
    public static void main(String[] args) {
        BauturaFactory cafenea = BauturaFactory.getInstance();

        Bautura cafea1 = cafenea.getBautura("Capuccino",200,20,BauturaType.Cafea);
        Bautura cafea2 = cafenea.getBautura("Americano",250,17,BauturaType.Cafea);
        Bautura ceai = cafenea.getBautura("Ceai de menta",200,13,BauturaType.Ceai);
        Bautura ciocolataCalda = cafenea.getBautura("Ciocolata calda cu vanilie",230,21,BauturaType.CiocolataCalda);

        cafea1.preparare();
        ceai.preparare();
        ciocolataCalda.preparare();


    }
}