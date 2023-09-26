package sumsala.develop;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static java.lang.Math.abs;
import static java.lang.Math.log;

public class Vigenere {
    public static void bruteSolve(char[] ct) {
        char[] abcd = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String sampleText = "eversinceIsawadocumentaryonthesubjectIvealwaysmaintainedthatthepeoplewiththeabsoluteworstjobintheworldarethosewhogointoLondonssewerseverydayandchiselawayatthesocalledfatbergswhicharegluedinplacebyallmannerofsanitaryproductsthatdontevenbearthinkingaboutHoweverImnowstartingtoreappraisethisviewbecausehavingwatchedafairbitoftheWorldCupIvedecidedthatIdratherremoveafatbergbyeatingitthanbeafootballrefereeYourunaroundconstantlyfornearlytwohoursinacauldronofhatredwhichismostlydirectedatyouandinayearyouwillearnmaybeWhichiswhatmostoftheplayersgeteveryminutesSowhydoitIgetthatlotsofyoungkidslikefootballbutarenogoodatitNormallythismeanstheybecomegoalkeepersorfansorballboysThatwaytheyrepartoftheteampartofaclubArefisntHesjustthereasapunchbagAndheknowsthathewilldevotehislifetothebeautifulgameandneverscoreagoalActuallythatsnottrueTwentyyearsagoachapcalledBrianSavillwasreffingamatchinEssexbetweenthemightylocaltitansofEarlsColneandWimpoleThescorewasandhefeltsosorryforthelosersthatinthedyingminuteshehammeredtheballintothenetmakingitHewassackedafterwardsFormostrefstheaimistoworkyourwayupthroughtherankstothetopflightandofficiateatthebiggamesinthePremiereLeagueButintheentireworldonlypeopleactuallyareselectgroupPremierLeaguerefereesWhichmeansyoustandabetterchanceofbecominganastronaut";
        char[] sample = sampleText.toUpperCase().toCharArray();
        double[][] refBigrams = L1BigramDistance.getDistances(sample);

        char[] bestKey1 = new char[4];
        char[] bestKey2 = new char[4];

        double bestFit = Double.MAX_VALUE;

        // First half of the key
        for (char c1 : abcd){
            for (char c2 : abcd) {
                for (char c3 : abcd){
                    for (char c4 : abcd){
                        char[] partialPt = vigenereShift(c1, c2, c3, c4, ct, 1);
                        double[][] partialPt_bigrams = L1BigramDistance.getDistances(partialPt);
                        double fit = log(L1BigramDistance.evaluate(partialPt_bigrams, refBigrams, partialPt.length));

                        if (fit < bestFit){
                            bestFit = fit;
                            bestKey1[0] = c1;
                            bestKey1[1] = c2;
                            bestKey1[2] = c3;
                            bestKey1[3] = c4;
                        }
                    }
                }
            }
        }

        // second half of the key
        bestFit = Double.MAX_VALUE;
        for (char c1 : abcd){
            for (char c2 : abcd) {
                for (char c3 : abcd){
                    for (char c4 : abcd){
                        char[] partialPt = vigenereShift(c1, c2, c3, c4, ct, 2);
                        double[][] partialPt_bigrams = L1BigramDistance.getDistances(partialPt);
                        double fit = log(L1BigramDistance.evaluate(partialPt_bigrams, refBigrams, partialPt.length));

                        if (fit < bestFit){
                            bestFit = fit;
                            bestKey2[0] = c1;
                            bestKey2[1] = c2;
                            bestKey2[2] = c3;
                            bestKey2[3] = c4;
                        }
                    }
                }
            }
        }

        // decipher using the best keys
        char[] key = new char[8];
        key[0] = bestKey1[0];
        key[1] = bestKey1[1];
        key[2] = bestKey1[2];
        key[3] = bestKey1[3];
        key[4] = bestKey2[0];
        key[5] = bestKey2[1];
        key[6] = bestKey2[2];
        key[7] = bestKey2[3];

        String pt = decipher(ct, key);
        System.out.println("--- PROCES FINISHED ---");
        System.out.print("key: ");
        addFun.printArray(key);
        System.out.println("deciphered text: ");
        System.out.println(pt);
    }


    private static String decipher(char[] ct, char[] key){
        StringBuilder pt = new StringBuilder();
        for (int i = 0; i < ct.length; i++){
            pt.append(shiftChar(ct[i], key[i % 8]));
        }

        return pt.toString();
    }

    private static char[] vigenereShift(char c1, char c2, char c3, char c4, char[] ct, int half) {
        StringBuilder partialPt = new StringBuilder();

        if (half == 1){
            for (int i = 0; i < ct.length - 1; i++) {
                partialPt.append(shiftChar(ct[i], c1));
                i++;
                partialPt.append(shiftChar(ct[i], c2));
                i++;
                partialPt.append(shiftChar(ct[i], c3));
                i++;
                partialPt.append(shiftChar(ct[i], c4));
                i += 4;
            }
        } else if (half == 2){
            for (int i = 4; i < ct.length - 1; i++) {
                partialPt.append(shiftChar(ct[i], c1));
                i++;
                partialPt.append(shiftChar(ct[i], c2));
                i++;
                partialPt.append(shiftChar(ct[i], c3));
                i++;
                partialPt.append(shiftChar(ct[i], c4));
                i += 4;
            }
        }
        return partialPt.toString().toCharArray();
    }


    private static char shiftChar(char c, char shift){
        return (char)((  ( ( (int)c - 97 ) + ( (int)shift - 97 ) ) % 26  ) + 65 );
    }









}
