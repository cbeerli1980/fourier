#! /bin/bash

for E in `ps -ef | grep firefox | grep -v grep | awk '{print $2}'`; do kill $E ; done
for E in `ps -ef | grep geckodriver | grep -v grep | awk '{print $2}'`; do kill $E ; done
for E in `ps -ef | grep chromium-browser | grep -v grep | awk '{print $2}'`; do kill $E ; done
