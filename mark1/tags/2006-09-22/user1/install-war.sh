#! /bin/sh

webappdir=../../tomcat1/webapps

ant dist

./shutdown.sh

rm -rf ${webappdir}/user1*

cp -f dist/lib/user1.war ${webappdir}

mysqld --datadir=/suse9.1/webdsl/db1/ &

./startup.sh
