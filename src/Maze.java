import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
* Clase que crea un laberinto a base de una matriz de objetos de tipo Box. Extiende a JPanel para crear una interfaz gráfica.
* @author Alfonso Mondragon Segoviano
* @author Andrea Alvarado Camacho
* @version 1.0
*/
public class Maze extends JPanel {

    // Colores de letra
    static String red="\033[31m"; 
    static String green="\033[32m"; 
    static String yellow="\033[33m";
    static String cyan="\033[36m";
    // Reset
    static String reset="\u001B[0m";

    Stack<Box> stack = new Stack();
    private Box[][] boxes;
    private Box actual;
    private Box siguiente;
    private int rowBeginBox, columnBeginBox, rowEndBox, columnEndBox;

    /**
     * Constructor para crear un objeto laberinto.
     * Como atributos tiene dos objetos de tipo Box, uno que indica la casilla actual y otro con la casilla siguiente.
     * @param file String - con las coordenadas necesarias para crear un laberinto nuevo.
     */
    public Maze(String file) {
        readMaze(file);
        actual = boxes[getRowBeginBox()][getColumnBeginBox()];
        siguiente = new Box();
    }

    /**
     * Metodo que inicia el intento para encontrar la solucion al laberinto.
     */
    public void begin(){
        actual.visit();
        stack.push(actual);
        siguiente = boxes[getRowBeginBox()][getColumnBeginBox()+1];
        siguiente.visit();
        stack.push(siguiente);
        actual = siguiente;
        extend();
    }

    /**
     * Metodo que verifica si el intento es una solucion para el laberinto.
     * @return boolean - true si es solucion, false en caso contrario.
     */
    public boolean isSolution(){
        return actual.getRow() == getRowEndBox() && actual.getColumn() == getColumnEndBox() || (siguiente.getRow() == getRowEndBox() && siguiente.getColumn() == getColumnEndBox());
    }

    /**
     * Metodo que verfica si el laberinto es extensible (Si puede seguir buscando una solucion).
     * @return boolean - true si es extensible, false en caso contrario.
     */
    public boolean isExtensible(){
        return actual.getNeighborsSize() != 0 && !isSolution();
    }

    /**
     * Metodo que extiende el laberinto. Verifica que extensible sea true, que haya vecinos disponibles,
     * para poder avanzar la casilla "actual" a la "siguiente". Si la casilla "actual" ya no tiene vecinos
     * disponibles, llama al metodo pop y realiza el backtraking.
     * En cada avance de la solucion, se va actualizando la interfaz grafica para mostrar el avance de la busqueda de la solucion.
     */
    public void extend(){
        if(isExtensible()){
            try {
                do{
                    if(actual.getNeighborsSize() == 0){
                        extend();
                    }
                    // Con base en la lista de vecinos posibles de la casilla, avanzará hacia arriba, abajo, derecha o izquierda.
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
                    actual.removeNeighbor(); // Removemos el elemento de la lista de vecinos posibles para que no vuelva a ir hacia esa dirección.
                }while(siguiente.isWall() || siguiente.isVisited());
                actual = siguiente; // Actualizamos la casilla actual si logro avanzar.
                actual.visit(); // La marcamos como visitada.
                Main.main.repaint();  //Clase Donde Esta El Main.Objeto Del Metodo Main.repaint()
                stack.push(actual); // Agregamos la casilla a la pila de la solucion.
                try {
                    Thread.sleep(100);
                }catch(InterruptedException e){

                }
                extend(); // Seguimos avanzando la casilla actual mientras siga teniendo vecinos disponibles.
            }catch(IndexOutOfBoundsException e){
            }
        }else{
            if(actual.getNeighborsSize() == 0){
                pop(); // Es aquí donde hacemos el backtraking haciendo pop's y regresando la casilla "actual" a una donde tenga más vecinos disponibles.
                extend();
            }
            if(isSolution()){
                solve(); // Si es solución, llamamos al metodo solve e imprimimos las coordenadas de la solución.
            }
        }
    }

    /**
     * Metodo que realiza pop's en la pila de boxes mientras la casilla actual no tenga vecinos disponibles.
     */
    public void pop(){
        while(actual.getNeighborsSize() == 0){
            if(actual.getRow() == getRowBeginBox() && actual.getColumn() == getColumnBeginBox()+1){
                System.out.println(red + "Este laberinto no tiene solución :c" + reset);
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){}
                System.exit(0);
            }
            actual.setDraw("     ");
            actual.setVisited(false);
            actual = stack.pop();
        }
    }

    /**
     * Metodo que imprime la solucion del laberinto en forma de coordenadas.
     */
    public void solve(){
        Stack<Box> stackAux = new Stack<>();
        //printMaze();
        int size = stack.size();
        stackAux.push(stack.top());
        String ccs = "Lista de coordenadas de la solución: [ ";
        for(int i = 1; i < size; i++){
            stackAux.push(stack.pop());
        }

        System.out.println(green + ccs + reset + cyan + stackAux + reset + green + " ]" + reset);

        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){}
        System.exit(0);
    }

    /**
     * Metodo que regresa la coordenada de fila inicial.
     * @return int - con el valor de la fila donde se ubica la casilla de inicio.
     */
    public int getRowBeginBox() {
        return rowBeginBox;
    }

    /**
     * Metodo que regresa la coordenada de columna inicial.
     * @return int - con el valor de la columna donde se ubica la casilla de inicio.
     */
    public int getColumnBeginBox() {
        return columnBeginBox;
    }

    /**
     * Metodo que regresa la coordenada de fila final.
     * @return int - con el valor de la fila donde se ubica la casilla de salida.
     */
    public int getRowEndBox() {
        return rowEndBox;
    }

    /**
     * Metodo que regresa la coordenada de columna final.
     * @return int - con el valor de la columna donde se ubica la casilla de salida.
     */
    public int getColumnEndBox() {
        return columnEndBox;
    }

    /**
     * Metodo que lee las coordenadas de un archivo .txt y crea la matriz de objetos de tipo Box
     * y lo va llenando mientras crea los objetos (casillas) conforme a los parametros necesarios.
     */
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
            System.out.println(yellow + "ARCHIVO "+name+" NO ENCONTRADO" + reset);
        } catch(IOException ioe){}
    }

    /**
     * Metodo que imprime el laberinto.
     */
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

    /**
     * Metodo encargado de representar graficamente el laberinto.
     */
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