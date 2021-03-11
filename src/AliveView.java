import javax.swing.*;
import java.awt.*;

public class AliveView extends JPanel {



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int indice =(GameOfLife.getInstance().getCountvivi())/10000;
        g.fillRect(0,9-indice,10,10);

    }

    public void aliveBar(){
        repaint();
    }

}
