# Scheduler_Project

This application assigns n people to m distinct tasks given each person's availability and preference for each task.

Input format:

[n, # students] [m, # tasks]

[student availabilities for task 1]

[student availabilities for task 2]

...

where each entry of availability is encoded as follows: 0 -> unavailable, 1 -> available, 2 -> preferred.

For example, consider the following data file:

3 2
0 1 2
1 0 1

This means that there are 3 students that can be assigned to 2 tasks. Student 1 can do task 2, student 2 can do task 1, and student 3 can do both tasks 1 and 2 but prefers task 1.


Input files are stored in src/data. To change the input file used, edit SchedulerEngine.main.

Output format: Array of task assignments, where element array[i] represents the student that performs task i. Student numbering begins at 1.