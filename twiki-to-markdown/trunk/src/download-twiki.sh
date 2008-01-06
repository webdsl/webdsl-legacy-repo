#! /bin/sh

echo "" > inserts.msql

basedir=`dirname $0`

for web in *
do
  if test -d ${web}
  then
    echo "converting web ${web}"
    # for file in $web/*.txt
    for file in `find ${web} -name \*.txt \
	          -a \( -not \( -name WebStatistics.txt \
                               -o -name WebPreferences.txt \
                               -o -name WebChanges.txt \
                               -o -name WebChanges500.txt \
                               -o -name WebChanges200.txt \
                               -o -name WebChanges100.txt \
                               -o -name WebChanges.txt \
                               -o -name WebRss.txt \
                               -o -name WebSearch.txt \
                               -o -name WebTools.txt \
                               -o -name WebTopicList.txt \
                               -o -name WebIndex.txt \
                               -o -name WebCustomMenus.txt \
                               -o -name WebCustomMenus.txt \
                               -o -name WebNotify.txt \
                               -o -name TWikiUsers.txt \
	                     \) \)`
    do
      
      topic=`basename ${file} .txt`

      #echo ${web}/${topic}

      ${basedir}/download-twiki-topic.sh ${web} ${topic}

    done
  fi
done


