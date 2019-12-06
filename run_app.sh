#!/usr/bin/env bash

java $(if [ $DEBUG_PROPS -eq 1 ]; then echo '-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n'; fi ) -Dspring.profiles.active=docker -Dlog.dir=/log -jar /app.jar
