/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> schedules = new ArrayList<>();
        for(List<Interval> oneSchedule : schedule) {
            schedules.addAll(oneSchedule);
        }
        schedules.sort((Interval s1, Interval s2) -> {
            return s1.start - s2.start;
        });

        List<Interval> freeTime = new ArrayList<>();
        int curUpLimt = schedules.get(0).start;
        for(int i = 0; i < schedules.size(); i++) {
            Interval temp = schedules.get(i);
            if(curUpLimt < temp.start) {
                freeTime.add(new Interval(curUpLimt, temp.start));
            }
            curUpLimt = Math.max(curUpLimt, temp.end);
        }
        return freeTime;
    }
}