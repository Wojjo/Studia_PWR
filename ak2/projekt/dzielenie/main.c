#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int size = 223;

extern void division(unsigned long* dzielna, unsigned long* dzielnik, 
		unsigned long* reszta_z_dzielenia, unsigned long rozmiar, unsigned long* wynik);


int main() {
    clock_t start=clock();
    unsigned long b[223] = {329232985,379854637,541232985,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,379854637,541232985,637654123,345454345,546541232,454985463,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,985469854,379854637,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,653426768, 876253768,379854637,541232985,637654123,345454345,546541232,454985463,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,541232985,379854637,541232985,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,379854637,541232985,637654123,345454345,546541232,454985463,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,985469854,379854637,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,653426768, 876253768,379854637,541232985,637654123,345454345,546541232,454985463,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,329232985,379854637,541232985,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,379854637,541232985,637654123,345454345,546541232,454985463,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,985469854,379854637,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,653426768, 876253768,379854637,541232985,637654123,345454345,546541232,454985463,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,541232985,379854637,541232985,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,379854637,541232985,637654123,345454345,546541232,454985463,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,985469854,379854637,541232985,637654123,345454345,546541232,454985463,654123234,454543765,123234545,653426768, 876253768, 379854637,541232985,637654123,345454345,546541232,454985463,637654123,345454345,546541232,454985463,345454345,546541232,454985463,654123234,356732789,379854637,379854637,31};
    unsigned long b1[1] = {0};
    unsigned long x[1] = {2};
    unsigned long c[1] = {0};
    unsigned long d[1] = {0};
    int counter = 1;
    int how_many;

 for(int i=0;i<size;i++)
    {
        how_many = b[i];
        for(int z=0; how_many >= 1; z++)
        {
            how_many = how_many/10;
            counter = counter *10;
        }
        b1[0] = (c[0] * counter) + b[i]; 
        division(b1,x,c,1,d);
	    printf("%ld",d[0]);
        d[0] = 0; 
        counter = 1;   
    }
        printf(",%ld\n",c[0]);

    
printf("Czas wykonywania: %lu ms\n",((100000*(clock()-start))/CLOCKS_PER_SEC)); 
}

