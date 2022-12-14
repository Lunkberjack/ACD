package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaImagen {
	public static void main(String[] args) {
		File imagen = new File("/home/lucia/Escritorio/copia - amogus.png");
		try {
			copiarImagen(imagen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void copiarImagen(File imagen) throws IOException {
			FileInputStream fis = new FileInputStream(imagen);
			File copia = new File(imagen.getParent() + "/copia - " + imagen.getName());
			copia.createNewFile();
			
			try {
				FileOutputStream fos = new FileOutputStream(copia);
				DataOutputStream dos = new DataOutputStream(fos);
				int digit = fis.read();
				dos.write(digit);
			
				while(digit != -1) {
					digit = fis.read();
					dos.write(digit);
					System.out.println(digit);
				}
				fis.close();
				dos.close();
				
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
}
