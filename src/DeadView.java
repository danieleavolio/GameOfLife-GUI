import javax.swing.*;
import java.awt.*;

public class DeadView extends JPanel {



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int indice =(GameOfLife.getInstance().getTotali() - GameOfLife.getInstance().getCountvivi())/10000;
        g.fillRect(0,10-indice,10,10);
    }

    public void deadBar(){
        repaint();
    }

}
