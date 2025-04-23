package ventanal_personal;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JButton btnAnadir, btnBorrar;

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

	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame("Lista de Personas");
		frame.setBounds(100, 100, 469, 300);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_Tabla = new JPanel();
		frame.getContentPane().add(panel_Tabla, BorderLayout.CENTER);
		
		modeloTabla = new DefaultTableModel(new Object[] {"dni", "nombre", "apellido", "telefono"}, 0) {

			public boolean isCellEditable(int row, int column) {
				
				return false;
				
			}
		};
		
		table = new JTable(modeloTabla);
		panel_Tabla.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel_Tabla.add(scrollPane);
		
		JPanel panel_Botones = new JPanel();
		frame.getContentPane().add(panel_Botones, BorderLayout.SOUTH);
		panel_Botones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAnadir = new JButton("Añadir");
		panel_Botones.add(btnAnadir);
		
		btnBorrar = new JButton("Borrar");
		panel_Botones.add(btnBorrar);
		
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
				
				if (filaSeleccionada == -1) {
					
					JOptionPane.showMessageDialog(frame, "Debes seleccionar una fila para poder borrar!");
					
				}
				
				modeloTabla.removeRow(filaSeleccionada); // Devuelve -1 si no hay fila seleccionada
				
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
