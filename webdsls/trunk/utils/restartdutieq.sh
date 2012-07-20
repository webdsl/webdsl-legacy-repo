/var/tomcat/bin/catalina.sh stop
kill -3 `ps axU tomcat | grep /var/tomcat/temp | grep  org.apache.catalina.startup.Bootstrap | awk '{print $1}'`
kill -9 `ps axU tomcat | grep /var/tomcat/temp | grep  org.apache.catalina.startup.Bootstrap | awk '{print $1}'`
NOW=$(date +"%y-%m-%d-%T")
LOGFILE="log-researchr-$NOW.log"
tail -n 10000 /var/tomcat/logs/catalina.out > $LOGFILE
export CATALINA_OPTS="-Xms350m -Xss8m -Xmx8G -Djava.security.egd=file:/dev/./urandom -XX:MaxPermSize=512M -XX:PermSize=512M -XX:-UseGCOverheadLimit -XX:+UseCompressedOops"
/var/tomcat/bin/catalina.sh start
