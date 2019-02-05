using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Project2.Models.Genetic
{
    class AlgorithmGenetic
    {
        private int[,] Matrix { get; set; }
        private int Size { get; set; }
        private int Time { get; set; }
        private int PopulationValue { get; set; }
        private double Crossing { get; set; }
        private double Mutation { get; set; }
        private string Method { get; set; }

        private PopulationItem BestSolution { get; set; }

        private List<PopulationItem> Population { get; set; }

        Random random = new Random();
        Stopwatch stopwatch = new Stopwatch();

        public AlgorithmGenetic(int[,] matrix, int size, int time, int population, double crossing, double mutation, string method)
        {
            Size = size;
            Time = time;
            PopulationValue = population;
            Crossing = crossing;
            Mutation = mutation;
            Method = method;

            initMatrix(matrix);

            Population = new List<PopulationItem>();
        }

        private void initMatrix(int[,] matrix)
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

        public void Start()
        {
            stopwatch.Reset();
            stopwatch.Start();
            GenerateStartPopulation();

            while (Time > (stopwatch.Elapsed.Seconds) + (stopwatch.Elapsed.Minutes * 60))
            {
                Evoluation(); // operacje ewolucyjne - krzyzowanie oraz mutacje
                GenerateNewPopulation(); //wybór najlepszej x najlepszej populacji
            }
            stopwatch.Stop();

            ShowRezult();
        }

        private void GenerateStartPopulation()
        {
            for (int i = 0; i < PopulationValue; i++)
            {
                Population.Add(RandomPopulation());
            }
            SortPopulation();
            BestSolution = new PopulationItem(Population[0].Solution, Population[0].Value);
        }

        private void GenerateNewPopulation()
        {
            SortPopulation();
            int CountToRemove = Population.Count() - PopulationValue;
            Population.RemoveRange(PopulationValue,CountToRemove);
        }

        private void SortPopulation()
        {
            Population.Sort((x, y) => x.Value.CompareTo(y.Value));
        }


        private void Evoluation()
        {

            //Krzyzowanie
            List<PopulationItem> EvolvePopulation = new List<PopulationItem>();
            for (int i = 0; i < PopulationValue; i++)
            {
                for (int j = 0; j < PopulationValue; j++)
                {
                    if(i != j)
                    {
                        if (random.NextDouble() < Crossing)
                        {
                            EvolvePopulation.Add(CrossingMetod(i, j));
                        }
                    }
                }
            }

            Population.AddRange(EvolvePopulation);

            //Mutacja
            for (int i = 0; i < Population.Count(); i++)
            {
                if (random.NextDouble() < Mutation)
                {
                    int element1, element2;
                    do
                    {
                        element1 = random.Next(1, Size - 1);
                        element2 = random.Next(1, Size - 1);
                    } while (element1 == element2);             
                    MutationMetod(i, element1, element2);
                }
            }
        }

        private void MutationMetod(int i, int element1, int element2)
        {
            int temp = Population[i].Solution[element1];
            Population[i].Solution[element1] = Population[i].Solution[element2];
            Population[i].Solution[element2] = temp;

            Population[i].Value = getValue(Population[i].Solution);
        }

        private PopulationItem CrossingMetod(int element1, int element2)
        {
            if(Method == "onepoint")
            {
                return OnePointMethod(element1, element2);
            }
            else
            {
                return TwoPointMethod(element1, element2);
            }
        }
        // metoda jednopunktowa
        private PopulationItem OnePointMethod(int element1, int element2)
        {
            int[] Solution = new int[Size + 1];

            List<int> list = new List<int>();
            int part = Size / 2;

            //Pierwsza połowa
            for (int i = 0; i < part; i++)
            {
                Solution[i] = Population[element1].Solution[i];
            }

            //Pozostałość
            int iterator = part;
            for (int i = 1; i < Size; i++)
            {
                if (!Solution.Contains(Population[element2].Solution[i]))
                {
                    Solution[iterator] = (Population[element2].Solution[i]);
                    iterator++;
                }
            }
            Solution[Size] = 0;


            int value = getValue(Solution);
            return new PopulationItem(Solution, value);
        }
        // metoda dwupunktowa
        private PopulationItem TwoPointMethod(int element1, int element2)
        {
            int[] Solution = new int[Size + 1];
            List<int> secondPart = new List<int>();
            
            int part1 = Size / 4;
            int part2 = Size / 2;

            //Pierwsza cześć 1 rodzica
            for (int i = 0; i < part1; i++)
            {
                Solution[i] = Population[element1].Solution[i];
            }
            
            //dane jakie potrzebne elementy z drugiego
            for (int i = part1; i < part1+part2; i++)
            {
                secondPart.Add(Population[element1].Solution[i]);
            }

            //2 rodzic
            int iterator = part1;
            for (int i = 1; i < Size; i++)
            {
                if (secondPart.Contains(Population[element2].Solution[i]))
                {
                    Solution[iterator] = (Population[element2].Solution[i]);
                    iterator++;
                }
            }

            //Druga cześć 1 rodzica
            for (int i = iterator; i < Size; i++)
            {
                Solution[i] = Population[element1].Solution[i];
            }

            Solution[Size] = 0;


            int value = getValue(Solution);
            return new PopulationItem(Solution, value);
        }
        // wyswietlenie wyniku
        private void ShowRezult()
        {
            if(Population[0].Value <= BestSolution.Value)
            {
                BestSolution = Population[0];
            }

            Console.WriteLine("Rezultat: \n");
            for (int i = 0; i < Size; i++)
            {
                Console.Write(BestSolution.Solution[i] + " --> ");
            }
            Console.Write(0);

            Console.WriteLine("\nKoszt: " + BestSolution.Value);
            Console.ReadKey();
        }
        // losowanie populacji 
        private PopulationItem RandomPopulation()
        {
            int[] Solution = new int[Size + 1];

            List<int> list = new List<int>();
            for (int i = 1; i < Size; i++)
            {
                list.Add(i);
            }

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
            // pobranie kosztu
            int value = getValue(Solution);
            // zapisanie 
            return new PopulationItem(Solution, value);
        }
        // liczenie kosztu
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

    class PopulationItem
    {
        public int[] Solution { get; set; }
        public int Value { get; set; }

        public PopulationItem(int[] solution, int value)
        {
            Solution = solution;
            Value = value;
        }
    }
}
