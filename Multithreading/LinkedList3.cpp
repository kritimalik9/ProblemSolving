#include <bits/stdc++.h>
using namespace std;
struct ListNode
{
    int val;
    ListNode* next = nullptr; // IMP_NOTE: MUST DO INITIALIZATION
    ListNode* prev= nullptr;
};
class List
{
public:
    ListNode* head = nullptr;
    int N=0;
public:
    void push_back(int val)
    {
        ListNode* node = new ListNode();
        node->val = val;
        node->next = nullptr;
        node->prev = nullptr;
        if(head == nullptr)
        {
            head = node;
        }
        else
        {
            ListNode* temp = head;
            while(temp != nullptr)
            {
                if(temp->next == nullptr)
                {
                    temp->next = node;
                    node->prev = temp;
                    break; // IMP_NOTE fix
                }
                temp = temp->next;
            }
        }
        N++;
    }
    void deleteMinAndAdjacents(ListNode** pp)
    {
        ListNode* node = *pp;
        ListNode* parent = node->prev;
        ListNode* pparent = nullptr;
        if(parent != nullptr)
        {
            pparent = parent->prev;
        }
        ListNode* nextnode = node->next;
        ListNode* nnextnode = nullptr;
        if(nextnode != nullptr)
        {
            nnextnode = nextnode->next;
        }
        if(parent != nullptr && pparent != nullptr && nextnode != nullptr && nnextnode != nullptr)
        {
            pparent->next = nnextnode;
            nnextnode->prev = pparent; // IMP_NOTE fix
            delete node;
            delete parent;
            delete nextnode;
            N = N-3;
        }
        else if((parent == nullptr || pparent == nullptr)
                && (nextnode == nullptr || nnextnode == nullptr))
        {
            delete node;
            N--;
            if(parent != nullptr)
            {
                N--;
                delete parent;
            }
            if(nextnode != nullptr)
            {
                N--;
                delete nextnode;
            }
            head = nullptr;
            // head->prev = nullptr;// IMP_NOTE fix DONT DO THIS
            // head->next = nullptr;// IMP_NOTE fix
        }
        else if(parent == nullptr || pparent == nullptr)
        {
            head = nnextnode;
            head->prev = nullptr;// IMP_NOTE fix
            if(parent != nullptr)
            {
                N--;
                delete parent;
            }
            delete node;
            delete nextnode;
            N = N-2;
        }
        else
        {
            pparent->next = nullptr;
            if(nextnode != nullptr)
            {
                N--;
                delete nextnode;
            }
            delete node;
            delete parent;
            N = N-2;
        }
    }
    void getMin(ListNode** pp)
    {
        // ListNode** pp = nullptr; // LEARN IMP_NOTE // GET double pointer memory from outside.
        ListNode* min_node = nullptr;
        ListNode* temp = head;
        while(temp != nullptr)
        {
            if(temp == head)
            {
                min_node = temp;
            }
            else
            {
                if(temp->val < min_node->val)
                {
                    min_node = temp;
                }
            }
            temp = temp->next;
        }
        *pp = min_node;
    }
    friend ostream& operator << (ostream& ostr, List l)
    {
        ListNode* temp = l.head;
        while(temp != nullptr)
        {
            cout << temp->val;
            temp = temp->next;
            if(temp == nullptr) break;
            cout << "->";
        }
        cout << endl;
        return ostr;
    }
};
int fn(List& l)
{
    int sum =0;
    ListNode* temp = new ListNode(); // LEARN IMP_NOTE
    ListNode** pp = &temp; 
    while(l.N != 0)
    {
        std::cout << "*****************************" << endl;
        l.getMin(pp);
        ListNode* minnode = *pp;
        cout << "min: " << minnode->val << std::endl;
        sum = sum + minnode->val;
        l.deleteMinAndAdjacents(pp);
        std::cout << "N at each iteration: " << l.N << std::endl;
        std::cout << l;
    }
    return sum;
}
int main(int argc, const char** argv)
{
    List l;
    std::cout << "Enter number of input list elements: ";
    int n=0;
    std::cin >> n;
    std::cout << "Enter elements: ";
    for(int i=0; i<n; i++)
    {
        int val = 0;
        cin >> val;
        l.push_back(val);
        cout << l;
    }
    cout << "added elements: " << l.N << endl;
    auto ans = fn(l);
    std::cout << "ans: " << ans << std::endl;
    return 0;
}