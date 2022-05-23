package org.example;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static ArrayList<Piatti> piatti = new ArrayList<>();

    public static void main( String[] args )
    {
        buildProductList();
        System.out.println(piatti);
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildProductList();
        //server.createContext("/applications/myapp", new MyHandler());
        assert server != null;
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    static void buildProductList() {
        piatti.add(new Piatti("carbonara", "Tipico piatto romano", 1, 8.0));
        piatti.add(new Piatti("Risotto allo zafferano", "Tipico piatto milanese", 2, 7.50));
        piatti.add(new Piatti("Pizza margherita", "Orgoglio napoletano", 3, 4.50));
        piatti.add(new Piatti("Pizza capricciosa", "Orgoglio napoletano", 4, 7.50));
        piatti.add(new Piatti("Cotoletta milanese", "Piatto simbolo di milano", 5, 9.50));
        piatti.add(new Piatti("Aragosta", "Piatto costoso", 5, 19.90));
    }

    public static ArrayList<Piatti> getProducts() {
        return piatti;
    }
}
