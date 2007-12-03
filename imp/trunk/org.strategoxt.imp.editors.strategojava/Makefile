SYNTAX     = Stratego-Java-15
EXTENSIONS = str
PACKAGE    = org.stratetegoxt.imp.editors.strategojava
SOURCE     = ~/.nix-profile/share/java-front/$(SYNTAX).def
START      = Module[StrategoHost] # TODO: or should this just be module?

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
