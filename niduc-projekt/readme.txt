Krótki poradnik jak używać programu we właściwy sposób:
1.  Należy wczytać plik wybierając z menu opcje nr 1. Nazwa pliku musi być prawidłowa, wraz z rozszerzeniem (np lena.bmp).
    Program do działania używa monochromatycznych map bitowych (.bmp) o głębi bitowej 8, których rozmiar nie przekracza miliona pikseli
    (bo pakiety mają 20 bitów indeksu, obliczenia z większymi wielkościami nie były testowane).
    Jeśli wszystkie wymagania zostaną spełnione obraz wczyta się pomyślnie. Niestety w związku z ilością obliczeń zajmuje to chwilę (zwykle kilka sekund)

2.  Należy podać szansę odwrócenia bitu w pakiecie w procentach (0-100). Dozwolone są wartości niecałkowite (np 1.7). Jeśli dane będą prawidłowe program je zaakceptuje i będzie można przejść dalej.

3.  Testy. Można wybrać algorytm przesyłania StopAndWait lub GoBackN. W trakcie procesu będą wypisywane dane informujące co się w danym momencie dzieje.

4.  Kiedy cały obraz zostanie przesłany program powróci do menu. Można teraz albo wybrać opcję nr 5 i zapisać plik
    (zostanie utworzony plik sent(nazwa_wczytanego_pliku).bmp który pokaże w jakim stopniu doszło do uszkodzeń obrazu).
    Można również wybrać opcję nr 6 i wyświetlić dane jakie zostały zebrane podczas procesu.

5.  Jeśli chce się uruchomić proces przysyłania jeszcze raz należy ponownie uruchomić program. Nie zdążyliśmy zaprogramować tego w prawidłowy sposób tak, aby wszystko działało bez resetów. Z góry za to przepraszamy.