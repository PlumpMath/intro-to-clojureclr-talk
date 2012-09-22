clojure.compile core
SET CLOJURE_HOME=\tools\clojure-clr-1.3.0-Debug-4.0
echo "%CLOJURE_HOME%"
copy "%CLOJURE_HOME%\core.*" ..\bin
copy "%CLOJURE_HOME%\Clojure.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core_clr.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core_proxy.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core_print.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.genclass.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core_deftype.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.core.protocols.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.gvec.clj.dll" ..\bin
copy "%CLOJURE_HOME%\Clojure.clr.io.clj.dll" ..\bin
copy "%CLOJURE_HOME%\microsoft.scripting.dll" ..\bin
copy "%CLOJURE_HOME%\microsoft.dynamic.dll" ..\bin
