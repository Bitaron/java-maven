#!/bin/bash
env="$1"
if [ $env = "DEV" ]
then
    mvn clean package -Pdevelopment
    echo "Deploying to tomcat .."
    cp -a ./target/*.war /var/lib/tomcat8/webapps
    echo "Restarting tomcat .."
    sudo service tomcat8 restart
    currentDate=`date +%Y-%m-%d`
    tail -f /var/log/tomcat8/"localhost.$currentDate.log" /var/log/tomcat8/catalina.out
else
    echo "$env"
fi



















########################### TOMCAT CHEAT SHEET #########################
# sudo service tomcat{v} restart/start/stop
# configuration file : /etc/tomcat{v}/