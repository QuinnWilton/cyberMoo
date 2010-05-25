package cybermoo;

public class Main {

  public static void main(String[] args) {
    Server server = Server.getInstance(8080);
    server.startListening();
  }
}