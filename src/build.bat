clojure.compile core
move *.dll ..\bin
move *.exe ..\bin
move *.pdb ..\bin
copy "c:\tools\clojureclr\Debug 4.0\Clojure.dll" ..\bin
copy "c:\tools\clojureclr\Debug 4.0\core.clj" ..\bin\clojure
copy "c:\tools\clojureclr\Debug 4.0\Microsoft.Scripting.dll" ..\bin
copy "c:\tools\clojureclr\Debug 4.0\Microsoft.Dynamic.dll" ..\bin
