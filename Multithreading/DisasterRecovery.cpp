// g++ DisasterRecovery.cpp -std=c++17
#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <string>
#include <bitset>
#include <cstdio>
#include <limits>
#include <vector>
#include <climits>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;


struct file_info
{
    string m_f; // filename
    string m_frid; // file repo id
    file_info(const string& f,
        const string& frid)
        : m_f{f},
          m_frid{frid}
    {}
};

struct commit_info
{
    int m_cid; // commit id
    int m_T;
    std::vector<file_info> m_files;
    commit_info(const int cid,
        const int T,
        const vector<file_info> files)
        : m_cid{cid},
          m_T{T},
          m_files{std::move(files)}
    {}
    friend ostream& operator << (ostream& os, const commit_info& commit)
    {
        std::cout << "commit id: " << commit.m_cid
            << ", T: " << commit.m_T << ":::";
        for(const auto& file: commit.m_files)
        {
            std::cout << file.m_f << "|"
                << file.m_frid << "|"
                << std::endl;
        }
        std::cout << std::endl;
        return os;
    }
};

struct repo_info
{
    vector<commit_info> m_commits;
    repo_info(vector<commit_info> commits)
     : m_commits{std::move(commits)}
    {}
};

enum errorcodeT
{
    eSuccess = 0,
    eAmbiguousInput = 1
};

struct query_info
{
    int m_startT;
    int m_endT;
    string m_filename;
    string m_frid;
};

struct commit_sort
{
    bool operator() (const commit_info& c1, const commit_info& c2)
    {
        if(c1.m_T == c2.m_T)
        {
            return c1.m_cid < c2.m_cid;
        }
        return c1.m_T < c2.m_T;
    }
};

bool is_match(std::unordered_map<string, int>& repo_map,
    string input_frid)
{// Assumption: there aren't much repos to loop over
    std::sort(input_frid.begin(), input_frid.end());
    for(const auto& [frid, repo_id]: repo_map)
    {
        auto sorted = frid;
        std::sort(sorted.begin(), sorted.end());
        if(sorted == input_frid)
        {
            return true;
        }
    }
    return false;
}

int main() 
{
    std::vector<repo_info> repos;

    //Below is a map of (file_repo id, repos vector idx)
    std::unordered_map<string, int> repo_map;
    errorcodeT err = errorcodeT::eSuccess;
    
    // read Log file
    string line;
    getline(cin, line);
    int N = stoi(line);
    int readLines = N;
    while(readLines != 0)
    {
        vector<string> words;
        string token;
        getline(cin, line);
        stringstream ss(line);
        int wordidx = 0;
        bool discard = false;
        while(getline(ss, token, ' '))
        {
            if(token == "malformed")
            {
                discard = true;
                break;
            }
            words.push_back(token);
        }
        if(discard)
        {
            readLines--;
            continue;
        }
        if((words.size() <= 4)
           || ((words.size() & 1) != 0))
        {
            std::cout << "Line: " << line << std::endl;
            readLines--;
            continue;
        }
        int cid = stoi(words[1]);
        int T = stoi(words[3]);
        std::vector<file_info> new_files;
        bool merge = false;
        int repo_idx = 0;
        for(int i=4; i<words.size(); i+=2)
        {
            if(i+1 == words.size())
            {
                break;
            }
            auto repo_idx_itr = repo_map.find(words[i+1]);
            if(repo_idx_itr != repo_map.end())
            {
                // auto last_saved_file_repo_id = repo_idx_itr->first;
                merge = true;
                repo_idx = repo_idx_itr->second;
            }
            else
            {
                auto match = is_match(repo_map, words[i+1]);
                if(match)
                {
                    err = errorcodeT::eAmbiguousInput;
                    break;
                }
                if(!merge)
                {
                    repo_idx = repos.size();
                }
            }
            new_files.emplace_back(words[i], words[i+1]);
        }
        if(err == errorcodeT::eAmbiguousInput)
        {
            readLines--;
            continue;
        }
        if(merge)
        {
            auto& repo = repos[repo_idx].m_commits;
            repo.emplace_back(cid, T, new_files);
        }
        else
        {
            repos.emplace_back(vector<commit_info>());
            repos[repo_idx].m_commits.emplace_back(cid, T, new_files);
        }
        for(auto& c: new_files)
        {
            if(repo_map.find(c.m_frid) == repo_map.end())
            {
                repo_map.emplace(c.m_frid, repo_idx);
            }
        }
        readLines--;
    }
    // Input Query:
    getline(cin, line);
    int num_queries = stoi(line);
    readLines = num_queries;
    vector<query_info> queries;
    while(readLines != 0)
    {
        vector<string> words;
        string token;
        getline(cin, line);
        stringstream ss(line);
        query_info q;
        ss >> token;
        q.m_startT = stoi(token);
        ss >> token;
        q.m_endT = stoi(token);
        ss >> q.m_filename;
        ss >> q.m_frid;
        queries.push_back(q);
        readLines--;
    }

    // Check for AMBIGUOUS INPUT
    if(err == errorcodeT::eAmbiguousInput)
    {
        std::cout << "AMBIGUOUS INPUT!";
        exit(0);
    }

    // Validate/Print input
    for(const auto& repo: repos)
    {
        const auto& commits = repo.m_commits;
        for(const auto& commit: commits)
        {
            std::cout << commit;
        }
        std::cout << "********************" << std::endl;
    }
    std::cout << "internal repo map: ";
    for(const auto [frid, repo_idx]: repo_map)
    {
        std::cout << frid << "->"
            << repo_idx << "|";
    }
    std::cout << std::endl;

    //Sort repos
    for(auto& repo: repos)
    {
        auto& commits = repo.m_commits;
        std::sort(commits.begin(), commits.end(), commit_sort());
    }

    //Generate result
    for(const auto& q: queries)
    {
        auto repo_id_itr = repo_map.find(q.m_frid);
        if(repo_id_itr != repo_map.end())
        {
            auto repo_idx = repo_id_itr->second;
            for(auto const& commit: repos[repo_idx].m_commits)
            {
                if(commit.m_T >= q.m_startT && commit.m_T <= q.m_endT)
                {
                    std::cout << commit.m_cid << " ";
                }
            }
        }
        std::cout << std::endl;
    }

}