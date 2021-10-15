A turing machine is an abstract computer, modeled by a machine moving over an infinite 1d memory tape. The machine
can take the following steps: read or write a binary character in its current cell, move right/left, change state, 
or halt (which ends the program). The current state, and the contents of the current cell, dictate the actions of the
turing machine. The busy beaver is the turing machine that writes the most "1"s, or takes the most steps before 
halting, but halts in a finite number of steps. 

I implement a bare-bones program in Java, to conduct a brute-force search for the busy beaver for a machine with 3
states. I use a linked list to model the memory tape, for maximum efficiency; the program searched across all 17 
million possible machines in 1.9 seconds, while other tools such as Wolfram Mathematica took as much as 2-3 hours
to conduct the same search. 