package edu.eci;

import java.io.*;
import java.net.*;

public class URLReader {
	public static void main(String[] args) throws Exception {

		try {
			URL direccion = new URL("http://url.com:80/tutorial/index.html?name=URL#PRUEBA");
			System.out.println("protocol = " + direccion.getProtocol());
			System.out.println("authority = " + direccion.getAuthority());
			System.out.println("host = " + direccion.getHost());
			System.out.println("port = " + direccion.getPort());
			System.out.println("path = " + direccion.getPath());
			System.out.println("query = " + direccion.getQuery());
			System.out.println("filename = " + direccion.getFile());
			System.out.println("ref = " + direccion.getRef());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}
