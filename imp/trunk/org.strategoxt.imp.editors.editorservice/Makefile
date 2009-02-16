SYNTAX     = EditorService
SOURCE     = ../org.strategoxt.imp.metatooling/src/syntax/$(SYNTAX).def
CTREE      = ../org.strategoxt.imp.metatooling/src/include/sdf2imp.ctree

SDF2IMP    = ../org.strategoxt.imp.metatooling/sdf2imp
ESVS       = ${wildcard src/*.esv}

all : src/include/$(SYNTAX).packed.esv $(CTREE)
	[ -e src/include ] || mkdir src/include
	cp $(CTREE) src/include

clean :
	rm -f src/parser/$(SYNTAX)ParseController.java bin/$(SYNTAX).packed.esv
	rm -rf src/parser/ast
	# ...

src/include/$(SYNTAX).packed.esv : $(SYNTAX).def src/include/$(SYNTAX).tbl $(SDF2IMP) Makefile $(ESVS)
	$(SDF2IMP) -i $< -p $(SYNTAX).tbl -m $(SYNTAX) --verbose 2

$(SYNTAX).def : $(SOURCE)
	ln -s $< $@

src/include/$(SYNTAX).tbl : $(SYNTAX).def
	sdf2table -i $< -o $@ -m $(SYNTAX)
