using System;
using System.IO;
using System.Diagnostics;
using Projekt_I_PEA.Algorithms.BF;
using Projekt_I_PEA.Algorithms.BAndB;

namespace Projekt_I_PEA
{
    class World
    {
        public int[,] Matrix { get; set; }          // tablica dwuwymiarowa - macierz
        public int Size { get; set; } = 0;  
        Stopwatch stopwatch = new Stopwatch();      // stoper
        double ms = 0;                              // wynik pomiaru jest w ms 
        
        public void RunProject()
        {
            bool show = true;
            while (show)
            {
                show = MainMenu();
            }
        }

        private bool MainMenu()
        {
            
            Console.Clear();
            Console.WriteLine("Menu glowne: \n" +
                              "------------------------\n" +
                              "1 -> Załaduj Macierz \n" +
                              "2 -> Wyświetl Macierz \n" +
                              "------------------------\n" +
                              "3 -> Algorytm BF\n" +
                              "4 -> Algorytm B&B \n" +
                              "------------------------\n" +
                              "0 -> Wyjscie \n" );
            int value = Int32.Parse(Console.ReadLine() ?? throw new InvalidOperationException());

          
            switch (value)
            {

                case 1:
                    FileRead();
                    break;
                case 2:
                    ShowMatrix();
                    break;
                case 3:
                    stopwatch.Reset();
                    Algorithm_BF algorithm_BF = new Algorithm_BF(Matrix, Size);
                    stopwatch.Start();
                    algorithm_BF.Start();
                    stopwatch.Stop();
                    ShowTime();
                    break;
                case 4:
                    stopwatch.Reset();
                    AlgorithmBAndB algorithm_BAndB = new AlgorithmBAndB(Matrix, Size);
                    stopwatch.Start();
                    algorithm_BAndB.Start();
                    stopwatch.Stop();
                    ShowTime();
                    break;
                case 0:
                    System.Diagnostics.Process.GetCurrentProcess().Kill();
                    break;
            }
            return true;
        }

        public void FileRead()
        {
            Console.WriteLine("Podaj nazwę pliku");
            string fileName = Console.ReadLine();

            try
            {
                using (StreamReader stream = new StreamReader("../../Data/" + fileName + ".txt"))
                {
                    Console.WriteLine("Wczytuje dane ");
                    String line = stream.ReadLine();
                    if(line != null)
                    {
                        // size ilosc wierzcholkow
                        Size = Int32.Parse(line);   // Przekształcenie danych z typu string na int
                        Matrix = new int[Size, Size];  // Macierz wielkości ilosc wierzcholkow x ilosc wierzcholkow

                        for (int i=0; i < Size; i++) 
                        {
                            line = stream.ReadLine();
                            if(line != null)
                            {
                                string[] infoString = line.Split(' '); // spacja rozdziela liczby w pliku
                                for(int j = 0; j < Size; j++)
                                {                                              // i kolumny w macierzy j wiersze w macierzy
                                    Matrix[i, j] = Int32.Parse(infoString[j]); // wypełnienie macierzy danymi
                                }
                            }
                        }
                    }

                    Console.WriteLine("Wczytywanie danych zakończone sukcesem");
                }
                   
            }
            catch (Exception e)
            {
                Console.WriteLine("Blad wczytania danych");
                Console.WriteLine(e.Message);
                Console.ReadKey();
            }
        }


        public void ShowMatrix()
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    Console.Write(Matrix[i, j] + " ");
                }
                Console.WriteLine();
            }
            Console.ReadKey();
        }


        public void ShowTime()
        {
            long ticks = stopwatch.ElapsedTicks;
            double ns = 1000000000.0 * (double)ticks / Stopwatch.Frequency;
            ms = ns / 1000000.0;
            Console.WriteLine();
            Console.WriteLine(ms + " ms");
            Console.ReadLine();
        }
    }
}
