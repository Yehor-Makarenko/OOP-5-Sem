package problems.problem1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import problems.problem1.person.Person;

public class Client {
  public void send(String name, int age) {
    try {      
      Person objectToSend = new Person(name, age);      
      Socket clientSocket = new Socket("localhost", 12345);      
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());      
      objectOutputStream.writeObject(objectToSend);      
      
      objectOutputStream.close();
      clientSocket.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
