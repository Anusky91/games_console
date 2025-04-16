package games;

import java.util.Scanner;

public class Hangman {

    private String logo = """
    _     _   _   ___   ____    ____     _     ____    ___  
   / \\   | | | | / _ \\ |  _ \\  / ___|   / \\   |  _ \\  / _ \\ 
  / _ \\  | |_| || | | || |_) || |      / _ \\  | | | || | | |
 / ___ \\ |  _  || |_| ||  _ < | |___  / ___ \\ | |_| || |_| |
/_/   \\_\\|_| |_| \\___/ |_| \\_\\ \\____|/_/   \\_\\|____/  \\___/ 
""";

    private String[] words = {
        // Comidas
        "pizza", "sushi", "taco", "pasta", "hamburguesa",
        // Animales
        "elefante", "tigre", "delfin", "jirafa", "gato",
        // Profesiones
        "doctor", "ingeniero", "maestro", "bombero", "arquitecto",
        // Sentimientos
        "felicidad", "tristeza", "amor", "miedo", "esperanza"
    };

    private String[] stages = {
        """
         +---+
         |   |
         |   O
         |  /|\\
         |  / \\
         |
        ===""",
        """
         +---+
         |   |
         |   O
         |  /|\\
         |  / 
         |
        ===""",
        """
         +---+
         |   |
         |   O
         |  /|\\
         |  
         |
        ===""",
        """
         +---+
         |   |
         |   O
         |   |
         |  
         |
        ===""",
        """
         +---+
         |   |
         |   O
         |  
         |  
         |
        ===""",
        """
         +---+
         |   |
         |   
         |  
         |  
         |
        ===""",
        """
             """,
    };

    private int lives = 6;
    private String chosenWord;
    private String whiteSpacesWord;
    private boolean isGameOn = true;

    public Hangman() {
        this.chosenWord = getWord();
        this.whiteSpacesWord = getWhiteStapes(this.chosenWord);
    }

    public int getLives() {
        return lives;
    }

    public String getLogo() {
        return logo;
    }

    public String getWord() {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    public String getWhiteStapes(String word) {
        StringBuilder whiteStapes = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            whiteStapes.append("_");
        }
        return whiteStapes.toString();

    }

    public boolean isLetterInWord(char letter) {
        char[] wordArray = this.chosenWord.toCharArray();
        for (char c : wordArray) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("La palabra a adivinar es: " + whiteSpacesWord);
        System.out.println("Tienes " + lives + " vidas");
        while (lives > 0 && isGameOn) {
            System.out.println("Elige una letra: ");
            char chosenLetter = scanner.nextLine().toLowerCase().charAt(0);
            if (isLetterInWord(chosenLetter)) {
                System.out.println("La letra " + chosenLetter + " está en la palabra.");
                // Aquí puedes agregar lógica para mostrar la letra en la posición correcta
                StringBuilder updatedWord = new StringBuilder(whiteSpacesWord);
                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == chosenLetter) {
                        updatedWord.setCharAt(i, chosenLetter);
                    }
                }
                System.out.println("Palabra actual: " + updatedWord.toString());
                whiteSpacesWord = updatedWord.toString();
                if (!whiteSpacesWord.contains("_")) {
                    isGameOn = false;
                }
            } else {
                lives--;
                System.out.println("La letra " + chosenLetter + " no está en la palabra. Te quedan " + lives + " vidas.");
                System.out.println(stages[lives]);
                if (lives == 0) {
                    isGameOn = false;
                }
                
            }
            
        }
        scanner.close();
        if (lives == 0) {
            System.out.println("¡Has perdido! La palabra era: " + chosenWord);
        } else {
            System.out.println("¡Felicidades! Has adivinado la palabra: " + chosenWord);
        }
    }



}
