package com.wojcinowicz.aplikacja;

import java.util.Scanner;
import com.wojcinowicz.biblioteka.Options;

public class MainMenu {
	public static void menu() {

		Scanner scan = new Scanner(System.in);
		int off = 1;
		int choose;

		while (off != 0) {
			System.out.println("1. Wprowadz wyposazenie");
			System.out.println("2. Wprowadz prowiant");
			System.out.println("3. Wyswietl wyposazenie");
			System.out.println("4. Wyswietl prowiant");
			System.out.println("0. Zakoncz");
			choose = scan.nextInt();
			switch (choose) {
			case 1:
				Options.addItems();
				break;
			case 2:
				Options.addFood();
				break;
			case 3:
				Options.showItems();
				break;
			case 4:
				Options.showFood();
				break;
			case 0:
				off = 0;
				break;

			default:
				System.out.println("Wybierz dostepna opjce");
				choose = scan.nextInt();
			}
		}

	}

}