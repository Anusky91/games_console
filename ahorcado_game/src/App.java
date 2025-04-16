import games.Hangman;
import games.TorreDeHanoi;
import games.TresEnRalla;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String option = "";
        do {
            System.out.println("""
                    Bienvenidos a juegos JAVA!
                    Selecciona un número para empezar a jugar:
                    1. Juego del Ahorcado.
                    2. Tres en linea.
                    3. Torre de Hanoi.
                    0. Salir.
                    """);
            option = scanner.nextLine();

            switch (option){
                case "1" -> {
                    Hangman hangman = new Hangman();
                    hangman.start();
                }
                case "2" -> {
                    TresEnRalla tresEnRalla = new TresEnRalla();
                    tresEnRalla.start();
                }
                case "3" -> {
                    TorreDeHanoi torreDeHanoi = new TorreDeHanoi(3);
                    torreDeHanoi.start();
                }
                case "0" -> System.out.println("Ha elegido salir del juego. \nHasta pronto!");
                default -> System.out.println("Opción no valida, vuelvelo a intentar.");
            }
        } while (!option.equals("0"));


    }
}
