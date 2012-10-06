(ns repl)

;; -----------------------------
;; Basics
;; -----------------------------
(def msg "Clojure is Cool!")

(defn echo [my-str]
  (println "You said:" my-str))

(System.Console/WriteLine "I just called a .NET method!")

;; ------------------------------
;; Sequences
;; ------------------------------
 (def numbers (range 10))
 
 (map println numbers)
 (apply + numbers)
 (filter evin? numbers)

;; ------------------------------
;; .NET - My Own Assembly
;; ------------------------------
 (assembly-load-from "deps\\ReplExample.dll")
 (def obj (new ReplExample.MyClass)) 
 
 (set! (.MyVal obj) 10)
 (prinltn (.MyVal obj))
 (.GetTime obj)
 (.SayHi obj “Richmond”)
 
;; ------------------------------
;; .NET Assemblies
;; ------------------------------
 (System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")
 (import [System.Windows.Forms MessageBox])

 (defn display-message [str] 
    (MessageBox/Show str "ClojureCLR Dialog"))



