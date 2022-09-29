package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LecturaEscrituraFlujosByte {

	public static void main(String[] args) {
		try {
			leer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void leer() throws IOException {
		File f = new File("/home/lucia/Escritorio/repaso.txt");
		FileInputStream fis = new FileInputStream(f);
		int letra = fis.read();
		System.out.print((char)letra);

		while (letra != -1) {
			letra = fis.read();
			System.out.print((char)letra);
		}
		fis.close();
	}

	public static void escribirTiposPrimitivos() throws IOException {
		File f = new File("/home/lucia/Escritorio/datos.dat");
		FileOutputStream fos = new FileOutputStream(f);
		try {
			fos = new FileOutputStream(f);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(12);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void leerTiposPrimitivos() throws IOException {
		File f = new File("/home/lucia/Escritorio/datos.dat");
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);

		try{
			// Bucle infinito
			for (;;){
				System.out.println(dis.readInt());
			}
		}
		catch(EOFException e){
			dis.close();
		}
	}
}
