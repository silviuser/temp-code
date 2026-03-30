package cts.Serban.Silviu.g1096.Singleton.model;

public class CasaDeMarcat {
    private static CasaDeMarcat instance;

    private CasaDeMarcat() {
    }

    public static synchronized CasaDeMarcat getInstance() {
        if(instance == null){
            instance = new CasaDeMarcat();
        }
        return instance;

    }
}
