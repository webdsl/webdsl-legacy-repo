SYNTAX     = StrategoWebDSL
EXTENSIONS = str
PACKAGE    = org.strategoxt.imp.editors.strategowebdsl
SOURCE     = ../../../webdsls/trunk/src/org/webdsl/dsl/syntax/$(SYNTAX).def
START      = Module[StrategoHost]

SDF2IMP    = ../org.strategoxt.imp.metatooling/sdf2imp

all : eclipse-deps

clean :
	rm -f src/parser/$(SYNTAX)ParseController.java plugin.xml
	rm -rf src/parser/ast
	# ...

eclipse-deps : plugin.xml # and others

plugin.xml : $(SYNTAX).def $(SYNTAX).tbl $(SDF2IMP) Makefile
	$(SDF2IMP) --verbose 2 -i $< -p $(SYNTAX).tbl -e $(EXTENSIONS) -s $(START) -m $(SYNTAX) -n $(PACKAGE)

$(SYNTAX).def : $(SOURCE)
	ln -s $< $@

$(SYNTAX).tbl : $(SYNTAX).def
	sdf2table -i $< -o $@ -m $(SYNTAX)
