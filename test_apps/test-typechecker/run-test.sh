#!/bin/bash

name=$0.app
show_result=$1
dsl-to-seam -i $name --stop-after 1 > /dev/null 2> $name.out
result=$?
if test 0 -ne $result; then
  echo "PASS: $name"
  rm $name.out
else
  if [ "$show_result" == "show" ]; then
    echo "FAIL: $name"
    cat $name.out
  else
    echo "FAIL: $name (output in $name.out)"
  fi
fi
test 0 -ne $result
