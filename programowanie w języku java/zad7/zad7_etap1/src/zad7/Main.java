package zad7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		Szyfr szyfr = new Szyfr();
		System.out.println("1. Wczytaj plik");
		System.out.println("2. Wyswietl");
		System.out.println("3. Zaszyfruj");
		System.out.println("4. Rozszyfruj");
		System.out.println("5. Wyjscie");

		int wyjscie = 0;
		Scanner scanner = new Scanner(System.in);
		String text = "";
		while (wyjscie != 1) {
			int wybor = scanner.nextInt();
			switch (wybor) {
			case 1:
				text = loadToFile();
				System.out.println("Wczytano!");
				System.out.println(text);
				break;
			case 2:
				System.out.println(text);
				break;
			case 3:
				text = szyfr.szyfruj(text);
				saveToFile(text);
				System.out.println(text);
				break;
			case 4:
				text = szyfr.odszyfruj(text);
				saveToFile(text);
				System.out.println("Odszyfrowano");
				System.out.println(text);
				break;
			case 5:
				wyjscie = 1;
				break;
			default:
				System.out.println("Wybierz dostepna opcje");
			}
		}

	}

	static String loadToFile() throws IOException {
		String text = "";
		String fileName = "test.txt";
		BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
		String linia = "";
		while ((linia = fileReader.readLine()) != null) {
			text += linia;
		}
		fileReader.close();
		return text;
	}

	static void saveToFile(String text) throws Exception {

		String fileName = "test.txt";
		String path = "D:/eclipse-workspace/zad7/test.txt";
		File file = new File(path);
		if (file.delete()) {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			pw.print(text);
			pw.close();
			System.out.println("Zaszyfrowano");
		} else {
			System.out.println("Operacja zakonczona niepowodzeniem.");
		}

		
	}

}
