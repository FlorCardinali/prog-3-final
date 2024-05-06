import java.util.Random;

public class Elfo extends Personaje{
    public Elfo(String genero,String nombre, String apodo, String fecha_de_nacimiento, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        super(genero,nombre, apodo, fecha_de_nacimiento, velocidad, destreza, fuerza, nivel, armadura);
        this.raza = Razas.Elfo;
    }

    @Override
    public int Atacar(int defensa) {
        Random random = new Random();
        double poder = this.destreza * this.fuerza * this.nivel;

        //para efectividad
        double min = 0.0;
        double max = 1.1;
        int precision = 100;
        double efectividad = min + random.nextDouble() * (max - min);
        efectividad = Math.round(efectividad * precision) / (double) precision;

        double valorDeAtaque = poder * efectividad;
        int danio = (int) Math.max(Math.ceil((((((valorDeAtaque * efectividad) - defensa) /500)*100)*1.05)),0) ;
        System.out.println(this.nombre + " " + this.apodo +
                " ataco con una efectividad del " + efectividad*100 +
                "%, provocando "+ danio + " puntos de daño." );
        return danio;
    }

    @Override
    public void Daniarse(int danio) {
        if (danio < this.salud) {
            setSalud(this.salud -danio);
            System.out.println("La salud de " + this.nombre + " es de "+ this.salud);
        } else {
            setSalud(0);
            this.Morir();
        }
    }
}
