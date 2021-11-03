import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Clase main ejecutable para resolver un laberinto.
 * @author Alfonso Mondragon Segoviano
 * @author Andrea Alvarado Camacho
 * @version 1.0
 */
public class Main extends JPanel{

    // Colores de letra
    static String yellow="\033[33m";
    // Reset
    static String reset="\u001B[0m";
    
    static String directorio = "src/laberintos/";
    static Main main = new Main();
    static Maze maze;
    static JFrame window = new JFrame("Maze");

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        maze.paint(g);
    }

    public static void main(String[] args) {

        initComponents();

        String opc;

        boolean rep = false;

        Scanner in = new Scanner(System.in);

        do{
            System.out.println("*** BIENVENIDO ***");
            System.out.println("\n¿Qué laberinto deseas probar?");
            System.out.println("Laberinto A = A");
            System.out.println("Laberinto B = B");
            System.out.println("O escribe el nombre del archivo donde se encuentra el laberinto");
            System.out.println("\nIngrese la letra de la opción deseada.");
            opc = in.nextLine();
            if(opc.equalsIgnoreCase("A")){
                maze = new Maze(directorio+"LaberintoA.txt");
                window.setVisible(true);
                maze.begin();
            }else if(opc.equalsIgnoreCase("B")){
                maze = new Maze(directorio+"LaberintoB.txt");
                window.setVisible(true);
                maze.begin();
            }else{
                try{
                    maze = new Maze(directorio+opc);
                    window.setVisible(true);
                    maze.begin();
                }catch(Exception e){
                    rep = true;
                }
            }
        }while(rep);
    }

    public static void initComponents(){
        window.add(main);
        window.setSize(650,672);
        window.setLocation(350,40);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(false);
    }

}
