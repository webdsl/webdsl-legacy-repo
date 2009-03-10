SYNTAX     = EditorService
SOURCE     = ../org.strategoxt.imp.metatooling/src/syntax/$(SYNTAX).def
PPAF       = ../org.strategoxt.imp.metatooling/src/syntax/$(SYNTAX)-pretty.pp.af
CTREE      = ../org.strategoxt.imp.metatooling/src/include/sdf2imp.ctree

SDF2IMP    = ../org.strategoxt.imp.metatooling/sdf2imp
ESVS       = ${wildcard src/*.esv}

all : src/include/$(SYNTAX).packed.esv $(CTREE) src/include/rtg.tbl src/include/EditorService-pretty.pp.af
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
	[ -e src/include ] || mkdir src/include
	sdf2table -i $< -o $@ -m $(SYNTAX)

# We need a better solution for this
src/include/EditorService-pretty.pp.af : $(PPAF)
	cp $< $@

src/include/rtg.tbl : ~/.nix-profile/share/sdf/stratego-regular/rtg.tbl
	baf2trm  -i $< -o $@ # cp $< $@
