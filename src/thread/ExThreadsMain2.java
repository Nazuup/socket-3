/*処理内容
行列A(matrixA)と行列B(matrixB)の掛け算(A×B)を行うプログラム
行列Aの列数(行列Bの行数)回だけスレッドが作成され乗算、加減が行われる
*/

package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExThreadsMain2 extends Thread {
    public static void main(String[] arge) throws IOException {
        String file = "6_D_Result.txt";
        FileWriter output = new FileWriter(file, false);
        PrintWriter pri = new PrintWriter(new BufferedWriter(output));

        int[][] matrixA = { { 1, 2, 3 }, { 2, 3, 4 }, { 3, 4, 5 } }; // 行列A
        int[][] matrixB = { { 4, 5, 6 }, { 5, 6, 7 }, { 6, 7, 8 } }; // 行列B
        int[][] resultMatrix = new int[matrixA[0].length][matrixB.length];

        // 行列Aの列数と行列Bの行数が一致しない場合は終了する。
        if (matrixA[0].length != matrixB.length) {
            return;
        }
        Thread[] th = new Thread[matrixA[0].length * matrixB.length];
        int[] matB = new int[matrixB.length];
        // 計算前の行列出力
        System.out.println("行列A :");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                System.out.print(matrixA[i][j] + " ");
                pri.print(matrixA[i][j] + " ");
            }
            System.out.println();
            pri.println();
        }
        System.out.println("行列B :");
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[i].length; j++) {
                System.out.print(matrixB[i][j] + " ");
                pri.print(matrixB[i][j] + " ");
            }
            System.out.println();
            pri.println();
        }

        // スレッド作成
        for (int i = 0; i < th.length; i++) {
            for (int j = 0; j < matrixB[i].length; j++) {
                matB[j] = matrixB[j][i];
            }
            th[i] = new Thread(new ExRunnable(matrixA[i], matB, i));
        }

        // スレッド開始
        for (int i = 0; i < th.length; i++) {
            for(int j = 0; j<th.length; j++){
            System.out.println("col : "+i+", row : "+j+" running");
            th[i].start();
            //resultMatrix[i][j] = th[i].getNumber();
            }
        }

        // スレッド終了
        for (Thread thread : th) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
