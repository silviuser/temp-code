package cts.Serban.Silviu.g1096.SimpleFactory.model;

public class Ceai implements Bautura{
    private String name;
    private int volum;
    private double pret;

    public Ceai(String name, int volum, double pret) {
        this.name = name;
        this.volum = volum;
        this.pret = pret;
    }

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
}
