# configure git environment on pc

These are main steps to set up git on your pc.

## 1. set user name and pwd

you need these two commands to set local user and password:

```git
$ git config --global user.name "tian"
$ git config --global user.email "zht1702@outlook.com"
```

and this command to check them:

```git
$ git config --global --list
```

## 2. generate a ssh public key and find it

### generate

use this command to generate a public key:

```git
$ ssh-keygen -t rsa
```

press `space` when the process stops and ask for any information.

### check

open your user directory and find hidden dir `.ssh`, open it and copy the content, it's the public key.
