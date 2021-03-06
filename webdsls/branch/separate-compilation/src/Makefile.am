include $(top_srcdir)/Makefile.xt
include $(wildcard *.dep)
include $(wildcard org/webdsl/dsl/*/*.dep)

### distribution

# HACK: This project is almost unbuildable with -O2, the default in many configurations
CFLAGS += -O0

CLEANFILES = \
    $(wildcard lib*.c) \
    $(wildcard *.dep) \
    $(wildcard *.rtree) \
    $(wildcard */*.rtree) \
    $(wildcard */*/*.rtree) \
    $(wildcard */*/*/*.rtree) \
    $(wildcard */*/*/*/*.rtree) \
    $(wildcard */*/*/*/*/*.rtree) \
    $(wildcard */*/*/*/*/*/*.rtree) \
    $(wildcard *.ctree) \
    $(wildcard org/webdsl/dsl/*/*.c) \
    $(wildcard org/webdsl/dsl/*/*.o) \
    $(wildcard org/webdsl/dsl/*/*.dep)

FULLCLEANFILES = \
    $(wildcard org/webdsl/dsl/*/*.def) \
    $(wildcard org/webdsl/dsl/*/*.pp.af) \
    $(wildcard org/webdsl/dsl/*/*.rtg) \
	$(wildcard org/webdsl/dsl/*/*.tbl)

# "make clean" leaves syntax intact; use fullclean/distclean

distclean : fullclean

cleaner : fullclean # alias

fullclean :
	$(MAKE) clean
	rm -rf $(FULLCLEANFILES)
	rm -rf src-gen

EXTRA_DIST = \
    $(wildcard *.str) \
    $(wildcard org/webdsl/dsl/*/*.dep) \
    $(wildcard org/webdsl/dsl/*/*.meta) \
    $(wildcard org/webdsl/dsl/*/*.str) \
    $(wildcard org/webdsl/dsl/*/*.sdf) \
    $(wildcard org/webdsl/dsl/*/*.pp) \
    $(wildcard org/webdsl/dsl/*/*/*.str) \
    $(wildcard org/webdsl/dsl/*/*/*.meta) \
    $(wildcard org/webdsl/dsl/*/*/*/*.str) \
    $(wildcard org/webdsl/dsl/*/*/*/*.meta) \
    $(wildcard separation/*.str) \
    $(wildcard separation/*.meta) \
    META-INF/MANIFEST.MF

### developer targets

copy-rtrees-to-lib-dir-for-editor-support :
	rm -rf ../lib
	mkdir ../lib
	cp *.rtree ../lib

only-backend-trans :
	make libback-end-transformations.la
	touch libto-java-servlet.rtree
	touch libto-java-servlet.c
	touch .libs/libto-java-servlet.la
	touch .libs/libto_java_servlet_la-libto-java-servlet.o
	touch .libs/libto_java_servlet_la-libto-java-servlet.lo
	touch .libs/libjava-transformations.la
	touch .libs/libjava-transformations.so
	touch libjava-transformations.str
	touch libjava-transformations.rtree libjava-transformations.c
	make webdslc
	
only-gen :
	make libwebdsl-generator.la
	touch .libs/libto-java-servlet.la
	touch .libs/libback-end-transformations.la
	touch libto-java-servlet.c
	touch libto-java-servlet.rtree
	touch .libs/libto-java-servlet.la
	touch libjava-transformations.c
	touch libjava-transformations.rtree
	touch .libs/libjava-transformations.la
	touch .libs/libwrite-files.la
	touch .libs/libfast-java-pp.la     
	make webdslc
	make install
	
javafiles : $(JAVAS)
.PHONY : javafiles

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

syntax : $(TBLS)

sdfdata_DATA = $(SYNTAXDEFS) $(TBLS) $(SIGS) $(PPTBLS)

PGEN_FLAGS = -m `basename $*`

PPTABLES = \
	$(syntax)-pretty.pp.af \
	$(syntax).tbl \
	$(hql)-pretty.pp.af \
	$(css).tbl \
	$(css)-pretty.pp.af

webdslversionterm.trm :
	echo \"@PACKAGE_VERSION@\" > webdslversionterm.trm 

org/webdsl/dsl/syntax/WebDSL.def: $(wildcard org/webdsl/dsl/syntax/*.sdf) \
                                  org/webdsl/dsl/syntax/MixHQL.sdf

# .str dependencies
                 
FRONTSTRFILES =  $(wildcard ./org/webdsl/dsl/syntax/*.str) \
                 $(wildcard ./org/webdsl/dsl/utils/*.str)
                 
JAVASTRFILES =   $(wildcard ./org/webdsl/dsl/to-java-servlet/*.str) \
                 $(wildcard ./org/webdsl/dsl/to-java-servlet/*/*.str)
            
