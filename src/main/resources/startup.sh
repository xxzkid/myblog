#!/bin/bash
export BUILD_ID=dontKillMe
whoami
cPwd="$(pwd)"
JAVA_HOME="/opt/jdk1.8"
appName="myblog"
appJar="${cPwd}/${appName}-1.0.0-SNAPSHOT.jar"
appLog="${cPwd}/run.log"
JAVA_OPTS="-server -Xms512m -Xmx512m -XX:SurvivorRatio=8 -XX:+UseParallelGC -XX:+DisableExplicitGC -Xloggc:./logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/${appName} -XX:+PrintTenuringDistribution"
nohup ${JAVA_HOME}/bin/java ${JAVA_OPTS} -Dspring.profiles.active=prod -jar ${appJar} > ${appLog} 2>&1 &
echo "ok..."