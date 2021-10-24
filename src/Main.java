public class Main {
    public static void main(String[] args) {

        /*String directorio = "src/laberintos/";

        Box[][] laberintoA = ArrayReader.readMatrix(directorio + "LaberintoA.txt");
        //String[][] laberintoB = ArrayReader.readMatrix(directorio + "LaberintoB.txt");

        printLaberinto(laberintoA);
        //System.out.println("");
        //printLaberinto(laberintoB);*/


        /*Queue queue = new Queue();

        queue.enqueue("Wofito");
        queue.enqueue("Milly");
        queue.enqueue("Zuly");

        System.out.println(queue.toString());*/

        Box box = new Box(true);
        box.neighborsQueue();
        box.toString();







    }

    public static void printLaberinto(Box[][] laberinto){
        Box box;
        for(int i = 0; i < laberinto.length; i++){
            for(int j = 0; j < laberinto[0].length; j++){
                box = laberinto[i][j];
                if(box.isWall() == false){
                    System.out.print("     ");
                }else{
                    System.out.print("@@@@@");
                }

            }
            System.out.println("");
        }
    }

}
