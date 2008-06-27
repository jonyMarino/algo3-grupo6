package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import controlador.ControladorJuego;

public class PantallaUsuario extends JPanelConImagen {

	private static final long serialVersionUID = 1L;
	private CuadroIngresoUsuario panelIngreso;
	private ComboBoxCars comboBoxCars;
	private JLabel botonError;
	
	public PantallaUsuario(ControladorJuego controladorJuego) {
		
		super();
		this.setImage("FondoTransparente");
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(new BoxLayout(panelUsuario,BoxLayout.Y_AXIS));
		
			this.panelIngreso = new CuadroIngresoUsuario(controladorJuego);
			this.panelIngreso.setOpaque(false);
			
			this.comboBoxCars = new ComboBoxCars();
			comboBoxCars.setOpaque(false);
			
			this.botonError = new JLabel();
	        botonError.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
	        botonError.setForeground(Color.BLACK);
	        botonError.setFont(new Font("Arial", Font.BOLD, 17));      
	        
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridx =0;
	    c.gridy =0;
	    c.anchor = GridBagConstraints.NORTH;
	    panelUsuario.setLayout(new GridBagLayout());
	    panelUsuario.add(panelIngreso,c);
	    c.gridy =1;
	    panelUsuario.add(comboBoxCars,c);
	    c.gridy =2;
	    panelUsuario.add(botonError,c);
	    panelUsuario.setOpaque(false);
		
		BotoneraUsuario botoneraUsuario = new BotoneraUsuario(controladorJuego);

		this.add(panelUsuario);
		this.add(botoneraUsuario);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   		this.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));
	}

	public void generarMensajeError(String mensajeError) {
	   
	   botonError.setText(mensajeError);
	   panelIngreso.getBox().setBackground(Color.WHITE);
	   panelIngreso.getBox().setOpaque(true);			
	}
	
	public String obtenerNombreBoxUsuario() {
		
		return panelIngreso.getBox().getText();
	}
	
	public ImageIcon obtenerImagenSeleccionada() {
		
		return comboBoxCars.getSeleccionado();
	}

	private class BotoneraUsuario extends JPanel {

		private static final long serialVersionUID = 1L;

		public BotoneraUsuario(ControladorJuego controladorJuego) {

			Boton botonVolver = new Boton("Volver");
			botonVolver.setActionCommand("volver");
			botonVolver.addActionListener(controladorJuego);

			Boton botonAceptar = new Boton("Aceptar");
			botonAceptar.setActionCommand("aceptar");
			botonAceptar.addActionListener(controladorJuego);

			this.add(botonVolver);
			this.add(botonAceptar);

			setOpaque(false);
		}

	}
	
	private class ComboBoxCars extends JPanel {
		    
			private static final long serialVersionUID = 1L;
			private ComboBoxRenderer renderer;
			private ImageIcon[] images;
			private String[] carStrings = { "Guido", "Flo", "Mate", "Sheriff", "Rayo McQueen" };

		    public  ComboBoxCars() {
		        super(new BorderLayout());

		        images = new ImageIcon[carStrings.length];
		        Integer[] intArray = new Integer[carStrings.length];
		        for (int i = 0; i < carStrings.length; i++) {
		            intArray[i] = new Integer(i);
		            images[i] = createImageIcon("/vista/images/" + carStrings[i]+ ".gif");
		            if (images[i] != null) {
		                images[i].setDescription(carStrings[i]);
		            }
		        }
  
		        JComboBox carList = new JComboBox(intArray);
		        renderer= new ComboBoxRenderer();
		        renderer.setPreferredSize(new Dimension(250, 100));
		        carList.setRenderer(renderer);
		        carList.setMaximumRowCount(3);

		        carList.setSelectedIndex(4);
		        add(carList, BorderLayout.PAGE_START);
		        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		    }

		   public ImageIcon getSeleccionado(){
			   return images[renderer.getSelectedIndex()];
		   }
		   
		   protected ImageIcon createImageIcon(String path) {
		        java.net.URL imgURL =  ComboBoxCars.class.getResource(path);
		        if (imgURL != null) {
		            return new ImageIcon(imgURL);
		        } else {
		            System.err.println("No se pudo encontrar la imagen: " + path);
		                return null;
		        }
		    }
		    
		    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		
				private static final long serialVersionUID = 1L;
				private int selectedIndex;
				
		        public ComboBoxRenderer() {
		            setOpaque(true);  
		        }

		        public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
		       
		            selectedIndex = ((Integer)value).intValue();

		            if (isSelected) {
		                setBackground(list.getSelectionBackground());
		              setForeground(list.getSelectionForeground());    
		            } else {
		                setBackground(list.getBackground());
		                setForeground(list.getForeground());
		            }
		  
		            ImageIcon icon = images[selectedIndex];
		            String nombre = carStrings[selectedIndex];
		            setIcon(icon);
		            if (icon != null) {
		                setText(nombre);
		       
		            }
		            return this;
		        }
		        
		        public int getSelectedIndex(){
		        	return selectedIndex;
		        } 
		}

	}
	
	private class CuadroIngresoUsuario extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private JTextField box;
		
		public CuadroIngresoUsuario(ControladorJuego controladorJuego){
			setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

			JLabel label = new JLabel("Nombre Usuario: ");
			label.setFont(new Font("Verdana",Font.BOLD,16));
				
			this.add(label);
			box = new JTextField("",30);
			box.setFont(new Font("Verdana",Font.BOLD,11));
			box.setOpaque(false);
			
			this.add(box);
		}

		public JTextField getBox() {
			return box;
		}
	}


}

