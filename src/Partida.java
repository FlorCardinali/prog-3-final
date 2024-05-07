import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Partida {
    Random rand = new Random();
    private int id =0;
    private String log = "";

    private List <String> nombreHumana;
    private List <String> nombreHumano;
    private List <String> nombreOrca;
    private List <String> nombreOrco;
    private List <String> nombreElfa;
    private List <String> nombreElfo;

    private List <String> apodosMujer;
    private List <String> apodosHombre;


    private final ArrayList<Personaje> PersonajesJ1 = new ArrayList<Personaje>();
    private final ArrayList<Personaje> PersonajesJ2 = new ArrayList<Personaje>();
    public Partida () {
        //validar que no se repita
        Random ran = new Random();
        this.id = ran.nextInt(501);
        crearLog();
        cargarNombresYApodos();
        //gaurdado de la id de la partida
        escribirLog("\n \n--------------------------",this.log);
        escribirLog("PARTIDA " + id,this.log);
        escribirLog("--------------------------",this.log);

        System.out.println("PARTIDA " + id + " INICIADA: " + getFecha() + "\n");
        System.out.println("-----------\n");

    }//partida



    public boolean iniciar() {
        Scanner scan = new Scanner(System.in);
        int eleccion=0;
        while (eleccion==0){
            System.out.println("ingreso de personajes manual o automatico? \n1. Automatioco. \n2. Manual.");
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

        //NUMERO DEL ROUND
        int round = 0;

        //PARA VER QUIEN GANA
        int ganador = 0;

        //VER QUIEN INICIA
        int inicio = rand.nextInt(2);


        //guardado de quien inicio
        escribirLog("\n \n--------------------------",this.log);
        escribirLog("INICIO EL JUGADOR " + inicio,this.log);
        escribirLog("--------------------------",this.log);

        while (ganador == 0) {
            int estado = iniciarRound(round,inicio);
            round++;
            switch (estado) {
                case 10 -> {
                    escribirLog("\n \n--------------------------",this.log);
                    escribirLog("GANADOR, EL JUGADOR 1. ",this.log);
                    escribirLog("--------------------------\n",this.log);
                    System.out.println("El jugador 1 es el vencedor!--------");
                    ganador = 1;
                    return true;
                }
                case 20 -> {
                    escribirLog("\n \n--------------------------",this.log);
                    escribirLog("GANADOR, EL JUGADOR 2. ",this.log);
                    escribirLog("--------------------------\n",this.log);
                    System.out.println("El jugador 2 es el vencedor!--------");
                    ganador = 2;
                    return true;
                }
                case 1 -> {
                    escribirLog("\n \n--------------------------",this.log);
                    escribirLog("RONDA PARA EL JUGADOR 1.",this.log);
                    escribirLog("--------------------------",this.log);
                    System.out.println("El jugador 1 ha ganado el round. \n");
                    estado = iniciarRound(round, 1);
                    ganador =0;
                }
                case 2 -> {
                    escribirLog("\n \n--------------------------",this.log);
                    escribirLog("RONDA PARA EL JUGADOR 2.",this.log);
                    escribirLog("--------------------------",this.log);
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
        //ALGUIEN GANO?
        if (PersonajesJ2.size() == 0) {
            return 10;
        }
        if (PersonajesJ1.size() == 0) {
            return 20;
        }
        escribirLog("\n \n--------------------------",this.log);
        escribirLog("ROUND "+ (round + 1),this.log);
        escribirLog("--------------------------\n",this.log);

        System.out.println("\n---------------------------------------------------");
        System.out.println("----------------------ROUND " + (round+1) + "----------------------");
        System.out.println("---------------------------------------------------\n");


        Personaje jugador1;
        Personaje jugador2;

        //ASIGANDO EL PERSONAJE A JUAGR PARA CADA UNO
        Random random = new Random();
        int indice1 = random.nextInt(PersonajesJ1.size());
        jugador1 = PersonajesJ1.get(indice1);
        int indice2 = rand.nextInt(PersonajesJ2.size());
        jugador2 = PersonajesJ2.get(indice2);

        // 0 inicia el j1, 1 inicia el j2
        if (inicio == 0) {
            System.out.println("------------------------");
            System.out.println("JUGADOR 1 COMIENZA: ");
            System.out.println("-----------------------\n");

            //PRESENTACION
            System.out.println("El jugador 1 usara su campeon numero " + (indice1 + 1));
            System.out.println("\nJugador 1 usara a: \n");
            System.out.println(jugador1.toString() + "\n");

            //PRESENTACION
            System.out.println("El jugador 2 usara su campeon nuemro " + (indice2 + 1));
            System.out.println("\nJugador 2 usara a: \n");
            System.out.println(jugador2.toString() + "\n");

        } else {
            System.out.println("------------------------");
            System.out.println("JUGADOR 2 COMIENZA: ");
            System.out.println("------------------------\n");

            //PRESENTACION
            System.out.println("El jugador 2 usara su campeon nuemro " + (indice2 + 1));
            System.out.println("\nJugador 2 usara a: \n");
            System.out.println(jugador2.toString());

            //PRESENTACION
            System.out.println("El jugador 1 usara su campeon nuemro " + (indice2 + 1));
            System.out.println("\nJugador 1 usara a: \n");
            System.out.println(jugador1.toString() + "\n");
        }
        escribirLog("\n \n--------------------------",this.log);
        escribirLog("JUGADOR 1: \n" + jugador1.toString(),this.log);
        escribirLog("JUGADOR 2: \n" + jugador2.toString(),this.log);
        escribirLog("--------------------------",this.log);
        System.out.println("\n \n------------------\n");
        System.out.println("COMIENZA UN ENFRENTAMIENTO:\n");
        System.out.println("------------------\n");

        //QUIEN ATACA
        int turnoDeAtaque = 0;
        //14 ATAQUES = 7 ROUNDS
        for (int b=0; b<14; b++){
            System.out.println("\n---------------");
            System.out.println("TURNO " + (b+1));
            System.out.println("----------------\n");
            int danio = 0;
            if (inicio == 0 || turnoDeAtaque==0) {
                //dañar jugador 2
                danio = jugador1.Atacar(jugador2.getPoderDeDefensa());
                jugador2.Daniarse(danio);
                turnoDeAtaque =1;
                if (jugador2.getSalud() < 1) {
                    //para que inicie el otro si este perdio
                    inicio = 1;
                    //para indicar que gano el j1 pero solo la ronda
                    estado = 1;
                    //cortar el bucle, tratando de romper las cosas lo menos posible.
                    b=15;
                    escribirLog("MURIO " + jugador1.getNombre(),this.log);
                    escribirLog("--------------------------",this.log);

                };
                //ya no importa quien fue el priemro en empezar.
                inicio=4545545;
            } else {
                //dañar jugador 1
                danio = jugador2.Atacar(jugador1.getPoderDeDefensa());
                jugador1.Daniarse(danio);
                turnoDeAtaque = 0;
                if (jugador1.getSalud() < 1) {
                    inicio = 0;
                    estado = 2;
                    b=15;
                    escribirLog("MURIO " + jugador1.getNombre(),this.log);
                    escribirLog("--------------------------",this.log);
                };
                inicio = 654654;
            }//else
        }//for

        //QUITANDO A LOS MUERTOS
        if (estado == 1){
            PersonajesJ2.remove(indice2);
        }
        if (estado ==2){
            PersonajesJ1.remove(indice1);
        }

        System.out.println("FIN DEL ROUND "+ round +": \nJugador 1 tiene: " +  PersonajesJ1.size() + " personajes.\nJugador 2 tiene " + PersonajesJ2.size() + " personajes.");

        escribirLog("FIN DEL ROUND "+ round + ": \nJugador 1 tiene: " +  PersonajesJ1.size() + " personajes.\nJugador 2 tiene " + PersonajesJ2.size() + " personajes.",this.log);
        escribirLog("--------------------------",this.log);
        return estado;
    }//round

    private void crearAutomatico() {
        int turno = 0; //semaforo xd
        for (int i = 1; i < 7; i++) {
            if (turno == 0) {
                this.PersonajesJ1.add(crearPersonaje());
                turno = 1;
            } else {
                this.PersonajesJ2.add(crearPersonaje());
                turno = 0;
            }
        }//for


        escribirLog("\n \n--------------------------",this.log);
        escribirLog("CREACION AUTOMATICA: \n"+ "Jugador 1: \n" + PersonajesJ1 +
        "Jugador 2: \n" + PersonajesJ2 ,this.log);
        escribirLog("--------------------------",this.log);

    }//crearAutomatico

    private Personaje crearPersonaje() {
        Random ran = new Random();
        Personaje per = null;

        //raza random
        int raza = ran.nextInt(3);

        //genero random
        int genRan = ran.nextInt(2);
        String genero = "";
        if (genRan==0){
            genero = "F";
        } else {
            genero = "M";
        }

        //nombre y apodo random
        String nombre = "";
        String apodo = "";
        if (genRan==0) {
            if (raza == 0) {
               nombre = this.nombreHumana.get(ran.nextInt(nombreHumana.size() - 1));
            } else if (raza == 1) {
                nombre = this.nombreOrca.get(ran.nextInt(nombreOrca.size()-1));
            } else {
                nombre = this.nombreElfa.get(ran.nextInt(nombreElfa.size()-1));
            }
            apodo = this.apodosMujer.get(ran.nextInt(apodosMujer.size()-1));
        }else {
            if (raza == 0) {
                nombre = this.nombreHumano.get(ran.nextInt(nombreHumano.size() - 1));
            } else if (raza == 1) {
                nombre = this.nombreOrco.get(ran.nextInt(nombreOrco.size()-1));
            } else {
                nombre = this.nombreElfo.get(ran.nextInt(nombreElfo.size()-1));
            }
            apodo = this.apodosHombre.get(ran.nextInt(apodosHombre.size()-1));
        }

        //fecha de nacimiento random
        String fechaNacimiento = (ran.nextInt(30)+1) + "/" + (ran.nextInt(12)+1) +
                "/" + (ran.nextInt(99)+1);


        int velocidad = ran.nextInt(11)+5;
        int destresa = ran.nextInt(11)+5;
        int fuerza = ran.nextInt(16)+10;
        int nivel = ran.nextInt(3)+1;
        int armadura = ran.nextInt(11)+5;

        switch (raza) {
                case 0 -> {
                    per = new Humano(genero, nombre, apodo, fechaNacimiento,velocidad ,destresa , fuerza, nivel, armadura);
                }
                case 1 -> {
                    per = new Orco(genero, nombre, apodo, fechaNacimiento,  velocidad, destresa, fuerza, nivel, armadura);
                }
                case 2 -> {
                    per = new Elfo(genero, nombre, apodo, fechaNacimiento,  velocidad,destresa, fuerza, nivel, armadura);
                }
                default -> {
                    System.out.println("Error al crear el personaje.");
                }
            }//switch
        return per;
    }//crear eprsonaje



    private void cargarNombresYApodos() {
        Random ran = new Random();
        try {
            FileReader reader = new FileReader("nombres.json");
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            this.nombreHumana = new Gson().fromJson(jsonObject.get("HumanoMujer"), List.class);
            this.nombreHumano = new Gson().fromJson(jsonObject.get("HumanoHombre"), List.class);
            this.nombreOrca = new Gson().fromJson(jsonObject.get("OrcoMujer"), List.class);
            this.nombreOrco = new Gson().fromJson(jsonObject.get("OrcoHombre"), List.class);
            this.nombreElfa = new Gson().fromJson(jsonObject.get("ElfoMujer"), List.class);
            this.nombreElfo = new Gson().fromJson(jsonObject.get("ElfoHombre"), List.class);

            this.apodosMujer = new Gson().fromJson(jsonObject.get("ApodosMujer"), List.class);
            this.apodosHombre = new Gson().fromJson(jsonObject.get("ApodosHombre"), List.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void creacionManual(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------\n");
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
            }while (raza==0 || raza>3);


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


        System.out.println("---------------\n");
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
            }while (raza==0 || raza>3);


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
        escribirLog("\n \n--------------------------",this.log);
        escribirLog("CREACION MANUAL: \n" + "Jugador 1: \n" + PersonajesJ1 +"\nJugador 2: \n" + PersonajesJ2,this.log);
        escribirLog("--------------------------",this.log);
    }

    private String getFecha () {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaActual.format(formato);
    }

    private void crearLog() {
        Random ran = new Random();
        String path ="logs\\log"+ran.nextInt(800)+".txt";
        File log = new File(path);
        while (log.exists()){
            path ="logs\\log"+ran.nextInt(800)+".txt";
            log = new File(path);
        }
        this.log = path;
        try {
            PrintWriter escribirLog = new PrintWriter(new FileWriter(log,true));
            //actualizar el directorio
            File carpeta = new File("logs");
            File[] archivos = carpeta.listFiles();
            escribirLog.close();
        } catch (IOException e) {
            System.out.println("No se creo nada.");
        }

    }

    private void escribirLog(String contenido, String ruta){
        File guardar = new File(ruta);
        try {
            PrintWriter escribir = new PrintWriter(new FileWriter(guardar,true));
            escribir.println(contenido);
            escribir.close();
        } catch (IOException e) {
            System.out.println("no se pudo escribir: " + contenido);
        }
    }
}
