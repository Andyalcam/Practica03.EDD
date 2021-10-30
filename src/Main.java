import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        String directorio = "src/laberintos/";

        String opc;

        boolean rep = false;

        Scanner in = new Scanner(System.in);

        Maze mazeA = new Maze(directorio+"LaberintoA.txt");

        Maze mazeB = new Maze(directorio+"LaberintoB.txt");

        do{
            System.out.println("*** BIENVENIDO ***");
            System.out.println("\n¿Qué laberinto deseas probar?");
            System.out.println("Laberinto A = A");
            System.out.println("Laberinto B = B");
            opc = in.nextLine();
            if(opc.equalsIgnoreCase("A")){
                mazeA.begin();
            }else if(opc.equalsIgnoreCase("B")){
                mazeB.begin();
            }else{
                System.out.println("\nEscribe 'A' o 'B'");
                rep = true;
            }
        }while(rep);

    }

}
