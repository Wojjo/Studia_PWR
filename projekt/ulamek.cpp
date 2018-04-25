#include "ulamek.h"
#include <iostream>
#include <math.h>
#include <stdio.h>
#include <conio.h>
using namespace std;

void Ulamek::wypisz()
{
 
    cout << licz << "/" << mian << endl;
}

void Ulamek::operator +( Ulamek a )
{
    if( mian == a.mian )
    {
        cout <<( licz + a.licz ) << "/" << mian << endl;
    }
    else
         cout <<( licz * a.mian ) +( a.licz * mian ) << "/" <<( mian * a.mian ) << endl;
    
}

void Ulamek::operator -( Ulamek a )
{
    if( mian == a.mian )
    {
        cout <<( licz - a.licz ) << "/" << mian << endl;
    }
    else
         cout <<( licz * a.mian ) -( a.licz * mian ) << "/" <<( mian * a.mian ) << endl;
    
}

void Ulamek::operator *( Ulamek a )
{
    cout <<( licz * a.licz ) << "/" <<( mian * a.mian ) << endl;
}

void Ulamek::operator /( Ulamek a )
{
    cout <<( licz * a.mian ) << "/" <<( mian * a.licz ) << endl;
}
