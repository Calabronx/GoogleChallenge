import java.util.Scanner;

public class BrailleInputProgram {
    public static String solution(String plainText) {
        final String A = "100000", B = "110000", C = "100100", D = "100110", E = "100010", F = "110100", G = "110110", H = "110010", I = "010100", J = "010110", K = "101000", L = "111000",
                M = "101100", N = "101110", O = "101010", P = "111100", Q = "111110", R = "111010", S = "011100", T = "011110", U = "101001", V = "111001", W = "010111", X = "101101", Y = "101111", Z = "101011";

        String[] brailleHarcodes = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
        String[] brailleUnicode = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        int[] unicodes = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};

        final String WHITE_SPACES = "000000";
        final String UPPER_MARK = "000001";

        String brailleCode = " ";
        char caracter = ' ';

        for (int i = 0; i < plainText.length(); i++) {
            caracter = plainText.charAt(i);
            String key_obtained = String.valueOf(caracter);
            boolean isAlphabetic = Character.isAlphabetic(plainText.codePointAt(i));

            if (isAlphabetic || key_obtained.equals(" ")) {
                if (Character.isUpperCase(caracter)) {
                    brailleCode += UPPER_MARK;

                    String convertToLowKey = key_obtained.toLowerCase();
                    int value = binarySearch(brailleUnicode,convertToLowKey,unicodes);
                    brailleCode += brailleHarcodes[value];
                    continue;
                }
                for (String index : brailleUnicode) {
                    if (!key_obtained.equals(" ")) {
                        int index_search = binarySearch(brailleUnicode, key_obtained,unicodes);
                        brailleCode += brailleHarcodes[index_search];
                        break;
                    }
                    else if (Character.isWhitespace(caracter)) {
                        brailleCode += WHITE_SPACES;
                        break;
                    }
                }
            } else {
                System.exit(0);
            }
        }
        return brailleCode;
    }

    public static int binarySearch(String[] input, String character,int[] integer_unicodes) {
        int start = 0;
        int end = input.length;
        int value = character.codePointAt(0);

        while (start < end) {
            int midpoint = (start + end) / 2;
            if (integer_unicodes[midpoint] == value) {
                return midpoint;
            } else if (integer_unicodes[midpoint] < value) {
                start = midpoint + 1;
            } else {
                end = midpoint;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        System.out.println("--------------------");
        System.out.println("*Braille ASCII Unicode program");
        System.out.println("*Braille ASCII Unicode software");
        System.out.println("Ingrese una cadena de caracteres (frase,oracion,palabra,etc) para traducir a codigo Braille");
        System.out.println("No se permiten numeros como cadena");
        while (flag) {
            System.out.println("--------------------");
            String inputPhrase = input.nextLine();
            if (inputPhrase.equals("exit")) {
                System.out.println("Adios");
                input.close();
                flag = false;
            } else {
                String output = solution(inputPhrase);
                System.out.println("--------------------");
                System.out.println("* Cadena ingresada: " + inputPhrase);
                System.out.println("--------------------");
                System.out.println("* Codigo Braille Unicode: " + output);
                System.out.println("--------------------");
                System.out.println("Ingresar otra cadena");
                System.out.println("--------------------");
                System.out.println("Ingrese exit para salir");
            }
        }
    }
}

