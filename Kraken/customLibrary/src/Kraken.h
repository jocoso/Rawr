#ifndef KRAKEN_H
#define KRAKEN_H

#include <stdio.h>
#include <string>
#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <array>

using namespace std;

#ifdef __cplusplus
    extern "C" {
#endif

#ifdef BUILD_DLL
    #define SHARED_LIB __declspec(dllexport)
#else
    #define SHARED_LIB __declspec(dllimport)
#endif

void SHARED_LIB pipecommand(string strCmd);

#ifdef __cplusplus
    }
#endif

void SHARED_LIB saySomething(string str);

#endif // KRAKEN_H