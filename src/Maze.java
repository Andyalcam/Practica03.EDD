import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Maze {

    Scanner scanner = new Scanner(System.in);
    Stack<Box> stack = new Stack();
    private Box[][] boxes;
    private Box actual;
    private Box siguiente;
    private int rowBeginBox, columnBeginBox, rowEndBox, columnEndBox;

    public Maze(String file) {
        readMaze(file);
        actual = boxes[getRowBeginBox()][getColumnBeginBox()];
        siguiente = new Box();
    }

    public void begin(){
        actual.visit();
        stack.push(actual);
        siguiente = boxes[getRowBeginBox()][getColumnBeginBox()+1];
        siguiente.visit();
        stack.push(siguiente);
        //printMaze();
        actual = siguiente;
        extend();
    }

    public boolean isSolution(){
        return actual.getRow() == getRowEndBox() && actual.getColumn() == getColumnEndBox() || (siguiente.getRow() == getRowEndBox() && siguiente.getColumn() == getColumnEndBox());
    }

    public boolean isExtensible(){
        return actual.getNeighborsSize() != 0 && !isSolution();
    }

    public void extend(){
        try{
            Thread.sleep(1);
        }catch (Exception e){}
        if(isExtensible()){
            try{
                do{
                    if(actual.getNeighborsSize() == 0){
                        extend();
                    }else if(actual.getRow() == getRowBeginBox() && actual.getColumn() == getColumnBeginBox()){
                        pop();
                        System.out.println("Fakiu no hay solución puñetas :3");
                        System.exit(0);
                    }
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
                    actual.removeNeighbor();
                }while(siguiente.isWall() || siguiente.getVisited());
                actual = siguiente;
                actual.visit();
                stack.push(actual);
                //printMaze();
                extend();
            }catch(IndexOutOfBoundsException e){

            }
        }else{
            if(actual.getNeighborsSize() == 0){
                pop();
                extend();
            }
            if(isSolution()){
                solve();
            }else{
                System.out.println("Fakiu no hay solución");
            }
        }

    }

    public void pop(){
        while(actual.getNeighborsSize() == 0){
            if(actual.getRow() == getRowBeginBox() && actual.getColumn() == getColumnBeginBox()+1){
                System.out.println("Fakiu no hay solución puñetas :3");
                System.exit(0);
            }
            actual.setDraw("     ");
            actual = stack.pop();
        }
    }

    public void solve(){
        printMaze();
        System.out.println(stack.toString());
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
                    rowBeginBox = i;
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
                System.out.print(box.getDraw());
            }
            System.out.println("");
        }
    }
}
