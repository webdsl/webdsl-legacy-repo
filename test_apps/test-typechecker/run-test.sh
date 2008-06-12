#!/bin/bash

name=$0.app
dsl-to-seam -i $name --stop-after 1 > /dev/null 2> .$name.out
test 0 -ne $?
#if test 0 -eq $?; then
#  echo "$name did not return any error but should"
#else
#  echo "$name succeeded!"
#fi
