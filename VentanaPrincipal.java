package ventanal_personal;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JButton btnAnadir, btnBorrar;
	
	private List<Persona> listaPersonas = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame("Lista de Personas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);

		JMenuItem itemCargar = new JMenuItem("Cargar JSON…");
		menuArchivo.add(itemCargar);
		
		itemCargar.addActionListener(e -> {
		    // TODO cargarDesdeJSON();
		});

		JMenuItem itemGuardar = new JMenuItem("Guardar JSON…");
		menuArchivo.add(itemGuardar);
		
		itemGuardar.addActionListener(e -> {
		    // TODO guardarTodasLasPersonasEnJSON();
		});

		menuArchivo.addSeparator(); // Añade un separador visual en el menú

		JMenuItem itemSalir = new JMenuItem("Salir");
		menuArchivo.add(itemSalir);
		
		itemSalir.addActionListener(e -> {
		    frame.dispose();
		});

		JMenu menuAyuda = new JMenu("Ayuda");
		menuBar.add(menuAyuda);

		JMenuItem itemAcerca = new JMenuItem("Acerca de");
		menuAyuda.add(itemAcerca);
		
		itemAcerca.addActionListener(e -> {
		    JOptionPane.showMessageDialog(frame, "Desarrollado por Xaime", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
		});

		
		modeloTabla = new DefaultTableModel(new Object[] {"dni", "nombre", "apellido", "telefono"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(modeloTabla);
		table.setFillsViewportHeight(true); // La tabla llena el área del scroll
		
		JScrollPane scrollPane = new JScrollPane(table);
		 frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_Botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnAnadir = new JButton("Añadir");
		btnBorrar = new JButton("Borrar");
		panel_Botones.add(btnAnadir);
		panel_Botones.add(btnBorrar);
		frame.getContentPane().add(panel_Botones, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		btnAnadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				new VentanaSecundaria(modeloTabla);
				
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = table.getSelectedRow();
				
				if (filaSeleccionada < 0) {
					
					JOptionPane.showMessageDialog(frame, "Debes seleccionar una fila para poder borrar!");
					
				} else {
					
					int opcion = JOptionPane.showConfirmDialog(frame, "¿Seguro que deseas borrar la fila seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
					
					if (opcion == JOptionPane.YES_OPTION) {
						
						modeloTabla.removeRow(filaSeleccionada); // Devuelve -1 si no hay fila seleccionada
						
					}
					
				}
				
			}

		});
		
		table.addMouseListener(new MouseAdapter() {
		
			  public void mouseClicked(MouseEvent e) {
				  
				if (e.getClickCount() == 2) {
					
					int fila = table.getSelectedRow();
					
					if (fila != -1) {
						
						new VentanaModificar(modeloTabla, fila);
						
					}
					
				}
				  
			  }
		});
		
	}

}