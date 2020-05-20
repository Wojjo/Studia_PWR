#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

extern void multiply(int* A, int* B, int* result, int lenA, int lenB);


int readToArray(int* array, char input[]);
const int base=10;
const int digitNum= 4000;


int main()
{
   char liczba1[4000]="150";
   char liczba2[4000]="150";
   int potega = 2000;
   int a[digitNum];
   int b[digitNum];
   int *res;
   int a_size, b_size;
   clock_t start=clock();
   int i;
   a_size=readToArray(a, liczba1);

// Sprawdzenie czy potega jest parzysta

if(potega%2==0)
{
   // Dzielenie potegi przez 2 
    potega = potega / 2;
    // podniesienie liczby do potegi / 2
    for(int count=1; count < potega; count ++)
    {
       
        b_size=readToArray(b, liczba2);

        res = calloc(a_size+b_size, sizeof(int));

        // wynik czastkowy 
        for(i=0; i<a_size+b_size;i++)
        {   
            res[i]=0;
        }

        multiply(a, b, res, a_size, b_size);
        int counter = a_size+b_size-1; 
        
        // zapisanie wyniku czastkowego do liczby B
        for(i=0; i<=a_size+b_size-1; i++)
        {
            liczba2[i-1] = res[counter]+'0';
            counter = counter -1;
        }
        
    }


        b_size=readToArray(b, liczba2);

        res = calloc(b_size+b_size, sizeof(int));
        // przygotowanie wyniku 
        for(i=0; i<b_size+b_size;i++)
        {   
            res[i]=0;
        }
        // podniesienie do kwadratu liczby podniesionej do n/2 potegi
        multiply(b, b, res, b_size, b_size);

}
else
{   // gdy potega nieparzysta 
    // potega - 1, a nastepnie potega/2 
    potega = (potega - 1) / 2;

    // podniesienie do potegi n/2 
    for(int count=1; count < potega; count ++)
    {
       
        b_size=readToArray(b, liczba2);

        res = calloc(a_size+b_size, sizeof(int));


        for(i=0; i<a_size+b_size;i++)
        {   
            res[i]=0;
        }

        multiply(a, b, res, a_size, b_size);
        int counter = a_size+b_size-1; 
        
        for(i=0; i<=a_size+b_size-1; i++)
        {
            liczba2[i-1] = res[counter]+'0';
            counter = counter -1;
        }
        
    }


        b_size=readToArray(b, liczba2);

        res = calloc(b_size+b_size, sizeof(int));

        for(i=0; i<b_size+b_size;i++)
        {   
            res[i]=0;
        }
        // podniesienie do kwadratu liczby podniesionej do n/2 potegi
        multiply(b, b, res, b_size, b_size);
        int counter = b_size+b_size-1; 

        for(i=0; i<=b_size+b_size-1; i++)
        {
            liczba2[i-1] = res[counter]+'0';
            counter = counter -1;
        }

        b_size=readToArray(b, liczba2);

        res = calloc(a_size+b_size, sizeof(int));

        for(i=0; i<a_size+b_size;i++)
        {   
            res[i]=0;
        }
        // pomnozenie wyniku przez podstawe 
        multiply(a, b, res, a_size, b_size);



}



for(i=b_size+b_size-1; i>=0;i--)
{
     printf("%d", res[i]);
}
     printf("\n");
     printf("Czas wykonywania: %lu ms\n",((1000*(clock()-start))/CLOCKS_PER_SEC)); 
     return 0;
}


int readToArray(int* array, char input[])
{
    int input_size=0;
    do{
        input_size++;
    }while(input[input_size]!=0);
    

    for(int i=0;i<input_size;i++)
    {
            array[input_size-i-1]=input[i]-'0';
    }
    return input_size;
}
