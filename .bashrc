# Source global definitions

if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi
#GOOGLE_HOME="/run/user/1000/gvfs/google-drive:host=gmail.com,user=cbeerli1980/My Drive"

# User specific environment
if ! [[ "$PATH" =~ "$HOME/project:$HOME/project/getAssets/shared/*:$HOME/project/lib:$HOME/project/getAssets:$PATH" ]]
then
    PATH="$HOME/project:$HOME/project/getAssets/shared/*:$HOME/project/lib:$HOME/project/getAssets:$PATH"
fi
export PATH
export EDITOR='vim'

# Uncomment the following line if you don't like systemctl's auto-paging feature:
# export SYSTEMD_PAGER=

# User specific aliases and functions

alias lg="gio list -a standard::display-name"

. $HOME/project/.appEnv
