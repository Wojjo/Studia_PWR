using System;

namespace Projekt_I_PEA.Algorithms.BAndB
{
    class ExploreNode
    {
        public int Position { get; set; }
        public int ParentNode { get; set; }
        public int ParentPosition { get; set; }
        public int Node { get; set; }
        public int[,] Matrix { get; set; }
        public bool IsExplored { get; set; }
        public int Cost { get; set; }
        private bool[] Visited { get; set; }
        public int Size { get; set; }
        public int Progress { get; set; }

        public ExploreNode(int[,] matrix, bool[] visited, int size)
        {
            Size = size;
            Progress = 0;
            ParentPosition = -1;
            initMatrix(matrix);
            initVisited(visited);

            Cost = 0;
            Position = 0;
            Node = 0;
            ParentNode = -1;

            ReduceMatrix();
        }

        public ExploreNode(int position, int parentNode, int node, int[,] matrix, int cost, bool[] visited, int size, int progress, int parentPosition)
        {
            Size = size;
            Progress = progress;
            ParentPosition = parentPosition;
            initMatrix(matrix);

            Visited = new bool[Size];
            Cost = cost + Matrix[parentNode, node];
            Position = position;
            Node = node;
            initVisited(visited);

            ParentNode = parentNode;

            AddInfinityToMatrix();
            ReduceMatrix();
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

        // redukowanie macierzy w celu uzyskania zer
        private void ReduceMatrix()
        {
 
            //po wierszach
            for (int i = 0; i < Size; i++)
            {
                int minValue = Int32.MaxValue;          // przypisanie zmiennej minValue maksymalna wartosc dla int
                // szukanie najmniejszej wartosci w wierszach
                for (int j = 0; j < Size; j++)
                {
                    if (Matrix[i, j] < minValue)
                    {
                        minValue = Matrix[i, j];       // przypisanie wartosci minimalnej z danego wiersza do zmiennej minValue
                    }
                }

                if (minValue != 0 && minValue != Int32.MaxValue)
                {
                    Cost += minValue;                  // dodanie wartosci minimalnej do kosztu drogi
                    for (int j = 0; j < Size; j++)
                    {
                        if (Matrix[i, j] != Int32.MaxValue)
                        {
                            Matrix[i, j] = Matrix[i, j] - minValue; // redukowanie wartosci macierzy w danym wierszu o najmniejsza wartosc w celu uzyskania zer
                        }
                    }
                }
            }

            // po kolumnach, jezeli w danej kolumnie nie wystepuje na zadnej pozycji wartosc zero, nalezy odjac od wartosci kolumny
            // najmniejsza wartosc z tej kolumny w celu wytworzenia zera
            for (int i = 0; i < Size; i++)
            {
                int minValue = Int32.MaxValue;

                for (int j = 0; j < Size; j++)
                {
                    if (Matrix[j, i] < minValue)
                    {
                        minValue = Matrix[j, i];
                    }
                }

                if (minValue != 0 && minValue != Int32.MaxValue)
                {
                    Cost += minValue;                   // koszt drogi nie bedzie mniejszy niz ten, ktory tutaj nam wyjdzie, moze byc wiekszy, ale nie mniejszy
                    for (int j = 0; j < Size; j++)
                    {
                        if (Matrix[j, i] != Int32.MaxValue)
                        {
                            Matrix[j, i] = Matrix[j, i] - minValue;
                        }
                    }
                }
            }
        }
        // wstawienie "nieskonczonosci" dla poszczegolnych wierzcholkow
        private void AddInfinityToMatrix()
        {
            // wiersz ojca dla danego wierzcholka w macierzy ustawiony jako "nieskonczonosc"
            for (int i = 0; i < Size; i++)
            {
                Matrix[ParentNode, i] = Int32.MaxValue;
            }
            // kolumna danego wierzcholka ustawiona jako "nieskonczonosc"
            for (int i = 0; i < Size; i++)
            {
                Matrix[i, Node] = Int32.MaxValue;
            }
            // wartosc drogi od danego wierzcholka do jego ojca ustawiona na nieskonczonosc - nie cofamy sie 
            Matrix[Node, 0] = Int32.MaxValue;
        }

        private void initVisited(bool[] visited)
        {
            // zaznaczenie ze wierzcholek zostal odwiedzony
            Visited = new bool[Size];

            for (int i = 0; i < Size; i++)
            {
                Visited[i] = visited[i];
            }

            Visited[Node] = true;
            Progress++;
        }

        public bool[] GetVisited()
        {
            return Visited;
        }

        public int[,] GetMatrix()
        {
            return Matrix;
        }


    }
}

