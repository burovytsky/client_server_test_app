package client_server_app;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(3345)) {
            Socket client = server.accept();
            System.out.print("Connection accepted.");
            DataOutputStream writer = new DataOutputStream(client.getOutputStream());
            DataInputStream reader = new DataInputStream(client.getInputStream());
            CommandFinder commandFinder = new CommandFinder("commands.txt");
            InputFormatter inputFormatter = new InputFormatter();
            List<String> log = new ArrayList<>();
            String serverResponse;
            String clientMessage;
            while (!(clientMessage = reader.readUTF()).isEmpty()) {
                log.add("Client: " + clientMessage);
                if (commandFinder.checkInput(clientMessage)) {
                    serverResponse = commandFinder.getCommandAnswer(clientMessage);
                } else {
                    serverResponse = inputFormatter.createAnswer(clientMessage);
                }
                log.add(serverResponse);
                writer.writeUTF(serverResponse);
                save(log);

                writer.flush();

            }
            System.out.println("Client disconnected");
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void save(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("log.txt")))) {
            for (String str : log) {
                out.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}