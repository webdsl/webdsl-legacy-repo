#! /bin/sh

webappdir=../../tomcat1/webapps

./shutdown.sh

ant dist

rm -rf ${webappdir}/user1*

rm -rf ${webappdir}/work/Standalone/localhost/user1*

cp -f dist/lib/user1.war ${webappdir}

#mysqld_safe --force --datadir=/share/webdsl/db1/ &

mysqld_safe --datadir=/home/eelco/webdsl/db1/ &

./startup.sh
