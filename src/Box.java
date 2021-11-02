import java.util.Random;
/**
* Clase que simula una casilla que pertenece al laberinto, con atributos
* como ser pared o camino, estar visitada y sus coordenadas dentro de la matriz
* @author Alfonso Mondragon Segoviano
* @author Andrea Alvarado Camacho
* @version 1.0
*/
public class Box {

    private boolean wall, visited;
    private Queue neighbors;
    private String draw;
    private int row, column;

    /**
     * Constructor para crear un objeto de tipo Box (casilla). Si es pared dibuja "@@@@@",
     * inicia siendo no visitada y crea una cola de vecinos posibles.
     * @param wall - boolean, si es true será pared, si es false es camino.
     * @param row - int con la coordenada de la fila.
     * @param column - int con la coordenada de la columna.
     */
    public Box(boolean wall, int row, int column) {
        if(wall){
            draw = "@@@@@";
        }else{
            draw = "     ";
        }
        this.wall = wall;
        this.row = row;
        this.column = column;
        visited = false;
        neighborsQueue();
    }

    /**
     * Constructor por omision.
     */
    public Box() {

    }

    /**
     * Metodo que verifica si la casilla es pared o camino.
     * @return boolean - true si es pared y false si es camino.
     */
    public boolean isWall(){
        return wall;
    }

    /**
     * Metodo que verifica si la casilla ya fue visitada.
     * @return boolean - true si ya se visito y false en caso contrario.
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * Metodo que visita una casilla y cambia el dibujo para indicarlo
     * graficamente en la casilla.
     */
    public void visit(){
        setDraw("  o  ");
        setVisited(true);
    }

    /**
     * Metodo que rellena la cola de vecinos con números de 0 a 3 aleatoriamente
     */
    public void neighborsQueue() {
        if(!isWall()){
            neighbors = new Queue();
            List list = new List();
            Random random = new Random();
            int aux = random.nextInt(4);
            for(int i = 0; i < 4; i++){
                while(list.contains(aux)){
                    aux = random.nextInt(4);
                }
                list.add(0,aux);
                neighbors.enqueue(aux);
            }
        }
    }

    /**
     * Metodo que cambia el estado de visitada de una casilla.
     * @param visited - boolean con el valor, true para cambiar a visitada y false para cambiar a no visitada.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Metodo que cambia el dibujo de la casilla.
     * @param draw - String con la cadena para dibujar en la casilla.
     */
    public void setDraw(String draw) {
        this.draw = draw;
    }

    /**
     * Metodo para obtener el dibujo que contiene la casilla.
     * @return String - cadena con el dibujo contenido.
     */
    public String getDraw(){
        return draw;
    }

    /**
     * Metodo para obtener la coordenada de fila de la casilla.
     * @return int - indice que indica la fila donde se ubica la casilla.
     */
    public int getRow() {
        return row;
    }

    /**
     * Metodo para obtener la coordenada de columna de la casilla.
     * @return int - indice que indica la columna donde se ubica la casilla.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Metodo que regresa el tamaño de la cola de vecinos posibles para una casilla.
     * Cambia el estado de visitado a true si la cola es vacia (si ya no hay vecinos disponibles).
     * @return int - valor con el tamaño de la cola.
     */
    public int getNeighborsSize(){
        if(neighbors.size() == 0){
            setVisited(true);
        }
        return neighbors.size();
    }

    /**
     * Metodo que regresa el primer elemento de la cola.
     * @return int - con el valor del número aleatorio entre 0 y 3.
     */
    public int getNextNeighbor(){
        return (int) neighbors.first();
    }

    /**
     * Metodo que remueve el primer elemento de la cola.
     */
    public void removeNeighbor(){
        neighbors.dequeue();
    }

    /**
     * Metodo que imprime la cola de vecinos.
     * @return String - con la cadena de vecinos de una casilla.
     */
    public String printNeighbors(){
        return neighbors.toString();
    }

    /**
     * Metodo que imprime una casilla en forma de coordenadas.
     * @return String - con la cadena que repesenta las coordenadas (fila y columna) de una casilla.
     */
    @Override
    public String toString() {
        return "(" + getRow() + "," + getColumn() +")";
    }
}