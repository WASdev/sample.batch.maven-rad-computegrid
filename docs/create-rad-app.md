# Creating your application from RAD (initially)

## Create new projects

1. Do *File->New->Batch Project*.
    * **Note:**  Do NOT choose "Java EE Batch Project"!  (That refers to the new, JSR 352 standard Java Batch support with many enhancements provided in WebSphere Liberty.)
1. Type **RADBatch** as "Project name".
1. Select a WebSphere Application Server traditional V9.0 install as your "Target runtime".
	- You can use the *New Runtime* button to creating a new runtime definition here if you don't have one already.  (Instructions shown [here](create-maven-app.md) as well as readily available in general RAD documentation).
1. Keep the default "Configuration" and click the *Finish* button to take remaining defaults, which will generate the associated **RADBatchEJB** and **RADBatchEAR** projects.
1. **Note:** At this point the EJB project will show a red X, this is expected.
1. Convert the EAR project to EE 5.0 or higher (if it is not already).
	* Right-click EAR project and select *Properties->Project Facets* then select the **EAR** facet and in the **Version** drop-down choose Version **5.0** (or higher).  		
1. Create a new Server and add the **RADBatchEAR** application.
    - Instructions for this are also available in general RAD documentation.  
    - Basically right-click anywhere in the *Servers* view and do *New->Server*.  Select server type of **Websphere Application Server traditional V9.0** and the runtime environment selected in **step 3.** above.  Let the wizard configure your server by mapping to the correct profile and determining the correct ports, etc.   Be sure to leave the **Run server with resources in the workspace** checkbox checked (the default).


## Develop the application

You can develop whatever job(s) you want here, from scratch or by importing existing Java and xJCL resources. 

From here we refer you to the batch tools [documentation](https://www.ibm.com/support/knowledgecenter/SSRTLW_9.0.0/com.ibm.servertools.doc/topics/batch/t_batch_project.html).    Now that you've already created the projects, you can use the tool and all its features however you'd like: to edit the xJCL (XML) job definition file in a design editor, to generate Java code, etc.

Another source is Chapter 17 in the [Rational Application Developer for WebSphere Software V8 Programming Guide](http://www.redbooks.ibm.com/redbooks/pdfs/sg247835.pdf).

Note that there are several different, related runtime products and environments providing the same Java batch programming model:  WebSphere Application Server Version 8.5 & 9.0, WebSphere Compute Grid Version 8.0, the Modern Batch Feature Pack.   Most tooling instructions will apply analogously across all these related products and versions.

## Run the batch job from RAD

There's no particular need to stop at this point and run your job(s).   You can proceed directly to converting to a Maven-friendly project and can choose to only worry about getting your job to run at a later point.   

But, if you are brand new to working with RAD and Batch, it might be helpful to stop and take what you've already done and try running the job(s).

To do so, follow instructions:

1. For instructions with screenshots, also see Section 17.3.4 in the [Rational Application Developer for WebSphere Software V8 Programming Guide](http://www.redbooks.ibm.com/redbooks/pdfs/sg247835.pdf), OR
1. You could look [here](https://www.ibm.com/support/knowledgecenter/SSRTLW_9.0.0/com.ibm.servertools.doc/topics/batch/t_batch_run_config.html)


## Next step

* [Next article](convert-rad-to-maven.md) - Convert RAD projects to Maven projects


