using System;
using System.Collections.Generic;
using System.Text;

namespace PEAII_TS
{
    class Neighbor
    {
        public int Number1 { get; set; }
        public int Number2 { get; set; }
        public int[] Solution { get; set; }
        public int Value { get; set; }

        public Neighbor(int size)
        {
            Solution = new int[size + 1];
            Value = Int32.MaxValue;
        }

        public void ChangeBestNeighbor(int number1, int number2, int[] solution, int value)
        {

            Number1 = number1;
            Number2 = number2;
            Value = value;
            
            for(int i = 0; i < solution.Length; i++)
            {
                Solution[i] = solution[i];
            }
        }
    }
}
