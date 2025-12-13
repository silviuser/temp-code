package com.example.seminar_9;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Network extends AsyncTask<URL, Void, InputStream> {

    static String rezultat = "";

    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection connection;

        try{
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            InputStream ist = connection.getInputStream();

            getRatesBNR(ist);

//            InputStreamReader isr = new InputStreamReader(ist);
//            BufferedReader br = new BufferedReader(isr);
//            String linie = null;
//            while ((linie = br.readLine())!=null)
//            {
//                rezultat+=linie;
//            }
//            rezultat += "";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void getRatesBNR(InputStream ins){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDOC = db.parse(ins);
            domDOC.getDocumentElement().normalize();

            // search for cube node
            Node cube = null;
            NodeList list = domDOC.getDocumentElement().getChildNodes();
            for(int i=0; i<list.getLength(); i++)
                if (list.item(i).getNodeName().toLowerCase().strip().equals("body")){
                    list = list.item(i).getChildNodes();
                    break;
                }
            for(int i=0; i<list.getLength(); i++)
                if (list.item(i).getNodeName().toLowerCase().strip().equals("cube")){
                    cube = list.item(i);
                    break;
                }

            rezultat = "";
            String dataCurs =  ((Element) cube).getAttribute("date");
            rezultat = "Data cotatiei - " + dataCurs + "\n";
            rezultat += "========================\n";

            NodeList listaCopii = cube.getChildNodes();
            for (int i = 0; i < listaCopii.getLength(); i++){
                Node curs = listaCopii.item(i);
                String atribut = ((Element) curs).getAttribute("currency");
                if (atribut.equals("EUR")){
                    rezultat += "EURO - " + curs.getTextContent() + "\n";
                }
                if (atribut.equals("USD")){
                    rezultat += "US DOLLAR - " + curs.getTextContent() + "\n";
                }
                if (atribut.equals("CHF")){
                    rezultat += "SWISS FRANC - " + curs.getTextContent() + "\n";
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }


    }
}
