#!/bin/bash

echo "[INFO] run smoking functional test."

cd ..

set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

mvn clean cobertura:cobertura -Pfunctional-test

cd bin
