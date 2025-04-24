package ventanal_personal;

public class Persona {

	private String dni;
	private String nombre;
	private String apellido;
	private String telefono;
	
	public Persona (String dni) throws DNIInvalidoException {
		
		setDni(dni);
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		
	}
	
	public Persona (String dni, String nombre, String apellido, String telefono) throws DNIInvalidoException, TlfException {
		
		setDni(dni);
		this.nombre = nombre;
		this.apellido = apellido;
		setTelefono(telefono);
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws DNIInvalidoException {
		
		if (dni == null || dni.length() != 9) {
			
			throw new DNIInvalidoException("El DNI debe tener 9 caracteres.");
			
		}
		
		for (int i = 0; i < 8; i++) {
			
			if (!Character.isDigit(dni.charAt(i))) {
				
				throw new DNIInvalidoException("Los primeros 8 caracteres tienen que ser dígitos.");
				
			}
			
		}
		
		char letra = dni.charAt(8);
		
		if (!Character.isLetter(letra)) {
			
			throw new DNIInvalidoException("El último caracter tiene que ser una letra.");
			
		}
		
		String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
		int numero = Integer.parseInt(dni.substring(0, 8));
		char letraEsperada = letrasDNI.charAt(numero % 23);
		
		if (Character.toUpperCase(letra) != letraEsperada) {
			
			throw new DNIInvalidoException("Letra inválida, se esperaba: " + letraEsperada);
			
		}
		
		this.dni = dni.toUpperCase();
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws TlfException{

		if (telefono == null || telefono.length() != 9) {
			
			throw new TlfException("El teléfono no puede estar incompleto o tener mas o menos de 9 dígitos.");
			
		}
		
		char primerDigito = telefono.charAt(0); 
		
		if (primerDigito != '6' && primerDigito != '7' && primerDigito != '9') {
			
			throw new TlfException("El teléfono tiene que comenzar por 6, 7 o 9.");
			
		}
		
		for (int i = 0; i < telefono.length(); i++) {
			
			if (!Character.isDigit(telefono.charAt(i))) {
				
				throw new TlfException("Todos los caracteres deben ser números.");
				
			}
			
		}
		
		this.telefono = telefono;
		
	}
	
}