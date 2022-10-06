package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoRandom {

	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("datos.dat", "rw");
			raf.writeInt(69);
			raf.seek(0);
			System.out.println(raf.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
