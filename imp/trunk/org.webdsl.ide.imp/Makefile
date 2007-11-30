SDF2IMP = ../org.strategoxt.imp.metatooling/sdf2imp
SYNTAX  = WebDSL

all : eclipse-deps

clean :
	rm -f src/parser/$(SYNTAX)ParseController.java
	rm -rf src/parser/ast

eclipse-deps : plugin.xml # and others

plugin.xml : $(SYNTAX).def $(SYNTAX).tbl $(SDF2IMP)
	$(SDF2IMP) -i $< -p $(SYNTAX).tbl -e app,mod -m $(SYNTAX) -n org.stratetegoxt.samples.php --verbose 2

$(SYNTAX).def : ../../../webdsls/trunk/src/org/webdsl/dsl/syntax/$(SYNTAX).def
	ln -s $< $@

$(SYNTAX).tbl : $(SYNTAX).def
	sdf2table -i $< -o $@ -m $(SYNTAX)
