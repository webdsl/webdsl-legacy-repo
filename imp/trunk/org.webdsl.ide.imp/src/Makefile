SDF2IMP = ../../org.strategoxt.imp.metatooling/sdf2imp
WEBDSL  = ../../../../webdsls/trunk/src/org/webdsl/dsl/syntax/WebDSL

all : eclipse-deps

clean :
	rm -f parser/WebDSLParseController.java
	rm -rf parser/ast

eclipse-deps : syntax/WebDSL.tbl

syntax/WebDSL.tbl : $(SDF2IMP) $(WEBDSL).def
	$(SDF2IMP) -i $(WEBDSL).def -m WebDSL --verbose 2
