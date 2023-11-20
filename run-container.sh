#!/bin/bash

mkdir /app && cd /app
git clone https://github.com/guanchgonzalez/practice_jbehave.git
cp /tmp/pom.xml /app/practice_jbehave/pom.xml
mvn clean compile
