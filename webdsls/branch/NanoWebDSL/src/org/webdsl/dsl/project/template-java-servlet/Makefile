# Makefile for invoking code generator
# should eventually be integrated in ant build.xml file
# and be invocable from Eclipse

dsltoseam = @@DSLTOSEAM@@

apps = $(wildcard *.app)

all : @@APPNAME@@.appc
	
%.appc : %.app $(apps)
	$(dsltoseam) --servlet -i $< @@ROOTAPPOPTION@@ @@SESSIONTIMEOUTOPTION@@ @@STATISTICSOPTION@@ @@FASTPPOPTION@@

dump :
	mysqldump --user=@@DBUSER@@ --password=@@DBPASSWORD@@ @@DBNAME@@ > db.sql

clean :
	rm $(wildcard *.appc)

