import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String directorio = "src/laberintos/";

        Maze mazeA = new Maze(directorio+"LaberintoA.txt");

        Maze mazeB = new Maze(directorio+"LaberintoB.txt");

        mazeA.begin();
        System.out.println("Inicia Maze B");
        mazeB.begin();
    }

}
