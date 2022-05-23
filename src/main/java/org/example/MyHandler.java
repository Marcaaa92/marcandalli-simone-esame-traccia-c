package org.example;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;

class MyHandler implements HttpHandler {
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
    public void runner(String query, HttpExchange t) throws IOException {
        String[] param;
        param = query.split("&");
        if(param.length==1) {
            String[] value = param[0].split("=");
            if (value[0].equals("cmd")) {
                switch (value[1]) {
                    case "all" -> printResponse(printHTML(printUL("all")), 200, t);
                    case "more_expensive" -> printResponse(printHTML(printUL("more_expensive")), 200, t);
                    case "all_sorted" -> printResponse(printHTML(printUL("all_sorted")), 200, t);
                    default -> printResponse("You have not inserted a correct filter", 400, t);
                }
            }
            else {
                printResponse("wrong syntax", 400, t);
            }
        }
        else{
            printResponse("Too much param", 400, t);
        }
    }
    public void printResponse(String text, int errorCode, HttpExchange t) throws IOException {
        String response = printHTML(text);
        t.sendResponseHeaders(errorCode, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    public String printUL(String action){
        StringBuilder outputList = null;
        switch (action) {
            case "all" -> {
                return App.allStr;
            }
            case "all_sorted" -> {
                outputList = new StringBuilder("<ul>");
                ArrayList<Piatti> sorted;
                sorted = sort();
                for (int i=0; i<sorted.size(); i++) {
                    outputList.append("<li>Id: ").append(sorted.get(i).getId()).append(" Name: ").append(sorted.get(i).getName()).append(" Description: ").append(sorted.get(i).getDescription()).append(" Price: ").append(sorted.get(i).getPrice()).append("</li>");
                }
                outputList.append("</ul>");
            }
            case "more_expensive" -> {
                outputList = new StringBuilder("<ul>");
                ArrayList<Piatti> sorted;
                sorted = sort();
                outputList.append("<li>Id: ").append(sorted.get(sorted.size() - 1).getId()).append(" Name: ").append(sorted.get(sorted.size() - 1).getName()).append(" Description: ").append(sorted.get(sorted.size() - 1).getDescription()).append(" Price: ").append(sorted.get(sorted.size() - 1).getPrice()).append("</li>");
                outputList.append("</ul>");
            }
        }
        return outputList.toString();
    }
    public String printHTML(String text){
        String htmlCode="""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Piatti</title>
                            </head>
                            <body>
                                <p>
                                    """+text+"""
                                </p>
                            </body>
                        </html>
                """;
        return htmlCode;
    }
    public ArrayList<Piatti> sort(){
        ArrayList<Piatti> p = App.getProducts();
        p.sort((Piatti primo, Piatti secondo) -> {if(primo.getPrice()>secondo.getPrice()){return 1;}if(primo.getPrice()<secondo.getPrice()){return -1;}return 0;});
        return p;
    }
}
