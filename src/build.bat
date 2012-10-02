echo on
REM SET CLOJURE_HOME=\tools\clojure-clr-1.3.0-Debug-4.0
SET CLOJURE_HOME=\tools\clojureclr\Debug 4.0
SET BUILD_DIR=\code\talks\intro-to-clojureclr-talk\src
del ..\bin\hockey* /Q
del ..\bin\db\* /Q
del ..\bin\export* /Q

"%CLOJURE_HOME%"\clojure.compile hockey
"%CLOJURE_HOME%"\clojure.compile export.html

echo "%CLOJURE_HOME%"
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
copy "%BUILD_DIR\deps\*" ..\bin\deps
copy "%BUILD_DIR%\db\queries.clj" ..\bin\db
copy "%BUILD_DIR%\hockey.clj" ..\bin
move "%BUILD_DIR%\hockey.exe" ..\bin
move "%BUILD_DIR%\export.html.exe" ..\bin
move "%BUILD_DIR%\export.html.*.*" ..\bin
