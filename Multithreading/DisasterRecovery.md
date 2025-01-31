Sample Input 1
7
id 8 timestamp 200 qs.cpp 839ad0 ms.cpp 0cdde1 bubs.cpp 248dd1
id 0 timestamp 500 arr.h 163111 seq.h 294d3f
id 6 timestamp 200 ms.cpp 0cdde1 bs.cpp 4213ff
timestamp malformed input id ms.cpp 0cdde1
id 4 timestamp 1000 arr.h 163111 vec.h fcc2af
id 2 timestamp 300 bubs.cpp 248dd1 bs.cpp 4213ff
id 3 timestamp 300 bubs.cpp eaf88a bs.cpp 4f11aa
4
0 10000 qs.cpp 839ad0
0 500 vec.h fcc2af
0 100000 no_found.h empty_response
100 200 bs.cpp 4213ff
/*******************************/
Sample Output 1
6 8 2
0

6 8

/*****************************************************************************************/

Sample Input 2
5
id 38024 timestamp 74820 foo.py ac819f bar.py 0d82b9
id 49283 timestamp 19837 bar.py 0d82b9 baz.py f28dc2
id 20391 timestamp 23488 baz.py f28dc2 foo.py f918ca
id 2938 timestamp 101 qux.h d139af qux.cpp 718bc3
id 2939 timestamp 102 qux.h d139af
2
0 1000000 bar.py 0d82b9
0 1000000 qux.h d139af
/*******************************/
Sample Output 2
AMBIGUOUS INPUT!




