echo on
REM SET CLOJURE_HOME=\tools\clojure-clr-1.3.0-Debug-4.0
SET CLOJURE_HOME=\tools\clojureclr\Debug 4.0
SET BUILD_DIR=\code\talks\intro-to-clojureclr-talk\src
del ..\bin\hockey* /Q

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

REM For Clojure 1.3
REM copy "%CLOJURE_HOME%\hockey.*" ..\bin
REM copy "%CLOJURE_HOME%\hockey.clj.dll" ..\bin
REM copy "%CLOJURE_HOME%\export.html.*" ..\bin
REM copy "%CLOJURE_HOME%\export.html.*.*" ..\bin

REM FOR Clojure 1.4
copy "%CLOJURE_HOME%\clojure\hockey.clj" ..\bin\clojure
copy "%CLOJURE_HOME%\clojure\instant.clj" ..\bin\clojure
copy "%CLOJURE_HOME%\clojure\uuid.clj" ..\bin\clojure
copy "%BUILD_DIR\deps\*" ..\bin\deps
copy "%BUILD_DIR%\hockey.clj" ..\bin
copy "%BUILD_DIR%\hockey.exe.config" ..\bin
copy "%BUILD_DIR%\db\queries.clj" ..\bin\db
move "%BUILD_DIR%\hockey.exe" ..\bin
move "%BUILD_DIR%\hockey.clj.dll" ..\bin
move "%BUILD_DIR%\export.html.exe" ..\bin
move "%BUILD_DIR%\export.html.*.*" ..\bin
move "%BUILD_DIR%\export.html.*" ..\bin
REM move "%BUILD_DIR%\db.core.clj.dll" ..\bin
REM move "%BUILD_DIR%\db.queries.clj.dll" ..\bin
