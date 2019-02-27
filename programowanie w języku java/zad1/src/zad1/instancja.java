package zad1;

import java.util.ArrayList;

public class instancja {
private static ArrayList<przedmiot> itemList;

public static ArrayList<przedmiot> getItemList() {
	return itemList;
}

public static void setItemList(ArrayList<przedmiot> itemList) {
	instancja.itemList = itemList;
}
}
