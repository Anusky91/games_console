package games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TresEnRalla {

    private String logo = readFile();
    private String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    private String player1 = "X";
    private String player2 = "O";
    private boolean isGameOn = true;
    private int rounds = 1;

    private String boardToString() {
        StringBuilder builderBoard = new StringBuilder();
        builderBoard.append("\n   A B C\n");
        builderBoard.append("  -------\n");
        for (int i = 0; i < board.length; i++) {
            builderBoard.append(i + " |");
            for (int j = 0; j < board[i].length; j++) {
                builderBoard.append(board[i][j]);
                if (j < board[i].length - 1) {
                    builderBoard.append("|");
                }
            }
            builderBoard.append("|\n");
            if (i < board.length - 1) {
                builderBoard.append("  -------\n");
            }
        }
        builderBoard.append("  -------\n");
        return builderBoard.toString();
    }

    private String readFile() {
        StringBuilder content = new StringBuilder();
        File file = new File("ahorcado_game/src/logo.txt"); // Ruta relativa al archivo logo.txt
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return content.toString();
    }

    private void setPieceOnBoard(int row, char collumn, String player) {
        // Comprobar que ese luegar este libre.
        switch (collumn) {
            case 'A' -> {
                if (this.board[row][0].equals(" ")) {
                    this.board[row][0] = player;
                } else {
                    System.out.println("La posicion ya esta ocupada, elige otra.");
                    this.rounds++;
                }
            }
            case 'B' -> {
                if (this.board[row][1].equals(" ")) {
                    this.board[row][1] = player;
                } else {
                    System.out.println("La posicion ya esta ocupada, elige otra.");
                    this.rounds++;
                }
            }
            case 'C' -> {
                if (this.board[row][2].equals(" ")) {
                    this.board[row][2] = player;
                } else {
                    System.out.println("La posicion ya esta ocupada, elige otra.");
                    this.rounds++;
                }
            }
            default -> System.out.println("Columna no valida, elige A, B o C.");
        }

    }

    private boolean has3InARow(String player) {
        for (String[] strings : board) {
            int counter = 0;
            for (String string : strings) {
                if (string == player) {
                    counter += 1;
                }
                if (counter == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean has3InAColumn(String player) {
        for (int i = 0; i < 3; i++) {
            int counter = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j][i].equals(player)) counter++;
            }
            if (counter == 3) return true;
        }
        return false;
    }

    private boolean has3InDiagonal(String player) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i].equals(player)) counter++;
        }
        if (counter == 3) return true;
        counter = 0;
        for (int j = 2; j > -1; j--) {
            if (board[j][j].equals(player)) counter++;
        }
        if (counter == 3) return true;
        return false;
    }

    private boolean hasWon(String player) {
        return has3InAColumn(player) || has3InARow(player) || has3InDiagonal(player);
    }

    private boolean isValidPosition(String position) {
        List<Character> acceptedColumns = List.of('A', 'B', 'C');
        return (Character.isDigit(position.charAt(0)) && (Integer.parseInt(position.substring(0, 1)) < 3) && acceptedColumns.contains(position.charAt(1)));
    }

    public void start() {
        System.out.println(readFile());
        System.out.println("Bienvenidos al juego del ahorcado!!");
        System.out.println(boardToString());
        Scanner scanner = new Scanner(System.in);
        String player = " ";
        while (isGameOn) {
            String position = "";
            if (rounds % 2 != 0) {
                player = this.player1;
            } else {
                player = this.player2;
            }
            System.out.println("Inserte la posición a marcar: \nEs turono de " + player);
            position = scanner.nextLine();

            if (isValidPosition(position)) {
                setPieceOnBoard(Integer.parseInt(position.substring(0, 1)), position.charAt(1), player);
                System.out.println(boardToString());
                if (hasWon(player)) {
                    isGameOn = false;
                    System.out.println("El jugador '".concat(player).concat("' ha ganado la partida!"));
                    return;
                }
                rounds += 1;
            } else {
                System.out.println("Posicion " + position + " incorrecta, intentelo otra vez!");
            }

        }
        scanner.close();
    }

}
