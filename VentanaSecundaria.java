package ventanal_personal;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


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
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 2, 5, 15));
		
		JLabel jlDni = new JLabel("DNI:");
		panel.add(jlDni);
		
		JTextField jtDni = new JTextField();
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
		
		JButton btnAniadir = new JButton("Añadir");
		
		panel_1.add(btnAniadir);
		
		btnAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = jtDni.getText().trim();
				String nombre = jtNombre.getText().trim();
				String apellido = jtApellido.getText().trim();
				String telefono = jtTelefono.getText().trim();
				
				Border bordeRojo = BorderFactory.createLineBorder(Color.RED);
				Border bordeNormal = BorderFactory.createLineBorder(Color.GRAY);
				
				jtDni.setBorder(bordeNormal);
				jtNombre.setBorder(bordeNormal);
				jtApellido.setBorder(bordeNormal);
				jtTelefono.setBorder(bordeNormal);
				
				Persona persona = null;
				
				try {
					
					persona = new Persona(dni, nombre, apellido, telefono);
					
				} catch (DNIInvalidoException ex) {
					
					jtDni.setBorder(bordeRojo);
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error en el DNI", JOptionPane.ERROR_MESSAGE);
					
					return;
					
				} catch (TlfException ex) {
					
					jtTelefono.setBorder(bordeRojo);
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error en el Teléfono", JOptionPane.ERROR_MESSAGE);
					
					return;
					
				}
				
				modelo.addRow(new Object[] {dni, nombre, apellido, telefono});
				guardarPersonaJSON(persona);
				
				frame.dispose();
				
			}

			private void guardarPersonaJSON(Persona persona) {
				
				String jsonFile = "personas.json";
				
				String objeto =  "{\n" +
	                     "\t\"dni\": \""      + persona.getDni() + "\",\n" +
	                     "\t\"nombre\": \""   + persona.getNombre() + "\",\n" +
	                     "\t\"apellido\": \"" + persona.getApellido() + "\",\n" +
	                     "\t\"telefono\": \"" + persona.getTelefono() + "\"\n" +
	                     "}\n";

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFile, true))) {
											
					bw.write(objeto);
					
				} catch (IOException e) {

					e.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Error guardando JSON: ", e.getMessage(), JOptionPane.ERROR_MESSAGE);
				
				}
				
			}

		});
		
	}

}