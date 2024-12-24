#!/bin/bash

docker run --it --name puppies-db \
  -e MYSQL_ROOT_PASSWORD=puppies \
  -e MYSQL_DATABASE=puppies \
  -e MYSQL_USER=puppies \
  -e MYSQL_PASSWORD=foo \
  -p 3306:3306 -d mysql:8.0