#! /bin/bash

basedir=`dirname $0`

web=$1
topic=$2
url=`cat url.txt`

echo "downloading ${web}/${topic}"

# initialize download directory

rm -fr ${web}/${topic}
mkdir -p ${web}/${topic}

##############################################################################

function getMetaData
{
  grep "META:TOPICINFO" ${web}/${topic}.txt,v \
       | sed "s/ /;/g" \
       | sed "s/@//" \
       | sed "s/%META:TOPICINFO{//" \
       | sed "s/}%//" \
       > ${web}/${topic}.meta

  for meta in `cat ${web}/${topic}.meta`
  do
    echo ${meta} | sed "s/;/\n/g" > meta
    . ./meta
    version=`echo ${version} | sed "s/1.//"`
    echo "author=${author}"   > ${web}/${topic}/${version}.meta
    echo "date=${date}"       > ${web}/${topic}/${version}.meta
    echo "version=${version}" > ${web}/${topic}/${version}.meta
  done
}

function download
{
  if test -s $2.html 
  then
    echo "not re-downloading $2.html"
  else
    wget --no-clobber ${url}/$1 -O $2.html
  fi
}

function convert
{
  html2xhtml $1.html > $1.xhtml
  parse-xml-info -i $1.xhtml | ${basedir}/twiki-to-markdown > $1.md
}

function extractUser
{
  parse-xml-info -i $1/$2/last.xhtml | ${basedir}/extract-user > $1/$2/user.sh
  username=""
  fullname=""
  email=""
  homepage=""
  country=""
  affiliation=""
  . $1/$2/user.sh
  if test ${username} = ${topic}
  then
    echo "insert into TwikiUser (_username, _fullname, _email, _homepage, _country, _affiliation) " \
         " values ('${username}', '${fullname}', '${email}', '${homepage}', '${country}', '${affiliation}');" \
         > $1.insert
  else
    echo "${topic} does not define a user"
  fi
}

function insert
{
  echo "insert into TwikiPage (_key, _web, _topic, _date, _version, _author, _content) " \
       " values ('${web}/${topic}_${version}', '${web}', '${topic}', '${date}', '${version}', '${author}', '" \
               > $1.insert
  cat $1.md   >> $1.insert
  echo "');"  >> $1.insert
}

function downloadRevisions 
{
  getMetaData
  for meta in ${web}/${topic}/*.meta
  do
    . ${meta}
    topicrev=${web}/${topic}/${version}
    download ${web}/${topic}?skin=plain\&rev=${version} ${topicrev}
    convert ${topicrev}
    insert ${topicrev}
  done
}

##############################################################################

# download latest version

download ${web}/${topic}?skin=plain ${web}/${topic}/last
convert  ${web}/${topic}/last

if test ${web} = Main
then

  extractUser ${web} ${topic}

else

  downloadRevisions 

fi