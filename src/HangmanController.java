import javax.swing.*;

public class HangmanController {

    protected HangmanView view;
    protected HangmanModel model;

    public HangmanController(HangmanView v, HangmanModel m){
        this.view = v;
        this.model = m;
    }

    public void start(){
        this.view.getStartButton().addActionListener(click -> this.playTheGame());
    }

    private void playTheGame(){
        this.view.update();

        for(int row = 0; row < this.model.getHeight(); row++){
            for(int col = 0; col < this.model.getWidth(); col++){
                JButton button = this.view.getBuchstabenButtons().get(row).get(col);
                final int r = row;
                final int c = col;
                button.addActionListener(click -> this.clicked(r, c));
            }
        }
    }

    private void clicked(int row, int col){
        this.model.guessMaskedWord(this.model.get(row, col));
        this.view.update();

        if(this.model.isGuessed() || this.model.isDead()) this.finshedGame();
    }

    private void finshedGame(){
        this.view.getStartButton().removeActionListener(this.view.getStartButton().getActionListeners()[0]);
        this.view.getStartButton().addActionListener(click -> this.newGame());
    }

    private void newGame(){
        this.view.getStartButton().removeActionListener(this.view.getStartButton().getActionListeners()[0]);
        this.model.clear();
        this.view.setModel(this.model);
        this.view.clearUpdate();
        this.start();
    }
}
