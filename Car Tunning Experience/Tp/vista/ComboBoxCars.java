package vista;

import java.awt.*;
import javax.swing.*;


public class ComboBoxCars extends JPanel {
	    
		private static final long serialVersionUID = 1L;
		ImageIcon[] images;
		String[] carStrings = { "Guido", "Flo", "Mate", "Sheriff", "Rayo McQueen" };

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
	        ComboBoxRenderer renderer= new ComboBoxRenderer();
	        renderer.setPreferredSize(new Dimension(250, 100));
	        carList.setRenderer(renderer);
	        carList.setMaximumRowCount(3);

	        carList.setSelectedIndex(4);
	        add(carList, BorderLayout.PAGE_START);
	        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    }

	  
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL =  ComboBoxCars.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	                return null;
	        }
	    }

	    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	
			private static final long serialVersionUID = 1L;

	        public ComboBoxRenderer() {
	            setOpaque(true);  
	        }

	        public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
	       
	            int selectedIndex = ((Integer)value).intValue();

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

	}

}
