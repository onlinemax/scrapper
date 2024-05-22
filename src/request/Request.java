package request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class Request{
    private final String address;
    private String body;
    public Request(String address){
        this.address = address;
    } 
    public String getBody(){
        return body;
    }
    public void writeBodyToFile(File file){
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(body);            
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void establishConnection(){
        URL url = null;
        this.body = "";
        try{
            url = new URI(address).toURL();       
            
        }catch(URISyntaxException e){
            System.err.println(e.getMessage());
        }
        catch(MalformedURLException e){
            System.err.println(e.getMessage());
        }
        try{
            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            System.out.println("Connection gives response code: " + connection.getResponseCode());
            this.body = getResponseString(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            connection.disconnect();
        }catch(IOException e){
            System.err.println("Can't connect");
        }



    }
    private String getResponseString(BufferedReader reader) throws IOException{
        StringBuilder builder = new StringBuilder("");
        while (reader.ready()){ 
            builder.append(reader.readLine());
            builder.append("\n");
        } 
        return builder.toString();
    }
    
   
}