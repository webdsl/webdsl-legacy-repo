all: rebuild deploy

ibootstrap:
	mkdir -p stylesheets/bootstrap javascript/bootstrap images/bootstrap
	cp -r bootstrap/css stylesheets/bootstrap
	cp -r bootstrap/js  javascript/bootstrap
	cp -r bootstrap/img images/bootstrap
	cp -r bootstrap/img stylesheets/bootstrap

rebuild:
	webdsl rebuild

deploy:
	webdsl deploy

build:
	webdsl build

clean:
	webdsl clean

css:
	cp -r stylesheets javascript images /opt/tomcat/webapps/bootstrap/
	
server:
	webdsl cleanall war
