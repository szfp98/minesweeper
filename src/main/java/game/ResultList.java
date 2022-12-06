package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Az eredmények tárolását és fájlba írását megvalósító osztály
 */
public class ResultList {
    ArrayList<Result> results;
    String filePath;
    public ResultList(){
        results=new ArrayList<>();
        filePath=System.getProperty("user.dir")+"/results.JSON";
    }

    /**
     * @param result Az eredményt hozzáadja a listához, majd sorba rendezi a végigjátszási idő szerint
     */
    public void addResult(Result result){
        if(result!=null){
            results.add(result);
            results.sort(Comparator.comparingInt(Result::getTime));
        }
    }

    public void loadFromJson() throws IOException {
        try{
            Gson gson=new Gson();
            Path path=Paths.get(filePath);
            if(Files.exists(path)){
                Reader reader= Files.newBufferedReader(path);
                results=gson.fromJson(reader, new TypeToken<ArrayList<Result>>(){}.getType());
                reader.close();
            }
        } catch (IOException e){
            throw new IOException("Failed to load results from file: "+e.getMessage());
        }
    }
    public void saveToJson() throws IOException {
        try{
            Writer writer=Files.newBufferedWriter(Paths.get(filePath));
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            JsonElement json=gson.toJsonTree(results);
            gson.toJson(json, writer);
            writer.close();
        } catch (IOException e) {
            throw new IOException("Failed to save results to file: "+e.getMessage());
        }
    }

    public String[][] toArray() throws NullPointerException{
        if(!results.isEmpty()){
            String[][] arr=new String[results.size()][4];
            for(int i=0; i<results.size(); i++){
                Result result=results.get(i);
                arr[i][0]=String.valueOf(i+1);
                arr[i][1]=String.valueOf(result.getTime());
                arr[i][2]=result.getLevel();
                arr[i][3]=result.getDate().toString();
            }
            return arr;
        }
        else
            throw new NullPointerException("The results list is empty");
    }
    public String toString(){
        StringBuilder ret= new StringBuilder();
        for(int i=0; i<results.size(); i++){
            ret.append(i+1).append(".\t").append(results.get(i).toString()).append('\n');
        }
        return ret.toString();
    }
}
