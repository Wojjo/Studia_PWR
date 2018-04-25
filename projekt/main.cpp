/*Program obliczaj¹cy sumê, iloraz, iloczyn, ró¿nicê u³amków  */


#include <iostream>
#include "ulamek.h"
#include <string>
#include <sstream>
 using namespace std;


 
 void historia(string x, string y, int ile)
 {
 	int w;
 	w=ile;
 	int k;
 	k=w-1;
	  	
	cout << "Ulamek pierwszy: ";
	string* tab = new string [w]; //alokacja pamieci
	for ( k; k < w; k++) //wpisanie wartosci do tablicy
	{		
	tab[k]=x;
	}
	//wypisz tab[w]
	for ( int i=0 ; i < w; i++)
	{
	cout<<tab[i]<<'\t';
	}
	cout<<endl;
    cout << "Ulamek drugi: ";
    
 	string* tab2 = new string [w]; //alokacja pamieci
	for ( int i = 0; i < w; i++) //wpisanie wartosci do tablicy
	tab2[i]=y;
	//wypisz tab[w]
	for ( int i = 0; i < w; i++)
	cout<<tab2[i]<<'\t';
 	cout<<endl;
	
    
	
 }
 
 
 
 
 
 

int main()
{
	string ulamek1;
	string ulamek2;
	int ile = 0;
	char odpowiedz;
    cout << " Czy chcesz wprowadzic ulamki? t/n " << endl;
poczatek:	cin >> odpowiedz;
	
	while(odpowiedz=='t')
	{
	
		
		cout << " Podaj licznik nastepnie mianownik " << endl;
		int l1,l2,m1,m2;
		cin >> l1 >> m1;
		cout << " Podaj licznik nastepnie mianownik " << endl;
		cin >> l2 >> m2;
		ile++;
    	Ulamek u1( l1, m1 );
    	Ulamek u2( l2, m2 );
    	u1.wypisz();
    	u2.wypisz();
    	cout << "Wynik dodawania dwoch ulamkow = ";
    	u1 + u2;
    	cout << "Wynik odejmowania dwoch ulamkow: ulamek 1 - ulamek 2 = ";
    	u1 - u2;
    	cout << "Wynik odejmowania dwoch ulamkow: ulamek 2 - ulamek 1 = ";
    	u2 - u1;
    	cout << "Wynik mnozenia dwoch ulamkow = ";
    	u1 * u2;
    	cout << "Wynik dzielenia dwoch ulamkow: ulamek 1/ulamek 2 = ";
    	u1 / u2;
    	cout << "Wynik dzielenia dwoch ulamkow: ulamek 2/ulamek 1 = ";
    	u2 / u1;
    		   
			ostringstream licznik1;
			licznik1 << l1;
			string str = licznik1.str();
			
		
			ostringstream mianownik1;
			mianownik1 << m1;
			string str1 = mianownik1.str();   
			
			
			ostringstream licznik2;
			licznik2 << l2;
			string str2 = licznik2.str();   
				
			ostringstream mianownik2;
			mianownik2 << m2;
			string str3 = mianownik2.str();   	
			
			ulamek1= licznik1.str() + "/" + mianownik1.str();
			ulamek2= licznik2.str() + "/" + mianownik2.str();
			
			historia(ulamek1,ulamek2,ile);
			
			goto poczatek;
			
			
		
    	
	}
	if (odpowiedz=='n')
	{
		cout << "Koniec programu" << endl;
		
	}
	

	
 
}