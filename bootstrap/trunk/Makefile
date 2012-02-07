all: rebuild deploy

ibootstrap:
	mkdir stylesheets/bootstrap javascript/bootstrap images/bootstrap
	cp -r bootstrap/css stylesheets/bootstrap
	cp -r bootstrap/js  javascript/bootstrap
	cp -r bootstrap/img images/bootstrap

rebuild:
	webdsl rebuild

deploy:
	webdsl deploy

build:
	webdsl build

clean:
	webdsl clean

css:
	cp -r stylesheets/* /opt/tomcat/webapps/bootstrap/stylesheets
	
server:
	webdsl cleanall war
