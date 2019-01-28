package edu.eci.sockets.cudrados;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35002);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35002.");
			System.exit(1);
		}
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine, outputLine;
		while ((inputLine = in.readLine()) != null) {
			double result;
			StringTokenizer st = new StringTokenizer(inputLine);
			int oprnd1 = Integer.parseInt(st.nextToken());
			result = Math.pow(oprnd1, 2);
			System.out.println("Mensaje: " + inputLine);
			outputLine = "Respuesta: " + Double.toString(result);
			out.println(outputLine);
			if (outputLine.equals("Respuesta:  "))
				break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}
