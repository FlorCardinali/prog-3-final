
public abstract class Personaje implements AccionesPersonaje{

    public Personaje(Razas raza, String nombre, String apodo, String fecha_de_nacimiento, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        setFecha_de_nacimiento(fecha_de_nacimiento);
        setSalud(100);
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        setNivel(nivel);
        this.armadura = armadura;
    }


    protected Razas raza;
    protected String nombre = "";
    private String apodo = "";
    private String fecha_de_nacimiento = "";

    protected int edad = 0;
    protected int salud = 0;
    protected int velocidad = 0;
    protected int destreza = 0;
    protected int fuerza = 0;
    protected int nivel = 0;
    protected int armadura = 0;

    protected void Morir() {
        System.out.println("El " + raza);
        setSalud(0);
        setNivel(0);

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
    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        //validar despues.
    }
    public void setSalud(int salud) {
        if (salud > -1 && salud < 101 ) {
            this.salud = salud;
        } else {
            System.out.println("La salud asignada no se puede asignar");
        }
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
    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
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
}
