TEST
	in the first case their several words found.
	[mahadha@fleurus sur2]$ java Oblig3 needle.txt haystack.txt
	The needle is	c_g
	----------------------------
		index:	3
		match:	cag
	----------------------------
		index:	18
		match:	cbg
	----------------------------
		index:	29
		match:	ccg
	----------------------------
		index:	37
		match:	cdg
	----------------------------
		index:	43
		match:	ceg
	----------------------------
		index:	48
		match:	cfg
	----------------------------
		index:	62
		match:	chg
	---------------------------
	
	in case 2 needle is empty
	[mahadha@fleurus sur2]$ java Oblig3 needle1.txt haystack.txt
	needle  is empty
	in case 3 haystack is empty
	[mahadha@fleurus sur2]$ java Oblig3 needle.txt haystack1.txt
 	haystack is empty
	in case 4 needle has several wildcards
	[mahadha@fleurus sur2]$ java Oblig3 needle2.txt haystack2.txt
	The needle is	_all__ _o_ a__ y__
	----------------------------
	index:	0
	match:	hallow how are you
	---------------------------	
NOTE
	needle1.txt and haystack1.txt are empty file. i did sent. so u can
       try with any empty files.

EXPLANATION.
	Without going into the details on how  the boyer_
	more_algorithm works . I mplemented a condition in that 
	if a wildcard '_' is found then the character matches 
	condition is triggered. The wildcards has to be treated 
	like a letter to match any character.Upon matching, if
	a wildcard is found then it treat the character in the 
	corresponding index in haystack as equal.The program 
	checks until the the end of haystack file.if matching
	is found word i add it to the list and the needle 
	position is reset to the end of the needle.If their is 
	a mismatch then the offset  is moved to where the needles
 	end was pointing .
	

My program has two classes
.
	Oblig3.java . has the main method.
	searchWords.java
	

How to compile
	javac *.javac
	
		java Oblig
3 <needle>.txt<haystack>.txt.
              
Assumption.       
	haystack dont contain wildcard '_' or non letters.
	

Any perculiarities
	To my knowledge i haven't noticed any.
program status 
 
 	i have implemented all the assignment as required.
	i have checked  manually with the small file and 
	the index and the results i am getting from the 
	program are right.
       
My major source 
	lecture notes on textalgorithms
	Got some tips from google.	

