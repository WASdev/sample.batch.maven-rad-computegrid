# Creating your application from a Maven archetype
---

## Introduction

The approach below shows a pattern for creating a batch application as a group of related modules, in a way that is easy to import into RAD.  From within RAD you can continue to work with the application, taking advantage of batch job design features, code generation wizard, all while taking advantage of RAD's incremental compilation and publishing of your application updates onto a live server.

### Sample Archetype
The pattern is captured in a sample Maven archetype, a template for a Maven project, with instructions for creating your own project from this sample archetype.  The archetype sets up a simple job with a single step that reads line-by-line from a text file (via the TextFileReader class), does a simple String transform to the line, and then writes to an output text file (via the TextFileWriter class).   The two xJCL (XML) references to the application name and EJB JNDI name are all set up for you, so this part of the xJCL requires no additional editing.

## Setup using sample archetype (only necessary the first time)

1. Install the sample Maven archetype:

		cd archetype   # IMPORTANT !
		mvn install:install-file -Dfile=./textfile-io-archetype-1.0-SNAPSHOT.jar -DpomFile=./textfile-io-archetype-1.0-SNAPSHOT.pom

    **Note:** It's important to run from the **archetype** subdirectory, since that contains the **pom.xml** that governs the installation of the archetype!

1. Update your local catalog (optional, but makes creation step a bit easier).

		mvn -f textfile-io-archetype-1.0-SNAPSHOT.pom archetype:update-local-catalog

## Create your new project

1. Make new directory and change to this directory:

		mkdir newproject; cd newproject
2. Create your new project from your local catalog:

		mvn archetype:generate -DarchetypeCatalog=local 

    Interactive session shown [here](console-arch-create.md)

    Note we select these values and take the defaults for the rest (the *package* name isn't even used in this sample archetype):

        Define value for property 'groupId': : com.xyz.radbatch
        Define value for property 'artifactId': : MyBatch-parent

1. Customize the file locations in the xJCL file:

    * **Important:** The sample logic requires you point the `IN_FILENAME` substitution property to a text file with some text data in it (the format of the data doesn't matter, it will process one line at a time).  You also need to customize the `OUT_FILENAME` property.  You can also override these property values at submission time, conveniently, in RAD's *Modern Batch Job* "Run configuration".
    
            <substitution-props>
                <prop name="IN_FILENAME" value="/tmp/in.txt" />
                <prop name="OUT_FILENAME" value="/tmp/out.txt" />
            </substitution-props>
        
1. Change to new parent module directory and install:
 
		cd MyBatch-parent;   mvn install
    
    You should see something like:

        ...
        ****** ********** ejb-jar.xml END ********** ******
          
        Successfully packaged EAR file C:\xxxx\newproject\MyBatch-parent\MyBatchEJB\target/MyBatchEAR

## Deploy your EAR to WebSphere Application Server and run in RAD

**Note:** at this point your EAR file is not ready to run !   You must complete the following steps in RAD in order to even run it for the first time. 

If you wanted to add functional and/or integration tests to your Maven build keep this in mind, since they would likely not be ready to run yet with this approach.

## Limitations of archetype

This is just a starting point !

As mentioned in the overview doc, the value of this archetype in this doc is the fact that it shows a path through the special packaging and deploy of the batch application using the "controller" EJB in a manner that allows you to work with the app in RAD at the same time.  

These hard problems are solved!  

On the other hand, there are a number of things you'd probably change before really using this archetype to create multiple applications:  the fact that the projects are always named **MyBatch\***, the location of the Java source (it's not **src/main/java**), etc.


## Next step

* [Next article](run-maven-app.md) - Bring your app into RAD, deploy it to a server, and run it for the first time.
