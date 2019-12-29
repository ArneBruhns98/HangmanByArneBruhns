import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class HangmanView extends JFrame {


    private JTextField display = new JTextField("");
    private List<List<JButton>> buchstabenButtons = new LinkedList<>();
    private JButton startButton = new JButton("Let's Go ");
    private JButton displayHangman = new JButton();

    private HangmanModel model;

    private static final int INSET = 20;
    private static final Font FONT = new Font("Sans Serif", Font.BOLD, 25);
    private static final Font FONT_HANGMAN = new Font("Sans Serif", Font.BOLD, 10);
    private static final Insets INSETS = new Insets(INSET, INSET, INSET, INSET);

    public HangmanView(HangmanModel m){
        super("Hangman by Arne Bruhns");
        this.model = m;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.display.setEditable(false);
        this.display.setPreferredSize(new Dimension(this.model.getWidth(),65));
        this.display.setFont(FONT);

        this.displayHangman.setPreferredSize(new Dimension(300,this.model.getHeight()));
        this.displayHangman.setFont(FONT_HANGMAN);
        this.displayHangman.setBackground(Color.DARK_GRAY);
        this.setDisplayHangmanImage();
        //this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Programmieren II/Hangman by Arne Bruhns/HangmanLeben0.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));

        this.startButton.setBackground(Color.BLACK);
        this.startButton.setForeground(Color.WHITE);
        this.startButton.setPreferredSize(new Dimension(this.model.getWidth(),75));
        this.startButton.setFont(FONT);

        Panel tastenpanel = new Panel();
        GridLayout grid = new GridLayout(this.model.getHeight(), this.model.getWidth(), 5, 5);
        tastenpanel.setLayout(grid);

        for(int row = 0; row < this.model.getHeight(); row++){
            List<JButton> button_row = new LinkedList<>();
            buchstabenButtons.add(button_row);
            for(int col = 0; col < this.model.getWidth(); col++){
                JButton b = new JButton(this.model.get(row, col));
                b.setOpaque(true);
                b.setBorderPainted(false);
                b.setFont(FONT);
                b.setMargin(INSETS);
                b.setBackground(Color.LIGHT_GRAY);
                button_row.add(b);
                tastenpanel.add(b);
            }
        }

        this.add(this.display, BorderLayout.NORTH);
        this.add(this.startButton, BorderLayout.SOUTH);
        this.add(this.displayHangman, BorderLayout.WEST);
        this.add(tastenpanel, BorderLayout.CENTER);


        this.setSize(900, 750);
        this.setVisible(true);
    }

    public List<List<JButton>> getBuchstabenButtons(){
        return this.buchstabenButtons;
    }

    public JButton getStartButton(){
        return this.startButton;
    }

    protected void setModel(HangmanModel m){
        this.model = m;
    }

    public void clearUpdate(){
        for(int row = 0; row < this.model.getHeight(); row++) {
            for (int col = 0; col < this.model.getWidth(); col++) {
                JButton b = this.buchstabenButtons.get(row).get(col);
                b.setVisible(true);
                b.setBackground(Color.LIGHT_GRAY);
                this.startButton.setBackground(Color.BLACK);
                this.startButton.setForeground(Color.WHITE);
                this.startButton.setText("Leben: " + this.model.getLife());
                this.display.setForeground(Color.BLACK);
                this.display.setText(this.model.getMaskedWord());
                this.setDisplayHangmanImage();
            }
        }
    }

    public void update(){
        for(int row = 0; row < this.model.getHeight(); row++){
            for(int col = 0; col < this.model.getWidth(); col++){
                JButton b = this.buchstabenButtons.get(row).get(col);
                b.setVisible(true);
                b.setBackground(Color.LIGHT_GRAY);
                startButton.setText("Leben: " + this.model.getLife());
                display.setText(this.model.getMaskedWord());

                this.setDisplayHangmanImage();

                if(this.model.getCorrectChars().contains(this.model.get(row, col))) b.setBackground(Color.GREEN);
                if((this.model.getFalseChars().contains(this.model.get(row, col)))) b.setBackground(Color.RED);

                if(this.model.isDead()){
                    this.startButton.setText("You lost! Play Again?");
                    this.startButton.setBackground(Color.RED);
                    this.startButton.setForeground(Color.WHITE);
                    this.display.setText("Das gesuchte Wort war: " + this.model.getWord());
                    this.display.setForeground(Color.RED);
                }

                if (this.model.isGuessed()) {
                    this.startButton.setText("You win! Play Again?");
                    this.startButton.setBackground(Color.GREEN);
                    this.startButton.setForeground(Color.WHITE);
                    this.display.setForeground(Color.GREEN);
                }
            }
        }
    }

    public void setDisplayHangmanImage(){
        if(this.model.getLife() == 7) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben7.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 6) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben6.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 5) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben5.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 4) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben4.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 3) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben3.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 2) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben2.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 1) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben1.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));
        if(this.model.getLife() == 0) this.displayHangman.setIcon(new ImageIcon(new ImageIcon("c:/users/arne-/IdeaProjects/Hangman by Arne Bruhns/HangmanLeben0.png").getImage().getScaledInstance(300, 580, java.awt.Image.SCALE_SMOOTH)));

    }

}
