#! /bin/bash

ssh $ilreeb mysqldump --password=CityC0penh@gen --user=thething symbols > $PROJECT_SCRIPTS/sql/symbols_export.sql
mysql --password=CityC0penh@gen --user=thething symbols < $PROJECT_SCRIPTS/sql/symbols_export.sql
