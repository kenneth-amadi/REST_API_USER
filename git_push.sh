#!/bin/sh

#Configure app into

txtrst=$(tput sgr0) # Text reset
txtgrn=$(tput setaf 2) # Green
txtylw=$(tput setaf 3) # Yellow
txtblu=$(tput setaf 4) # Blue
txtpur=$(tput setaf 5) # Purple
txtcyn=$(tput setaf 6) # Cyan
txtwht=$(tput setaf 7) # White
txtrst=$(tput sgr0) # Text reset.

git add .
echo "${txtgrn}add unpublished code into current git repository......${txtrst}"
now=$(date +"%Y-%m-%d %T")
echo "${txtgrn}Updated on : $now ${txtrst}"
git commit -am "Updated on : $now"
echo "${txtgrn}commit current code into git repository......${txtrst}"
git push origin
echo "${txtgrn}update current code into git repository......${txtrst}"
