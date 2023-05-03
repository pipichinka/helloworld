package ru.nsu.Sirotkin.lab2604.task1;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Rss {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://habr.com/ru/rss/best/daily/");
            URLConnection conn = url.openConnection();
            try (InputStream in = conn.getInputStream()){
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.parse(in);
                NodeList list = doc.getElementsByTagName("item");
                for (int i = 0; i < list.getLength(); i++){
                    NodeList children = list.item(i).getChildNodes();
                    for (int j = 0; j < children.getLength(); j++){
                        if (children.item(j).getNodeName().equals("title")){
                            System.out.print("title: " + children.item(j).getTextContent() +".");
                        }
                        else if (children.item(j).getNodeName().equals("link")) {
                            System.out.print(" link: " + children.item(j).getTextContent() + ".");
                        }
                        else if (children.item(j).getNodeName().equals("dc:creator")){
                            System.out.println(" Creator: " + children.item(j).getTextContent() + ".");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
