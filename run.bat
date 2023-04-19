@echo off
cd src
javac *.java
echo Normal Case:
java WriteArxml ../new1.arxml
pause
echo Not valid Autosar file Case
java WriteArxml ../new1.txt
pause
echo Empty file Case
java WriteArxml ../empty.arxml
pause