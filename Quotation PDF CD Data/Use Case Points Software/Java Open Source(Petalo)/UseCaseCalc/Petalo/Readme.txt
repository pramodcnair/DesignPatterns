REQUIREMENTS
You need a functional java2 virtual machine for running
And a j2sdk for compiling

INTRODUCTION

This program is experimental
Right now it needs a lot of improvement.

So far it is able to open a MDL file
Parse it, and detect all the use cases at the use case model
It ignores the Bussines Use Case Model because it is not used in a estimation

The process of UCP counting is based on the Master thesis 
Estimating Object-Oriented Software Projects with Use Cases by Kirsten Ribu 2001.

Because it is possible that diferent scenarys will be expressed with
independent interaction diagrams and repeating the common transactions in all them
I use the label of the transaction as an identifier so YOU MUST NOT use the same label
if there are actually different transactions.. the inverse applies if you scatter different
scenarios at different diagrams YOU MUST label with the same words in order of not counting them twice.

Also all the GUI is written in spanish language (my mother language) so it is planned to internacionalize it
Translators to others languages are need for that work.
	
LICENSE

This software is distributed using the GPL license
also there is a license.txt with the full license
You must accept it if you use this software.
	
COMPILING
The packages came with all the .class files
But because they are created with blackdown jdk,it could
not work in your system.
If that is the case, you can erase all the .class files
typing in the Petalo folder:
  if you are in linux:
	rm *.class 
 or if you are in windows
	 del *.class
		
after that, just compile all the javas with
javac *.java
RUNING
For runing just type
java MDL2UCP



I am willing to hear about your experience with this software
for questions, suggestions or anything use the sourceforge page
or just send an email to:
jgutie at gmail.com