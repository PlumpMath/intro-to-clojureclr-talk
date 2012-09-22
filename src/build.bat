REM SET CLOJURE_HOME=\tools\clojure-clr-1.3.0-Debug-4.0
SET CLOJURE_HOME=\tools\clojureclr\Debug 4.0
SET BUILD_DIR=\code\talks\intro-to-clojureclr-talk\src
del ..\bin\* /Q
"%CLOJURE_HOME%"\clojure.compile core
echo "%CLOJURE_HOME%"
copy "%CLOJURE_HOME%\core.*" ..\bin
copy "%CLOJURE_HOME%\core.clj.dll" ..\bin
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

REM FOR Clojure 1.4
copy "%CLOJURE_HOME%\clojure\core.clj" ..\bin\clojure
copy "%CLOJURE_HOME%\clojure\instant.clj" ..\bin\clojure
copy "%CLOJURE_HOME%\clojure\uuid.clj" ..\bin\clojure
move "%BUILD_DIR%\core.exe" ..\bin
move "%BUILD_DIR%\core.clj.dll" ..\bin
