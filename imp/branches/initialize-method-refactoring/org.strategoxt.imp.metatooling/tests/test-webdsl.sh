#!/bin/bash

cd `dirname $0`

if [ ! -d webdsl ]
then mkdir webdsl
fi

cd webdsl

WD=../../../../../webdsls/trunk/src/org/webdsl/dsl/

../../sdf2imp -i $WD/syntax/WebDSL.def -p $WD/syntax/WebDSL.tbl -m WebDSL -s Application -e app,mod --verbose 2 2>&1
