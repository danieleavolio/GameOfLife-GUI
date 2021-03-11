import javax.swing.*;
import java.awt.*;

public class Main {

    public final static int width = 1300;
    public final static int height = 800;
    public static void main(String[] args) {
        //colori iniziali da usare

        JFrame frame = new JFrame("Game of Life");
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        //Clicca start per iniziare la simulazione
        JButton start = new JButton("Start");
        JButton pause = new JButton("Pause");
        JButton reset = new JButton("RESET");

        start.addActionListener(e->{
            GameOfLife.getInstance().setRunning(true);
        });
        pause.addActionListener(e->{
            GameOfLife.getInstance().setRunning(false);
        });

        reset.addActionListener(e->{
            GameOfLife.getInstance().reset();
        });

        JPanel panelBottoni = new JPanel();
        JPanel aliveView = new AliveView();
        JPanel deadView = new DeadView();
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

        JLabel aliveString = new JLabel("Alive:");
        JLabel alive = new JLabel("");

        panelBottoni.add(start);
        panelBottoni.add(pause);
        panelBottoni.add(coloriVivi);
        panelBottoni.add(coloriMorti);
        panelBottoni.add(ticks);
        panelBottoni.add(ticksShown);
        panelBottoni.add(aliveString);
        panelBottoni.add(alive);
        panelBottoni.add(aliveView);
        panelBottoni.add(deadView);
        panelBottoni.add(reset);
        frame.add(panelBottoni, BorderLayout.SOUTH);

        Grafica grafica = new Grafica(String.valueOf(coloriMorti.coloriSomething.getSelectedItem()),String.valueOf(coloriVivi.coloriSomething.getSelectedItem()), alive, ticksShown, aliveView, deadView);
        frame.add(grafica,BorderLayout.CENTER);
        frame.setFocusable(true);
        frame.setVisible(true);

        Thread t = new Thread(grafica);
        t.start();

    }
}
