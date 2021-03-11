import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Grafica extends  JPanel implements  Runnable {

    int cellDim =4;
    int mouseMovX = 0;
    int mouseMovY = 0;

    JLabel alive;
    Color sceltaViva;
    Color sceltaMorte;
    JLabel tickShown;
    AliveView aliveView;
    DeadView deadView;
    HashMap <String,Color> mappaColori = new HashMap<>();
    GameOfLife gameOfLife = GameOfLife.getInstance();
    public Grafica(String sceltaViva, String sceltaMorte, JLabel alive, JLabel ticksShown, JPanel aliveView, JPanel deadView) {
        //String colori [] = {"Blue", "Yellow", "Red", "Green", "Black", "White", "Cyan"};
        mappaColori.put("Blue", Color.BLUE);
        mappaColori.put("Yellow", Color.yellow);
        mappaColori.put("Red", Color.red);
        mappaColori.put("Green", Color.GREEN);
        mappaColori.put("Black", Color.BLACK);
        mappaColori.put("White", Color.WHITE);
        mappaColori.put("Cyan", Color.CYAN);
        this.setBackground(Color.black);
        this.sceltaViva = mappaColori.get(sceltaViva);
        this.sceltaMorte = mappaColori.get(sceltaMorte);
        this.tickShown = ticksShown;
        this.alive = alive;
        this.aliveView = (AliveView) aliveView;
        this.deadView = (DeadView) deadView;
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //MOVED UP
                if(e.getWheelRotation() < 0)
                    cellDim = cellDim+1;
                else
                    cellDim = cellDim-1;
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                grabFocus();
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    mouseMovX+=10;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    mouseMovX-=10;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    mouseMovY-=10;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    mouseMovY+=10;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color backup = sceltaMorte;
        sceltaMorte = mappaColori.get(gameOfLife.getColoriMorti());
        sceltaViva = mappaColori.get(gameOfLife.getColoriVivi());
        this.setBackground(sceltaMorte);
        if (sceltaMorte == sceltaViva)
            sceltaMorte = backup;
        tickShown.setText(gameOfLife.getTicks());
        for (int i = 0; i < gameOfLife.matrice.length; i++) {
            for (int j = 0; j < gameOfLife.matrice.length; j++) {
                if (gameOfLife.matrice[i][j] == 1)
                    g.setColor(sceltaViva);
                else
                    g.setColor(sceltaMorte);
                g.fillRect((i*cellDim)+mouseMovX, ((j*cellDim)+mouseMovY), cellDim, cellDim);
            }
        }
        gameOfLife.controllo();

        alive.setText(String.valueOf(gameOfLife.getCountvivi()));
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(Long.parseLong(gameOfLife.getTicks()));
                if (gameOfLife.getRunning()) {
                    repaint();
                    aliveView.aliveBar();
                    deadView.deadBar();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
