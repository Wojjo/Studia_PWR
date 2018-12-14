using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Linq;
using System.Text.RegularExpressions;

namespace PEAII_TS
{
    class World
    {
        public int[,] Matrix { get; set; }
        public int Size { get; set; } = 0;
        public int Time { get; set; } = 1;
        public void StartProgram()
        {
            bool menu = true;
            while (menu)
            {
                menu = Menu();
            }
        }

        private bool Menu()
        {
            Console.Clear();
            Console.WriteLine("Menu glowne: \n" +
                               "-------------------\n" +
                               "1. Wczytaj macierz\n" +
                               "2. Wyswietl macierz\n" +
                               "3. Kryterium stopu\n" +
                               "4. Algorytm TS\n" +
                               "0. Koniec programu\n");
            int value = Int32.Parse(Console.ReadLine());
            switch (value)
            {
                case 1:
                    ReadFromFile();
                    break;

                case 2:
                    showMatrix();
                    break;

                case 3:
                    Console.WriteLine("Wprowadz kryterium stopu");
                    Time = Int32.Parse(Console.ReadLine());
                    break;

                case 4:
                    Algorithm_TS algorithm = new Algorithm_TS(Matrix, Size, Time);
                    algorithm.StartAlgorithm();
                    break;

                case 0:
                    return false;

                default:
                    break;


            }
            return true;
        }

        public void ReadFromFile()
        {


            Console.WriteLine("Podaj nazwę pliku");
            string file = Console.ReadLine();

            try
            {
                using (StreamReader stream = new StreamReader("../../Data/" + file + ".txt"))
                {
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

                        int x = 0, y = 0;
                        Matrix = new int[Size, Size];

                        for (int i = 0; i < list.Count; i++)
                        {
                            if (list[i] != "")
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
                    Console.WriteLine("Wczytywanie zakonczone sukcesem");

                }

            }
            catch (Exception e)
            {
                Console.WriteLine("Blad - nie można otworzyć pliku");
                Console.WriteLine(e.Message);
                Console.ReadKey();
            }
        }

        public void showMatrix()
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j =0 ; j < Size; j++)
                {
                    Console.Write(Matrix[i, j] + " ");
                }
                Console.WriteLine();
            }
            Console.WriteLine("Kryterium stopu wynosi: " + Time + " sekund");
            Console.ReadKey();

        }

      
    }

}

       