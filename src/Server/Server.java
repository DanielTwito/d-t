package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Aviadjo on 3/2/2017.
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket clientSocket = server.accept(); // blocking call
                    LOG.info(String.format("Client excepted: %s", clientSocket.toString()));
                    new Thread(() -> {
                        handleClient(clientSocket);
                    }).start();
                } catch (SocketTimeoutException e) {
                    LOG.debug("SocketTimeout - No clients pending!");
                }
            }
            server.close();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            LOG.debug("Client excepted!");
            LOG.debug(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.getInputStream().close();
            clientSocket.getOutputStream().close();
            clientSocket.close();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    public void stop() {
        LOG.info("Stopping server..");
        stop = true;
    }
}
