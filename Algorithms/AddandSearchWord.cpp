/*
 * Design a data structure that supports the following two operations:
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * void addWord(word)
 * bool search(word)
 *
 * search(word) can search a literal word or a regular expression string containing only letters `a-z` or `.`
 * A `.` means it can represent any one letter.
 *
 * For example:
 *
 *   addWord("bad")
 *   addWord("dad")
 *   addWord("mad")
 *   search("pad") -> false
 *   search("bad") -> true
 *   search(".ad") -> true
 *   search("b..") -> true
 *
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * click to show hint.
 *
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 *
 */

#include <iostream>
#include <string>
#include <string.h>
using namespace std;

const int maxChars = 26;

class TrieNode
{
    TrieNode *children[maxChars];

public:
    bool isWord;
    TrieNode()
    {
        isWord = false;
        memset(children, 0, sizeof(children));
    }
    TrieNode *&operator[](char index)
    {
        int i = (index - 'a') % maxChars;
        return children[i];
    }
    TrieNode *&operator[](int index)
    {
        int i = index % maxChars;
        return children[i];
    }
};

class TrieTree
{
    TrieNode *root;

    void freeTree(TrieNode *root)
    {
        for (int i = 0; i < maxChars; i++)
            if ((*root)[i] != NULL)
                freeTree((*root)[i]);

        delete root;
    }

    bool get(string &s, TrieNode *root, int index = 0) // As index is optional (defaults to 0)
    {
        TrieNode *node = root;

        for (int i = index; i < s.size(); i++)
        {
            if (s[i] != '.') // if s[i]is not a '.' then move to the next index
            {
                node = (*node)[s[i]];
                if (node == NULL)
                    return false;
            }
            else
            {
                for (int j = 0; j < maxChars; j++)
                {
                    TrieNode *p = (*node)[j];
                    if (p == NULL) // Try Next
                        continue;

                    // p!=NULL
                    if (i < s.size() - 1)
                    {
                        if (this->get(s, p, i + 1))
                            return true;
                        continue;
                    }

                    // p!=NULL && i==s.size()-1
                    if (p->isWord)
                        return true;
                }
                return false;
            }
        }
        return node->isWord;
    }

public:
    TrieTree() : root(new TrieNode()) {}
    ~TrieTree() { freeTree(root); }

    void put(string &s)
    {
        TrieNode *node = root;
        for (int i = 0; i < s.size(); i++)
        {
            if ((*node)[s[i]] == NULL)
                (*node)[s[i]] = new TrieNode();

            node = (*node)[s[i]];
        }
        node->isWord = true;
    }

    bool search(string &s)
    {
        return get(s, this->root);
    }
};

class WordDictionary
{
    TrieTree tree;

public:
    void addWord(string word)
    {
        tree.put(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    bool search(string word)
    {
        tree.search(word);
    }
};

int main(void)
{
    WordDictionary wd;

    wd.addWord("aba");
    cout << wd.search("a..") << endl;
    cout << wd.search("..a") << endl;

    wd.addWord("bad");
    cout << wd.search("b..") << endl;
    cout << wd.search("..d") << endl;
    return 0;
}
