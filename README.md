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

<br/>
<code>
;; Creates a new sequence of the numbers form 0 to 9
(def numbers (range 10))
 
;; Prints out each number in the sequence on its own line.  
(map println numbers)

;; Sums up the numbers in the sequence.
(apply + numbers)

;; Returns a lazy sequence of the even numbers in numbers.
(filter even? numbers)
</code>

### .NET - My Own Assembly - set/get value from a property, calling a method and passing parameters

[The C# Code for the class I'm working with below ](https://github.com/rippinrobr/intro-to-clojureclr-talk/blob/master/dotnet_src/HockeyStats/ReplExample/Class1.cs)

<br/>
<code>
;; Loads my ReplExample assembly into the name space
(assembly-load-from "deps\\ReplExample.dll")

;; Instantiates an MyClass object 
;; I could also do the samething this way (def obj (ReplExample.MyClass.))
(def obj (new ReplExample.MyClass)) 

;; Sets the MyVal property in the obj to 10
(set! (.MyVal obj) 10)

;; Prints out the number 10
(prinltn (.MyVal obj))

;; Calls the MyClass method GetTime
(.GetTime obj)

;; Calls the MyClass SayHi method passing the string "Richmond"
(.SayHi obj "Richmond")
</code>

### .NET Assemblies - interacting with .NET System.Windows.Forms.MessageBox
<br/>
<code>
;; Brings in the System.Windows.Forms assembly into the namespace
(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")

;; Brings the System.Windows.Forms into the namespaces and allows me to
;; use MessageBox instead of the entire namespace
(import [System.Windows.Forms MessageBox])

;; Brings up a MessageBox with the string "This isn't Durham!"
(MessageBox/Show "This Isn't Durham!" "ClojureCLR Dialog")
</code>

## Questions? Resources?

If you have any questions hit me up on twitter -> @rippinrobr
[My blog has a few posts on Clojure-clr](http://www.myclojureadventure.com/search/label/clojureclr)

