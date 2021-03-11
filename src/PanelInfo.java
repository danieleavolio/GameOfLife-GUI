import javax.swing.*;

public class PanelInfo extends JPanel {
    String colori [] = {"Blue", "Yellow", "Red", "Green", "Black", "White", "Cyan"};
    public JComboBox coloriSomething = new JComboBox(colori);

    public PanelInfo(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(coloriSomething);

    }

}
