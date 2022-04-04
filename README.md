# ThreadProblems
<br><br>
For using jar->Download-><br>
```java -cp ABSOLUTE_PATH/ThreadProblems.jar main.Main```
<br><br>
//TODO:Make this a proper README. This project was done for educational purposes. The goal was to understand the pattern/framework of using threads into a given problem.
<br>
So in the end the only thing that a programmer needs to write is the actual code for the problem, and hide the complexity of threading.
<br>
This framework is suitable for specifying A Single Type of a problem per run.(Thrading into a single group of responsibilities)
<br><br>
How to create a problem
<br><br>
Create a class into problems package and extend it to ThreadProblems. 
<br>Now depending on your problem you shall define the implemented methods.
<br>Take care of fields at your class, they are beeing considered as all threads accesible.
<br>Use the already problems created as your guide. 
<br>sharedLocationMethod is used for safe usage of ThreadProblem fields. 
<br>Use map function to create your data and use them in the reduce function as needed.
<br><br>
How to create a blocking method
<br><br>
Create a class at blockMethods package and extend it to SharedLocationMethod. 
<br>Then create a method that calls threadProblem.sharedLocationMethod(). 
<br>Then call this method from the unimplemented method of the class.
<br><br>
Attention
<br><br>
In both cases -> Update the superclass with a private static String with the name of your created class. 
<br>Then add that string to the respective Array. 
<br>Then create a case at the selection method. 
<br>Use the already created custom classes as your guide.
