package edu.eci;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Browser {
	public static void main(String[] args) throws Exception {
		Scanner sc =new Scanner(System.in);
		String url;
		url = sc.nextLine();
		URL paginas = new URL(url);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(paginas.openStream()))) {
			BufferedWriter wr = new BufferedWriter(new FileWriter("pruebas.html"));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				wr.write(inputLine);
			}
			wr.close();
		} catch (IOException x) {
			System.err.println(x);

		}
	}

}