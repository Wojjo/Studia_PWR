#include "Run.h"
#include "Thread.h"
#include "Display.h"
#include <thread>
#include <string>
#include <ncurses.h>

int Run::enter()
{
    int how_many;
    scanw ("%d", &how_many);
    return how_many;
}


void Run::run()
{
    int x1,x2, index_1 =1, index_2 =2;
    char a = 'a', b ='b';

    printw("Podaj ile razy ma sie wyswietlic 'a'\n");
    x1 = enter();
    printw("Podaj ile razy ma sie wyswietlic 'b'\n");
    x2 = enter();
    clear();
    std::thread th(Thread::thr, x1, index_1, a);
    std::thread th2(Thread::thr, x2, index_2, b);
    th.join();
    th2.join();
    std::this_thread::sleep_for( std::chrono::seconds( 5 ));
    clear();
    printw("Zakonczono wykonywanie watkow");
    getch();
    endwin();
}