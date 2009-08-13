#!/bin/sh
source $stdenv/setup

# Fix the permissions

fixPermissions()
{
    find . -type f | while read i
    do
	chmod 644 "$i"
    done

    find . -type d | while read i
    do
	chmod 755 "$i"
    done
}

# Copy template files
cp -av $webdsl/share/webdsl/template-java-servlet/* .
fixPermissions

# Copy the source code
cp -av $src/* .
fixPermissions

# Replace all template variables with WebDSL variables

(find . -name Makefile; find . -name build.xml; find . -name \*.properties; find . -name \*.java) | while read i
do
    sed -i -e "s|@@APPNAME@@|$name|" \
           -e "s|@@DSLTOSEAM@@|$webdsl/bin/webdslc|" \
           -e "s|@@DBSERVER@@|$databaseServer|" \
	   -e "s|@@DBNAME@@|$databaseName|" \
	   -e "s|@@DBUSER@@|$databaseUser|" \
	   -e "s|@@DBPASSWORD@@|$databasePassword|" \
	   -e "s|@@DBMODE@@|$databaseMode|" \
	   -e "s|@@SMTPHOST@@|$smtpHost|" \
	   -e "s|@@SMTPPORT@@|$smtpPort|" \
	   -e "s|@@SMTPUSER@@|$smtpUser|" \
	   -e "s|@@SMTPSSL@@|$smtpSSL|" \
	   -e "s|@@SMTPTLS@@|$smtpTLS|" \
	   -e "s|@@SESSIONTIMEOUTOPTION@@||" \
	   -e "s|@@STATISTICSOPTION@@||" \
	   -e "s|@@FASTPPOPTION@@||" \
	   -e "s|@@VERBOSEOPTION@@||" \
	   -e "s|@@DEBUGOPTION@@||" \
	   -e "s|@@DBSTORAGEOPTION@@|org.hibernate.dialect.MySQL5InnoDBDialect|" \
	   -e "s|@@DEBUGHIBERNATE@@||" \
        $i
done

# Build the WebDSL application
make

# Build the generated Java webapplication
ant war

# Copy the Java webapplication into the Nix store

ensureDir $out/webapps
cp $name.war $out/webapps
