#! /bin/sh 

# setting up development environment

#nix-env -i j2sdk-1.4.2 jakarta-tomcat-5.0.27

JAVA_HOME=$HOME/.nix-profile/

NIXTOMCAT=$HOME/.nix-profile/jakarta-tomcat-5.0.27

#CATALINA_HOME=$NIXTOM

CATALINA_HOME=`pwd`/tomcat
CATALINA_BASE=`pwd`/tomcat

CHOME=$CATALINA_HOME

CLASSPATH=$CHOME/common/lib/servlet-api.jar:`pwd`/src:.
export CLASSPATH

for dir in bin common server shared 
do
  rm -f tomcat/$dir
  ln -s $NIXTOMCAT/$dir tomcat/$dir
done

for dir in webapps
do
  /bin/cp -rf $NIXTOMCAT/$dir tomcat
  chmod -R +w tomcat/$dir
done

for dir in logs temp work
do
  mkdir -p tomcat/$dir
done

