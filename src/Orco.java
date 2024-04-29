import java.util.Random;
public class Orco  extends Personaje{
    public Orco(String nombre_p,String apodo_p, String fecha_de_nacimiento_p, int velocidad_p, int destreza_p, int fuerza_p, int nivel_p, int armadura_p) {
        super(Razas.Orco,nombre_p,apodo_p,fecha_de_nacimiento_p,velocidad_p,destreza_p,fuerza_p,nivel_p,armadura_p);
    }
    @Override
    public int Atacar(int defensa) {
        Random random = new Random();
        double poder = this.destreza * this.fuerza * this.nivel;
        int efectividad = random.nextInt(101);
        double valorDeAtaque = poder * efectividad;
        return (int) Math.ceil((((((valorDeAtaque * efectividad) - defensa) /500)*100)*1.1)) ;
    }

    @Override
    public void Daniarse(int danio) {
        if (danio > this.salud) {
            setSalud(danio);
        } else {
            this.Morir();
        }
    }

}
