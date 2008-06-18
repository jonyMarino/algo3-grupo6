package vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComboBoxCars extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel picture;

    public ComboBoxCars() {
        super(new BorderLayout());
        String[] carsStrings = { "Guido", "Flo", "Mate", "Sheriff", "Rayo McQueen" };

        JComboBox carsList = new JComboBox(carsStrings);
        carsList.setSelectedIndex(4);
        carsList.addActionListener(this);

        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(carsStrings[carsList.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        picture.setPreferredSize(new Dimension(50, 122+10));

        add(carsList, BorderLayout.PAGE_START);
        add(picture, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String carName = (String)cb.getSelectedItem();
        updateLabel(carName);
    }

    protected void updateLabel(String name) {
        ImageIcon icon = createImageIcon("/vista/images/" + name + ".gif");
        picture.setIcon(icon);
        picture.setToolTipText("A drawing of a " + name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ComboBoxCars.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
