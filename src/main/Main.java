package main;
import java.io.File;
import java.io.IOException;
import request.Request;
public class Main{
    public static void main(String[] args) {
        Request req = new Request("https://usito.usherbrooke.ca/index/articles#a");
        req.establishConnection();
        File f = new File(System.getProperty("user.dir") + "/test/output.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.writeBodyToFile(f);    
    }
}