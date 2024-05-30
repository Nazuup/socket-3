package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CountAZTenRunnable implements Runnable {
    String myAlfabetStr = "noalfabet";
    static String file = "6_C_Result.txt";

    public static void main(String[] args) throws IOException {
        FileWriter output = new FileWriter(file, false);
        PrintWriter pri = new PrintWriter(new BufferedWriter(output));
        char[] c = new char[23];
        CountAZTenRunnable[] cts = new CountAZTenRunnable[23];
        Thread[] th = new Thread[23];
        // 2つの文字を初期化
        char c1 = 97; // ASCII値 97 は 'a'
        char c2 = (char) (c1 + 1);
        char c3 = (char) (c1 + 2);
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c1 + 3 + i);
        }

        // 初期化した文字をコンソールに出力
        pri.println(c1); // 出力: a
        pri.println(c2); // 出力: b
        pri.println(c3); // 出力: c
        for (int i = 0; i < c.length; i++) {
            pri.println(c[i]);
        }

        // CountTenRunnableクラスのインスタンス作成
        CountAZTenRunnable ct1 = new CountAZTenRunnable();
        CountAZTenRunnable ct2 = new CountAZTenRunnable();
        CountAZTenRunnable ct3 = new CountAZTenRunnable();

        ct1.setAlfabet(c1 + "thread");
        ct2.setAlfabet(c2 + "thread");
        ct3.setAlfabet(c3 + "thread");
        for (int i = 0; i < c.length; i++) {
            cts[i] = new CountAZTenRunnable();
            cts[i].setAlfabet(c[i] + "thread");
        }

        // スレッド作成
        Thread th1 = new Thread(ct1, "th-1");
        Thread th2 = new Thread(ct2, "th-2");
        Thread th3 = new Thread(ct3, "th-3");
        for (int i = 0; i < c.length; i++) {
            int number = i + 4;
            th[i] = new Thread(cts[i], "th-" + number);
        }

        // スレッド開始
        pri.println("th1.getName() : " + th1.getName());
        th1.start();
        pri.println("th2.getName() : " + th2.getName());
        th2.start();
        pri.println("th3.getName() : " + th3.getName());
        th3.start();
        for (int i = 0; i < c.length; i++) {
            int number = i + 4;
            pri.println("th" + number + ".getName() : " + th[i].getName());
            th[i].start();
        }
        pri.close();

        //
        try {
            th1.join();
            th2.join();
            th3.join();
            for (Thread thread : th) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // この try-catch ブロックは、0 から 9 までの値を 500 ミリ秒間隔で出力するループを実行します。
        // try {
        // for (int i = 0; i < 10; i++) {
        // System.out.println("main:i=" + i);

        // // メインスレッドを 500 ミリ秒間一時停止します。
        // Thread.sleep(500); // ミリ秒単位のスリープ時間
        // }
        // } catch (InterruptedException e) {
        // // スレッドが中断された場合は、例外を出力します。
        // System.err.println(e);
        // }
    }

    public void setAlfabet(String alfabetstr) {
        myAlfabetStr = alfabetstr;
    }

    public void run() {
        // 0 から 9 までの値を 1000 ミリ秒間隔で出力する
        try {
            FileWriter output = new FileWriter(file, true);
            PrintWriter pri = new PrintWriter(new BufferedWriter(output));
            for (int i = 0; i < 10; i++) {
                pri.println("runnable thread:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止
                Thread.sleep(1000); // ミリ秒単位のスリープ時間
            }
            pri.close();
        } catch (InterruptedException e) {
            // 例外処理
            System.err.println(e);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }
}
