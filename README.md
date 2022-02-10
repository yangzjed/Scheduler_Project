# Scheduler_Project

This application assigns n people to m distinct tasks given each person's availability and preference for each task.

Input format:

[n, # students] [m, # tasks]

[student availabilities for task 1]

[student availabilities for task 2]

...

where each entry of availability is encoded as follows: 0 -> unavailable, 1 -> available, 2 -> preferred.

Input files are stored in src/data. To change the input file used, edit SchedulerEngine.main.
