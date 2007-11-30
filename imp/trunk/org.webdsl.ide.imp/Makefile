SDF2IMP = ../org.strategoxt.imp.metatooling/sdf2imp
WEBDSL  = ../../../webdsls/trunk/src/org/webdsl/dsl/syntax/WebDSL

all : eclipse-deps

clean :
	rm -f src/parser/WebDSLParseController.java
	rm -rf src/parser/ast

eclipse-deps : plugin.xml # and others

plugin.xml : $(WEBDSL).def $(SDF2IMP)
	$(SDF2IMP) -i $< -p $(WEBDSL).tbl -e app,mod -m WebDSL --verbose 2

$(WEBDSL).tbl : $(WEBDSL).def
	sdf2table -m WebDSL -i $< -o $@

