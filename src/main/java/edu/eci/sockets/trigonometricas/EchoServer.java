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
		Double result = null;
		String operando = null;
		Integer var = null;
//		Fragmento de codigo hecho por Javier Vargas y Sebastian Goenaga
		while ((inputLine = in.readLine()) != null) {
			StringTokenizer stdInTok = new StringTokenizer(inputLine);

			String oper = stdInTok.nextToken();
			boolean flag;

			try {
				var = Integer.parseInt(oper);

				flag = true;
			} catch (Exception e) {
				flag = false;
				var = Integer.parseInt(stdInTok.nextToken());
				operando = oper;
			}

			if (flag == true) {
				if (operando.equals("cos")) {
					result = Math.cos(Math.toRadians(var));
				} else if (operando.equals("sin")) {
					result = Math.sin(Math.toRadians(var));
				} else if (operando.equals("tan")) {
					result = Math.tan(Math.toRadians(var));
				}
			}
			System.out.println("Mensaje: " + inputLine);

			outputLine = "Respuesta: " + result;
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