include $(top_srcdir)/Makefile.xt
include $(wildcard *.dep)
include $(wildcard org/webdsl/dsl/*/*.dep)

### distribution

CLEANFILES = \
    rtrees \
    $(wildcard *.c) \
    $(wildcard *.dep) \
    $(wildcard *.rtree) \
    $(wildcard org/webdsl/dsl/*/*.def) \
    $(wildcard org/webdsl/dsl/*/*.tbl) \
    $(wildcard org/webdsl/dsl/*/*.rtg) \
    $(wildcard org/webdsl/dsl/*/*.c) \
    $(wildcard org/webdsl/dsl/*/*.o) \
    $(wildcard org/webdsl/dsl/*/*.dep) \
    $(wildcard org/webdsl/dsl/*/*.pp.af) \
    $(wildcard org/webdsl/dsl/*/*.rtree) \
    $(wildcard org/webdsl/dsl/modules/*/*.rtree) 

EXTRA_DIST = \
    $(wildcard *.str) \
    $(wildcard org/webdsl/dsl/*/*.dep) \
    $(wildcard org/webdsl/dsl/*/*.meta) \
    $(wildcard org/webdsl/dsl/*/*.str) \
    $(wildcard org/webdsl/dsl/*/*.sdf) \
    $(wildcard org/webdsl/dsl/*/*.pp) \
    $(wildcard org/webdsl/dsl/modules/*/*.str) \
    $(wildcard org/webdsl/dsl/modules/*/*.meta)

### syntax definitions

syntax 		= org/webdsl/dsl/syntax/WebDSL
hql 		= org/webdsl/dsl/syntax/HQL
mixhql 		= org/webdsl/dsl/syntax/MixHQL
css 		= org/webdsl/dsl/syntax/CSS
mixcss 		= org/webdsl/dsl/syntax/StrategoCSS
metasyntax 	= org/webdsl/dsl/syntax/StrategoWebDSL
mixmetasyntax 	= org/webdsl/dsl/syntax/Stratego-WebDSL-Java-XML

SYNTAXDEFS = $(syntax).def					  \
	     $(mixcss).def $(metasyntax).def $(mixmetasyntax).def \
	     $(hql).def $(css).def  # used for mixing		  

TBLS   = $(syntax).tbl $(metasyntax).tbl $(mixmetasyntax).tbl $(mixcss).tbl 

SIGS   = $(hql).rtg $(hql).str $(css).rtg $(css).str $(syntax).rtg $(syntax).str

PPTBLS = $(hql)-pretty.pp.af $(css)-pretty.pp.af $(syntax)-pretty.pp.af

sdfdata_DATA = $(SYNTAXDEFS) $(TBLS) $(SIGS) $(PPTBLS)

PGEN_FLAGS = -m `basename $*`

PPTABLES = \
	$(syntax)-pretty.pp.af \
	$(syntax).tbl \
	$(hql)-pretty.pp.af \
	$(css).tbl \
	$(css)-pretty.pp.af

org/webdsl/dsl/syntax/WebDSL.def: org/webdsl/dsl/syntax/MixHQL.sdf

### pre-parsing Stratego modules

STRFILES = $(wildcard ./org/webdsl/dsl/*/*.str) \
	   $(wildcard ./org/webdsl/dsl/*/*/*.str)

# bogus dependency to force pre-parsing of .str files
rtrees : $(STRFILES:.str=.rtree)
	touch rtrees

STRINCLUDES = \
	-I . \
	-I $(JAVA_FRONT)/share/java-front \
	-I $(JAVA_FRONT)/share/java-front/languages/java/eblock \
	-I $(JAVA_FRONT)/share/java-front-syntax \
	-I $(GPP)/share/sdf/gpp \
	-I $(XML_FRONT)/share/sdf/xml-front \
	-I org/webdsl/dsl/syntax \
	-I org/webdsl/dsl/modules \
	# UNDONE: $(JAVA_FRONT_STRCFLAGS) --verbose 1

SDFINCLUDES = $(STRINCLUDES)

STRCFLAGS = \
	-la stratego-xtc \
	-la stratego-lib \
	-la stratego-gpp \
	-la stratego-tool-doc \
	-la stratego-sglr \
	-la $(JAVA_FRONT_LIBS) \
	--format-check 0

XTC_IMPORT = $(JAVA_FRONT_XTC)

# Disable some of the bogus warnings:
# - multiple external references are the unfortunate effect of librarification and is out of our control right now
# - nullary constructor warnings are not interesting unless they're lowercase imho, like 'top'
#   specifically, you don't want warnings for nullary constructor builds, especially for overlays (e.g., |[ e_UTILS.isInstance(...) ]|)
#
SCLIMITWARNS = 2>&1 | grep -vE 'warning ] Nullary constructor .*[A-Z]|warning ] multiple external| \[ExtSDef\("' \
               >&2

SDF2RTG_FLAGS = --main `basename $*`

Mix%.sdf : %.def
	gen-sdf-mix -i $< --main `basename $*` --name Mix`basename $*` -o $@

SCFLAGS = --main $*-main	

bin_PROGRAMS = dsl-to-seam

dsl_to_seam_LDADD = $(LDADD) $(JAVA_FRONT_LIBS) $(STRATEGO_GPP_LIBS) $(STRATEGO_SGLR_LIBS) $(STRATEGO_TOOL_DOC_LIBS) \
                    libwebdsl-front.la

dsl-to-seam.c : dsl-to-seam.str rtrees

### libraries

nobase_data_DATA = libwebdsl-front.rtree 
lib_LTLIBRARIES  = libwebdsl-front.la 

libwebdsl_front_la_SOURCES = libwebdsl-front.c
libwebdsl_front_la_CFLAGS   = $(AM_CFLAGS)
libwebdsl_front_la_LDFLAGS  = -avoid-version -no-undefined $(AM_LDFLAGS)
libwebdsl_front_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS)
libwebdsl_front_la_LIBADD   = $(STRATEGO_XTC_UNINSTALLED_LIBS) \
			      $(STRATEGO_GPP_UNINSTALLED_LIBS) \
			      $(STRATEGO_UNINSTALLED_LIBS)

libwebdsl-front.rtree libwebdsl-front.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) 
	strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/syntax/webdsl-front.str -o libwebdsl-front.rtree \
		--verbose 1 $(STRCFLAGS) $(SCLIMITWARNS)
	rm libwebdsl-front.str
