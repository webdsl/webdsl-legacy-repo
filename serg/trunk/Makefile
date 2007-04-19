# Makefile for invoking code generator
# should eventually be integrated in ant build.xml file
# and be invocable from Eclipse

all : serg.appb

%.appa : %.app
	$(dsltoseam) -i $< -o $@
	
	JAVAFRONT = ${HOME}/.nix-profile

GENDIR = $(HOME)/webdsl/webdsls/src/org/webdsl/dsl

all : serg.appb

%.appa : %.app
	sglri -p $(GENDIR)/syntax/WebDSL.tbl -i $< | pp-aterm -o $@
	
%.appb : %.appa
	$(GENDIR)/generation/dsl-to-seam -i $< | \
	pp-aterm -o $@

clean :
	rm $(wildcard *.appa) $(wildcard *.appb)

cleanserg :
	rm -rf org.webdsl.serg.domain