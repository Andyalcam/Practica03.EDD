import java.util.Random;

public class Box {

    private Boolean wall, visited;
    private Queue neighbors;

    public Box(Boolean wall) {
        this.wall = wall;
        neighbors = new Queue();
    }

    public boolean isWall(){
        return wall;
    }

    public boolean isVisited(){
        return visited;
    }

    public void visit(){
        this.setVisited(true);
    }


    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public void randomQueue(){

    }

    /**
     * * Rellena la cola con números de 0 a 3
     */
    public void neighborsQueue(){
        Random random = new Random();
        int aux = random.nextInt(4);
        neighbors.enqueue(aux);
        for(int i = 0; i < 4; i++){
            while(neighbors.contains(aux)){
                aux = random.nextInt(4);
            }
            neighbors.enqueue(aux);
        }

    }

    @Override
    public String toString() {
        return neighbors.toString();
    }
}
