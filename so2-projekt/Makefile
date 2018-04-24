all: main

main:   main.cpp Thread.cpp Display.cpp
	g++ main.cpp Thread.cpp Display.cpp -std=c++11 -o main -lncurses -pthread

test: all
	./main 

clean:
	rm -rf *.o main

