using System;
using System.Collections.Generic;


namespace Projekt_I_PEA.Algorithms.BAndB
{
    class AlgorithmBAndB
    {
        public int[,] Matrix { get; set; }    // tablica dwuwymiarowa - Macierz 
        public int Size { get; set; }       // ilosc wierzcholkow -  rozmiar macierzy size x size 
        private int Counter { get; set; }   // pozycja wierzcholka
        private ExploreNode LastNode { get; set; }  // ostatni wierzcholek w trasie

        public List<ExploreNode> ExploreNodes { get; set; }     //wierzcholki danej trasy zapisywane sa do listy

        public AlgorithmBAndB(int[,] matrix, int size, bool test = false)
        {
            ExploreNodes = new List<ExploreNode>();
            Size = size;
            Matrix = new int[size, size];
            Matrix = matrix;
            Counter = 0;
            
            TransformZeroToInfinity();
        }

        public void Start()
        { // wszystkie odkryte wierzchołki danej trasy sa na liscie "ExploreNode"
            
            ExploreNodes.Add(new ExploreNode(getMatrix(), initVisited(), Size));    // dodanie wierzcholka startowego

            Counter++;
            bool next = true;
            while (next)
            {
                next = AddNewNodes(FindMinWeightNode());      
            }
            // wyswietlenie koncowych wynikow
            ShowResult();    
        }
 
     
        // zmiana wartosci w macierzy z zera na "nieskonczonosc" w celu unikniecia przy redukcji redukowania przez zero
        private void TransformZeroToInfinity()
        {

            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    if (Matrix[i, j] == 0) Matrix[i, j] = Int32.MaxValue;
                }
            }
        }

        private ExploreNode FindMinWeightNode()
        {
            // szukanie dostepnych wierzcholkow z najmniejsza waga
            // dostepny wierzcholek to taki, ktory nie zostal wczesniej użyty, ma wartosc IsExplored = false
            ExploreNode result = ExploreNodes.Find(a => a.IsExplored == false);
            ExploreNodes.FindAll(a => a.IsExplored == false).ForEach(b =>
            {
                if (result.Cost > b.Cost)
                {
                    result = b;
                }
            });
            return result;
        }
        // dodanie nowego wierzcholka
        private bool AddNewNodes(ExploreNode node)
        {
            node.IsExplored = true;             // zaznaczenie ze wierzcholek zostal odwiedzony
            bool[] list = node.GetVisited();     
            bool result = true;                   
            for (int i = 0; i < Size; i++)
            {  
                if (list[i] == false)
                {
                    // dodawanie wszystkich pozostalych wierzcholkow oprocz zerowego
                    ExploreNode newNode = new ExploreNode(Counter, node.Node, i, node.GetMatrix(), node.Cost, list, Size, node.Progress, node.Position);
                    ExploreNodes.Add(newNode);     // dodanie kolejnego wierzcholka do listy 
                    Counter++;                     
                    if (newNode.Progress == Size)
                    {
                        result = false;
                    }
                }
            }

            if (result == false)
            {
                ExploreNode lastNode = FindMinWeightNode();      // szukamy ostatni wierzcholek o najmniejszym koszcie trasy
                if (lastNode.Progress != Size)                  // wartosc progress dla ostatniego wierzcholka nie jest rowna liczbie wierzcholkow to znaczy ze trzeba wszyszukac ostatni wierzcholek
                {
                    result = true;
                }
                else
                {
                    LastNode = lastNode;
                }
            }
            return result;
        }

        // wyswietlenie wynikow koncowych 
        public void ShowResult()
        {
            Console.WriteLine();
            Console.WriteLine("Najkrótsza droga ma wartość: " + LastNode.Cost);
            List<int> road = new List<int>();   

            road.Add(0);                // dodanie wierzchołka zero na koniec drogi
            road.Add(LastNode.Node);    // dodanie ostatniego wierzcholka przed wierzcholkiem koncowym

            bool next = true;
            ExploreNode temp = LastNode;    // zmienna pomocnicza temp w celu przypisania wszystkich wierzchokow do drogi

            while (next)
            {
                // dodawanie wierzcholkow do drogi od konca do poczatku
                temp = findNode(temp.ParentPosition);
                road.Add(temp.Node);
                if (temp.Node == 0)
                {
                    next = false;       // brak kolejnych wierzcholkow
                }

            }
            // wyswietlenie drogi
            for (int i = road.Count - 1; i >= 0; i--)
            {
                if (i != 0)
                {
                    Console.Write(road[i] + " --> ");   // wyswietlenie wszystkich wierzcholkow oprocz ostatniego
                }
                else
                {
                    Console.Write(road[i]);             // wyswietlenie koncowego wierzcholka zero
                }
            } 
        }

        private int[,] getMatrix()
        {
            return Matrix;
        }
        private ExploreNode findNode(int number)
        {
            return ExploreNodes.Find(a => a.Position.Equals(number));
        }
        private bool[] initVisited()
        {
            bool[] result = new bool[Size];
            for (int i = 0; i < Size; i++)
            {
                result[i] = false;
            }
            return result;
        }

    }
}

