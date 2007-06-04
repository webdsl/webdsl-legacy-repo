# Makefile for invoking code generator
# should eventually be integrated in ant build.xml file
# and be invocable from Eclipse

JAVAFRONT = ${HOME}/.nix-profile

GENDIR = $(HOME)/webdsl/webdsls/src/org/webdsl/dsl

dsltoseam = $(GENDIR)/generation/dsl-to-seam

all : serg.appb serg.appc

%.appa : %.app
	$(dsltoseam) -i $< --no-desugaring --no-pp | pp-aterm -o $@

%.appb : %.app
	$(dsltoseam) -i $< --desugar --no-pp | pp-aterm -o $@

%.appbpp : %.app
	$(dsltoseam) -i $< --desugar -i $< -o $@
	
%.appc : %.app
	$(dsltoseam) -i $< 

clean :
	rm $(wildcard *.appa) $(wildcard *.appb)

cleanserg :
	rm -rf org.webdsl.serg.domain
