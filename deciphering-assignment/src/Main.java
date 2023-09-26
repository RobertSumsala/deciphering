package sumsala.develop;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        char[] input = addFun.fileToArr("16.txt");
        System.out.println("SOLVING. PLEASE WAIT!");
        Vigenere.bruteSolve(input);






    }
}
