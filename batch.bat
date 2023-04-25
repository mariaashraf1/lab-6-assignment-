@echo off
echo Testing normal case...
javac *.java
java main inputfile.ARXML
echo ----------------------------------------------------------------------------------
echo Testing empty file case...
javac *.java
java main Empty.ARXML
echo ----------------------------------------------------------------------------------
echo Testing not valid Autosar file case...
javac *.java
java main validAutosarfile.txt
pause
