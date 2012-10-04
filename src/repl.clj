(System.Reflection.Assembly/LoadWithPartialName "System.Windows.Forms")
(ns repl
  (:import [System.Windows.Forms MessageBox]))


(defn call-static-method 
  "This function illustrates how to call a static .NET function"
  []
  (System.Console/WriteLine "I just called a .NET method!"))


(defn call-message-box
  "This function loads the Windows.Forms assembly, imports the 
   System.Windows.Forms.MessageBox namespace and then calls
   MessageBox.Show displaying the message that was passed in.

   In a 'normal' app you would have the System.Reflection.Assembly/Load
   at the top of the source file and the import would come after the ns
   call.  I have them here for demo purposes only."
   [msg]
    (System.Reflection.Assembly/Load "System.Windows.Forms, Version=2.0.0.0, 
                Culture=neutral, PublicKeyToken=b77a5c561934e089")
    (import (System.Windows.Forms MessageBox))
    (MessageBox/Show msg  "ClojureCLR Dialog"))



