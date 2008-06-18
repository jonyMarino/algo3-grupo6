package vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CuadroIngresoUsuario extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public CuadroIngresoUsuario() {
		JLabel label = new JLabel("Nombre Usuario: ");
			label.setFont(new Font("Usuario",Font.BOLD,16));
		this.add(label);
			JTextField box = new JTextField("",50);
			box.setBackground(Color.LIGHT_GRAY);
			//box.addActionListener();
		this.add(box);
		
		this.setLayout(new FlowLayout());
	}
	
}
