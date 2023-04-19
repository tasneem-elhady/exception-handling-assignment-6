@echo off
cd src
javac *.java
echo Normal Case:
java WriteXml ../new1.arxml
pause
echo Not valid Autosar file Case
java  WriteXml ../new1.txt
pause
echo Empty file Case
java  WriteXml ../empty.arxml
pause