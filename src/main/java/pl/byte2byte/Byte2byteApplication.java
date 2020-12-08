package pl.byte2byte;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Spring Boot start class.
 * @author Jakub Kalinowski
 */

@SpringBootApplication
public class Byte2byteApplication {

  /**
   * The main apllication method.
   * @param args
   * @throws IOException
   */

  public static void main(String[] args) throws IOException {
    SpringApplication.run(Byte2byteApplication.class, args);
    openHomePage();
  }

  /**
   * This method starts application in a local web browser.
   * @throws IOException
   */

  private static void openHomePage() throws IOException {
    Runtime rt = Runtime.getRuntime();
    rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost");
  }
}