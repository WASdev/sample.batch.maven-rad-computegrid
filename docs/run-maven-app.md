### Import your project into RAD to deploy to WebSphere Application Server

Note: These instructions assume you already have an existing WebSphere Application Server install along with a profile ready to be used from RAD, but you can also create a new profile at one point below if you haven't already. 

1. This assumes you have already built the modules with `mvn install` from the parent module, and, like in this sample archetype, are using an EE 5.0 level EAR or above.
1. Import your Maven projects
    * *File->Import->Maven->Existing Maven Projects*
    * In the  "Root Directory" entry field, enter the top-level directory of the **MyBatch-parent** project.   The "Projects" panel will be populated with the parent and its three children:  **MyBatch**, **MyBatchEJB**, and **MyBatchEAR**.
    * Click the *Finish* button.
1. Create a new Server definition referencing your WebSphere Application Server install, and add your application 
    * *Ctrl+N* and select the *Server* wizard.
    * Select a server type of *IBM->WebSphere Application Server traditional V9.0*
        * **Note*:* Older versions of RAD may use a different variant of this name
    * For "Server runtime environment" choose "Create a new runtime environment"
    * Click the *Next* button
    * On the **WebSphere Application Server Traditional Runtime Environment** page, fill in the "Installation directory" value of your WebSphere Application Server 9.0 install.
        * Note the "Name" value, it might be pre-selected as "WebSphere Application Server traditional V9.0" but you can change it if not.
    * Click the *Next* button.
    * On the **WebSphere Application Server Traditional Settings** page, fill in your profile details, (assuming you have already created one).
        * If you haven't created a profile already, you can click the "Configure profiles..." link to do so.
    * Make sure the checkbox "Run server with resources within the workspace" remains checked.
    * Click the *Next* button.
    * On the **Add and Remove** page, select your EAR, **MyBatchEAR(MyBatchEAR-1.0-SNAPSHOT)** from the "Available" panel, and click the *Add* button, and watch it get added to the "Configured" panel.
    * Click the *Finish* button.  (You might still see a red X at this point).
1. To enable EJB deploy, set target runtime on EAR project
    * Select your **MyBatchEAR** project in a view such as **Enterprise Explorer** and right-click and select *Properties*.
    * Select *Targeted Runtimes* and then click the checkbox matching the "Name" earlier chosen for your Runtime Environment, (e.g. "WebSphere Application Server traditional V9.0"), then click the *OK* button.
