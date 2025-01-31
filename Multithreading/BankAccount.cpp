#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
using namespace std;

struct BankAccount {
private:
    uint32_t m_Bal = 0; // do initialize global variables
    mutex mut;
    condition_variable cond;
public:
    BankAccount(int amount) : m_Bal(amount) {}
    void deposit(uint32_t amt){
        lock_guard<mutex> lk(mut);
        m_Bal = m_Bal + amt;
        cond.notify_all();
    }
    bool withdraw(uint32_t amt){
        unique_lock<mutex> ul(mut);
        if(m_Bal < amt)
            cout << "Insufficient balance: " << m_Bal << ", required funds: " << amt << ", awaiting deposit!" << endl;
        cond.wait(ul, [this, amt]{return m_Bal >= amt;});
        if(m_Bal >= amt){
            m_Bal = m_Bal - amt;
            return true;
        }
        cout << "withdraw amt: " << amt << " failed, zero balance!" << endl;
        return false;
    }
    uint32_t getBalance(){
        lock_guard<mutex> lk(mut);
        return m_Bal;
    }
    void setBalance(uint32_t amt){
        lock_guard<mutex> lk(mut);
        m_Bal = amt;
    }
};

struct Transaction
{
private:
    BankAccount& m_Acnt;
public:
    enum TType : uint8_t {
       T_Deposit,
       T_Withdrawal,
       T_Enquire
    };
    Transaction() = delete;
    Transaction(BankAccount& a_Acnt):
        m_Acnt{a_Acnt}
    {}
    void perform(TType a_TType, uint32_t amt) {
        switch(a_TType){
            case T_Deposit:
                {
                    m_Acnt.deposit(amt);
                    break;
                }
            case T_Withdrawal:
                {
                    m_Acnt.withdraw(amt);
                    break;
                }
        }
    }
};

int main(int argc, const char* argv[]){
    BankAccount acnt1{0};
    Transaction tr(acnt1);

    // thread th1(&Transaction::perform, tr, Transaction::T_Deposit, 100); // user/atm 1
    // thread th2(&Transaction::perform, tr, Transaction::T_Withdrawal, 50); // user/atm 2
    // thread th3(&Transaction::perform, tr, Transaction::T_Deposit, 200); // user/atm 3
    // thread th4(&Transaction::perform, tr, Transaction::T_Withdrawal, 150);

    
    thread th1(&BankAccount::deposit, &acnt1, 100); // user/atm 1
    thread th2(&BankAccount::withdraw, &acnt1, 50); // user/atm 1
    thread th3(&BankAccount::deposit, &acnt1, 200); // user/atm 1
    thread th4(&BankAccount::withdraw, &acnt1, 150); // user/atm 1

    th1.join();
    th2.join();
    th3.join();
    th4.join();
    // acnt1.deposit(100);

    cout << "Final Balance: " << acnt1.getBalance() << endl;

    return 0;
}