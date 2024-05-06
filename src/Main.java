import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int control = 999999;
            System.out.println("Preparate para la avenmtura! \n" +
                    "1 - Iniciar partida. \n" +
                    "2 - Partidas anteriores. \n" +
                    "0 - Salir.\n");
            control = scan.nextInt();
            switch (control){
                case 1: {
                    //inicio del juego
                    Partida partida = new Partida();
                    boolean finalizada = false;
                    while  (!finalizada){
                        if (partida.iniciar()){
                            finalizada = true;
                        }
                    }
                    break;
                }
                case 2: break;
                case 0: System.exit(0);
                default: {
                    System.out.println("Error, ingrese un numero valido.");
                    break;
                }
            }
        }


    }
}