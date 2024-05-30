package networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            String repmessage = "thanks!";
            byte[] bytesToSend = repmessage.getBytes();

            while (true) {
                // 受信パケットを作成
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("受信: " + message);

                // 演習６－イ
                System.out.println("sending msg is" + repmessage);
                InetAddress repnetaddress = receivePacket.getAddress();
                int repportnum = receivePacket.getPort();
                DatagramPacket repPacket2 = new DatagramPacket(bytesToSend, bytesToSend.length, repnetaddress,
                        repportnum);
                socket.send(repPacket2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
