import java.util.Random;
public class Orco  extends Personaje{
    public Orco(String genero_p,String nombre_p,String apodo_p, String fecha_de_nacimiento_p, int velocidad_p, int destreza_p, int fuerza_p, int nivel_p, int armadura_p) {
        super(genero_p,nombre_p,apodo_p,fecha_de_nacimiento_p,velocidad_p,destreza_p,fuerza_p,nivel_p,armadura_p);
        this.raza = Razas.Orco;
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
        int danio = (int) Math.max(Math.ceil((((((valorDeAtaque * efectividad) - defensa) /500)*100)*1.1)),0) ;
        System.out.println(this.nombre + " " + this.apodo +
                " ataco con una efectividad del " + efectividad*100 +
                "%, provocando "+ danio + " puntos de daño." );
        return danio;
    }

    @Override
    public void Daniarse(int danio) {
        if (danio < this.salud) {
            setSalud(this.salud-danio);
            System.out.println("La salud de " + this.nombre + " es de "+ this.salud);
        } else {
            setSalud(0);
            this.Morir();
        }
    }

}
