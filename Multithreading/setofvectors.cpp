#include <bits/stdc++.h>
using namespace std; 

// auto cmp = [](const vector<int>& v1, const vector<int>& v2)
//     {
//         std::sort(v1.begin(), v1.end());
//         std::sort(v2.begin(), v2.end());
//         return v1 < v2; // lexicographically
//     };
auto cmp = [](vector<int> v1, vector<int> v2)
    {
        std::sort(v1.begin(), v1.end());
        std::sort(v2.begin(), v2.end());
        return v1 < v2; // lexicographically
    };

// https://stackoverflow.com/questions/2620862/using-custom-stdset-comparator
set<vector<int>, decltype(cmp)> set_of_vectors(cmp); 
  
// Print elements of Vector 
void Print_Vector(vector<int> Vec) 
{ 
    for (int i = 0; i < Vec.size(); i++) { 
        cout << Vec[i] << " "; 
    } 
    cout << endl; 
    return; 
} 
int main() 
{ 
    // Initializing some vectors 
    vector<int> data_1{ 10, 20, 30, 40 }; 
    vector<int> data_2{ 5, 10, 15 }; 
    vector<int> data_3{ 1, 3, 5, 7, 9, 11, 13 }; 
    vector<int> data_4{ 5, 10, 15 };  
    vector<int> data_5{ 10, 20, 30, 40 }; 
    vector<int> data_6{ 5, 15, 10 }; 
  
    // Inserting vectors into set 
    set_of_vectors.insert(data_1); 
    set_of_vectors.insert(data_2); 
    set_of_vectors.insert(data_3); 
    set_of_vectors.insert(data_4); 
    set_of_vectors.insert(data_5); 
    set_of_vectors.insert(data_6);
  
    // printing all the unique vectors in set 
    cout << "Set of Vectors: \n"; 
    for (auto it = set_of_vectors.begin(); 
         it != set_of_vectors.end(); 
         it++) { 
  
        Print_Vector(*it); 
    } 
    auto res = cmp(data_2, data_6);
    cout << "res: " << res << endl;
    res = cmp(data_2, data_5);
    cout << "res: " << res << endl; 
    return 0; 
} 