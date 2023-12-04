package problems.problem1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import problems.problem1.person.Person;

public class Server {
  ServerSocket serverSocket;
  Person person = null;

  public void open() {
    try {
      serverSocket = new ServerSocket(12345);
    } catch (IOException e) {      
      e.printStackTrace();
    }
  }

  public void read() {
    Socket clientSocket;
    try {
      clientSocket = serverSocket.accept();
      ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
      person = (Person) objectInputStream.readObject();

      objectInputStream.close();
      clientSocket.close();
    } catch (IOException | ClassNotFoundException e) {      
      e.printStackTrace();
    }                        
  }

  public void close() {
    try {
      serverSocket.close();
    } catch (IOException e) {      
      e.printStackTrace();
    }
  }

  public Person getPerson() {
    return person;
  }

  public void print() {
    System.out.println("Get person from client:\n" + person);
  }
}
