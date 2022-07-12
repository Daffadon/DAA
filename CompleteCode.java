import java.util.Iterator;
import java.util.LinkedList;

import java.util.Scanner;

public class CompleteCode {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int banyakMatriks = cin.nextInt();
        int[] p = new int[banyakMatriks];
        for (int i = 0; i < banyakMatriks; i++) {
            p[i] = cin.nextInt();
        }
        LinkedList<int[][]> matrixContainer = new LinkedList<>();
        matrixChainOrder(p, matrixContainer);
        Iterator<int[][]> itr = matrixContainer.iterator();
        while (itr.hasNext()) {
            int[][] arr = (int[][]) itr.next();
            matrixView(arr);
            System.out.println("============================================================");
        }
        cin.close();
    }

    static LinkedList<int[][]> matrixChainOrder(int[] arr, LinkedList<int[][]> matrixContainer) {
        int n = arr.length - 1;
        int[][] m = new int[arr.length + 1][arr.length + 1];
        int[][] s = new int[arr.length + 1][arr.length + 1];
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        matrixContainer.addLast(m);
        matrixContainer.addLast(s);
        return matrixContainer;
    }

    static void matrixView(int[][] arr) {
        System.out.print("j|i");
        for (int i = 1; i < arr[i].length - 1; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i = 1; i < arr.length - 1; i++) {
            System.out.print(i + "\t");
            for (int j = 1; j < arr[i].length - 1; j++) {
                if (arr[i][j] == 0 && i != j) {
                    System.out.print("\t");
                } else {
                    System.out.print(arr[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
}
