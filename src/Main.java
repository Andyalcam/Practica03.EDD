import java.util.Scanner;
<<<<<<< HEAD

=======
>>>>>>> 38f0ffa19fa2ca5abf3da4b362b63b6ca26f7028
public class Main {
    public static void main(String[] args) {

        String directorio = "src/laberintos/";

<<<<<<< HEAD
        Maze mazeA = new Maze(directorio+"LaberintoA.txt");
=======
        String opc;
>>>>>>> 38f0ffa19fa2ca5abf3da4b362b63b6ca26f7028

        boolean rep = false;

        Scanner in = new Scanner(System.in);

<<<<<<< HEAD
        mazeA.begin();
        System.out.println("Inicia Maze B");
        mazeB.begin();
=======
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

>>>>>>> 38f0ffa19fa2ca5abf3da4b362b63b6ca26f7028
    }

}
