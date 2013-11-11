econceptsound
=============

Description
===============
Online Audio Solution use SoundCloud API

License
=============
The MIT License (MIT) Copyright (c) 2013 Kai-Ting (Danil) Ko 
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in  the Software without restriction, including without limitation the rights to use, copy, modify,merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

OpenShift
===============

The Code is currently deployed to OpenShift
http://econcept-econcept.rhcloud.com/econceptsound/

===============

LOCALLY DEPLOYMENT INSTRUCTION
===============

Setup the Postgresql Database Connection

Export following environment variables in the current session, below is the example of OpenShift

SoundCloud Credential
```
#Setup necessary environments for the application to work
export SOUNDCLOUD_URL='https://api.soundcloud.com'
export SOUNDCLOUD_USERNAME='12321@gmail.com'
export SOUNDCLOUD_PASSWORD='adfas12
export SOUNDCLOUD_CLIENT_ID='12312dsf21fdb2134sdfsfas12'
export SOUNDCLOUD_CLIENT_SECRET='1dsfasdf1fdb2dfsc134sdfsfas12'
```

```
# Execute embedded maven
mvn clean package tomcat7:run -DskipTests
```

You should be able to see it in localhost:8080/econceptsound

===============

OPENSHIFT DEPLOYMENT INSTRUCTION
===============

Create Openshift gears and embedded PostgreSQL cartridge

Create JBossews - 2.0 Cartridge

```
#Create JBosses-2.0 gear
rhc app create -a ${GEAR_NAME} -t jbossews-2.0
```

Code Deployment

Need to perform in same session

Export the absolute file path to the root folder that contain this github project
Example

```
export SOURCE_GIT_REPO=/home/danilko/econceptsound
```

Following is a real code

```
#Absolute path to the root folder that conatin this gitub project
#Example
#export SOURCE_GIT_REPO=/home/danilko/econceptsound
export SOURCE_GIT_REPO=/home/danilko/econceptsound
```

Export the absolute file path to the root folder that contain this github project
Example

```
export GEAR_GIT_REPO=/home/danilko/econceptsound_openshift
```

Following is a real code

```
#Absolute path to GEAR_REPO
#Example
#export GEAR_GIT_REPO=/home/danilko/econceptsound_openshift
export GEAR_GIT_REPO=/home/danilko/econceptsound_openshift
```

Set up necessary environment variables

```
#Setup necessary environments for the application to work
rhc set-env -e SOUNDCLOUD_URL='https://api.soundcloud.com' -a ${GEAR_NAME} -p ${RHC_PASSWORD}
rhc set-env -e SOUNDCLOUD_USERNAME='12321@gmail.com' -a ${GEAR_NAME} -p ${RHC_PASSWORD}
rhc set-env -e SOUNDCLOUD_PASSWORD='adfas12' -a ${GEAR_NAME} -p ${RHC_PASSWORD}
rhc set-env -e SOUNDCLOUD_CLIENT_ID='12312dsf21fdb2134sdfsfas12' -a ${GEAR_NAME} -p ${RHC_PASSWORD}
rhc set-env -e SOUNDCLOUD_CLIENT_SECRET='1dsfasdf1fdb2dfsc134sdfsfas12' -a ${GEAR_NAME} -p ${RHC_PASSWORD}
```

Package the war

```
# Execute maven to package the war
cd ${SOURCE_GIT_REPO}
mvn clean package -DskipTests

if [ -f ${GEAR_GIT_REPO}/pom.xml ]
then
	rm ${GEAR_GIT_REPO}/pom.xml 
fi

if [ -d ${GEAR_GIT_REPO}/src ]
then
	rm -rf ${GEAR_GIT_REPO}/src
fi

if [ -d ${GEAR_GIT_REPO}/webapps ]
then
	rm -rf ${GEAR_GIT_REPO}/webapps
	mkdir -p ${GEAR_GIT_REPO}/webapps
fi

if [ -d ${GEAR_GIT_REPO}/.openshift ]
then
	rm -rf ${GEAR_GIT_REPO}/webapps/.openshift/action_hooks
fi

cp ${SOURCE_GIT_REPO}/target/econceptsound.war ${GEAR_GIT_REPO}/webapps
cp -rf ${SOURCE_GIT_REPO}/.openshift/action_hooks ${GEAR_GIT_REPO}/.openshift

# Push the change into the gear
cd ${GEAR_GIT_REPO}
git add -A .
git update-index --chmod=+x .openshift/action_hooks/*
# Get the git revision
git commit -m "SOURCE GIT HEAD `cat ${SOURCE_GIT_REPO}/.git/refs/heads/master`"
git push origin master
```

Example of Complete Script

```
#Absolute path to the root folder that conatin this gitub project
#Example
#export SOURCE_GIT_REPO=/home/danilko/econceptsound
export SOURCE_GIT_REPO=/home/danilko/econceptsound

#Absolute path to the root folder that contain the gear repo
#Example
#export GEAR_GIT_REPO=/home/danilko/econceptsound_openshift
export GEAR_GIT_REPO=/home/danilko/econceptsound_openshift

# Execute maven to package the war
cd ${SOURCE_GIT_REPO}
mvn clean package -DskipTests

if [ -f ${GEAR_GIT_REPO}/pom.xml ]
then
	rm ${GEAR_GIT_REPO}/pom.xml 
fi

if [ -d ${GEAR_GIT_REPO}/src ]
then
	rm -rf ${GEAR_GIT_REPO}/src
fi

if [ -d ${GEAR_GIT_REPO}/webapps ]
then
	rm -rf ${GEAR_GIT_REPO}/webapps
	mkdir -p ${GEAR_GIT_REPO}/webapps
fi

if [ -d ${GEAR_GIT_REPO}/.openshift ]
then
	rm -rf ${GEAR_GIT_REPO}/webapps/.openshift/action_hooks
fi

cp ${SOURCE_GIT_REPO}/target/econceptsound.war ${GEAR_GIT_REPO}/webapps
cp -rf ${SOURCE_GIT_REPO}/.openshift/action_hooks ${GEAR_GIT_REPO}/.openshift

# Push the change into the gear
cd ${GEAR_GIT_REPO}
git add -A .
git update-index --chmod=+x .openshift/action_hooks/*
# Get the git revision
git commit -m "SOURCE GIT HEAD `cat ${SOURCE_GIT_REPO}/.git/refs/heads/master`"
git push origin master
```

===============