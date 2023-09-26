package sumsala.develop;

import static java.lang.Math.abs;

public class L1BigramDistance {
    public static double[][] getDistances(char[] text){
        double[][] distances = new double[26][26];

        for(int i = 0; i < text.length - 1; i++){
            char a = text[i];
            char b = text[i+1];
            distances[a - 'A'][b - 'A']++;
        }
        return distances;
    }

    public static double evaluate(double[][] text_distances, double[][] reference, int textLength){
        double sum = 0.0;

        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++){
                double nv = reference[i][j] - (text_distances[i][j] / (double)textLength);
                sum += abs(nv);
            }
        }
        return sum;
    }

}
