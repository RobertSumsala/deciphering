package sumsala.develop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class addFun {
    public static char[] fileToArr(String name){
        StringBuilder txt = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            int num = 0;
            char ch;
            while ((num = br.read()) != -1) {
                ch = (char) num;
                txt.append(ch);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return txt.toString().toCharArray();
    }


    public static void printArray(char[] text){
        for (char c : text){
            System.out.print(c);
        }
        System.out.println();
    }



}


