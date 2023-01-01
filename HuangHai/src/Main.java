import java.util.ArrayList;
import java.util.Random;

/**
 * 原神荒海暴力解密
 *     3
 * 2       4
 *     1
 * 顺时针转
 * 2     3
 *    1
 * 3     4
 * position:
 * 1  2
 * 3  4
 */
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        int maxLength = 8;
        int[][] matrix = new int[][]{
                {2, 3},
                {3, 4}
        };
        ArrayList<Integer> result = TryDo(matrix, 1);
        while (result.size() > maxLength){
            result = TryDo(matrix, 1);
        }
        System.out.println(result);
    }

    public boolean isOk(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != target){
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] rotate(int[][] matrix, int position){
        int[][] matrixClone = MatrixClone(matrix);
        switch (position) {
            case 1 -> {
                matrixClone[0][0] = matrixClone[0][0] % 4 + 1;
                matrixClone[0][1] = matrixClone[0][1] % 4 + 1;
                matrixClone[1][0] = matrixClone[1][0] % 4 + 1;
            }
            case 2 -> {
                matrixClone[0][1] = matrixClone[0][1] % 4 + 1;
                matrixClone[0][0] = matrixClone[0][0] % 4 + 1;
                matrixClone[1][1] = matrixClone[1][1] % 4 + 1;
            }
            case 3 -> {
                matrixClone[1][0] = matrixClone[1][0] % 4 + 1;
                matrixClone[0][0] = matrixClone[0][0] % 4 + 1;
                matrixClone[1][1] = matrixClone[1][1] % 4 + 1;
            }
            case 4 -> {
                matrixClone[1][1] = matrixClone[1][1] % 4 + 1;
                matrixClone[0][1] = matrixClone[0][1] % 4 + 1;
                matrixClone[1][0] = matrixClone[1][0] % 4 + 1;
            }
        }
        return matrixClone;
    }

    public boolean isBack(int[][] matrix, int[][] OldMatrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != OldMatrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] MatrixClone(int[][] matrix) {
        int[][] s = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, s[i], 0, matrix.length);
        }
        return s;
    }

    public ArrayList<Integer> TryDo(int[][] matrix, int target) {
        int[][] OldMatrix = MatrixClone(matrix);
        Random R = new Random();
        ArrayList<Integer> re = new ArrayList<>();
        for (int i = 0; !isOk(matrix, target); i++) {
            int position = R.nextInt(4) + 1;
            matrix = rotate(matrix, position);
            if (isBack(matrix, OldMatrix)){
                re.clear();
            }
//            System.out.println(Arrays.deepToString(matrix));
            re.add(position);
        }
        return re;
    }
}