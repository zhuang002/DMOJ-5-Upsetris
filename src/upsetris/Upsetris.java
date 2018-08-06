/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upsetris;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class Upsetris {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i = 0; i < 5; i++) {
            char[][] board = readTestCase();
            UpsetrisProcessor processor = new UpsetrisProcessor(board);
            processor.doIt();
            processor.print();

        }
    }

    private static char[][] readTestCase() {
        ArrayList<char[]> lines = new ArrayList();
        String line = sc.nextLine();
        while (line.charAt(1) != '=') {
            char[] lineData = line.substring(1, line.length() - 1).toCharArray();
            lines.add(lineData);
            line = sc.nextLine();
        }
        char[][] a = new char[lines.size()][line.length() - 2];
        return lines.toArray(a);
    }

}

class UpsetrisProcessor {

    char[][] board = null;

    public UpsetrisProcessor(char[][] bd) {
        this.board = bd;
    }

    public void doIt() {
        int rows = board.length;
        int cols = board[0].length;
        for (int col = 0; col < cols; col++) {
            int emptyRow = -1;
            for (int row = 0; row < rows; row++) {
                switch (this.board[row][col]) {
                    case ' ':
                        if (emptyRow == -1) {
                            emptyRow = row;
                        }
                        break;
                    case 'O':
                        this.board[emptyRow][col] = 'O';
                        emptyRow++;
                        this.board[row][col] = ' ';
                        break;
                }
            }
        }
    }

    public void print() {
        int rows = board.length;
        int cols = board[0].length;
        int deletedRows = 0;
        for (int i = 0; i < rows; i++) {
            String line = new String(board[i]);
            if (line.indexOf(' ') == -1) {
                deletedRows++;
            }
        }

        for (int i = 0; i < deletedRows; i++) {
            System.out.print('|');
            for (int j = 0; j < cols; j++) {
                System.out.print(' ');
            }
            System.out.println('|');
        }

        for (int row = rows - 1; row >= deletedRows; row--) {

            System.out.print('|');
            for (int col = cols - 1; col >= 0; col--) {
                System.out.print(board[row][col]);
            }
            System.out.println('|');

        }

        System.out.print('|');
        for (int j = 0; j < cols; j++) {
            System.out.print('=');
        }
        System.out.println('|');
    }

}
