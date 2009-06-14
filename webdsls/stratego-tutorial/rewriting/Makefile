
NIX=$(HOME)/.nix-profile

WEBDSL_FRONT=/usr/local/pkg/nano
WEBDSL_FRONT_LIBS=/usr/local/pkg/nano/lib/libwebdsl-front.la

JAVA_FRONT = $(NIX)
JAVA_FRONT_CFLAGS =  
JAVA_FRONT_LIBS = $(NIX)/lib/libjava-front.la  

JAVA_FRONT_STRCFLAGS = -I $(NIX)/share/java-front-syntax -I $(NIX)/share/java-front -la $(NIX)/lib/libjava-front.la

JAVA_FRONT_XTC = $(NIX)/share/java-front/XTC

STRCFLAGS = \
        -la stratego-xtc \
        -la stratego-lib \
        -la stratego-gpp \
        -la stratego-tool-doc \
        -la stratego-sglr \
        -la $(JAVA_FRONT_LIBS) \
	-la $(WEBDSL_FRONT_LIBS) \
        -I $(WEBDSL_FRONT)/share \
        -I $(WEBDSL_FRONT)/share/sdf/webdsl \
        --format-check 0 -O 1

all : pretty-print normalize typecheck

clean : 
	rm -f normalize

pretty-print : pretty-print.str
	strc -i pretty-print.str $(STRCFLAGS)

normalize : normalize.str
	strc -i normalize.str $(STRCFLAGS)

typecheck : typecheck.str
	strc -i typecheck.str $(STRCFLAGS)
