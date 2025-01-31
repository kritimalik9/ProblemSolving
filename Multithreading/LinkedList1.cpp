#include <bits/stdc++.h>
using namespace std;
int fn(vector<int>& a)
{
    int sum = 0;
    while(a.size() != 0)
    {
        int min_idx = -1;
        int min = INT_MAX;
        for(int i=0; i<a.size(); i++)
        {
            if(a[i] < min)
            {
                min_idx = i;
                min = a[i];
            }
        }
        sum = sum + min;
        if(min_idx-2 >= 0 && min_idx+2 < a.size())
        {
            a.erase(a.begin()+min_idx-1, a.begin()+min_idx+2);
        }
        else if(min_idx-2 < 0 && min_idx+2 >= a.size())
        {
            a.clear();
        }
        else if(min_idx-2 < 0)
        {
            a.erase(a.begin(), a.begin()+min_idx + 2);
        }
        else
        {
            a.erase(a.begin()+min_idx-1, a.end());
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
    vector<int> a(N, 0);
    for(int i=0; i<N; i++)
    {
        cin >> a[i];
    }
    auto ans = fn(a);
    std::cout << "ans: " << ans << std::endl;
    return 0;
}