import static org.junit.Assert.assertEquals;

import org.junit.Test;

import problems.problem1.Client;
import problems.problem1.Server;

public class Test1 {
  @Test
  public void testPersonReceiving() {
    Server server = new Server();
    Client client = new Client();
    server.open();
    client.send("John", 32);
    server.read();
    server.close();
    assertEquals("John", server.getPerson().getName());
    assertEquals(32, server.getPerson().getAge());
  }
}
