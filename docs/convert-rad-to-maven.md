## Convert to Maven 


You will make use of these [general instructions](http://www.ibm.com/support/knowledgecenter/en/SSEQTP_8.5.5/com.ibm.websphere.wdt.doc/topics/convert.htm) on converting Java EE projects to Maven.  

Note that because you are using your own application rather than a sample one, the instructions here might be more generic than necessary.   

The notes below try to guide you through those steps by describing the "bare minimum" steps that you need to do.

It will help to scan over the instructions linked above, then read over the notes below, then go back to those instructions and begin executing them, referring to these notes as necessary.   

### Conversion notes

Convert the three projects to Maven, one at a time.  As the instructions recommend to  convert the EAR project last, we'll convert first the **RADBatch** project, then the **RADBatchEJB** project, then the **RADBatchEAR** project.

You can leave the server up through this whole process.  Even if there is a failure before you finish, while RAD tries to publish an incomplete application, you can still eventually publish a successful update.

1. **RADBatch** 
    * Note this project falls in the category of **Connector, Utility, web fragment, and EJB client projects** which gets the *jar* type of Maven packaging.
    * For this project, add the following Maven dependencies (along with, of course, any other dependencies used by your particular application).  You can use the **Dependencies** tab in RAD or paste the following in the **pom.xml** tab (the Source view):


		    <dependencies>
		      <dependency>
		        <groupId>com.ibm.websphere.appserver</groupId>
		        <artifactId>was_public</artifactId>
		        <version>9.0.0</version>
		      </dependency>
		      <dependency>
		        <groupId>com.ibm.ws.scottkurz.waslib</groupId>
		        <artifactId>batch-runtime</artifactId>
		        <version>1.0-SNAPSHOT</version>
		      </dependency>
		      <dependency>
		        <groupId>com.ibm.ws.scottkurz.waslib</groupId>
		        <artifactId>batfepapi</artifactId>
		        <version>1.0-SNAPSHOT</version>
		      </dependency>
		    </dependencies>

    *  If you see a red X you might try clean/rebuild and/or redeploy (Select project then *Alt+Shift+Y*) to make red x's go away.  If not, it might be OK to just ignore.   
      
1.  **RADBatchEJB**
    1. For this project, add the following Maven dependencies, common to any batch project:
    
			<dependencies>
			    <dependency>
			        <groupId>com.ibm.ws.scottkurz.waslib</groupId>
			        <artifactId>batch-runtime</artifactId>
			        <version>1.0-SNAPSHOT</version>
			    </dependency>
			    <dependency>
			        <groupId>com.ibm.ws.scottkurz.waslib</groupId>
			        <artifactId>ejb-embeddableContainer</artifactId>
			        <version>1.0-SNAPSHOT</version>
			    </dependency>
		    </dependencies>

    1. Also add a dependency to the batch/utility project, as a child of the same `<dependencies/>` element immediately above.  This is **RADBatch** in this example:
  
		    <dependency>
                <!-- These would have been the default coordinates from Eclipse -->
		        <groupId>RADBatch</groupId>
		        <artifactId>RADBatch</artifactId>
		        <version>0.0.1-SNAPSHOT</version> 
    		</dependency>

    1. Now configure the *maven-ejb-plugin* plugin like this:

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-ejb-plugin</artifactId>
                <version>2.5.1</version>
                <configuration>
                    <archive>
                        <manifestEntries>
                            <Class-Path>RADBatch-${project.version}.jar</Class-Path>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
     (Without this step you can ONLY run this application within the RAD workspace, but won't be able to build a standalone EAR file with your Maven build.)
    2. Finally, the same comment as for **RADBatch**, if you see a red X  try clean/rebuild/redeploy.
        

1.  **RADBatchEAR**

**Note**: Be sure to note that the instructions for converting the EAR, including working with the *Deployment Assembly*, are a bit more involved than the earlier two projects.

Some notes and pointers:
    
*  When configuring the Maven *Dependencies* you only need to add the **RADBatchEJB** module as a dependency, not the **RADBatch** utility module.  
  
		<dependencies>
			<dependency>
           		<!-- These would have been the default coordinates from Eclipse -->
    	   		<groupId>RADBatchEJB</groupId>
    	   		<artifactId>RADBatchEJB</artifactId>
    	   		<version>0.0.1-SNAPSHOT</version>
				<type>ejb</type>			
    		</dependency>
		</dependencies>
**NOTE**:  The last element of the dependency: `<type>ejb</type>`.  This is important to include rather than to take the default. 

* You will need to add the EJB module to the EAR module by directly editing the pom.xml source (in the **pom.xml** tab).  As mentioned earlier you should be using an EAR which is at EE level of 5.0 or above.

Configure the *maven-ear-plugin* plugin like this:
	
            <plugin>
                <artifactId>maven-ear-plugin</artifactId>
                <version>2.10</version>

                <configuration>
                    <!-- This means an EAR at EE level 5.0 -->
                    <version>5</version> 
                    <modules>
                        <ejbModule>
                            <groupId>RADBatchEJB</groupId>
                            <artifactId>RADBatchEJB</artifactId>
                        </ejbModule>
                    </modules>
                    <packagingIncludes>META-INF/**,**/RADBatch-*.jar,**/RADBatchEJB-*.jar</packagingIncludes>
                </configuration>
            </plugin>
        
Note this `<packagingIncludes>` definition allows for dependencies present in the WebSphere Application Server install to be picked up from the runtime, which results in a much smaller EAR.

* Do a Maven update project.  Right-click EAR project, *Maven->Update Project*.
        
## Run your Mavenized job

The projects have now been converted to Maven.

You can continue running these from RAD, making incremental changes and taking advantage of incremental compilation and automatic publishing of app updates.

You don't need to run this job any differently, now that it's been converted to Maven, than you did before.

But anyway, here are some real basic instructions:

* In a view such as **Enterprise Explorer**, expand the **MyBatch** project directory tree and select file **xJCL/MyJob.xml**.   Right-click and select *Run As->Modern Batch Job*. 
    * Note you get the normal dialog in which you can use "Update substitution properties" to provide overrides on job submission, for the output filename in this sample.
* Click the *Run* button.

We don't go into detail since this path is more well-documented in other RAD batch tooling documentation.

Also some details in running the application from the "Start from Maven" track may been of use:

*  [Running the application](run-maven-app.md).

For more detail we repeat the links from above: 

* [Official RAD batch documentation](https://www.ibm.com/support/knowledgecenter/SSRTLW_9.0.0/com.ibm.servertools.doc/topics/batch/t_batch_project.html).   
* [Rational Application Developer for WebSphere Software V8 Programming Guide](http://www.redbooks.ibm.com/redbooks/pdfs/sg247835.pdf) - (See Chapter 17).

## Do a Maven build

The changes you're making in RAD and running via the RAD Batch tools will get picked up when you do a Maven build of these modules.   

You don't have  to do anything special to switch back and forth between working in RAD and working in Maven.  (There is no way provided in these instructions to run a job from Maven though.)

This point is also shown in a bit more detail here:

*  [Updating the application](update-maven-app.md).


## Next step

* [Next article](other-notes.md) - Some final, additional thoughts and tips.
