#include <iostream>
#include <queue>
#include <cstring>

// Reference: https://www.geeksforgeeks.org/aho-corasick-algorithm-pattern-searching

using namespace std;

const int MaxC = 26;
const int MaxS = 500;

int out[MaxS], f[MaxS];
int g[MaxS][MaxC];

int buildMatchingMachine(string arr[], int k)
{
    memset(out, 0, sizeof(out)); // Initialize all values in output function as 0
    memset(g, -1, sizeof(g));    // Initialize all values in output function as -1

    int states = 1;

    for (int i = 0; i < k; i++)
    {
        const string &word = arr[i];
        int curr_state = 0;

        for (size_t j = 0; j < word.size(); j++) // Insert all characters of current word in arr[]
        {
            int ch = word[j] - 'a';

            if (g[curr_state][ch] == -1) // Allocate a new node (create a new state) if a node for ch doesn't exist.
                g[curr_state][ch] = states++;

            curr_state = g[curr_state][ch];
        }
        out[curr_state] |= (1 << i); // add current word in output function
    }
    // For all characters which don't have an edge from root (or state 0) in Trie, add a goto edge to state 0 itself
    for (int ch = 0; ch < MaxC; ch++)
        if (g[0][ch] == -1)
            g[0][ch] = 0;

    // build the failure function
    memset(f, -1, sizeof(f)); // Initialize values in failure function
    // Failure function is computed in breadth first order using a queue
    queue<int> q;

    // Iterate over every possible unit
    for (int ch = 0; ch < MaxC; ch++)
        // all nodes of depth 1 have failure function value as 0.
        if (g[0][ch] != 0)
        {
            f[g[0][ch]] = 0;
            q.push(g[0][ch]);
        }

    while (q.size())
    {
        int state = q.front(); // remove the front state from queue
        q.pop();

        // for the removed state, find failure function for all thode characters for which go to function is not defined.

        for (int ch = 0; ch <= MaxC; ch++)
        {
            // if hoto function is defined for 'ch' and 'state'
            if (g[state][ch] != -1)
            {
                int failure = f[state]; // find failure state to removed state

                // find the deepest node labeled by proper suffix of string from root to current state

                while (g[failure][ch] == -1)
                    failure = f[failure];

                failure = g[failure][ch];
                f[g[state][ch]] = failure;

                out[g[state][ch]] |= out[failure]; // merge output values

                q.push(g[state][ch]); // insert the next level node in queue
            }
        }
    }
    return states;
}

/*
Returns the next state the machine will transition to using goto and failure function.
currState - The current state of the machine.
            Must be between 0 and number of states - 1, inclusive.
nextInput - The next character that enters into the machine.
*/

int findNextState(int currState, char nextInput)
{
    int ans = currState;
    int ch = nextInput - 'a';

    // if goto is not defined, use failure function
    while (g[ans][ch] == -1)
        ans = f[ans];

    return g[ans][ch];
}

void searchWord(string arr[], int k, string txt) // finds all occurrences of all array words
{
    // Preprocess patterns
    // build machine goto, failure and output
    buildMatchingMachine(arr, k);

    int currState = 0;
    // traverse the text through the built machine to find all occurrences of words in arr[]

    for (int i = 0; i < txt.size(); i++)
    {
        currState = findNextState(currState, txt[i]);

        if (out[currState] == 0) // if match not found, move to the next state
            continue;

        // Match found, print all matching words of arr[] using output function
        for (int j = 0; j < k; j++)
            if (out[currState] & (1 << j))
                cout << "Word " << arr[j] << " appears from " << i - arr[j].size() + 1 << " to " << i << endl;
    }
}

int main(void)
{
    string arr[] = {"he", "she", "hers", "his"};
    string txt = "ahishers";
    int k = sizeof(arr) / sizeof(arr[0]);

    searchWord(arr, k, txt);
}