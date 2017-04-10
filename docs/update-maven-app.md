
# Work with your application 

Now you have achieved a Maven-integrated development environment that takes advantage of RAD's incremental compilation with publishing to a live server.  

## Make changes in RAD

1. Edit your xJCL, edit your Java files, and then save your changes. 

2. Wait for the changes to be published.  You will see something like:

		[7/22/16 10:54:05:379 EDT] 00000062 AppBinaryProc I   ADMA7021I: Distribution of application MyBatchEAR completed successfully.
		[7/22/16 10:54:05:381 EDT] 00000062 FileRepositor A   ADMR0009I: Document cells/IBM341-R9Z20GKNode07Cell/applications/MyBatchEAR.ear/deltas/MyBatchEAR/delta-1469199244503 is created.
		[7/22/16 10:54:05:381 EDT] 00000062 FileRepositor A   ADMR0010I: Document cells/IBM341-R9Z20GKNode07Cell/applications/MyBatchEAR.ear/deployments/MyBatchEAR/META-INF/ibm-application-runtime.props is modified.
		[7/22/16 10:54:05:382 EDT] 00000062 FileRepositor A   ADMR0010I: Document cells/IBM341-R9Z20GKNode07Cell/applications/MyBatchEAR.ear/deployments/MyBatchEAR/deployment.xml is modified.
		[7/22/16 10:54:05:382 EDT] 00000062 FileRepositor A   ADMR0010I: Document cells/IBM341-R9Z20GKNode07Cell/nodes/IBM341-R9Z20GKNode08/serverindex.xml is modified.
	
	Note that changing the xJCL (XML) alone might not cause a publish, since this file is not part of the deployed application.

3. Note the changes won't show up instantly, since to avoid continual scanning, the app is scanned for update only so often.  To adjust this interval (which may default to 15 seconds), go to the **Servers** view, and double-click your WebSphere Application Server to open the server editor, and look for the "Publishing" and expand this section.  See [here](http://www.ibm.com/support/knowledgecenter/SSRTLW_9.0.1/com.ibm.servertools.doc/topics/tpublishv6.html) for more info on publishing config.
4.  Run the updated job, again using the *Run As->Modern Batch Job* Run Configuration.   Note the application changes.

### Pick up with RAD changes in Maven

There's nothing to do !   Your Maven build will instantly pick up the updated application with the latest changes.

## Make changes outside of RAD and build with Maven

Edit your files in whatever way and do your Maven build.    

### Pick up your latest Maven changes in RAD, (even with an already started server)

Simply select all your applications in a view like **Enterprise Explorer**, right-click and select *Refresh* (or hit *F5*).

The changes will be published (after the normal publishing interval) to a running server without even needing to restart the server.

### Thoughts

In this example there's no particular reason to go outside of RAD, because there's not much going on in the Maven build.   

But the point is that you have full freedom to work with the app in either Maven or RAD, and an app rebuilt in Maven can even be auto-published to a live server. 

## Next step

* [Next article](other-notes.md) - Some final, additional thoughts and tips.

