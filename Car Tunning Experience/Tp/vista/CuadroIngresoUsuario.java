package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CuadroIngresoUsuario extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public CuadroIngresoUsuario(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		JLabel label = new JLabel("Nombre Usuario: ");
			label.setFont(new Font("Verdana",Font.BOLD,14));
			
		this.add(label);
			JTextField box = new JTextField("",30);
			box.setBackground(Color.LIGHT_GRAY);

		this.add(box);
	}
}
