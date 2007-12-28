#! /bin/sh

web=$1
topic=$2

echo "web = ${web} topic = ${topic}"

#curl -b cookies.txt -c cookies.txt http://localhost:8080/strategoxt/home

#curl -b cookies.txt -c cookies.txt \
#   -F "j_id79:j_id85:j_id86=ZefHemel" \
#    -F "j_id79:j_id90:j_id91=secret" \
#    -F "j_id79:j_id94=Sign%20in" \
#    http://localhost:8080/strategoxt/login

curl -v \
     -L -b cookies.txt -c cookies.txt \
     http://localhost:8080/strategoxt/newPage.seam \
     > newPage.form

# obtain (hidden) fields from form

rm -f hidden_* input_*

fields=`parse-xml-info -i newPage.form \
          | ${HOME}/webdsl/twiki-to-markdown/src/extract-fields`

echo ${fields}

# submit

echo "${topic}" > input_0
echo "${topic}" > input_1
cp ${web}/${topic}.md input_2

echo curl -v -L -b cookies.txt -c cookies.txt --raw \
     ${fields} \
     http://localhost:8080/strategoxt/newPage.seam \
     > command

cat command

sh command > xxx

#curl -v \
#     -L -b cookies.txt -c cookies.txt \
#     ${fields} \
#     http://localhost:8080/strategoxt/newPage.seam

