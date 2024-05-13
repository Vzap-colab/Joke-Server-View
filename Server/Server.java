package ChatServer.Server.Server;

import ChatServer.Client.UserDAO.Methods;
import ChatServer.Server.ClientHandeler.ClientHandler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

import static ChatServer.Client.UserDAO.DBConfig.username;

public class Server {
    public static CopyOnWriteArrayList<PrintWriter> clientOutputStreams = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4545);
            System.out.println("Chat Server running");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                ClientHandler clientHandler = new ClientHandler(clientSocket, writer);
                clientHandler.run();
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}