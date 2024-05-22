package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FileOpener{
    private final File file;
    public FileOpener(File f){
        this.file = f;
        checkIfExist(this.file);
    }
    public FileOpener(String filePath){
        this(new File(filePath));
    }
    private void checkIfExist(File f){
        if (!f.exists())
            throw new IOException(String.format("The file: %s, does not exits\n", f.getAbsolutePath()));
    }
    public String readFile(){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            while (reader.ready()){
                builder.append(reader.readLine());
                builder.append("\n");
            }
        } catch (FileNotFoundException ex) {
            return "Couldn't read file";
        } catch (IOException ex) {
            return "Couln't read file";
        }
        return builder.toString();
    }
}