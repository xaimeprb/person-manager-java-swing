package ventanal_personal;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaSecundaria {

	private JFrame frame;
	private DefaultTableModel modelo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSecundaria window = new VentanaSecundaria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaSecundaria () {

		initialize();
		
	}
	
	public VentanaSecundaria(DefaultTableModel modeloTabla) {
		
		this.modelo = modeloTabla;
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame("Añadir Persona");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null); // Lo ponemos después de que sepa el temaño, de lo contrario no sabrá donde centrarla
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 5, 15));
		
		JLabel jlDni = new JLabel("DNI:");
		panel.add(jlDni);
		
		JTextArea jtDni = new JTextArea();
		panel.add(jtDni);
		
		JLabel jlNombre = new JLabel("Nombre:");
		panel.add(jlNombre);
		
		JTextArea jtNombre = new JTextArea();
		panel.add(jtNombre);
		
		JLabel jlApellido = new JLabel("Apellido:");
		panel.add(jlApellido);
		
		JTextArea jtApellido = new JTextArea();
		panel.add(jtApellido);
		
		JLabel jlTelefono = new JLabel("Telefono:");
		panel.add(jlTelefono);
		
		JTextArea jtTelefono = new JTextArea();
		panel.add(jtTelefono);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAniadir = new JButton("Añadir");
		
		panel_1.add(btnAniadir);
		
		btnAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = jtDni.getText().trim();
				String nombre = jtNombre.getText().trim();
				String apellido = jtApellido.getText().trim();
				String telefono = jtTelefono.getText().trim();

				modelo.addRow(new Object[] {dni, nombre, apellido, telefono});
				
				frame.dispose();
			}

		});
		
	}

}
