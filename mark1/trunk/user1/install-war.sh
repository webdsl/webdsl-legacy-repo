#! /bin/sh

webappdir=../../tomcat1/webapps

./shutdown.sh

ant dist

rm -rf ${webappdir}/user1*

cp -f dist/lib/user1.war ${webappdir}

./startup.sh

