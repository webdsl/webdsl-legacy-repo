
tobuild = WebDSL.def WebDSL.tbl WebDSL.rtg WebDSL.str WebDSL.pp WebDSL-Test.tbl

all : $(tobuild)

clean:
	rm -f $(tobuild) $(wildcard *.trm)

check: $(subst testsuite,res,$(wildcard *.testsuite))


%.def : %.sdf $(wildcard *.sdf)
	pack-sdf -i $< -o $@

%.tbl : %.def
	sdf2table -i $< -o $@ -m $* 2>/dev/null

%.rtg : %.def
	sdf2rtg -i $< -o $@ -m $* 2>/dev/null

%.str : %.rtg
	rtg2sig -i $< -o $@

%.pp : %.def
	ppgen -i $< -o $@

%.res : %.testsuite WebDSL-Test.tbl
	parse-unit -p WebDSL-Test.tbl -i $< -o $@


%.trm : %.app
	sglri -p WebDSL.tbl -i $< | pp-aterm -o $@

%.trm.chk : %.trm 
	format-check --rtg WebDSL.rtg -i $< -o $@

