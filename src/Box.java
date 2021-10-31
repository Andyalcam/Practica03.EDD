import java.util.Random;

public class Box {

    private Boolean wall, visited;
    private Queue neighbors;
    private String draw;
    private int row, column;

    public Box(Boolean wall, int row, int column) {
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

    public Box() {

    }

    public boolean isWall(){
        return wall;
    }

    public boolean isVisited(){
        return visited;
    }

    public void visit(){
        setDraw("  o  ");
        setVisited(true);
    }

    /**
     * * Rellena la cola con números de 0 a 3
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

    public Boolean getVisited() {
        return visited;
    }

    public void setWall(Boolean wall) {
        this.wall = wall;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getDraw(){
        return draw;
    }

    public int getNeighborsSize(){
        if(neighbors.size() == 0){
            setVisited(true);
        }
        return neighbors.size();
    }

    public int getNextNeighbor(){
        return (int) neighbors.first();
    }

    public void removeNeighbor(){
        neighbors.dequeue();
    }

    public String printNeighbors(){
        return neighbors.toString();
    }

    @Override
    public String toString() {
        return "(" + getRow() + "," + getColumn() +")";
    }
}
