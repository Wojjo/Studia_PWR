cmake_minimum_required(VERSION 3.10)
project(so2_projekt)

set(CMAKE_CXX_STANDARD 11)

add_executable(so2_projekt main.cpp Thread.h Thread.cpp Display.cpp Display.h Run.cpp Run.h)