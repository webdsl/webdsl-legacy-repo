
JAVAFRONT = ${HOME}/.nix-profile

GENDIR = ../webdsls/src/datamodel/

all : master.appb

%.appa : %.app
	sglri -p $(GENDIR)/syn/DataModel.tbl -i $< | pp-aterm -o $@
	
%.appb : %.app
	sglri -p $(GENDIR)/syn/DataModel.tbl -i $< | \
	$(GENDIR)/gen/datamodel-to-hbm | \
	pp-aterm -o $@

clean :
	rm $(wildcard *.appa) $(wildcard *.appb)

cleanmaster :
	rm -rf org.webdsl.master.domain
