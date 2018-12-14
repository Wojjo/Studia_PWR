using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Projekt_I_PEA.Algorithms.DP
{
    class AlgorithmDP
    {
        public int[,] Matrix { get; set; }
        public int Size { get; set; }
        List<int> Set { get; set; }
        public List<int> Road { get; set; }
        public bool Test { get; set; }
        public int Result;

        public AlgorithmDP(int[,] matrix, int size, bool test = false)
        {
            Size = size;
            Matrix = new int[size, size];
            Matrix = matrix;
            Road = new List<int>();
            Set = new List<int>();
            Test = test;
        }

        public void Start()
        {
            for (int i = 1; i < Size; i++)
            {
                Set.Add(i);
            }
            Result = G(0, Set);

            if (!Test)
            {
                ShowResult();
            }
        }


        private void ShowResult()
        {
            Console.WriteLine(Result);
            for (int i = 0; i < Road.Count; i++)
            {
                if (i == Road.Count - 1)
                {
                    Console.Write(Road[i]);
                }
                else
                {
                    Console.Write(Road[i] + " --> ");
                }
            }
        }

        private int G(int node, List<int> set)
        {

            if (set.Count == 0)
            {
                return Matrix[node, 0];
            }

            else
            {
                List<int> items = new List<int>();

                for (int i = 0; i < set.Count; i++)
                {
                    List<int> newSet = new List<int>();

                    set.ForEach(a =>
                    {
                        if (a != set[i]) newSet.Add(a);
                    });

                    items.Add(Matrix[node, set[i]] + G(set[i], newSet));
                }
                // ----
                int min = items[0];
                int x = 0;
                for (int i = 1; i < items.Count; i++)
                {
                    if (min > items[i])
                    {
                        min = items[i];
                        x = i;
                    }
                }
                return min;
            }
        }
    }

    class Item
    {
        public int G { get; set; }
        public int P { get; set; }

    }
}

