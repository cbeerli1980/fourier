#! /bin/bash

. ~/.bashrc

if [[ $1 == "-h" ]];then
  echo "all '0|1'"
  echo "$headless $stderr sdtout shutdown"
fi 

name=`basename $0 | awk 'BEGIN {FS="."}; {print $1}'`
head=$1
stderr=$2
stdout=$3
shutdown=$4
logfile=$PROJECT_HOME/logs/$name".log"

if [[ $1 == "" ]]
then
  java $name -headless 2>/dev/null
#  exit 0
fi

if [[ $head -eq 0 && $stderr -eq 0 && $stdout -eq 0 ]]
then
  echo "java $name -showhead"
  java $name -showhead
fi

if [[ $head > 0 && $stderr -eq 0 && $stdout -eq 0 ]]
then
  echo "java $name -headless"
  java $name -headless
fi

if [[ $head -eq 0 && $stderr -eq 0 && $stdout > 0 ]]
then
  echo "java $name -showhead > $logfile"
  java $name -showhead > $logfile
fi

if [[ $head > 0 && $stderr > 0 && $stdout -eq 0 ]]
then
  echo "java $name -headless 2>/dev/null"
  java $name -headless 2>/dev/null
fi

if [[ $head -eq 0 && $stderr > 0 && $stdout > 0 ]]
then
  echo "java $name -showhead 2>/dev/null > $logfile"
  java $name -showhead 2>/dev/null > $logfile
fi

if [[ $head -eq 0 && $stderr > 0 && $stdout -eq 0 ]]
then
  echo "java $name -showhead 2>/dev/null"
  java $name -showhead 2>/dev/null
fi

if [[ $head > 0 && $stderr > 0 && $stdout > 0 ]]
then
  echo "java $name -headless 2>/dev/null"
  java $name -headless 2>/dev/null > $logfile
fi

if [[ shutdown -eq 0 ]]
then
  sleep 240
  shutdown now
fi
