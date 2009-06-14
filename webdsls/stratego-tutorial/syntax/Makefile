
all: WebDSL.def WebDSL.tbl WebDSL.str

clean:
	rm -f WebDSL.def WebDSL.tbl WebDSL.str

%.def : %.sdf $(wildcard *.sdf)
	pack-sdf -i $< -o $@

%.tbl : %.def
	sdf2table -i $< -o $@ -m $* 2>/dev/null

%.rtg : %.def
	sdf2rtg -i $< -o $@ -m $*

%.str : %.rtg
	rtg2sig -i $< -o $@
