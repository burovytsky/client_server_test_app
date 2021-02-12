package client_server_app;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
             DataInputStream reader = new DataInputStream(socket.getInputStream())) {
            String clientCommand;
            while (!socket.isOutputShutdown()) {
                if ((clientCommand = br.readLine()).isEmpty()) {
                    socket.close();
                    break;
                }
                writer.writeUTF(clientCommand);
                writer.flush();
                System.out.println("Client sent message " + clientCommand + " to server.");
                System.out.println("Server response" + reader.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
