package edu.eci.sockets.webserver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HttpServerNoConcurrente {
	public static void main(String[] args) throws IOException {
		while (true) {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(35000);
			} catch (IOException e) {
				System.err.println("Could not listen on port: 35000.");
				System.exit(1);
			}
			PrintWriter out;
			BufferedReader in;
			Socket clientSocket;

			clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			while (!clientSocket.isClosed()) {
				out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8),
						true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String inputLine, totalInput = "";
				while ((inputLine = in.readLine()) != null) {
					totalInput = totalInput + "\n" + inputLine;
					System.out.println("Received: " + inputLine);
					if (inputLine.contains("GET")) {
						String[] tempArray = inputLine.split(" ");
						String path = System.getProperty("user.dir") + "/web" + tempArray[1];
						BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader(path));

						} catch (Exception e) {
							out.println("HTTP/1.1 404 Not Found");
							out.println("Content-Type: text/html");
							System.out.println("Not found");
							e.printStackTrace();
						}
						if (path.contains(".html")) {
							out.write("HTTP/1.1 200 OK");
							out.println("Content-Type: text/html");
							out.println();
							String temp = br.readLine();
							while (temp != null) {
								out.write(temp);
								temp = br.readLine();
							}

							br.close();
						} else if (path.contains(".png")) {
							out.write("HTTP/1.1 200 OK");
							out.println("Content-Type: image/png");
							out.println();
							BufferedImage image = ImageIO
									.read(new File(System.getProperty("user.dir") + "/web" + tempArray[1]));
							ImageIO.write(image, "PNG", clientSocket.getOutputStream());

						}

						out.close();
					}
					if (!in.ready()) {
						break;
					}
				}
				in.close();
			}
			clientSocket.close();
			serverSocket.close();
		}
	}

}