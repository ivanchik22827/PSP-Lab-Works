@echo off
echo Compiling Airport Management System...
mkdir target\classes 2>nul
javac -d target\classes -cp src\main\java src\main\java\*.java src\main\java\model\*.java src\main\java\model\interfaces\*.java src\main\java\manager\*.java src\main\java\utils\*.java
if %ERRORLEVEL% == 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
    pause
)