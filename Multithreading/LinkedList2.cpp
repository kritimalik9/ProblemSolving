#include <bits/stdc++.h>
using namespace std;
int fn(list<int>& a)
{
    // 3 2 1 1 2 4 6 5
    int sum = 0;
    while(a.size() != 0)
    {
        list<int>::iterator min_itr = a.end();
        int min = INT_MAX;
        auto itr = a.begin();
        for(; itr!=a.end(); itr++)
        {
            if(*itr < min)
            {
                min_itr = itr;
                min = *itr;
            }
        }
        sum = sum + min;
        auto min_rev_itr = std::make_reverse_iterator(min_itr);
        // the relationship &*r == &*(i - 1)
        // reverse iterator constructed
        // from a one-past-the-end iterator 
        // dereferences to the last element in a sequence
        // which means - reverse itr points to elem befor the input itr
        list<int>::reverse_iterator parent = min_rev_itr;
        list<int>::reverse_iterator pparent = a.rend();
        if(parent != a.rend())
        {
            // ++pparent;
            pparent = std::next(parent, 1);
        }
        list<int>::iterator nextnode = min_itr;
        ++nextnode;
        list<int>::iterator nnextnode = a.end();
        if(nextnode != a.end())
        {
            nnextnode = std::next(nextnode);
        }
        if(parent != a.rend() && pparent != a.rend() && nextnode != a.end() && nnextnode != a.end())
        {
            // pparent.base() is to get the parent forward itr
            a.erase(pparent.base(), nnextnode);
        }
        else if((parent == a.rend() || pparent == a.rend())
                && (nextnode == a.end() || nnextnode == a.end()))
        {
            a.clear();
        }
        else if(parent == a.rend() || pparent == a.rend())
        {
            a.erase(a.begin(), nnextnode);
        }
        else
        {
            a.erase(pparent.base(), a.end());
        }
    }
    return sum;
}
int main(int argc, const char** argv)
{
    std::cout << "Enter number of input array elements: ";
    int N = 0;
    std::cin >> N;
    std::cout << "Enter elements: ";
    list<int> a;
    for(int i=0; i<N; i++)
    {
        int val = 0;
        cin >> val;
        a.push_back(val);
    }
    auto ans = fn(a);
    std::cout << "ans: " << ans << std::endl;
    return 0;
}