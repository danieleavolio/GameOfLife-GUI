import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Grafica extends  JPanel implements  Runnable {

    Color sceltaViva;
    Color sceltaMorte;
    JLabel tickShown;
    HashMap <String,Color> mappaColori = new HashMap<>();
    GameOfLife gameOfLife = GameOfLife.getInstance();
    public Grafica(String sceltaViva, String sceltaMorte, JLabel ticksShown) {
        //String colori [] = {"Blue", "Yellow", "Red", "Green", "Black", "White", "Cyan"};
        mappaColori.put("Blue", Color.BLUE);
        mappaColori.put("Yellow", Color.yellow);
        mappaColori.put("Red", Color.red);
        mappaColori.put("Green", Color.GREEN);
        mappaColori.put("Black", Color.BLACK);
        mappaColori.put("White", Color.WHITE);
        mappaColori.put("Cyan", Color.CYAN);

        this.sceltaViva = mappaColori.get(sceltaViva);
        this.sceltaMorte = mappaColori.get(sceltaMorte);
        this.tickShown = ticksShown;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color backup = sceltaMorte;
        sceltaMorte = mappaColori.get(gameOfLife.getColoriMorti());
        sceltaViva = mappaColori.get(gameOfLife.getColoriVivi());
        if (sceltaMorte == sceltaViva)
            sceltaMorte = backup;
        tickShown.setText(gameOfLife.getTicks());
        for (int i = 0; i < gameOfLife.matrice.length; i++) {
            for (int j = 0; j < gameOfLife.matrice.length; j++) {
                if (gameOfLife.matrice[i][j] == 1)
                    g.setColor(sceltaViva);
                else
                    g.setColor(sceltaMorte);
                g.fillRect(i*5, j*5, 5, 5);
            }
        }
        gameOfLife.controllo();
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(Long.parseLong(gameOfLife.getTicks()));
                if (gameOfLife.getRunning())
                    repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
