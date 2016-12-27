cmake_minimum_required(VERSION 3.5)
project(Dumino2)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp dumino.h)
add_executable(Dumino2 ${SOURCE_FILES})