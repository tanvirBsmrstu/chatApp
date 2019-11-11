#!/bin/sh

inputNumber=10
exitNumber=0
while [ $inputNumber -ne $exitNumber ];
do
    echo "----------------------------------------------------------\n"
    echo "For solution5: press 5\nFor solution7: press 7\nExit: press 0"
    read inputNumber
    case $inputNumber in
        0) break;;
        5) java -jar com.tanvir-0.0.1-SNAPSHOT.JAR 5;;
        7) java -jar com.tanvir-0.0.1-SNAPSHOT.JAR 7 server localhost 3156 &
         java -jar com.tanvir-0.0.1-SNAPSHOT.JAR 7 client localhost 3156;;
    
    esac
done
echo Thank you
