import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JPanel{
    
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
            System.out.println("Ingrese la letra de la opción deseada.");
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
                System.out.println("\nEscribe 'A' o 'B'");
                rep = true;
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
