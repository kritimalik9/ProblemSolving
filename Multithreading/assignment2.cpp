#include <iostream>
#include <vector>
#include <iterator> // istream_iterator
#include <unordered_map>
#include <algorithm> // foreach
#include <unordered_set>
    // mum 1 0 10
    // dad 2 0 15
    // tm 3 4 3
    // pm 4 5 5
    // km 5 0 7

    // 1 0 10
    // 2 0 15
    // 3 4 3
    // 4 5 5
    // 5 0 7

class job
{
public: // Made it public ONLY for quick accessibility for now.
    int m_job_id{-1};
    int m_next_job_id{-1};
    int m_time_to_complete{-1};

    friend std::istream& operator >> (std::istream& is, job& job_obj);
    friend std::ostream& operator <<  (std::ostream& os, const job& job_obj);

public:
    job(const int job_id,
        const int next_job_id,
        const int time_to_complete)
        : m_job_id{job_id},
          m_next_job_id{next_job_id},
          m_time_to_complete{time_to_complete}
    {}
    job()
    {}
};

std::istream& operator >>  (std::istream& is, job& job_obj)
{
    std::cin >> job_obj.m_job_id >> job_obj.m_next_job_id >> job_obj.m_time_to_complete;
    return is;
}
std::ostream& operator <<  (std::ostream& os, const job& job_obj)
{
    std::cout << job_obj.m_job_id << "|" << job_obj.m_next_job_id << "|" << job_obj.m_time_to_complete << "|";
    return os;
}
int main(int argc, const char* argv[])
{
    // step 1: create a demo example and test your basic class instatiation
    job tmp(1, 2, 200);

    // step 2: fetch input
    std::istream_iterator<job> istream_job_itr(std::cin), istream_job_itr_end;
    std::vector<job> jobs;
    std::copy(istream_job_itr, istream_job_itr_end, std::back_inserter<decltype(jobs)>(jobs));

    // USE Ctrl + D to proceed on Linux for cin or istream_iterator.

    //step 3: prep necessary structs/arrays/maps
    std::unordered_map<int, job> id_parentjob_map;
    std::for_each(jobs.begin(), jobs.end(), [&id_parentjob_map](const job& j)
    {
        if(j.m_next_job_id != 0)
        {
            id_parentjob_map.insert({j.m_next_job_id, j});
        }
    });
    std::unordered_set<int> s;// jobs_not_in_chain
    std::transform(jobs.begin(), jobs.end(), std::inserter(s, s.begin()), [] (const job& obj)
    {
        return obj.m_job_id;
    });

    //step 4: MAIN LOGIC
    std::vector<job> report; // Or, just like comparator, create a hash functor obj & pass it to the set template instantiation
    for(auto j = jobs.begin(); j < jobs.end(); j++)
    {
        int start_job_id = j->m_job_id;
        int end_job_id = j->m_next_job_id;
        int tot_t = j->m_time_to_complete;
        if(j->m_next_job_id == 0)
        {
            auto map_itr = id_parentjob_map.find(start_job_id);
            // bool is_chain_found = false;
            while(map_itr != id_parentjob_map.end())
            {
                s.erase(start_job_id); // erase all which have a parent
                tot_t = tot_t + map_itr->second.m_time_to_complete;
                start_job_id = map_itr->second.m_job_id;
                map_itr = id_parentjob_map.find(start_job_id);
                // is_chain_found = true;
            }
            // vector emplace_back dont require {} braces
            report.emplace_back(start_job_id, end_job_id, tot_t);
        }
    }

    // std::cout << "Computed report: ";
    // for(const auto& r: report)
    // {
    //     std::cout << r << std::endl;
    // }
    // std::cout << std::endl;
    //step 5: print OUTPUT of main logic
    std::ostream_iterator<job> out(std::cout, "-"); // NEED
    std::copy(report.begin(), report.end(), out);
    std::cout << "job ids which have no parent: ";
    for(const auto no_parent_id: s)
    {
        std::cout << no_parent_id << " ";
    }
    std::cout << std::endl;
    return 0;
}