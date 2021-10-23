public class Main {
    public static void main(String[] args) {

        /*String directorio = "src/laberintos/";

        String[][] laberintoA = ArrayReader.readMatrix(directorio + "LaberintoA.txt");
        String[][] laberintoB = ArrayReader.readMatrix(directorio + "LaberintoB.txt");

        printLaberinto(laberintoA);
        System.out.println("");
        printLaberinto(laberintoB);*/

        Queue queue = new Queue();

        queue.enqueue("Wofito");
        queue.enqueue("Milly");
        queue.enqueue("Zuly");

        System.out.println(queue.first());



    }

    public static void printLaberinto(String[][] laberinto){
        for(int i = 0; i < laberinto.length; i++){
            for(int j = 0; j < laberinto[0].length; j++){
                if(laberinto[i][j] != null){
                    System.out.print(laberinto[i][j]);
                }else{
                    System.out.print("@@@@");
                }

            }
            System.out.println("");
        }
    }


}
