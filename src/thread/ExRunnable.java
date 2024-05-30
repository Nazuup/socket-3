package thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ExRunnable implements Runnable {
    private int[] matA;
    private int[] matB;
    private int result;
    private int row;
    private int col;

    public ExRunnable(int[] a, int[] b, int i) {
        this.matA = a;
        this.matB = b;
        if((i+1)%3==0){
            this.row = 3;
        }else{
            this.row = (i+1)%3;
        }
        this.col = i/3;
    }

    @Override
    public void run() {
        try{
            String file = "6_D_Result.txt";
            FileWriter output = new FileWriter(file, true);
            PrintWriter pri = new PrintWriter(new BufferedWriter(output));
            for(int i = 0; i<matA.length; i++){
                result += matA[i]*matB[i];
            }
        
        System.out.print("col "+col+", row "+row+" : "+result);
        pri.println("col "+col+", row "+row+" : "+result);
        pri.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public int getNumber(){
        return result;
    }

}