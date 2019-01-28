package edu.eci.sockets.trigonometricas;

import java.net.*;
import java.io.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(35000);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 35000.");
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
			String operation = st.nextToken();
			int oprnd1 = Integer.parseInt(st.nextToken());
			if (operation.equals("cos")) {
				result = Math.cos(oprnd1 * (Math.PI / 180.0));
			}

			else if (operation.equals("sin")) {
				result = Math.sin(oprnd1 * (Math.PI / 180.0));
			} else {
				result = Math.tan(oprnd1 * (Math.PI / 180.0));
			}
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