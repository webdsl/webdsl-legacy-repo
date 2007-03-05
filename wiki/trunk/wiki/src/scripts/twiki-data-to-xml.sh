#! /bin/sh

web=$1

#echo $web

echo "<wiki>"
echo "<topic name=\"$web\">"
echo "<title>$web</title>"
echo "<text></text>"
echo "<subtopics>"

cd $web

for file in *.txt
do

topic=`basename $file .txt`

echo "<topic name=\"$topic\">"
echo "<title>$topic</title>"
echo "<text><![CDATA["
iconv -f ISO-8859-1 -t UTF-8 $file
echo "]]></text>"
echo "</topic>"

done

echo "</subtopics>"
echo "</topic>"
echo "</wiki>"
