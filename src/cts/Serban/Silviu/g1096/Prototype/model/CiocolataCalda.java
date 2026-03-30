package cts.Serban.Silviu.g1096.Prototype.model;

public class CiocolataCalda implements Bautura, BauturaPresetata {

    private String name;
    private int volum;
    private double pret;

    public CiocolataCalda(String name, int volum, double pret) {
        this.name = name;
        this.volum = volum;
        this.pret = pret;
    }
    private CiocolataCalda(){};

    @Override
    public String getDetalii() {
        final StringBuilder sb = new StringBuilder("CiocolataCalda - ");
        sb.append(name);
        sb.append(", ").append(volum);
        sb.append(" ml , ").append(pret);
        sb.append(" lei ");
        return sb.toString();
    }


    @Override
    public double getPret() {
        return this.pret;
    }

    @Override
    public boolean adaugaTopping() {
        return false;
    }

    @Override
    public void preparare() {
        System.out.println("Ciocolata calda este in curs de preparare");
    }

    @Override
    public BauturaPresetata copiere() {
        CiocolataCalda copie = new CiocolataCalda();
        copie.pret = this.pret;
        copie.name = this.name;
        copie.volum = this.volum;
        return copie;
    }
}
