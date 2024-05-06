import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Partida {
    Random rand = new Random();
    private int id =0;

    private ArrayList<Personaje> PersonajesJ1 = new ArrayList<Personaje>();
    private ArrayList<Personaje> PersonajesJ2 = new ArrayList<Personaje>();
    public Partida () {
        //validar que no se repita
        Random ran = new Random();
        this.id = ran.nextInt(501);
        System.out.println("Partida " + id + " iniciada: " + getFecha() + "\n");
        System.out.println("-----------\n");
    }//partida



    public boolean iniciar() {
        Scanner scan = new Scanner(System.in);

        int eleccion=0;
        while (eleccion==0){
            System.out.println("ingreso de personajes manual o automatico?\n 1. Automatioco. \n 2.Manual.");
            eleccion = scan.nextInt();
            scan.nextLine();
            if (eleccion==1){
               crearAutomatico();
            } else if (eleccion==2){
                creacionManual();
            } else {
                System.out.println("respeusta invalida.");
                eleccion =0;
            }
        }

        int round = 0;
        int ganador = 0;
        int inicio = rand.nextInt(2);
        while (ganador == 0) {
            int estado = iniciarRound(round,inicio);
            round++;
            switch (estado) {
                case 10 -> {
                    System.out.println("El jugador 1 es el vencedor!--------");
                    ganador = 1;
                    return true;
                }
                case 20 -> {
                    System.out.println("El jugador 2 es el vencedor!--------");
                    ganador = 2;
                    return true;
                }
                case 1 -> {
                    System.out.println("El jugador 1 ha ganado el round. \n");
                    estado = iniciarRound(round, 1);
                    ganador =0;
                }
                case 2 -> {
                    System.out.println("El jugador 2 ha ganado el round. \n");
                    estado = iniciarRound(round, 0);
                    ganador =0;
                }
                case 0 -> {
                    System.out.println("empate");
                    estado = iniciarRound(round,rand.nextInt(2));
                }
                default -> {
                    System.out.println("Error al recibir estado de ronda.");
                    System.exit(0);
                    ganador =3;
                }
            }//case
        } //while
        return false;
    }//iniciar

    private int iniciarRound(int round, int inicio) {

        int estado = 0;
        System.out.println(PersonajesJ1.size() + " " + PersonajesJ2.size());
        if (PersonajesJ2.size() == 0) {
            return 10;
        }
        if (PersonajesJ1.size() == 0) {
            return 20;
        }

        System.out.println("-----Round " + round + "------");

        Personaje jugador1;
        Personaje jugador2;
        Random random = new Random();

        int indice1 = random.nextInt(PersonajesJ1.size());
        //test
        System.out.println("El jugador 1 usara su campeon numero " + indice1 + 1);

        jugador1 = PersonajesJ1.get(indice1);
        int indice2 = rand.nextInt(PersonajesJ2.size());

        //test
        System.out.println("El jugador 2 usara su campeon nuemro " + indice2 + 1);

        jugador2 = PersonajesJ2.get(indice2);

        // 0 inicia el j1, 1 inicia el j2
        if (inicio == 0) {
            System.out.println("Jugador 1 comienza: \n");
            System.out.println("-----------------------\n");
            System.out.println("Jugador 1 usara a: \n");
            System.out.println(jugador1.toString() + "\n");
            System.out.println("Jugador 2 usara a: \n");
            System.out.println(jugador2.toString() + "\n");

        } else {
            System.out.println("El jugador 2 comieza: \n");
            System.out.println("------------------------\n");
            System.out.println("Jugador 2 usara a: \n");
            System.out.println(jugador2.toString());
            System.out.println("Jugador 1 usara a: \n");
            System.out.println(jugador1.toString() + "\n");
        }

        System.out.println("COMIENZA UN ENFRENTAMIENTO: \n "+ PersonajesJ1.size() + " " + PersonajesJ2.size());
        int turnoDeAtaque = 0;
        for (int b=0; b<14; b++){
            System.out.println("CICLO FOR " + b);
            int danio = 0;
            if (inicio == 0 || turnoDeAtaque==0) {
                //dañar jugador 2
                danio = jugador1.Atacar(jugador2.getPoderDeDefensa());
                jugador2.Daniarse(danio);
                turnoDeAtaque =1;
                if (jugador2.getSalud() < 1) {
                    System.out.println("tiene que morir " + jugador2.nombre);
                    inicio = 1;
                    estado = 1;
                    b=15;
                };
                inicio=4545545;
            } else {
                //dañar jugador 1
                danio = jugador2.Atacar(jugador1.getPoderDeDefensa());
                jugador1.Daniarse(danio);
                turnoDeAtaque = 0;
                if (jugador1.getSalud() < 1) {
                    System.out.println("tiene que morir " + jugador1.nombre);
                    inicio = 0; //para que inicie el otro si este perdio
                    estado = 2; // para ver que paso
                    b=15;
                };
                inicio = 654654;
            }//else
        }//for


        if (estado == 1){
            PersonajesJ2.remove(indice2);
        }
        if (estado ==2){
            PersonajesJ1.remove(indice1);
        }
        System.out.println("FIN INICIAR ROUND" +  PersonajesJ1.size() + " y " + PersonajesJ2.size());
        return estado;
    }//round

    private void crearAutomatico() {
        int turno = 0;
        for (int i = 1; i < 7; i++) {
            if (turno == 0) {
                this.PersonajesJ1.add(crearPersonaje());
                turno = 1;
            } else {
                this.PersonajesJ2.add(crearPersonaje());
                turno = 0;
            }
        }//for
    }
    private Personaje crearPersonaje() {
        Random ran = new Random();
        Personaje per = null;
        int raza = ran.nextInt(3);
            switch (raza) {
                case 0 -> {
                    per = new Humano("M", "Pedro", "el feo", "16/3/1215", 15, 15, 15, 2, 15);
                }
                case 1 -> {
                    per = new Orco("M", "Rufus", "el choto", "16/2/1215", 14, 14, 14, 3, 14);
                }
                case 2 -> {
                    per = new Elfo("F", "Lorena", "la chota", "16/2/1216", 16, 11, 18, 5, 10);
                }
                default -> {
                    System.out.println("Error al crear el personaje.");
                }
            }
        return per;
    }//crear eprsonaje

    private void creacionManual(){
        Scanner sc = new Scanner(System.in);
        System.out.println("INGRESANDO PERSONAJES DEL JUGADOR 1: \n");
        for (int i=0;i<3;i++){
            //RAZA
            int raza = 0;
            sc.nextLine();
            do {
                System.out.println("Ingrese la raza de su personaje: \n 1.Elfo. \n2.Humano \n3.Orco.");
                raza = sc.nextInt();
                sc.nextLine();
                System.out.println(raza);
                if (raza==0){
                    System.out.println("Numero incorrecto");
                    raza=5555;
                }
            }while (raza==0 || raza>4);


            //NOMBRE
            String nombre = "";
            System.out.println("Ingrese el nombre de su personaje: ");
            nombre = sc.nextLine();
            System.out.println(nombre);

            //GENERO
            String genero = "";
            do {
                System.out.println("Ingrese el genero de su personaje: (F,M)");
                genero = sc.nextLine();
                System.out.println(genero);
            } while (!Objects.equals(genero, "F") && !Objects.equals(genero, "M"));



            //APODO
            String apodo = "";
            System.out.println("Ingrese el apodo del personaje: ");
            apodo = sc.nextLine();
            System.out.println(apodo);

            //FECHA DE NACIMIENTO
            String fechaNac = "";
            System.out.println("Ingrese la fecha de nacimiento: ");
            fechaNac = sc.nextLine();
            System.out.println(fechaNac);

            //VELOCIDAD
            int velocidad = 0;
            System.out.println("Ingrese la velocidad del personaje: ");
            velocidad = sc.nextInt();
            sc.nextLine();
            System.out.println(velocidad);

            //DEFENSA
            int defensa = 0;
            System.out.println("Ingrese la defensa del personaje:");
            defensa = sc.nextInt();
            sc.nextLine();
            System.out.println(defensa);

            //DESTREZA
            int destreza = 0;
            System.out.println("Ingrese la destreza del personaje:");
            destreza = sc.nextInt();
            sc.nextLine();
            System.out.println(destreza);

            //FUERZA
            int fuerza = 0;
            System.out.println("Ingrese la fuerza del personaje:");
            fuerza = sc.nextInt();
            sc.nextLine();
            System.out.println(fuerza);

            //NIVEL
            int nivel = 0;
            System.out.println("Ingrese el nivel del personaje:");
            nivel = sc.nextInt();
            sc.nextLine();
            System.out.println(nivel);

            //ARMADURA
            int armadura = 0;
            System.out.println("Ingrese la armadura del personaje:");
            armadura = sc.nextInt();
            sc.nextLine();
            System.out.println(armadura);

            if (raza==1){
                PersonajesJ1.add(new Elfo(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            }else if (raza==2){
                PersonajesJ1.add(new Humano(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            } else {
                PersonajesJ1.add(new Orco(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            }

        }//for


        System.out.println("INGRESANDO PERSONAJES DEL JUGADOR 2: \n");
        for (int i=0;i<3;i++){
            //RAZA
            int raza = 0;
            do {
                System.out.println("Ingrese la raza de su personaje: \n 1.Elfo. \n2.Humano \n3.Orco.");
                raza = sc.nextInt();
                sc.nextLine();
                System.out.println(raza);
                if (raza==0){
                    System.out.println("Numero incorrecto");
                    raza=5555;
                }
            }while (raza==0 || raza>4);


            //NOMBRE
            String nombre = "";
            System.out.println("Ingrese el nombre de su personaje: ");
            nombre = sc.nextLine();
            System.out.println(nombre);

            //GENERO
            String genero = "";
            do {
                System.out.println("Ingrese el genero de su personaje: (F,M)");
                genero = sc.nextLine();
                System.out.println(genero);
            } while (!Objects.equals(genero, "F") && !Objects.equals(genero, "M"));


            //APODO
            String apodo = "";
            System.out.println("Ingrese el apodo del personaje: ");
            apodo = sc.nextLine();
            System.out.println(apodo);

            //FECHA DE NACIMIENTO
            String fechaNac = "";
            System.out.println("Ingrese la fecha de nacimiento: ");
            fechaNac = sc.nextLine();
            System.out.println(fechaNac);

            //VELOCIDAD
            int velocidad = 0;
            System.out.println("Ingrese la velocidad del personaje: ");
            velocidad = sc.nextInt();
            sc.nextLine();
            System.out.println(velocidad);

            //DEFENSA
            int defensa = 0;
            System.out.println("Ingrese la defensa del personaje:");
            defensa = sc.nextInt();
            sc.nextLine();
            System.out.println(defensa);

            //DESTREZA
            int destreza = 0;
            System.out.println("Ingrese la destreza del personaje:");
            destreza = sc.nextInt();
            sc.nextLine();
            System.out.println(destreza);

            //FUERZA
            int fuerza = 0;
            System.out.println("Ingrese la fuerza del personaje:");
            fuerza = sc.nextInt();
            sc.nextLine();
            System.out.println(fuerza);

            //NIVEL
            int nivel = 0;
            System.out.println("Ingrese el nivel del personaje:");
            nivel = sc.nextInt();
            sc.nextLine();
            System.out.println(nivel);

            //ARMADURA
            int armadura = 0;
            System.out.println("Ingrese la armadura del personaje:");
            armadura = sc.nextInt();
            sc.nextLine();
            System.out.println(armadura);

            if (raza==1){
                PersonajesJ2.add(new Elfo(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            }else if (raza==2){
                PersonajesJ2.add(new Humano(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            } else {
                PersonajesJ2.add(new Orco(genero,nombre,apodo,fechaNac,velocidad,destreza,fuerza,nivel,armadura));
            }

        }//for

    }

    public String getFecha () {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaActual.format(formato);
    }



}
