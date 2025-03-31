import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int n;
        int[][] array;
        Scanner input = new Scanner(System.in); String user;

        System.out.print("What is your n?\n> ");
        n = input.nextInt();
        input.nextLine(); // catch "\n"

        array = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.out.printf("Please enter row %d:\n", i + 1);
            System.out.print("(separated by columns with NO SPACES)\n> ");

            user = input.nextLine();

            String[] row = user.split(",");

            if (row.length != n) {
                System.out.printf("Incorrect number of elements in row. Please only enter %d elements\n", n);
                i--;
            }
            else {
                for (int j = 0; j < n; j++) {
                    array[i][j] = Integer.parseInt(row[j]);
                }
            }
        }

        System.out.println(det_no_cof(array));
    }

    public static int[][] minor_generator(int i, int j, int[][] source) {
        int[][] ret = new int[source.length - 1][source.length - 1];
        int row = 0; int column = 0;

        for (int a = 0; a < source.length; a++) {
            if (a == i - 1) a++; // skips row i
            column = 0;
            for (int b = 0; b < source.length; b++) {
                if (b == j - 1) b++; // skips colum j

                if (a < source.length && b < source.length) // watch out for out of bounds!
                    ret[row][column] = source[a][b];
                column++;
            }
            row++;
        }

        return ret;
    }

    public static int det_no_cof(int[][] minor) {
        int answer = 0;

        if (minor.length == 2) { // base case
            return two_det(minor);
        }

        for (int count = 1; count <= minor.length; count++) {
            answer += (int) (Math.pow(-1, 1 + count) * minor[0][count-1] * det_no_cof(minor_generator(1, count, minor)));
        }

        return answer;
    }

    public static int two_det(int[][] source) {
        return (source[0][0] * source[1][1]) - (source[0][1] * source[1][0]);
    }

    // used for debugging only, no longer used
    public static void print_2d_array(int[][] source) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source.length; j++) {
                System.out.print(source[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
