import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze extends JPanel {
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
        if(isExtensible()){
            try {
                do{
                    if(actual.getNeighborsSize() == 0){
                        extend();
                    }else if(actual.getRow() == getRowBeginBox() && actual.getColumn() == getColumnBeginBox()){
                        pop();
                        System.out.println("Fakiu no hay solución puñetas :33");
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
                Main.main.repaint();  //Clase Donde Esta El Main.Objeto Del Metodo Main.repaint()
                stack.push(actual);
                try {
                    Thread.sleep(100);
                }catch(InterruptedException e){

                }
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
            actual.setVisited(false);
            actual = stack.pop();
        }
    }

    public void solve(){
        Stack<Box> stackAux = new Stack<>();
        //printMaze();
        int size = stack.size();
        stackAux.push(stack.top());
        String ccs = "Lista de coordenadas de la solución: [ ";
        for(int i = 1; i < size; i++){
            stackAux.push(stack.pop());
        }

        System.out.println(ccs + stackAux + " ]");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){}
        System.exit(0);
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

    public void paint(Graphics graphics){
        for(int i = 0; i < boxes.length; i++){
            for(int j = 0; j < boxes.length; j++){
                if(boxes[i][j].isWall()){
                    graphics.setColor(new Color(30,39,50));
                    graphics.fillRect(j * 30, i * 30, 30, 30);
                    graphics.setColor(new Color(55, 62, 74));
                    graphics.drawRect(j * 30, i * 30, 30, 30);
                }else if(boxes[i][j].isVisited()){
                    graphics.setColor(new Color(4, 164, 117));
                    graphics.fillRect(j * 30, i * 30, 30, 30);
                    graphics.setColor(new Color(55, 62, 74));
                    graphics.drawRect(j * 30, i * 30, 30, 30);
                }else{
                    graphics.setColor(new Color (94, 107, 128));
                    graphics.fillRect(j * 30, i * 30, 30, 30);
                }
            }
        }
    }
}