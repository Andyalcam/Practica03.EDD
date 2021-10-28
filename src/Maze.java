import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Maze {

    private Box[][] boxes;
    private Box actual;
    private Box siguiente;
    private int rowBeginBox, columnBeginBox, rowEndBox, columnEndBox;
    Scanner scanner = new Scanner(System.in);

    public Maze(String file) {
        readMaze(file);
        actual = boxes[getRowBeginBox()][getColumnBeginBox()];
        siguiente = new Box();
    }

    public void begin(){
        actual.visit();
        siguiente = boxes[getRowBeginBox()][getColumnBeginBox()+1];
        siguiente.visit();
        actual = siguiente;
        printMaze();
        extend();
    }

    public boolean isSolution(){
        return actual.getRow() == getRowEndBox() && actual.getColumn() == getColumnEndBox();
    }

    public boolean isExtensible(){
        return actual.getNeighborsSize() != 0 && !isSolution();
    }

    public void extend(){
        if(isExtensible()){
            System.out.println("Posibles vecinos: " + actual.printNeighbors());
            System.out.println("Siguiente: " + siguiente.isWall() + "," + siguiente.isVisited());
            do{
                switch(actual.getNextNeighbor()) {
                    case 0:
                        siguiente = boxes[(actual.getRow() - 1)][actual.getColumn()];
                        break;
                    case 1:
                        siguiente = boxes[actual.getRow()][(actual.getColumn() + 1)];
                        break;
                    case 2:
                        siguiente = boxes[(actual.getRow() + 1)][actual.getColumn()];
                        break;
                    case 3:
                        siguiente = boxes[actual.getRow()][(actual.getColumn() - 1)];
                        break;
                }
                System.out.println("Cc Siguiente: " + siguiente.getRow() + "," + siguiente.getColumn());
                System.out.println("Posibles: " + actual.printNeighbors());
            }while(siguiente.isWall() || siguiente.getVisited());
            actual = siguiente;
            actual.visit();
            printMaze();
            System.out.println("Posibles vecinos actuales: " + actual.printNeighbors());
            scanner.next();
            extend();
        }else{
            if(isSolution()){
                System.out.println("Oa crayoa");
            }else{
                System.out.println("Fakiu no hay solución");
            }
        }

    }

    public void solve(){

    }

    public int getRowBeginBox() {
        return rowBeginBox;
    }

    public int getColumnBeginBox() {
        return columnBeginBox;
    }

    public int getRowEndBox() {
        return rowEndBox;
    }

    public int getColumnEndBox() {
        return columnEndBox;
    }

    public void readMaze(String name){
        try(BufferedReader reader = new BufferedReader(new FileReader(name))){
            String[] dimensions = reader.readLine().split(",");
            // Se leen las dimensiones del laberinto
            int h = Integer.valueOf(dimensions[0]), w = Integer.valueOf(dimensions[1]);
            boxes = new Box[h][w];

            String line = null;
            int index = 0;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                // Se obtiene la fila y columna de la casilla del laberinto
                int row = Integer.valueOf(data[0]), column = Integer.valueOf(data[1]);
                // Depende de como definas el constructor de Box ajusta la siguiente linea
                boxes[row][column] = new Box(false, row, column);
            }

            for(int i = 0; i < boxes.length; i++){
                for(int j = 0; j < boxes[0].length; j++){
                    if(boxes[i][j] == null){
                        boxes[i][j] = new Box(true, i, j);
                    }
                }

            }

            for(int i = 0; i < boxes.length; i++){
                if(!boxes[i][0].isWall()){
                    rowBeginBox = i ;
                    columnBeginBox = 0;
                }
            }

            for(int j = 0; j < boxes.length; j++){
                if(!boxes[j][boxes.length-1].isWall()){
                    rowEndBox = j;
                    columnEndBox = boxes.length-1;
                }
            }

        } catch(FileNotFoundException fnfe){
            System.out.println("ARCHIVO "+name+" NO ENCONTRADO");
        } catch(IOException ioe){}
    }

    public void printMaze(){
        Box box;
        for(int i = 0; i < boxes.length; i++){
            for(int j = 0; j < boxes[0].length; j++){
                box = boxes[i][j];
                System.out.print(box.toString());
            }
            System.out.println("");
        }
    }
}
