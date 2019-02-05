using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;
using Project2.Models;
using Project2.Models.Genetic;

namespace Project2
{
    class World
    {
        public int[,] Matrix { get; set; }
        private string MatrixName { get; set; } = "BRAK";
        public int Size { get; set; } = 0;
        public int Time { get; set; } = 20;
        public int Population { get; set; } = 30;
        public double Crossing { get; set; } = 0.8;
        public double Mutation { get; set; } = 0.01;
        public string Method { get; set; } = "twopoint";
        //onepoint / twopoint

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
            Console.WriteLine("Załadowana macierz: " + MatrixName);
            Console.WriteLine("Kryterium stopu: " + Time + "s");
            Console.WriteLine("Wielkość populacji początkowej: " + Population);
            Console.WriteLine("Współczynnik krzyżowania: " + Crossing);
            Console.WriteLine("Współczynnik mutacji: " + Mutation);
            Console.WriteLine("Metoda: " + Method + "\n");
            Console.WriteLine("------------------------------");
            Console.WriteLine("Wybierz opcję: \n" +
                              "1 -> Załaduj macierz \n" +
                              "3 -> Kryterum stopu \n" +
                              "4 -> Ustawienie wielkości populacji początkowej\n" +
                              "5 -> Ustawienie współczynnika mutacji\n" +
                              "6 -> Ustawienie współczynnika krzyzowania\n" +
                              "7 -> Wybór metody krzyżowania\n" +
                              "------------------------------\n" +
                              "8 -> Start Algorytmu\n" +
                              "0 -> Wyjscie \n");
            int value = Int32.Parse(Console.ReadLine() ?? throw new InvalidOperationException());

            switch (value)
            {
                case 1:
                    FileRead();
                    break;
 
                case 2:
                    break;

                case 3:
                    Console.WriteLine("Podaj kryterium stopu ");
                    Time = Int32.Parse(Console.ReadLine() ?? throw new InvalidOperationException());
                    break;

                case 4:
                    Console.WriteLine("Podaj wielkość populacji początkowej ");
                    Population = Int32.Parse(Console.ReadLine() ?? throw new InvalidOperationException());
                    break;
                case 5:
                    //norma współczynnik mutacji 0,01
                    Console.WriteLine("Podaj współczynnik mutacji ");
                    Mutation = Double.Parse(Console.ReadLine() ?? throw new InvalidOperationException());
                    break;

                case 6:
                    //norma współczynnik krzyżowania 0.8
                    Console.WriteLine("Podaj współczynnik krzyżowania ");
                    Crossing = Double.Parse(Console.ReadLine() ?? throw new InvalidOperationException());
                    break;
                case 7:
                    Console.WriteLine("Podaj metode krzyżowania ");
                    Console.WriteLine("onepoint/twopoint");
                    Method = Console.ReadLine();
                    break;
                case 8:
                    Console.Clear();
                    AlgorithmGenetic algorithmGenetic = new AlgorithmGenetic(Matrix, Size, Time, Population, Crossing, Mutation, Method);
                    algorithmGenetic.Start();
                    break;
                case 0:
                    return false;
                default:
                    break;
            }

            return true;
        }


        public void FileRead()
        {
            Console.WriteLine("Podaj nazwę pliku");
            MatrixName = Console.ReadLine();
         
            try
            {
                using (StreamReader stream = new StreamReader("../../Database/" + MatrixName + ".txt"))
                {
                    Console.WriteLine("Wczytywanie danych...");
                    List<string> list = new List<string>();
                    String line = stream.ReadLine();

                    if (line != null)
                    {
                        Size = Int32.Parse(line);
                        line = stream.ReadLine();
                        while (line != null)
                        {
                            string[] infoStrings = Regex.Split(line, @"\D+");
                            list.AddRange(infoStrings);
                            line = stream.ReadLine();
                        }

                        int x = 0;
                        int y = 0;
                        Matrix = new int[Size, Size];

                        for (int i = 0; i < list.Count; i++)                      
                        {
                            if(list[i] != "")
                            {
                                Matrix[x, y] = Int32.Parse(list[i]);
                                y++;
                                if (y == Size)
                                {
                                    y = 0;
                                    x++;
                                }
                            }
                        }
                    }

                    Console.WriteLine("...Wczytywanie zakończone.");
                }

            }
            catch (Exception e)
            {
                Console.WriteLine("Nie można otworzyć pliku");
                Console.WriteLine(e.Message);
                Console.ReadKey();
            }
        }

    }
}