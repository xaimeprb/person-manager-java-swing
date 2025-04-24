package ventanal_personal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaModificar {

	private JFrame frame;
	private DefaultTableModel modelo;
	private int filaModificar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificar window = new VentanaModificar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaModificar() {
		initialize();
	}

	public VentanaModificar(DefaultTableModel modeloTabla, int filaModificar) {
		
		this.modelo = modeloTabla;
		this.filaModificar = filaModificar;
		initialize();
		
	}
	
	private void initialize() {
		frame = new JFrame("Modificar Persona");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 5, 15));
		
		JLabel jlDni = new JLabel("DNI:");
		panel.add(jlDni);
		
		JTextField jtDni = new JTextField((String) modelo.getValueAt(filaModificar, 0)); // Si ponemos (String) modelo.getValueAt(filaModificar, 0) dentro de JTextArea nos aparecerá la info de la tabla
		panel.add(jtDni);
		
		JLabel jlNombre = new JLabel("Nombre:");
		panel.add(jlNombre);
		
		JTextField jtNombre = new JTextField();
		panel.add(jtNombre);
		
		JLabel jlApellido = new JLabel("Apellido:");
		panel.add(jlApellido);
		
		JTextField jtApellido = new JTextField();
		panel.add(jtApellido);
		
		JLabel jlTelefono = new JLabel("Telefono:");
		panel.add(jlTelefono);
		
		JTextField jtTelefono = new JTextField();
		panel.add(jtTelefono);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnModificar = new JButton("Modificar");
		
		panel_1.add(btnModificar);
		
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String dni = jtDni.getText().trim();
				String nombre = jtNombre.getText().trim();
				String apellido = jtApellido.getText().trim();
				String telefono = jtTelefono.getText().trim();
				
				modelo.setValueAt(dni, filaModificar, 0);
				modelo.setValueAt(nombre, filaModificar, 1);
				modelo.setValueAt(apellido, filaModificar, 2);
				modelo.setValueAt(telefono, filaModificar, 3);
				
				frame.dispose();
				
			}
		});
		
	}

}