1. Continue enabling EJB deploy by adjusting EJB project Deployment Assembly
	* Select your **MyBatchEJB** project in a view such as **Enterprise Explorer** and right-click and select *Properties*.
	* Select *Deployment Assembly* and make sure you only have a single entry with *Source* of **/target/ejbModule** mapped to *Deploy Path* of **/** (the "root" path, i.e. a single forward slash).  In particular, if you have an entry with *Source* of **.apt_generated** then be sure to select it and click*Remove*.   When you are done, click *Apply*.
	* **NOTE:** If you miss this step and do this "too late", and the EJB deploy below doesn't do anything, you might have to try things like closing and re-opening the project to get this type of change picked up.
1. Run EJB deploy     
    * Select your **MyBatchEAR** project again, right-click and select *Java EE->Prepare for Deployment*.
        * **Note:** You will see RMIC invoked and the EJB deploy classes generated into the **MyBatchEJB/target/ejbModule** directory  
    * Make sure the project builds in RAD.  E.g. do a *Project->Build Project* if the "Build Automatically" setting is not selected.
1. Run the sample batch job (and allow the server to auto-configure for batch the first time). 
    * Start the server:  In the **Servers** view, right-click the Server you created and added the EAR to and select *Start*.
    * If this is a new server, after the "open for e-business" message appears, the server  will detect that a batch application has been deployed to it and configure itself as a batch endpoint by installing the "Portable Grid Container" on it.
        * In the console output you'll see something like:
        
            	[7/22/16 9:59:13:593 EDT] 0000006f InstallSchedu I   ADMA5013I: Application PGCController_IBM341-R9Z20GKNode08_server1 installed successfully.    ADMA5013I: Application PGCController_IBM341-R9Z20GKNode08_server1 installed successfully.    
            	[7/22/16 9:59:13:593 EDT] 0000004d SystemOut     O **** INSTALL COMPLETE **** 
            	[7/22/16 9:59:13:640 EDT] 0000006a AppTypeDeploy I   CWLRB5868I: The Portable Grid Container has been configured on WebSphere:name=server1,process=server1, ......
            	...
            	...
            	[7/22/16 9:59:15:200 EDT] 00000067 ApplicationMg A   WSVR0221I: Application started: MyBatchEAR
            	[7/22/16 9:59:15:200 EDT] 00000067 CompositionUn A   WSVR0191I: Composition unit WebSphere:cuname=MyBatchEAR in BLA WebSphere:blaname=MyBatchEAR started.
        
            
        * You can ignore an exception like:
        
            	[7/22/16 9:59:13:625 EDT] 0000006a SystemErr     R java.lang.NullPointerException
            	[7/22/16 9:59:13:625 EDT] 0000006a SystemErr     R     at com.ibm.ws.batch.admin.utils.ConfigUtils.syncActiveNodes(ConfigUtils.java:1534)
            	[7/22/16 9:59:13:625 EDT] 0000006a SystemErr     R     at com.ibm.ws.batch.admin.utils.ConfigUtils.syncActiveNodes(ConfigUtils.java:1571)
            	...

    
    * In a view such as **Enterprise Explorer**, expand the **MyBatch** project directory tree and select file **xJCL/MyJob.xml**.   Right-click and select *Run As->Modern Batch Job*. 
        * Note you get the normal dialog in which you can use "Update substitution properties" to provide overrides on job submission, for the input and output filenames in this sample.  You may want/need to override the default values of **/tmp/in.txt** and **/tmp/out.txt**.
    * Click the *Run* button.
    * You should get a dialog titled "Configuring the Server for Batch Jobs" asking if you want to proceed with automatic configuration of the server for handling batch jobs.  As the dialog mentions this will involve a server restart and can take a minute or so.  This configures the batch Scheduler component on the server.
        * In some order, you will now see the Scheduler system application start:
        
           
            	[7/22/16 10:30:04:931 EDT] 0000008a ServletWrappe I com.ibm.ws.webcontainer.servlet.ServletWrapper init SRVE0242I: [LongRunningScheduler] [/jmc] [/invalidSession.jsp]: Initialization successful.
            	[7/22/16 10:30:05:066 EDT] 0000008a ServletWrappe I com.ibm.ws.webcontainer.servlet.ServletWrapper init SRVE0242I: [PGCProxyController] [/PGCProxyController] [PortableGridContainerProxyServlet]: Initialization successful.
            
        * and the job execute:
    
            	[7/22/16 10:30:04:429 EDT] 00000092 JobManagerImp I   CWLRB1860I: [07/22/16 10:30:04:429 EDT] Dispatching Job [MyJob:000000] Step [MyProcessor]
            	[7/22/16 10:30:04:493 EDT] 00000092 SystemOut     O INFO->jobid: MyJob:000000:GenericXDBatchStep.destroyStep()- Total Execution Time: 20
            	[7/22/16 10:30:04:497 EDT] 00000092 BatchJobContr I   CWLRB3800I: [07/22/16 10:30:04:497 EDT] Job [MyJob:000000] ended normally.

	    * **Note:** The first time you start the server after configuring it as a scheduler, you may see the message:  "Server server1 open for e-business, problems occurred during startup" in the console log.   One reason this can happen is because the server was already previously configured as a batch endpoint by a previous instruction.
    * Observe the effects of the job my looking at the output text file: **out.txt.MyJob.0000000**.   Note how the input file has been transformed by the logic in the **BatchRecordProcessor** and output **FileWriterPattern**
1. At this point, you can submit another job, and it should execute more quickly since the server is already configured.


### Troubleshooting

1. If you get a "Problem Occurred" pop-up dialog with the *Details* expanding to show the error message:  "Start the server before submitting a job", make sure you have the correct "Target server" selected in the drop down from within the "WebSphere Application Server Batch Job" Run Configuration dialog.

## Next step

* [Next article](update-maven-app.md) - Now that you've developed an initial sample, you can customize it, evolve it, develop your own application artifacts.
	
