package org.example;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;

class MyHandler implements HttpHandler {
    static final ArrayList<Piatti> all=App.getProducts();
    public void handle(HttpExchange t) throws IOException {
        URI uri = t.getRequestURI();
        String query = uri.getQuery();
        if(query!=null) {
            runner(query, t);
        }
        else{
            printResponse("You have not sent nothing", 400, t);
        }
    }
}
