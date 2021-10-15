# SortingCompetitionMaterials2021

Materials for UMM CSci 3501 "Algorithms and computability" 2021 sorting competition.

# Table of contents
* [Goal of the competition](#goal)
* [The data](#data)
* [How is the data generated](#generating)
* [How do you need to sort the data](#sortingRules)
* [Setup for sorting](#setup)
<!---
* [Submision deadlines](#deadlines)
* [Scoring](#scoring)
* [System specs](#specs)
* [Results of the first preliminary round](#round1)
* [Results of the final competition](#final)
* [Presentations](#presentation)
--->


## Goal of the competition <a name="goal"></a>

The Sorting Competition is a multi-lab exercise on developing the fastest sorting algorithm for a given type of data. By "fast" we mean the actual running time and not the Big-Theta approximation. The solutions are developed in Java and will be ran on a single processor.

## The data  <a name="data"></a>

Data for this sorting competition consists of non-negative numbers in the range from 0 to 10000000, passed to the sorting 
method as an array of integers (not ints). 

The range of [0 to 10000000] is broken down into 20 buckets of 500000 each. The probabilities of a number coming from the buckets are different and sharply decreasing for higher numbers, but within each bucket they are generated uniformly.  

More details are in the file [DataGenerator.java](src/DataGenerator.java).

The generated numbers are written into a data file one per line. Sample data files are: [data1.txt](data1.txt) (10000 elements), [dataSmall.txt](dataSmall.txt) (an example of with 12 small numbers, to illustrate the sorting. Created manually, doesn't follow the distribution). 
## How do you need to sort the data <a name="sortingRules"></a>

The numbers are sorted on the following criteria:

* The number of 1s in the binary representation of a number. For instance, 16 is 10000 is binary, and goes before 3, which is 11 in binary. There is no padding of zeros in the beginning. 
* If the number of 1s is equal, the numbers are compared by the length of the longest repeated substring of zeros and ones in their binary representation, 
i.e. by the length of the longest pattern that apprears at least twice (non-overlapping). For instance, 7 (111 in binary) and 73 (1001001 in binary) both have three 1s, 
but the longest repeated substring in 7 is 1, of length 1, and the longest repeated substring in 73 is 100 (or, alternatively, 001), of length 3. Thus 7 is before 73. 
Also note that even though the pattern 1001 appears twice in 1001001, the two occurrences share the middle 1, and therefore are ovelapping and don't count as a pattern of length 4. 
* If both of the above comparisons are equal, the number with the lower value is first. For instance, 5 (101 in binary) and 9 (1001 in binary) both have two 1s 
and the longest repeated substring of length 1, so 5 is before 9 since it is smaller.  

Example of sorted data, with comments:
```
5 101 // two 1s, largest repeated group length 1 (group = 1)
33 100001 // two 1s, largest repeated group length = 2 (group = 00)
66 1000010 // two 1s, largest repeated group length = 2 (group = 00 or 10)
36 100100 // two 1s, largest repeated group length = 3 (group = 100)
72 1001000 // two 1s, largest repeated group length 3 (group = 100)
7 111 // three 1s, largest repeated group length = 1 (group = 1)
35 100011 // three 1s, largest repeated group length = 1 (group = 0 or 1)
37 100101  // three 1s, largest repeated group length = 2 (group = 01)
88 1011000 // three 1s, largest repeated group length = 2 (group = 10)
29 11101   // four 1s, largest repeated group length = 1 (group = 1)
27 11011   // four 1s, largest repeated group length = 2 (group = 11)
31 11111   // five 1s, largest repeated group length = 2 (group = 11)
```

The file [Group0.java](src/Group0.java) provides a Comparator that implements this comparison and provides some tests. Please
consult it as needed. However, note that this is a slow implementation, and you should think of a way to make yours much faster. 

Once the data is sorted, it is written out to the output file, also one number per line, in the increasing order (according to the comparison given above). 
The file [out1.txt](out1.txt) has the results of sorting [data1.txt](data1.txt). 

## Setup for sorting <a name="setup"></a>

***The following will be updated***

The file [Group0.java](src/Group0.java) provides a template for the setup for your solution. Your class will be called `GroupN`, where `N` is the group number that is assigned to your group. The template class runs the sorting method once before the timing for the [JVM warmup](https://www.ibm.com/developerworks/library/j-jtp12214/index.html). It also pauses for 10ms before the actual test to let any leftover I/O or garbage collection to finish. Since the warmup and the actual sorting are done on the same array (for no reason other than simplicity), the array is cloned from the same input data. 

The data reading, the array cloning, the warmup sorting, and writing out the output are all outside of the timed portion of the method, and thus do not affect the total time. 

You may not use any global variables that depend on your data. You may, however, have global constants that are initialized to fixed values (no computation!) before the data is being read and stay the same throughout the run. These constants may be arrays of no more than 1000 `long` numberss or equivalent amount of memory. For instance, if you are storing an array of objects that contain two `long` fields, you can only have 500 of them. We consider one `long` to be the same as two `int` numbers, so you can store an array of 2000 `int` numbers.  
If in doubt about specific cases, please discuss with me. 

The method in the [Group0.java](src/Group0.java) files that you may modify is the `sort` method. It must take the array of strings. The return type of the method can be what it is now, which is the same as the parameter type `String []`, or it can be a different array type. If you are sorting in-place, i.e. the sorted result is in the same array, then you can just return a reference to that array, as my prototype method does, or make your sorting method `void`. If you are returning a different type of an array, the following rules have to be followed:
* Your `sort` method return type needs to be changed to whatever  array you are returning, and consequently the type of `sorted` array in `main` needs to be changed. 
* Your return type has to be an array (not an array list!) and it has to have the same number of elements as the original array. 
* You need to supply a method to write out your result into a file. The file has to be exactly the same as in the prototype implementation; they will be compared using `diff` system command. 

If you are not changing the return type, you don't need to modify anything other than `sort` method. 

Even though you are not modifying anything other than the `sort` method, you still need to submit your entire class: copy the template, rename the Java class to your group number, and change the`sort` method. You may use supplementary classes, just don't forget to submit them. Make sure to add your names in comments when you submit. 

Your program must print **the only value**, which is the **time** (as it currently does). Any other printed output disqualifies your submission. If you use test prints, make sure to turn them off for submission. 

**Important:** if the sorting times may be too small to distinguish groups based on just one run of the sorting, so I may loop over the sorting section multiple times. If this is the case, I will let you know no later than a day after the preliminary competition and will modify `Group0` file accordingly.  

## Scoring <a name="scoring"></a>

The programs are tested on a few (between 1 and 3) data sets. For each data set each group's program is run three times, the median value counts. The groups are ordered by their median score for each data file and assigned places, from 1 to N. 

The final score is given by the sum of places for all data sets. If the sum of places is equal for two groups, the sum of median times for all the runs resolves the tie. So if one group was first for one data set and third for the other one (2 sets total being run), it scored better than a group that was third for the first data set and second for the other. However, if one group was first for the first set and third for the other one, and the second group was second in both, the sum of times determines which one of them won since the sum of places is the same (1 + 3 = 2 + 2). 

If a program has a compilation or a runtime error, doesn't sort correctly, or prints anything other than the total time in milliseconds, it gets a penalty of 1000000ms for that run. 

## System specs <a name="specs"></a>

The language used is Java that's installed in the CSci lab). It's ran on a single CPU core.  

<!---
I will post a script for running this program (with a correctness check and all), but for now a couple of things to know: run your program out of `/tmp` directory to avoid overhead of communications with the file server, and pin your program to a single core, i.e. run it like this:
``taskset -c 0 java GroupN``
--->


