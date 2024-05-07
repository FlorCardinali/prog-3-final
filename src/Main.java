import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void eliminarLog (String ruta){
    File guardar = new File(ruta);
    if (guardar.delete()){
        System.out.println("el archivo se ha elimando con exito");
        File carpeta = new File("logs");
        File[] archivos = carpeta.listFiles();
    }else {
        System.out.println("el archivop no se ha eliminado");
    }
}
    public static String seleccionarLogs(){
        Scanner sc = new Scanner(System.in);
        String direc = "logs";
        File carpeta = new File(direc);

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null && archivos.length !=0) {
                System.out.println("Seleccione el log:");
                int op=0;
                for (int i = 0; i < archivos.length; i++) {
                    File archivo = archivos[i];
                    System.out.println((i + 1) + ". " + archivo.getName());
                }
                while (op==0 || op > archivos.length) {
                    op = sc.nextInt();

                }
                return archivos[op-1].getPath();
            } else {
                System.out.println("No hay logs.");
                return "";
            }
        } else {
            System.out.println("se perdio la carpeta logs");
            return "";
        }
    }
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
                case 2: {
                    String elim = seleccionarLogs();
                    if (!elim.equals("")){
                        eliminarLog(elim);
                    }
                    break;
                }
                case 0: System.exit(0);
                default: {
                    System.out.println("Error, ingrese un numero valido.");
                    break;
                }
            }
        }




    }


}
