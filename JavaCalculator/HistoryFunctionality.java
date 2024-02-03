package JavaCalculator;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class HistoryFunctionality {

    static int pattern = 0;
    public void read(){

        try {
            String path = "C:/Users/theva/IdeaProjects/Recursion/Project/src/JavaCalculator/History.txt";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void storeHistory(String values){
        String path = "C:/Users/theva/IdeaProjects/Recursion/Project/src/JavaCalculator/History.txt";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
            writer.append(values);

            if(pattern == 1){
                writer.newLine();
                writer.write("-----------------------------------------------");
            }

            if(values.contains("=")) {
                pattern++;
            }else{
                pattern = (pattern > 0) ? pattern - 1 : 0;
            }
            writer.newLine();
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

}
