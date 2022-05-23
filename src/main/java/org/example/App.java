package org.example;
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
