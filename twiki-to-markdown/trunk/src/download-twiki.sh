#! /bin/sh

echo "" > inserts.msql

for web in *
do
  if test -d ${web}
  then
    for file in $web/*.txt
    do

      topic=`basename ${file} .txt`

      echo "downloading ${web}/${topic}"

#      wget http://www.stratego-language.org/${web}/${topic}?skin=plain -O ${web}/${topic}.html

      html2xhtml ${web}/${topic}.html > ${web}/${topic}.xhtml

      parse-xml-info -i ${web}/${topic}.xhtml |\
	  ${HOME}/webdsl/twiki-to-markdown/src/twiki-to-markdown  > ${web}/${topic}.md

      sed s/\'/\\\'/g  ${web}/${topic}.md > ${web}/${topic}.md.esc 

#      cp ${web}/${topic}.md ${web}/${topic}.md.esc 

      #echo curl -F "j_id86:j_id91:j_id92=${topic}" \
      #	   -F "j_id86:j_id98:j_id99=<${web}/${topic}.md" \
      #	   http://localhost:8080/strategoxt/newPage.seam

      echo "insert into TwikiPage (_key, _content) " \
           " values ('${topic}', '`cat ${web}/${topic}.md`');" \
	   >> inserts.msql
    done
  fi
done


#/strategoxt/newPage.seam

#Name: j_id86:j_id91:j_id92

#Content: j_id86:j_id98:j_id99

