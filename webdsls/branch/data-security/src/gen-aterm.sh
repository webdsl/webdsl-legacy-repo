#!/bin/bash

/usr/local/bin/strc -I . -I /nix/store/0ljfh92wkjv984ajvcc14pq154x1y775-java-front-0.9pre1823618236/share/java-front -I /nix/store/0ljfh92wkjv984ajvcc14pq154x1y775-java-front-0.9pre1823618236/share/java-front/languages/java/eblock -I /nix/store/0ljfh92wkjv984ajvcc14pq154x1y775-java-front-0.9pre1823618236/share/java-front-syntax -I /nix/store/4iva8bw57ywcbyv9j04k7427v4ll24k5-strategoxt/share/sdf/gpp -I /nix/store/4iva8bw57ywcbyv9j04k7427v4ll24k5-strategoxt/share/sdf/xml-front -I org/webdsl/dsl/syntax --verbose 2 -c --library -i all.str -o libwebdsl-generator.rtree --verbose 1 -la stratego-xtc -la stratego-lib -la stratego-gpp -la stratego-tool-doc -la stratego-sglr -la /nix/store/0ljfh92wkjv984ajvcc14pq154x1y775-java-front-0.9pre1823618236/lib/libjava-front.la   --format-check 0 -O 1 --dump-aterms
cp all.str.aterm ~/svn/str-analysis/test/webdsl.aterm
