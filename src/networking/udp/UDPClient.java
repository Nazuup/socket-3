package networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            String message = "Hello, server!";
            byte[] sendData = message.getBytes();
            byte[] repReceiveData = new byte[1024];

            // 送信パケットを作成
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            socket.send(sendPacket);

            // 演習６－イ↓
            DatagramPacket receivePacket = new DatagramPacket(repReceiveData, repReceiveData.length);
            socket.receive(receivePacket);
            String repmessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("返信: " + repmessage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
