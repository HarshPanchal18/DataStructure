#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// https://ocw.mit.edu/courses/6-s095-programming-for-the-puzzled-january-iap-2018/pages/puzzle-2-the-best-time-to-party/
// The problems are from MIT Course from Programming for the Puzzled.

struct range
{
    int start = 0;
    int end = 0;

    range() = default;
    range(int start_time, int end_time) : start(start_time), end(end_time) { }
};

int celeb_density(vector<range> &schedule)
{
    vector<int> hours(24, 0);
    int best_time = schedule[0].start;
    int max_celebs = 1;

    for (auto celeb : schedule)
        for (int hour = celeb.start; hour < celeb.end; ++hour)
            if (++hours[hour] > max_celebs)
            {
                ++max_celebs;
                best_time = hour;
            }

    return best_time;
}

int main(void)
{
    vector<range> schedule;

    int number_of_celebs;
    cout << "Enter number of celebrities attending party: ";
    cin >> number_of_celebs;

    if (number_of_celebs == 0)
        return 0;

    cout << "Enter entry time and exit time for a celebrity\n";
    for (int i = 0; i < number_of_celebs; ++i)
    {
        int s, e;
        cin >> s >> e;
        schedule.push_back(range(s, e));
    }

    int best_time = celeb_density(schedule);
    cout << "Best time to attend party is from " << best_time << " to " << best_time + 1 << "\n";
}
