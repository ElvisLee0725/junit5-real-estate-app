#!/bin/bash

db_set() {
  echo "$1,$2" >> ./database.txt
}

db_get() {
  grep "^$1," database.txt | sed -e "s/^$1,//" | tail -n 1
}
