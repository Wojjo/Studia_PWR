using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;

namespace PEAII_TS
{
    class Algorithm_TS
    {
        public int TabuValue { get; set; } = 10;
        double ms = 0;
        private int[,] Matrix { get; set; }
        private int Size { get; set; }
        private bool Test { get; set; }
        private int Time { get; set; }
        private int[,] Tabu { get; set; }
        private int[] Solution { get; set; }
        private int Value { get; set; }
        public int[] BestSolution { get; set; }
        public int BestSolutionValue { get; set; }
     

        Random random = new Random();
        Stopwatch timer = new Stopwatch();
        Stopwatch timer2 = new Stopwatch();


        public Algorithm_TS(int[,] matrix, int size, int time, bool test = false)
        {
            Size = size;
            Time = time;
            Test = test;

            init_Matrix(matrix);
            init_Tabu();
            Solution = new int[Size + 1];
            BestSolution = new int[Size + 1];
            BestSolutionValue = Int32.MaxValue;
           
            
        }
        
        private void init_Matrix(int[,] matrix)
        {
            Matrix = new int[Size, Size];
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    Matrix[i, j] = matrix[i, j];
                }
            }
        }
        // tworzenie listy tabu, na poczatku jest wypelniona samymi zerami
        private void init_Tabu()
        {
            Tabu = new int[Size, Size];
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    Tabu[i, j] = 0;
                }
            }
        }

        public void StartAlgorithm()
        {
            // wygenerowanie losowej sciezki
            InitRandomSolution();
            timer.Reset();       
            timer2.Reset();
            timer.Start();
            timer2.Start();
            // petla wykonywana az do osiagniecia kryterium stopu
            while (Time > (timer.Elapsed.Seconds) + (timer.Elapsed.Minutes * 60))
            {
                getBestNeighbor();
            }
            timer.Stop();

            if (!Test)
            {
                if (Value < BestSolutionValue)
                {
                    ShowResult(Solution);
                }
                else
                {
                    ShowResult(BestSolution);
                }
            }
        }

        // losowe wygenerowanie sciezki
        private void InitRandomSolution()
        {
            List<int> list = new List<int>();
            for (int i = 1; i < Size; i++)
            {
                list.Add(i);
            }
            //wierzcholek poczatkowy i koncowy jest wierzcholkiem zero
            Solution[0] = 0;
            Solution[Size] = 0;
            // losowanie kolejnych wierzcholkow
            for (int i = 1; i < Size; i++)
            {
                int selected = random.Next(0, list.Count);
                int selectedValue = list[selected];

                Solution[i] = selectedValue;
                list.Remove(selectedValue);
            }

            // obliczenie kosztu wylosowanej sciezki
            Value = getValue(Solution);
        }

        private void getBestNeighbor()
        {
            Neighbor bestNeighbor = new Neighbor(Size);
            int[] newSolution = new int[Size + 1];
            int number1, number2;
            int created = 0;
            // zasieg od pierwszego do ostatniego wierzcholka oprocz zerowego
            int rangeFrom = 1;
            int rangeTo = Size - 1;
            
            // szukanie sąsiadów i wybór najlepszego
            for (int i = 0; i < Size; i++)
            {
                do
                {
                    // losowanie wierzcholkow 
                    number1 = random.Next(rangeFrom, rangeTo);
                    number2 = random.Next(rangeFrom, rangeTo);
                } while (number1 == number2);
                // sprawdzenie czy wierzcholki sa na liscie tabu
                if (CheckInTabu(number1, number2))
                {
                    created++;
                    // generowanie nowej sciezki
                    generateNewSolution(newSolution, number1, number2);
                    // obliczenie kosztu
                    int newValue = getValue(newSolution);
                   
                    if (newValue < bestNeighbor.Value)
                    {
                        bestNeighbor.ChangeBestNeighbor(number1, number2, newSolution, newValue);
                    }
                }
            }
            if (created > 0)
            {
                // sprawdzenie czy nowa sciezka ma nizszy koszt od poprzedniej
                if (BestSolutionValue > Value)
                {
                    // przypisanie nowej sciezki o najnizszym koszcie
                    for (int j = 0; j < Size + 1; j++)
                    {
                        BestSolution[j] = Solution[j];
                    }
                    // przypisanie nowego najnizszego kosztu 
                    BestSolutionValue = Value;
                }

                for (int j = 0; j < Size + 1; j++)
                {
                    Solution[j] = bestNeighbor.Solution[j];
                }
               
                Value = bestNeighbor.Value;

                // zmiejszenie tabu i dodanie nowych wierzcholkow do listy tabu
                DecreaseTabu();
                AddToTabu(bestNeighbor.Number1, bestNeighbor.Number2);
            }
            else
            {
                DecreaseTabu();
            }


        }
        // dodanie wierzcholkow do listy tabu
        private void AddToTabu(int number1, int number2)
        {
            Tabu[number1, number2] = TabuValue;
            Tabu[number2, number1] = TabuValue;
        }
        // sprawdzenie czy wierzcholki sa na liscie tabu
        private bool CheckInTabu(int number1, int number2)
        {
            if (Tabu[number1, number2] > 0) return false;
            return true;
        }

        // zmniejszenie wartosci w liscie tabu tam gdzie wartosci sa wieksze od zera
        private void DecreaseTabu()
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    if (Tabu[i, j] - 1 > -1) Tabu[i, j]--;
                }
            }
        }

       
        // generowanie nowej sciezki
        private void generateNewSolution(int[] newSolution, int number1, int number2)
        {
            // przypisanie sciezki 
            for (int i = 0; i < Size + 1; i++)
            {
                newSolution[i] = Solution[i];
            }
            // odwrocenie miejscami wierzcholkow
            int temp = newSolution[number1];
            newSolution[number1] = newSolution[number2];
            newSolution[number2] = temp;
        }
    
     
        // wyswietlenie koncowego wyniku
        public void ShowResult(int[] solution)
        {
            timer2.Stop();
            ShowTime();
            Console.WriteLine("Rezultat: \n");
            for (int i = 0; i < Size; i++)
            {
                Console.Write(solution[i] + " --> ");
            }
            Console.Write(0);

            Console.WriteLine("\nKoszt: " + getValue(solution));
            Console.ReadKey();
        }

        public void ShowTime()
        {
            long ticks = timer2.ElapsedTicks;
            double ns = 1000000000.0 * (double)ticks / Stopwatch.Frequency;
            ms = ns / 1000000.0;
            Console.WriteLine();
            Console.WriteLine("Czas dzialania programu: " + ms + " ms");
        }

        private int getValue(int[] solution)
        {
            int result = 0;
            for (int i = 0; i < Size; i++)
            {
                result += Matrix[solution[i], solution[i + 1]];
            }
            return result;
        }
    }
}



