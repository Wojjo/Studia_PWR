#include <iostream>
using namespace std;



class Ulamek
{

public:
    int licz, mian;
    Ulamek( int x, int y )
        : licz( x )
        , mian( y )
    { }   
    
	
	void wypisz();
    void operator +( Ulamek a );
    void operator -( Ulamek a );
    void operator *( Ulamek a );
    void operator /( Ulamek a );
    
};

