
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
        -I $(WEBDSL_FRONT)/share/sdf/webdsl 

all : derive

clean : 
	rm -f normalize

derive : derive.str $(wildcard *.str)
	strc -i derive.str $(STRCFLAGS)

%.str.pp : %.str
	pp-stratego -i $< $(STRCFLAGS) -o $@
