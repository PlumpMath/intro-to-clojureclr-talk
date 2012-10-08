# WORKING ON FORMATTING OF THIS - WILL BE FIXED SOON

Intro to Clojure-clr - Clojure for .NET Developers
====================================================

Code and Slides for my Intro to Clojure-clr talk at the 2012 Richmond CodeCamp 

Learn how to install Clojure-clr, use .NET libraries, connect to a database and Create UIs using Clojure. I will begin with an introduction to Clojure syntax followed by an example of how to make calls into a .NET library. Next, we will make queries into a database and display the results in a Windows.Forms UI.

## Setup 
- Install Clojure-Clr [Get the Binaries](https://github.com/clojure/clojure-clr/wiki/Getting-binaries)
- Add the directory you unzip the binaries into in your PATH
- Add the environment variable CLOJURE_LOAD_PATH and set it to the Clojure-clr directory

## Slides
A PDF version and a PowerPoint version of the slides in the slides directory

## REPL Work During the Talk - $.05 Clojure Tour
### The Basics - creating 'variables', functions and calling .NET static methods

- Defines a variable that is scoped to the user namespace since it was defined in the REPL
  <br/>
  <code>
	(def msg "Clojure is Cool!")
  </code>

- Defines a function in the user namespace with one parameter and writes it to stdout
 <br/>
 <code>
	(defn echo [ my-str ] 
      (println "You said:" my-str))
 </code>

- Calls the .NET static method WriteLine
<br/>
<code>
 (System.Console/WriteLine "I just called a .NET method!")
</code>

### Sequences - creating, processing and taking a subset
- Creates a new sequence of the numbers form 0 to 9
<br/>
<code>
(def numbers (range 10))
</code> 

- Prints out each number in the sequence on its own line.  
<br>
<code>
(map println numbers)
</code>

- Sums up the numbers in the sequence.
<br/>
<code>
	(apply + numbers)
</code>

- Returns a lazy sequence of the even numbers in numbers.
<br>
<code>
	(filter even? numbers)
</code>

### .NET - My Own Assembly - set/get value from a property, calling a method and passing parameters

[The C# Code for the class I'm working with below ](https://github.com/rippinrobr/intro-to-clojureclr-talk/blob/master/dotnet_src/HockeyStats/ReplExample/Class1.cs)

- Loads my ReplExample assembly into the name space
<br>
<code>
(assembly-load-from "deps\\ReplExample.dll")
</code>

- Instantiates an MyClass object I could also do the samething this way: (def obj (ReplExample.MyClass.))
<br>
<code>
	(def obj (new ReplExample.MyClass)) 
</code>

- Sets the MyVal property in the obj to 10
<br>
<code>
	(set! (.MyVal obj) 10)
</code>

- Prints out the number 10
<br>
<code>
	(prinltn (.MyVal obj))
</code>

- Calls the MyClass method GetTime
<br>
<code>
	(.GetTime obj)
</code>

- Calls the MyClass SayHi method passing the string "Richmond"
<br>
<code>
	(.SayHi obj "Richmond")
</code>

### .NET Assemblies - interacting with .NET System.Windows.Forms.MessageBox
- Brings in the System.Windows.Forms assembly into the namespace
<code>
(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")
</code>

- Brings the System.Windows.Forms into the namespaces and allows me to use MessageBox instead of the entire namespace
<br>
<code>
	(import [System.Windows.Forms MessageBox])
</code>

- Brings up a MessageBox with the string "This isn't Durham!"
<br>
<code>
	(MessageBox/Show "This Isn't Durham!" "ClojureCLR Dialog")
</code>

## Questions? Resources?

If you have any questions hit me up on twitter -> @rippinrobr
[My blog has a few posts on Clojure-clr](http://www.myclojureadventure.com/search/label/clojureclr)

