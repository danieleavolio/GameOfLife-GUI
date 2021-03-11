import org.ietf.jgss.GSSManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //colori iniziali da usare

        JFrame frame = new JFrame("Game of Life");
        frame.setSize(1300,800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        //Clicca start per iniziare la simulazione
        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");

        start.addActionListener(e->{
            GameOfLife.getInstance().setRunning(true);
        });
        pause.addActionListener(e->{
            GameOfLife.getInstance().setRunning(false);
        });

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());
        //Cambia i colori delle celle
        PanelInfo coloriVivi = new PanelVive();
        PanelInfo coloriMorti = new PanelMorte();

        coloriMorti.coloriSomething.addActionListener(e ->{
            GameOfLife.getInstance().setColoriMorti(String.valueOf(coloriMorti.coloriSomething.getSelectedItem()));
        });
        coloriVivi.coloriSomething.addActionListener(e ->{
            GameOfLife.getInstance().setColoriVivi(String.valueOf(coloriVivi.coloriSomething.getSelectedItem()));
        });

        JSlider ticks = new JSlider(JSlider.HORIZONTAL, 0,100,50);
        ticks.setMinorTickSpacing(2);
        ticks.setMajorTickSpacing(10);
        ticks.setPaintTicks(true);
        ticks.setPaintLabels(true);
        ticks.addChangeListener(e->{
            GameOfLife.getInstance().setTicks(String.valueOf(ticks.getValue()));
        });
        //Mostra il tick in modo esatto
        JLabel ticksShown = new JLabel(String.valueOf(ticks.getValue()));
        panelBottoni.add(start);
        panelBottoni.add(pause);
        panelBottoni.add(coloriVivi);
        panelBottoni.add(coloriMorti);
        panelBottoni.add(ticks);
        panelBottoni.add(ticksShown);
        frame.add(panelBottoni, BorderLayout.SOUTH);

        Grafica grafica = new Grafica(String.valueOf(coloriMorti.coloriSomething.getSelectedItem()),String.valueOf(coloriVivi.coloriSomething.getSelectedItem()), ticksShown);
        frame.add(grafica,BorderLayout.CENTER);
        frame.setVisible(true);

        Thread t = new Thread(grafica);
        t.start();

    }
}
