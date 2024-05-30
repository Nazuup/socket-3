package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ExThreadsMain implements Runnable {
    int randomNumber = 0;
    int number = 0;
    int result = 1;
    static String file = "6_D_Result.txt";

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        FileWriter output = new FileWriter(file, false);
        PrintWriter pri = new PrintWriter(new BufferedWriter(output));
        int[] n = new int[10];
        ExThreadsMain[] cts = new ExThreadsMain[10];
        Thread[] th = new Thread[10];
        for (int i = 0; i < n.length; i++) {

            n[i] = (char) (random.nextInt(10));
        }

        // 初期化した文字をコンソールに出力
        for (int i = 0; i < n.length; i++) {
            System.out.println("n["+i+"] = "+n[i]);
            pri.println("n["+i+"] = "+n[i]);
        }

        // CountTenRunnableクラスのインスタンス作成
        for (int i = 0; i < n.length; i++) {
            cts[i] = new ExThreadsMain();
            int[] num = {i, n[i]};
            cts[i].setNumber(num);
        }

        // スレッド作成
        for (int i = 0; i < n.length; i++) {
            th[i] = new Thread(cts[i]);
        }

        // スレッド開始
        System.out.println("start running");
        pri.println("start running");
        for (int i = 0; i < n.length; i++) {
            th[i].start();
        }
        pri.close();

        // スレッド終了
        try {
            for (Thread thread : th) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setNumber(int[] num) {
        number = num[0];
        randomNumber = num[1];
    }
    public int getNumber(){
        return number;
    }

    public void run() {
        try {
            result = 1;
            FileWriter output = new FileWriter(file, true);
            PrintWriter pri = new PrintWriter(new BufferedWriter(output));
            pri.print("No."+number + " : ");
            //n[i]の階乗を求める
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            pri.println(result);
            pri.close();
            System.out.print("No."+number + " : ");
            System.out.println(result);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }
}
