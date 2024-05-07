import java.util.Random;
public class Orco  extends Personaje{
    public Orco(String genero_p,String nombre_p,String apodo_p, String fecha_de_nacimiento_p, int velocidad_p, int destreza_p, int fuerza_p, int nivel_p, int armadura_p) {
        super(genero_p,nombre_p,apodo_p,fecha_de_nacimiento_p,velocidad_p,destreza_p,fuerza_p,nivel_p,armadura_p);
        this.raza = Razas.Orco;
    }
    @Override
    public int Atacar(int defensa) {
        Random random = new Random();
        double PO = this.destreza * this.fuerza * this.nivel;
        double ED = (double) random.nextInt(101) /100;
        double VA = PO * ED;
        int danio = (int) Math.max(Math.ceil((((((VA * ED) - defensa) /500)*100)*1.1)),0) ;
        System.out.println(this.nombre + " " + this.apodo +
                " ataco con una efectividad del " + ED*100 +
                "%");
        if (danio==0){
            System.out.println("Pero el oponente se defendio del daño.");
        } else {
            System.out.println("y provoco " + danio + " puntos de daño.");
        }
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
