package app.ledprojekt.server;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.ledprojekt.Main;
import app.ledprojekt.PlayerManager;
import app.ledprojekt.input.CustomKeyEvent;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetAddress;

public class Server implements Runnable {
    private static final int PORT = 8000;
//    static final File PUBLIC_DIR = new File(Server.class.getPackageName().replace(".", "/") + "/public");
    private static final File PUBLIC_DIR = new File("src/app/ledprojekt/server/public/");

    private static List<String> lockedPlayers = new ArrayList<>();

    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/", new DefaultHandler());
            server.createContext("/command", new CommandHandler());
            server.createContext("/players", new PlayersHandler());
            server.setExecutor(null);
            server.start();

            System.out.println("Server started at:");
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.getHostAddress() + ":" + PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DefaultHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if (!t.getRequestMethod().equals("GET")) {
                Server.sendMethodNotAllowed(t);
            }
            
            String path = t.getRequestURI().getPath();
            File file = new File(PUBLIC_DIR + ((path.equals("/")) ? "/index.html" : path));

            if (file.exists()) {
                t.sendResponseHeaders(200, file.length());
                OutputStream os = t.getResponseBody();
                Files.copy(file.toPath(), os);
                os.close();
            } else {
                Server.sendFileNotFound(t);
            }
        }
    }

    private class CommandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String method = t.getRequestMethod();
            if (!method.equals("POST")) {
                Server.sendMethodNotAllowed(t);
            }

            String query = t.getRequestURI().getQuery();
            if (query != null) {
                String parsedCommand = query.replace("command=", "").replace("player=", "").replace("+", " ");
                String[] splitCommand = parsedCommand.split("&");
                //System.out.println(Arrays.toString(splitCommand));

                if (splitCommand[0].equals("p1")) {
                    for (String key : PlayerManager.keybindingsP1.keySet()) {
                        if (key.equals(splitCommand[1])) {
                            //System.out.println(String.format("Key: %s, KeyEvent.VK-Nummer: %d", key, PlayerManager.keybindingsP1.get(key)));
                            Main.buffer.put(new CustomKeyEvent(PlayerManager.keybindingsP1.get(key), KeyEvent.KEY_RELEASED));
                        }
                    }
                } else if (splitCommand[0].equals("p2")) {
                    for (String key : PlayerManager.keybindingsP2.keySet()) {
                        if (key.equals(splitCommand[1])) {
                            //System.out.println(String.format("Key: %s, KeyEvent.VK-Nummer: %d", key, PlayerManager.keybindingsP2.get(key)));
                            Main.buffer.put(new CustomKeyEvent(PlayerManager.keybindingsP2.get(key), KeyEvent.KEY_RELEASED));
                        }
                    }
                }
            }

            String response = "OK\n";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private class PlayersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "OK\n";

            String method = t.getRequestMethod();
            if (!method.equals("POST")) {
                Server.sendMethodNotAllowed(t);
            }

            String query = t.getRequestURI().getQuery();
            if (query != null) {
                String parsedCommand = query.replace("command=", "");
                if (parsedCommand.equals("info")) {
                    response = Arrays.toString(lockedPlayers.toArray());

                    t.sendResponseHeaders(200, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }

                parsedCommand = parsedCommand.replace("player=", "").replace("+", " ");
                String[] splitCommand = parsedCommand.split("&");
                //System.out.println(Arrays.toString(splitCommand));
                if (splitCommand[0].equals("lock")) {
                    lockedPlayers.add(splitCommand[1]);
                    //System.out.println(Arrays.toString(lockedPlayers.toArray()));
                }

                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private static void sendMethodNotAllowed(HttpExchange t) throws IOException {
        String response = "Method not allowed\n";
        t.sendResponseHeaders(405, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    private static void sendFileNotFound(HttpExchange t) throws IOException {
        String response = "File not found\n";
        t.sendResponseHeaders(404, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}