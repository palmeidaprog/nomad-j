#!/bin/bash
# Script by Paulo R. Almeida Filho 
#<palmeidaprogramming@gmail.com>

read -p "Merge everything with 'sync'? (Y/N) " -n 1 resp
echo    # (optional) move to a new line
if [[ $resp == "y" || $resp == "Y" ]];
then

	echo MERGING SYNC WITH DEVELOPMENT...
	git checkout development
	git merge sync
	echo MERGING DEVELOPMENT WITH MASTER...
	git checkout master
	git merge development
	echo ALL BRANCHES MERGED!
	
fi

