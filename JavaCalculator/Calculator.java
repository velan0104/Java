package JavaCalculator;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Calculator implements ActionListener,KeyListener {


    Color color = Color.WHITE;
    Color operators = Color.BLUE;
    Color fColor = Color.decode("#383737");
    HashSet<String> operator = new HashSet<>();
    HistoryFunctionality history = new HistoryFunctionality();
    Frame frame = new Frame();
    TextField text = new TextField();
    public Button createButton(String name,Color colors){
        Button newBtn = new Button(name);
        Dimension btnSize = new Dimension(50,50);
        newBtn.setPreferredSize(btnSize);
        newBtn.setBackground(colors);
        if(colors.equals(color)) {
            newBtn.setForeground(Color.blue);
        }else{
            newBtn.setForeground(Color.WHITE);
        }
        newBtn.addActionListener(this);
        return newBtn;
    }

    public GridBagConstraints place(int x, int y,Insets inset){
        GridBagConstraints temp = new GridBagConstraints();
        temp.gridx = x;
        temp.gridy = y;
        temp.insets = inset;

        return temp;
    }

    public Calculator(){
        frame.setBackground(fColor);
        frame.setTitle("Calculator");
        frame.setSize(1200,700);
        frame.setVisible(true);

        Dimension dimension = new Dimension(250,40);
        text.setPreferredSize(dimension);
        text.setEditable(false);
        text.addKeyListener( this);

        Button btn1 = createButton("1",color);
        Button btn2 = createButton("2",color);
        Button btn3 = createButton("3",color);
        Button btn4 = createButton("4",color);
        Button btn5 = createButton("5",color);
        Button btn6 = createButton("6",color);
        Button btn7 = createButton("7",color);
        Button btn8 = createButton("8",color);
        Button btn9 = createButton("9",color);
        Button btn0 = createButton("0",color);
        Button btn00 = createButton("00",color);
        Button add = createButton("+",operators);
        Button sub = createButton("-",operators);
        Button mul = createButton("*",operators);
        Button div = createButton("/",operators);
        Button ans = createButton("=",operators);
        Button clear = createButton("C",operators);
        Button AC = createButton("AC",operators);
        Button dot = createButton(".",color);
        Button mod = createButton("%",operators);

//        History
        Button history = new Button("History");
        history.setPreferredSize(new Dimension(100,50));
        history.setBackground(Color.BLUE);
        history.setForeground(Color.WHITE);
        history.addActionListener(this);

        Panel panel = new Panel(new GridBagLayout());
        panel.setSize(300,500);
        panel.setBackground(fColor);

        Insets insets = new Insets(5,5,5,5);

        GridBagConstraints gbText = new GridBagConstraints();
        gbText.gridx = 0;
        gbText.gridy = 0;
        gbText.gridwidth = 4;
        gbText.insets = insets;

        GridBagConstraints gbAC= place(0,1,insets);
        GridBagConstraints gbMod= place(1,1,insets);
        GridBagConstraints gbC= place(2,1,insets);
        GridBagConstraints gbDiv= place(3,1,insets);
        GridBagConstraints gb7= place(0,2,insets);
        GridBagConstraints gb8 = place(1,2,insets);
        GridBagConstraints gb9 = place(2,2,insets);
        GridBagConstraints gbMul = place(3,2,insets);
        GridBagConstraints gb4 = place(0,3,insets);
        GridBagConstraints gb5 = place(1,3,insets);
        GridBagConstraints gb6 = place(2,3,insets);
        GridBagConstraints gbSub = place(3,3,insets);
        GridBagConstraints gb1 = place(0,4,insets);
        GridBagConstraints gb2 = place(1,4,insets);
        GridBagConstraints gb3 = place(2,4,insets);
        GridBagConstraints gbAdd= place(3,4,insets);
        GridBagConstraints gb00= place(0,5,insets);
        GridBagConstraints gb0= place(1,5,insets);
        GridBagConstraints gbDot= place(2,5,insets);
        GridBagConstraints gbAns= place(3,5,insets);

        GridBagConstraints gbHistory = new GridBagConstraints();
        gbHistory.gridx = 1;
        gbHistory.gridy = 6;
        gbHistory.gridwidth = 2;
        gbHistory.insets = insets;


        panel.add(text,gbText);
        panel.add(btn1,gb1);
        panel.add(btn2,gb2);
        panel.add(btn3,gb3);
        panel.add(btn4,gb4);
        panel.add(btn5,gb5);
        panel.add(btn6,gb6);
        panel.add(btn7,gb7);
        panel.add(btn8,gb8);
        panel.add(btn9,gb9);
        panel.add(btn0,gb0);
        panel.add(btn00,gb00);
        panel.add(AC,gbAC);
        panel.add(mod,gbMod);
        panel.add(clear,gbC);
        panel.add(mul,gbMul);
        panel.add(sub,gbSub);
        panel.add(add,gbAdd);
        panel.add(ans,gbAns);
        panel.add(dot,gbDot);
        panel.add(div,gbDiv);
        panel.add(history,gbHistory);

        frame.add(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        operator.add("+");
        operator.add("-");
        operator.add("*");
        operator.add("/");
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e){
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();

        if(Character.isDigit(key) || key == '+' || key == '-' || key == '*' || key == '/'){
            text.setText(text.getText() + key);
        }else if(text.getText() != "" && keyCode == KeyEvent.VK_BACK_SPACE){
            String eq = text.getText();
            text.setText(eq.substring(0,eq.length() - 1));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = ((Button)e.getSource()).getLabel();
        if(value.equals("History")){
            HistoryFrame hist = new HistoryFrame();
        }
        else if(value.equals("=")){
            String expression = text.getText();
            Solve solve = new Solve();
            String result = solve.eval(expression);
            history.storeHistory(value + " " + result);
            history.storeHistory(expression);
            text.setText(result);
            if(result.equals("ERR")){
                text.setForeground(Color.RED);
            }
        }else if(value.equals("AC")){
            text.setText("");
        }else if(value.equals("C")){
            String eq = text.getText();
            text.setText(eq.substring(0,eq.length() - 1));
        }else {
            if(operator.contains(value)){
                text.setText(text.getText() + " " + value + " ");
            }else{
                text.setText(text.getText() + value);
            }

        }

    }

    public static void main(String[] args){
        Calculator obj = new Calculator();
    }
}
