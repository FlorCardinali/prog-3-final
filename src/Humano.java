import java.util.Random;

public class Humano extends Personaje{


    public Humano(String genero,String nombre, String apodo, String fecha_de_nacimiento, int velocidad, int destreza, int fuerza, int nivel, int armadura) {
        super(genero,nombre, apodo, fecha_de_nacimiento, velocidad, destreza, fuerza, nivel, armadura);
        this.raza = Razas.Humano;
    }

    @Override
    public int Atacar(int defensa) {
        Random random = new Random();
        double PO = this.destreza * this.fuerza * this.nivel;
        double ED = (double) random.nextInt(101) /100;
        double VA = PO * ED;
        int danio = (int) Math.max(Math.ceil(((((VA * ED) - defensa) /500)*100)),0) ;
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
            setSalud(this.salud - danio);
            System.out.println("La salud de " + this.nombre + " es de "+ this.salud);
        } else {
            setSalud(0);
            this.Morir();
        }
    }
}

