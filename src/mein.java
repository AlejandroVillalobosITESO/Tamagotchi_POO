import Classes.Species.Species;
import Classes.Tamagotchi;
import Games.BlackJack;
import Games.C0necta4;
import Games.Gato;
import Games.Roshambo;

import java.util.Scanner;

public class mein {
    public static void main(String[] args) {
        int totalPets = 0;
        int currentPet = 0;
        Tamagotchi[] myPets = new Tamagotchi[64];

        //aqui va a ser el loop del juego
        String op;
        Scanner reader = new Scanner(System.in);
        boolean playing = true;
        while (playing){
            printMenu();
            op = reader.nextLine();
            switch(op){
                case "1":
                    newPet(myPets, totalPets);
                    totalPets++;
                    break;
                case "2":
                    currentPet = select(myPets, totalPets);
                    break;
                case "3":
                    jugar(myPets[currentPet]);
                    break;
                case "4":
                    explorar(myPets[currentPet]);
                    break;
                case "5":
                    pelear();
                    break;
                case "6":
                    break;
                case "0":
                    playing = false;
                    break;
            }
            //update
            for(int i=0; i<totalPets; i++)
                myPets[i].update();
            //procrear
            if(totalPets > 1) { //total pets
                for (int i = 0; i < totalPets - 1; i++) { //i
                    for (int j = i + 1; j < totalPets; j++) { //j
                        if(totalPets > 63)
                            break;
                        System.out.println(""+i+"v"+j);
                        myPets[totalPets] = Tamagotchi.procrear(myPets[i], myPets[j]);
                        if (myPets[totalPets] != null) {
                            totalPets++;
                            //System.out.println("procrearon " + totalPets);
                        }
                    }
                }
            }
        }
    }

    public static void printMenu(){
        System.out.println(" ~~~~~~~~~~~~~~~ MENU ~~~~~~~~~~~~~~~   ");
        System.out.println(" 1. Crear un Tamagotchi nuevo           ");
        System.out.println(" 2. Selecciona un Tamagotchi            ");
        System.out.println(" 3. Jugar con tu Tamagotchi             ");
        System.out.println(" 4. Manda el Tamagotchi a explorar      ");
        System.out.println(" 5. Pelea contra otro Tamagotchi        ");
        System.out.println(" 6. Skip                                ");
        System.out.println(" 0. Salir del juego                     ");
    }

    public static void newPet(Tamagotchi[] array, int total){
        System.out.println("Selecciona la especie: ");
        System.out.println(Species.menu());
        Scanner reader = new Scanner(System.in);
        int op = Integer.parseInt(reader.nextLine());
        if(op<0 || op>6)
            op = 1;
        System.out.println("Ingresa un nombre: ");
        String name = reader.nextLine();
        array[total] = new Tamagotchi(name, op);
    }

    public static int select(Tamagotchi[] array, int total){
        if(total == 0) {
            System.out.println("No hay mascotas, crea una nueva o carga de un archivo");
            return 0;
        }
        Scanner reader = new Scanner(System.in);
        for(int i=0; i<total; i++)
            System.out.println((i+1) + ". " + array[i].getName());
        System.out.println("0. Canelar");
        int op = Integer.parseInt(reader.nextLine());
        if(op<1 || op>total)
            return 0;
        op--;
        System.out.println(array[op].stats());
        return op;
    }

    public static void jugar(Tamagotchi pet){

        System.out.println(" ~~~~~~~~~~~~~~~ JUEGOS ~~~~~~~~~~~~~~~ ");
        System.out.println(" 1. BlackJack           ");
        System.out.println(" 2. Gato                ");
        System.out.println(" 3. Pelota              ");
        System.out.println(" 4. Connect 4               ");
        System.out.println(" 5. Roshambo            ");
        System.out.println(" 0. Cancelar            ");

        String op;
        Scanner reader = new Scanner(System.in);
        op = reader.nextLine();
        switch(op){
            case "1":
                BlackJack.main(pet);
                pet.play(20);
                break;
            case "2":
                Gato.main(pet);
                pet.play(5);
                break;
            case "3":

                break;
            case "4":
                C0necta4.main(pet);
                break;
            case "5":
                Roshambo.main(pet);
                pet.play(5);
                break;
            case "6":
                break;
            case "0":
                break;
        }

    }

    public static void explorar(Tamagotchi pet){
        System.out.println(pet.getName() + "esta explorando");
    }

    public static void pelear(){
        System.out.println("Pelea");
    }
}
