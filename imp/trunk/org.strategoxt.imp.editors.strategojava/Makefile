SYNTAX     = Stratego-Java-15
EXTENSIONS = str
PACKAGE    = org.stratetegoxt.imp.editors.strategojava
SOURCE     = ~/.nix-profile/share/java-front/$(SYNTAX).def

SDF2IMP    = ../org.strategoxt.imp.metatooling/sdf2imp

all : eclipse-deps

clean :
	rm -f src/parser/$(SYNTAX)ParseController.java
	rm -rf src/parser/ast
	# ...

eclipse-deps : plugin.xml # and others

plugin.xml : $(SYNTAX).def $(SYNTAX).tbl $(SDF2IMP) Makefile
	$(SDF2IMP) -i $< -p $(SYNTAX).tbl -e $(EXTENSIONS) -m $(SYNTAX) -n $(PACKAGE)

$(SYNTAX).def : $(SOURCE)
	ln -s $< $@

$(SYNTAX).tbl : $(SYNTAX).def
	sdf2table -i $< -o $@ -m $(SYNTAX)
