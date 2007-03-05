#! /bin/sh

webappdir=../../tomcat1/webapps

./shutdown.sh

ant dist

rm -rf ${webappdir}/wiki1*

rm -rf ${webappdir}/work/Standalone/localhost/wiki1*

cp -f dist/wiki1.war ${webappdir}

#mysqld_safe --force --datadir=/share/webdsl/db1/ &

mysqld_safe --host=127.0.0.1 --datadir=/home/eelco/webdsl/db1/ &

./startup.sh
