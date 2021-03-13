#!/bin/bash
cd components
for comp in *;
do
    cd $comp
    ./gradlew build_jar
    cd ..
done;