package cts.Serban.Silviu.g1096.Prototype.model;

public class Ceai implements Bautura,BauturaPresetata {
    private String name;
    private int volum;
    private double pret;

    public Ceai(String name, int volum, double pret) {
        this.name = name;
        this.volum = volum;
        this.pret = pret;
    }
    private Ceai(){};
    @Override
    public String getDetalii() {
        final StringBuilder sb = new StringBuilder("Ceai - ");
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
        System.out.println("Ceaiul este in curs de preparare");
    }

    @Override
    public BauturaPresetata copiere() {
        Ceai copie = new Ceai();
        copie.pret = this.pret;
        copie.name = this.name;
        copie.volum = this.volum;
        return copie;
    }
}
