package networking.udp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class kadaiAClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = null;
        String output = "6A_Result.txt";
        BufferedWriter fileWrite = new BufferedWriter (new FileWriter(output));
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] repReceiveData = new byte[1024];
            Scanner scanner = new Scanner(System.in);

            //送信文入力
            System.out.println("小文字のアルファベットの文字列を入力してください");
            String message = scanner.nextLine();
            fileWrite.write("送信："+message);
            fileWrite.newLine();
            byte[] sendData = message.getBytes();

            // 送信パケットを作成
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            socket.send(sendPacket);

            // 演習６－イ↓
            DatagramPacket receivePacket = new DatagramPacket(repReceiveData, repReceiveData.length);
            socket.receive(receivePacket);
            String repmessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("返信: " + repmessage);
            fileWrite.write("返信："+repmessage);
            fileWrite.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
