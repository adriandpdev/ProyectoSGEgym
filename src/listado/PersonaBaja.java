package listado;

public class PersonaBaja {

	String dni;
	String nombre;
	String apellido;
	String cuentabanc;
	String fechanac;
	int telefono;
	String correo;
	String rol;
	String fechabaja;
	
	public PersonaBaja() {
		// TODO Auto-generated constructor stub
	}
	
	public PersonaBaja(String dni, String nombre, String apellido, String cuentabanc, String fechanac, int telefono, String correo, String rol, String fechabaja) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuentabanc = cuentabanc;
		this.fechanac = fechanac;
		this.telefono = telefono;
		this.correo = correo;
		this.rol = rol;
		this.fechabaja = fechabaja;
		
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getCuentabanc() {
		return cuentabanc;
	}

	public void setCuentabanc(String cuentabanc) {
		this.cuentabanc = cuentabanc;
	}

	public String getFechanac() {
		return fechanac;
	}

	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getFechabaja() {
		return fechabaja;
	}

	public void setFechabaja(String fechabaja) {
		this.fechabaja = fechabaja;
	}
	
	
}