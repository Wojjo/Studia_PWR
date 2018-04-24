#include <thread>
#include <ncurses.h>
#include "Thread.h"
#include "Display.h"


void Thread::thr(int x, int index, char sign) {

    if(index==1)
    {
        for (int n=0; n<x; n++)
        {
            move(n, n);
            Display::shared_print(index, sign, n);
            std::this_thread::sleep_for( std::chrono::seconds( index ));

        }
    }

    if(index==2)
    {
        for (int n=0; n<x; n++)
        {
            std::this_thread::sleep_for( std::chrono::seconds( index ));
            move(n, n+20);
            Display::shared_print(index, sign, n);

        }
    }
}