GENSTRFILES =    $(wildcard ./org/webdsl/dsl/analysis/*.str) \
                 $(wildcard ./org/webdsl/dsl/generation/*.str) \
                 $(wildcard ./org/webdsl/dsl/languages/*.str) \
                 $(wildcard ./org/webdsl/dsl/languages/*/*.str) \
                 $(wildcard ./org/webdsl/dsl/languages/*/*/*.str) \
                 $(wildcard ./org/webdsl/dsl/modules/*.str) \
                 $(wildcard ./org/webdsl/dsl/modules/*/*.str) \
                 $(wildcard ./org/webdsl/dsl/modules/*/*/*.str) \
                 $(wildcard ./org/webdsl/dsl/transformation/*.str) \
                 $(wildcard ./org/webdsl/dsl/typechecker/*.str)
                 
SEPSTRFILES =    $(wildcard ./separation/*.str) \
		 $(wildcard ./separation/*/*.str) \
		 $(wildcard ./separation/*/*/*.str)
                 
BACKEND_TRANS_FILES = \
				 $(wildcard ./org/webdsl/dsl/back-end-transformations/*.str) \
				 $(wildcard ./org/webdsl/dsl/back-end-transformations/*/*.str) 
                    
### pre-parsing Stratego modules

STRFILES = ./webdslc.str \
           $(wildcard ./org/webdsl/dsl/*/*.str) \
           $(wildcard ./org/webdsl/dsl/*/*/*.str) \
           $(wildcard ./org/webdsl/dsl/*/*/*/*.str)

STRINCLUDES = \
	-I . \
	-I $(JAVA_FRONT)/share/java-front \
	-I $(JAVA_FRONT)/share/java-front/languages/java/eblock \
	-I $(JAVA_FRONT)/share/java-front-syntax \
	-I $(GPP)/share/sdf/gpp \
	-I $(GPP)/share/sdf/xml-front \
    -I $(GPP)/share/xml-front \
	-I org/webdsl/dsl/syntax

#	--verbose 2

# UNDONE: $(JAVA_FRONT_STRCFLAGS) --verbose 1

SDFINCLUDES = $(STRINCLUDES)

STRCFLAGS = \
	-la stratego-xtc \
	-la stratego-lib \
	-la stratego-gpp \
	-la stratego-aterm \
	-la stratego-tool-doc \
	-la stratego-sglr \
	-la $(JAVA_FRONT_LIBS) \
	--format-check 0 -O 1 \
	--verbose 3

XTC_IMPORT = $(JAVA_FRONT_XTC)

SDF2RTG_FLAGS = --main `basename $*` --ignore-missing-cons

Mix%.sdf : %.def
	gen-sdf-mix -i $< --main `basename $*` --name Mix`basename $*` -o $@

SCFLAGS = --main $*-main	

if XT_ENABLE_JAVA_BACKEND
  bin_PROGRAMS = webdsl.jar strategoxt.jar
  webdsl_jar_SOURCES =
  strategoxt_jar_SOURCES = 
else
  bin_PROGRAMS = webdslc

webdslc_SOURCES = webdslc.c
webdslc_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
webdslc_LDFLAGS  = $(AM_LDFLAGS)
webdslc_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
                   $(STRATEGO_GPP_CFLAGS) \
                   $(STRATEGO_SGLR_CFLAGS) \
                   $(STRATEGO_TOOL_DOC_CFLAGS) \
                   $(STRATEGO_CFLAGS) \
                   $(JAVA_FRONT_CFLAGS) 
                   
ALL_LIBS = \
                   libwebdsl-front.la \
                   libwebdsl-generator.la \
                   libto-java-servlet.la \
                   libwrite-files.la \
                   libjava-transformations.la \
                   libback-end-transformations.la \
                   libfast-java-pp.la \
		   libsepcomp.la
                   
webdslc_LDADD   = $(LDADD) $(STRATEGO_XTC_LIBS) \
                   $(STRATEGO_GPP_LIBS) \
                   $(STRATEGO_SGLR_LIBS) \
                   $(STRATEGO_TOOL_DOC_LIBS) \
                   $(STRATEGO_LIBS) \
                   $(JAVA_FRONT_LIBS) \
                   $(ALL_LIBS) 

webdslc.c : webdslc.str \
			$(ALL_LIBS)
endif

#### webdsl configuration utility c
#
#webdsl_config_SOURCES = webdsl-config.c
#webdsl_config_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
#webdsl_config_LDFLAGS  = $(AM_LDFLAGS)
#webdsl_config_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
#                   $(STRATEGO_GPP_CFLAGS) \
#                   $(STRATEGO_SGLR_CFLAGS) \
#                   $(STRATEGO_TOOL_DOC_CFLAGS) \
#                   $(STRATEGO_CFLAGS) \
#                   $(JAVA_FRONT_CFLAGS) 
#webdsl_config_LDADD = $(LDADD) $(STRATEGO_XTC_LIBS) \
#                   $(STRATEGO_GPP_LIBS) \
#                   $(STRATEGO_SGLR_LIBS) \
#                   $(STRATEGO_TOOL_DOC_LIBS) \
#                   $(STRATEGO_LIBS) \
#                   $(JAVA_FRONT_LIBS)
#
#webdsl-config.c : webdsl-config.str
#
#### webdsl configuration utility java
#
#if XT_ENABLE_JAVA_BACKEND
#
#webdsl-config.jar : class-webdsl-config
#	$(JAR) cfm webdsl-config.jar META-INF/MANIFEST.MF -C bin/ .  `find src-gen/org/webdsl/webdsl_config -type f | grep -E '(\.tbl|\.af)' | sed 's!src-gen/!-C src-gen/ !'`
#
#class-webdsl-config : $(JAVA_FILES_WEBDSL_CONFIG)
#	$(JAVAC) -cp $(STRATEGOXT_JAR):src-gen:. src-gen/org/webdsl/webdsl_config/Main.java -d bin
#
#JAVA_FILES_WEBDSL_CONFIG= \
#  src-gen/org/webdsl/webdsl_config/Main.java
#	
#src-gen/org/webdsl/webdsl_config/Main.java : webdsl-config.str mkdirs-webdsl-config
#	$(STRJ) $(STRJFLAGS) -m webdsl-config-main -i $< -o $@ -p org.webdsl.webdsl_config
#
#mkdirs-webdsl-config :
#	mkdir -p src-gen/org/webdsl/webdsl_config
#
#endif
#
####

ctrees : webdsl-front.ctree webdsl-generator.ctree to-java-servlet.ctree write-files.ctree java-transformations.ctree back-end-transformations.ctree

###

if XT_ENABLE_JAVA_BACKEND

STRATEGOXT_JAR=$(STRC_JAVA)/share/strc-java/strategoxt.jar
STRJ=$(STRC_JAVA)/bin/strj
STRJFLAGS=-O 3 \
  --verbose 3 \
  -la stratego-lib \
  -la stratego-rtg \
  -la stratego-sglr \
  -la stratego-gpp \
  -la stratego-xtc \
  -la stratego-aterm \
  -la stratego-tool-doc \
  -la java-front \
  -I $(JAVA_FRONT)/share \
  -clean \
  -I org/webdsl/dsl/syntax/ \
  -D PACKAGE_VERSION_TERM=\"@PACKAGE_VERSION@\"
  
JAVAC=`if which ecj >/dev/null; then echo ecj; else echo javac; fi` -J-Xmx512m -J-Xms100m -J-server -J-XX:+UseParallelGC -source 5 -target 5 -nowarn -time -g
JAR=`if which fastjar >/dev/null; then echo fastjar; else echo jar; fi`

JAVAS= \
  src-gen/org/webdsl/webdsl_front/Main.java \
  src-gen/org/webdsl/webdsl_generator/Main.java \
  src-gen/org/webdsl/java_transformations/Main.java \
  src-gen/org/webdsl/java_pp_fake/Main.java \
  src-gen/org/webdsl/write_files/Main.java \
  src-gen/org/webdsl/back_end_transformations/Main.java \
  src-gen/org/webdsl/to_java_servlet/Main.java \
  src-gen/org/webdsl/webdslc/Main.java \
  src-gen/org/webdsl/sepcomp/Main.java

strategoxt.jar: $(STRATEGOXT_JAR)
	cp $(STRATEGOXT_JAR) strategoxt.jar

webdsl.jar : $(JAVAS)
	mkdir -p bin
	$(JAVAC) -cp $(STRATEGOXT_JAR):src-gen:. src-gen/org/webdsl/webdslc/Main.java -d bin
	$(JAR) cfm webdsl.jar META-INF/MANIFEST.MF -C bin/ .  `find src-gen -type f | grep -E '(\.tbl|\.af|\.aterm)' | sed 's!src-gen/!-C src-gen/ !'`

src-gen/org/webdsl/webdsl_front/Main.java : webdsl-front.ctree
	mkdir -p src-gen/org/webdsl/webdsl_front
	@if ! which ecj >/dev/null; then \
	  echo "WARNING: ecj is not installed; using the (much) slower javac compiler instead" >&2; \
	fi
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.webdsl_front -i $< -o $@ 

src-gen/org/webdsl/webdsl_generator/Main.java : webdsl-generator.ctree
	mkdir -p src-gen/org/webdsl/webdsl_generator
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.webdsl_generator -i $< -o $@ -la org.webdsl.webdsl-front -la org.webdsl.sepcomp

src-gen/org/webdsl/java_transformations/Main.java : java-transformations.ctree
	mkdir -p src-gen/org/webdsl/java_transformations
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.java_transformations -i $< -o $@ -la org.webdsl.webdsl-generator -la org.webdsl.to-java-servlet -la org.webdsl.webdsl-front

src-gen/org/webdsl/write_files/Main.java : write-files.ctree
	mkdir -p src-gen/org/webdsl/write_files
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.write_files -i write-files.ctree -o $@ -la org.webdsl.webdsl-front -la org.webdsl.java_pp_fake -la org.webdsl.java_transformations

src-gen/org/webdsl/back_end_transformations/Main.java : back-end-transformations.ctree
	mkdir -p src-gen/org/webdsl/back_end_transformations
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.back_end_transformations -i $< -o $@ -la org.webdsl.webdsl-front -la org.webdsl.write-files

src-gen/org/webdsl/to_java_servlet/Main.java : to-java-servlet.ctree
	mkdir -p src-gen/org/webdsl/to_java_servlet
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.to_java_servlet -i $< -o $@ -la org.webdsl.webdsl-front -la org.webdsl.back-end-transformations -la org.webdsl.java_transformations -la org.webdsl.write_files

src-gen/org/webdsl/sepcomp/Main.java : sepcomp.ctree
	mkdir -p src-gen/org/webdsl/sepcomp
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.sepcomp -i $< -o $@ -la org.webdsl.webdsl-generator -la org.webdsl.webdsl_front

src-gen/org/webdsl/java_pp_fake/Main.java : java-pp-fake.str
	mkdir -p src-gen/org/webdsl/java_pp_fake
	$(STRJ) $(STRJFLAGS) --library -p org.webdsl.java_pp_fake -i $< -o $@

src-gen/org/webdsl/webdslc/Main.java : webdslc.str
	mkdir -p src-gen/org/webdsl/webdslc
	$(STRJ) $(STRJFLAGS) -m webdslc-main -i $< -o $@ -p org.webdsl.webdslc -la org.webdsl.webdsl-generator -la org.webdsl.to-java-servlet -la org.webdsl.webdsl-front -la org.webdsl.java-transformations -la org.webdsl.write-files -la org.webdsl.back-end-transformations -la org.webdsl.sepcomp
endif

### libraries

nobase_data_DATA = libwebdsl-front.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  = libwebdsl-front.la 

libwebdsl_front_la_SOURCES = libwebdsl-front.c
libwebdsl_front_la_CFLAGS   = $(AM_CFLAGS) -O0 $(STRATEGO_LIB_CFLAGS)
libwebdsl_front_la_LDFLAGS  = $(AM_LDFLAGS)
libwebdsl_front_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
                              $(STRATEGO_GPP_CFLAGS) \
                              $(STRATEGO_SGLR_CFLAGS) \
                              $(STRATEGO_ATERM_CFLAGS) \
                              $(STRATEGO_TOOL_DOC_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libwebdsl_front_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			                  $(STRATEGO_GPP_LIBS) \
			                  $(STRATEGO_SGLR_LIBS) \
			                  $(STRATEGO_ATERM_LIBS) \
                              $(STRATEGO_TOOL_DOC_LIBS) \
			                  $(STRATEGO_LIBS)
endif

libwebdsl-front.rtree libwebdsl-front.c : $(TBLS) $(SIGS) $(PPTABLES) $(PPTBLS) $(SYNTAXDEFS) $(FRONTSTRFILES:.str=.rtree) webdslversionterm.trm
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/syntax/webdsl-front.str -o libwebdsl-front.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libwebdsl-front.str

webdsl-front.ctree : libwebdsl-front.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/syntax/webdsl-front.str -o webdsl-front.ctree \
		--verbose 1 $(STRCFLAGS) 

## generator

nobase_data_DATA += libwebdsl-generator.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libwebdsl-generator.la 

libwebdsl_generator_la_SOURCES = libwebdsl-generator.c
libwebdsl_generator_la_CFLAGS   = $(AM_CFLAGS) -O0 $(STRATEGO_LIB_CFLAGS)
libwebdsl_generator_la_LDFLAGS  = $(AM_LDFLAGS)
libwebdsl_generator_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
                              $(STRATEGO_GPP_CFLAGS) \
                              $(JAVA_FRONT_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libwebdsl_generator_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			      $(STRATEGO_GPP_LIBS) \
                              $(JAVA_FRONT_LIBS) \
			      $(STRATEGO_LIBS)
endif

libwebdsl-generator.rtree libwebdsl-generator.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) $(GENSTRFILES:.str=.rtree) libwebdsl-front.rtree libsepcomp.rtree
	strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/generation/webdsl-generator.str -o libwebdsl-generator.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libwebdsl-generator.str

webdsl-generator.ctree : libwebdsl-generator.rtree  
	strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/generation/webdsl-generator.str -o webdsl-generator.ctree \
		--verbose 1 $(STRCFLAGS) 

### java transformations

nobase_data_DATA += libjava-transformations.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libjava-transformations.la 

libjava_transformations_la_SOURCES = libjava-transformations.c
libjava_transformations_la_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
libjava_transformations_la_LDFLAGS  = $(AM_LDFLAGS)
libjava_transformations_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS)
libjava_transformations_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			      $(STRATEGO_GPP_LIBS) \
			      $(STRATEGO_LIBS)
endif

libjava-transformations.rtree libjava-transformations.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) libwebdsl-front.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/java-transformations/java-transformations.str -o libjava-transformations.rtree \
		--verbose 1 $(STRCFLAGS)
	rm -f libjava-transformations.str

java-transformations.ctree : libjava-transformations.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/java-transformations/java-transformations.str -o java-transformations.ctree \
		--verbose 1 $(STRCFLAGS)
		
### back-end transformations

nobase_data_DATA += libback-end-transformations.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libback-end-transformations.la 

libback_end_transformations_la_SOURCES = libback-end-transformations.c
libback_end_transformations_la_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
libback_end_transformations_la_LDFLAGS  = $(AM_LDFLAGS)
libback_end_transformations_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS)\
                              $(STRATEGO_GPP_CFLAGS) \
                              $(JAVA_FRONT_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libback_end_transformations_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			      $(STRATEGO_GPP_LIBS) \
                              $(JAVA_FRONT_LIBS) \
			      $(STRATEGO_LIBS)
endif


libback-end-transformations.rtree libback-end-transformations.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) $(BACKEND_TRANS_FILES:.str=.rtree) libwebdsl-front.rtree libwrite-files.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/back-end-transformations/back-end-transformations.str -o libback-end-transformations.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libback-end-transformations.str

back-end-transformations.ctree : libback-end-transformations.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/back-end-transformations/back-end-transformations.str -o back-end-transformations.ctree \
		--verbose 1 $(STRCFLAGS) 

### java servlet back-end

nobase_data_DATA += libto-java-servlet.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libto-java-servlet.la 

libto_java_servlet_la_SOURCES = libto-java-servlet.c
libto_java_servlet_la_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
libto_java_servlet_la_LDFLAGS  = $(AM_LDFLAGS)
libto_java_servlet_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS)\
                              $(STRATEGO_GPP_CFLAGS) \
                              $(JAVA_FRONT_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libto_java_servlet_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			      $(STRATEGO_GPP_LIBS) \
                              $(JAVA_FRONT_LIBS) \
			      $(STRATEGO_LIBS)
endif


libto-java-servlet.rtree libto-java-servlet.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) $(JAVASTRFILES:.str=.rtree) libwebdsl-front.rtree libback-end-transformations.rtree libwrite-files.rtree libjava-transformations.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/to-java-servlet/to-java-servlet.str -o libto-java-servlet.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libto-java-servlet.str

to-java-servlet.ctree : libto-java-servlet.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/to-java-servlet/to-java-servlet.str -o to-java-servlet.ctree \
		--verbose 1 $(STRCFLAGS) 

## fast pretty-printer

if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libfast-java-pp.la 

libfast_java_pp_la_SOURCES = fast-java-pp.c
libfast_java_pp_la_LIBADD  = $(STRATEGO_XTC_LIBS)   $(STRATEGO_LIB_LIBS)   $(STRATEGO_RUNTIME_LIBS)   $(ATERM_LIBS)
libfast_java_pp_la_CFLAGS  = $(STRATEGO_XTC_CFLAGS) $(STRATEGO_LIB_CFLAGS) $(STRATEGO_RUNTIME_CFLAGS) $(ATERM_CFLAGS) $(AM_CFLAGS)
libfast_java_pp_la_LDFLAGS = -avoid-version -no-undefined $(AM_LDFLAGS)

endif

### rtg

Java-EBlock.rtg.af : $(JAVA_FRONT)/share/java-front/Java-EBlock.rtg
	parse-rtg -i $< -o $@

xml-doc.rtg.af : $(GPP)/share/xml-front/xml-doc.rtg
	parse-rtg -i $< -o $@

### write files

nobase_data_DATA += libwrite-files.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libwrite-files.la 

libwrite_files_la_SOURCES = libwrite-files.c
libwrite_files_la_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
libwrite_files_la_LDFLAGS  = $(AM_LDFLAGS)
libwrite_files_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
                              $(STRATEGO_GPP_CFLAGS) \
                              $(JAVA_FRONT_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libwrite_files_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
			      $(STRATEGO_GPP_LIBS) \
                              $(JAVA_FRONT_LIBS) \
			      $(STRATEGO_LIBS)
endif

libwrite-files.rtree libwrite-files.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) libwebdsl-front.rtree Java-EBlock.rtg.af xml-doc.rtg.af libjava-transformations.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/org/webdsl/dsl/write-files/write-files.str -o libwrite-files.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libwrite-files.str

write-files.ctree : libwrite-files.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/org/webdsl/dsl/write-files/write-files.str -o write-files.ctree \
		--verbose 1 $(STRCFLAGS) 

### separate compilation

nobase_data_DATA += libsepcomp.rtree 
if !XT_ENABLE_JAVA_BACKEND
  lib_LTLIBRARIES  += libsepcomp.la 

libsepcomp_la_SOURCES = libsepcomp.c
libsepcomp_la_CFLAGS   = $(AM_CFLAGS) -O0  $(STRATEGO_LIB_CFLAGS)
libsepcomp_la_LDFLAGS  = $(AM_LDFLAGS)
libsepcomp_la_CPPFLAGS = $(STRATEGO_XTC_CFLAGS) \
                              $(STRATEGO_GPP_CFLAGS) \
                              $(JAVA_FRONT_CFLAGS) \
                              $(STRATEGO_CFLAGS)
libsepcomp_la_LIBADD   = $(STRATEGO_XTC_LIBS) \
            $(STRATEGO_LIBS)
endif

libsepcomp.rtree libsepcomp.c : $(TBLS) $(SIGS) $(PPTABLES) $(SYNTAXDEFS) $(SEPSTRFILES)
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -i $(srcdir)/separation/methods/array/validity-forwards.str -o libsepcomp.rtree \
		--verbose 1 $(STRCFLAGS) 
	rm -f libsepcomp.str

sepcomp.ctree : libsepcomp.rtree
	$(STRC)/bin/strc $(STRINCLUDES) -c --library -O 1 -F -i $(srcdir)/separation/methods/array/validity-forwards.str -o sepcomp.ctree \
		--verbose 1 $(STRCFLAGS) 

