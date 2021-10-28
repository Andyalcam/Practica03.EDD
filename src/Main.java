import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String directorio = "src/laberintos/";

        Maze maze = new Maze(directorio+"LaberintoA.txt");

        maze.begin();

    }

}
