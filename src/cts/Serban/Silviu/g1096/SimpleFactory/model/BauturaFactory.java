package cts.Serban.Silviu.g1096.SimpleFactory.model;

public class BauturaFactory {
    private static BauturaFactory instance;
    public static synchronized BauturaFactory getInstance(){
        if(instance == null){
            instance = new BauturaFactory();
        }
        return instance;
    }

    private BauturaFactory() {
    }

    public Bautura getBautura(String nume, int volum, double pret, BauturaType tip){
        if(tip == BauturaType.Ceai) {
            return new Ceai(nume,volum,pret);
        }else if(tip == BauturaType.Cafea){
            return new Cafea(nume,volum,pret);
        }else if(tip==BauturaType.CiocolataCalda){
            return new CiocolataCalda(nume,volum,pret);
        }else return null;
    }
}
