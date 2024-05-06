
public abstract class Personaje implements AccionesPersonaje{

    public Personaje(String genero, String nombre, String apodo, String fechaDeNacimiento, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        this.genero = genero;
        this.nombre = nombre;
        this.apodo = apodo;
        setFechaDeNacimiento(fechaDeNacimiento);
        setSalud(100);
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        setNivel(nivel);
        this.armadura = armadura;
        this.salud = 100;
    }


    protected Razas raza;
    protected String genero;
    protected String nombre = "";
    protected String apodo = "";
    protected String fechaDeNacimiento = "";

    protected int edad = 0;
    protected int salud = 100;
    protected int velocidad = 0;
    protected int destreza = 0;
    protected int fuerza = 0;
    protected int nivel = 0;
    protected int armadura = 0;

    protected void Morir() {
        System.out.println(this.nombre + " ha muerto.");
    }


    //setters
    public void setRaza(Razas raza) {
        this.raza = raza;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
        //validar despues.
    }
    public void setSalud(int salud_p) {
        if (salud_p > -1 && salud_p < 101 ) {
            this.salud = salud_p;
        } else {
            System.out.println("La salud asignada no es valida: " + salud_p);
        }
    }
    public void setGenero(String genero) {
        //despues hacer un enum,
        this.genero = genero;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }
    public void setNivel(int nivel) {
        if (nivel>0) {
            this.nivel = nivel;
        } else {
            System.out.println("El nivel esta mal ingresado, se autocolocara en 1");
            this.nivel = 1;
        }

    }
    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    //getters
    public int getPoderDeDefensa() {
        return  this.armadura * this.velocidad;
    }
    public Razas getRaza() {
        return raza;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApodo() {
        return apodo;
    }
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public int getSalud() {
        return salud;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public int getDestreza() {
        return destreza;
    }
    public int getFuerza() {
        return fuerza;
    }
    public int getNivel() {
        return nivel;
    }
    public int getArmadura() {
        return armadura;
    }
    public int getEdad() {
        return edad;
    }
    public String getGenero() {
        return genero;
    }
    @Override
    public String toString() {
        return "Raza: " + this.raza +"\n" +
               "Nombre: " + this.nombre + " " + this.apodo + "\n" +
               "Cumpleaños: " + this.fechaDeNacimiento + "\n"  +
               "Estadisticas: " + this.armadura +"Arm. / "
                + this.destreza + " Dest. / " + this.fuerza + "Fue. / " +
                this.velocidad + " Vel. / " + this.nivel + "Nv. \n";

    }
}
