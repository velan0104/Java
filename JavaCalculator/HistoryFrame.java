package JavaCalculator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HistoryFrame{

    Frame frame = new Frame();
    TextArea content = new TextArea();
    HistoryFrame() {
        String path = "C:/Users/theva/IdeaProjects/Recursion/Project/src/JavaCalculator/History.txt";
        content.setText("-------------------HISTORY---------------------\n\n");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null){
                content.setText(content.getText() + line + "\n\n");

            }
        }catch(IOException e){
            System.out.println(e);
        }

        frame.setTitle("History");
        frame.setSize(500,900);
        frame.add(content);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                frame.dispose();
            }
        });


    }

}
