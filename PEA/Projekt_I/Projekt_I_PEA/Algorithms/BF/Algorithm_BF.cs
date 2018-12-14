using System;


namespace Projekt_I_PEA.Algorithms.BF
{
    class Algorithm_BF
    {
       
        public int[,] Matrix { get; set; }  // tablica dwuwymiarowa - Macierz 
        public int Size { get; set; }       // ilosc wierzcholkow - rozmiar macierzy size x size
        public int[] Solution;              // rozwiazanie problemu - droga
        public int ValueSolution = Int32.MaxValue; // koszt drogi 
        public int[] Posibilities;          // mozliwe rozwiazanie    

        public Algorithm_BF(int[,] matrix, int size, bool test = false)
        {
            Size = size;
            Matrix = new int[size, size];
            Matrix = matrix;
            Solution = new int[size];
            Posibilities = new int[size];          
        }

        public void Start()
        {
            for (int i = 0; i < Size; i++)
            {
                Posibilities[i] = i;                   // wpisanie wszystkich wierzcholkow
            }
            
            Permut(Posibilities, 1, Size - 1);        // mozliwe permutacje wierzcholkow. Size - 1, bo miasto poczatkowe jest stałe
            ShowSolution();                          // wyswietlenie koncowych wynikow
        }
       
      
        public void Permut(int[] list, int current, int size)
        {
            int i;
              
            if (current == size)
            {
                isSolution(list);    // sprawdzono wszystkie wierzcholi - jest rozwiazanie
            }
            else
            {
                for (i = current; i <= size; i++)
                {
                    swapNumbers(ref list[current], ref list[i]);
                    Permut(list, current + 1, size);
                    swapNumbers(ref list[current], ref list[i]);
                   
                }
            }
        }
        // Odwracamy dwie liczby  
        // np 0 -> 1 -> 2 -> 0 oraz 0 -> 2 -> 1 -> 0

        public void swapNumbers(ref int a, ref int b)
        {
            int temp = a;
            a = b;
            b = temp;
        }
        // jesli znaleziono rozwiazanie
        private void isSolution(int[] list)
        {
            int result = 0;
            for (int i = 0; i < Size; i++)
            {
                // dodawanie kosztow drogi
                if (i + 1 != Size)
                {
                    result += Matrix[list[i], list[i + 1]];
                }
                // dodanie ostatniej wagi - powrot do wierzcholka startowego 
                else
                {
                    result += Matrix[list[i], 0];
                }
            }
            // jesli nowa droga ma nizsze koszty niz poprzednie 
            if (result < ValueSolution)
            {
                ValueSolution = result;        // przypisanie nowego nizszego kosztu drogi     
                for (int i = 0; i < Size; i++)
                {
                    Solution[i] = list[i];     // przypisanie nowej drogi
                }
            }

        }
        public void ShowSolution()
        {
            Console.WriteLine("Najkrótsza droga ma wartość: " + ValueSolution);

            for (int i = 0; i < Size; i++)
            {
                if (i == Size - 1)
                {
                    Console.Write(Solution[i] + " --> 0"); 
                }
                else
                {
                    Console.Write(Solution[i] + " --> ");   
                }
            }
        }
    }

}

