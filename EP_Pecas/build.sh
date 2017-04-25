#!/bin/bash

production="./out/production"
sources="./src"

mkdir -p ${production} 2>/dev/null

for source in $(find ${sources} -name "*.java")
do
  javac -sourcepath ${sources} -d ${production} ${source}
done
