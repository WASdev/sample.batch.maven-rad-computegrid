# Common Setup

## Install WebSphere Application Server dependencies into local Maven repository

Since these JARs are not made publicly-available via a Maven repository, you will have to use your local WebSphere Application Server install image.

### Set shell variable
1. Set shell variable **WAS\_INSTALL\_ROOT** to point to your WebSphere Application Server installation root directory.

    	export WAS_INSTALL_ROOT=/opt/WebSphere/AppServer  # FOR EXAMPLE, CHANGE TO YOUR LOCATION

### Install the **was\_public.jar** API

1. The was_public.jar file is provided by IBM for use as a Maven dependency: 

    	mvn install:install-file -Dfile=$WAS_INSTALL_ROOT/dev/was_public.jar -DpomFile=$WAS_INSTALL_ROOT/dev/was_public-9.0.0.pom

### Install  WAS JARs 

**Note:** these do not come with associated POM files shipped along with WebSphere Application Server.  Though the JARs are shipped with the WebSphere Application Server install, the POMs are a creation of this sample GitHub project.  (In particular, the second one is large and wasn't originally intended as a minimal API dependency JAR). 

1. Install batch APIs:

		mvn install:install-file -Dfile=$WAS_INSTALL_ROOT/lib/batfepapi.jar  -DpomFile=batfepapi-1.0-SNAPSHOT.pom

1. Install batch application dependencies:

		mvn install:install-file -Dfile=$WAS_INSTALL_ROOT/plugins/com.ibm.ws.batch.runtime.jar  -DpomFile=batch-runtime-1.0-SNAPSHOT.pom

1. Install additional JAR for EJB deployment (for the batch "controller" EJB):
 
    	mvn install:install-file -Dfile=$WAS_INSTALL_ROOT/runtimes/com.ibm.ws.ejb.embeddableContainer_9.0.jar -DpomFile=ejb-embeddableContainer-1.0-SNAPSHOT.pom 

**Note:** This can also be done in Eclipse with the wizard: *Import->Maven->Install or deploy an artifact to a Maven repository*.  Screenshots included in [this article](https://www.ibm.com/support/knowledgecenter/was_beta/com.ibm.websphere.wdt.doc/topics/install_server_apis.htm).

## Overview and next steps: create an application

Now that the setup is done...

To create a new application from RAD to later convert into a Maven project, click [here](create-rad-app.md). 

To create a new application from a Maven archetype (template) to later import into RAD, click [here](create-maven-app.md).


