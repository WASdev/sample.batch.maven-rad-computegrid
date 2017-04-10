# Tips

1.  Be careful with the Java EE Export wizard.  
	* If you do *Export->Java EE->EAR file* of the EAR you built with the Maven-first approach, you may not necessarily get something that works.   In this sample approach, at least, the RAD project structure is good enough to run in the "loose config" enviroment where the resources are run out of the RAD workspace, but an exported EAR might not contain the correct inter-module dependencies in the manifest(s).
	* Don't worry, you don't need to export the EAR from RAD, you already have a copy in the EAR-level Maven module **target** directory.

1. If producing your own template, you will likely at some point deal with multiple, similar applications.   Note that to reuse (copy/paste sort of) xJCL from one application to another in the same server or cell, you would typically at least need to change the two values shown here:

		 <job xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="http://core.wcg.etools.ibm.com/xsd/xJCL.xsd" name="MyJob" default-application-name="RADBatchEAR">
		     <jndi-name>ejb/com/ibm/ws/batch/RADBatchEARBatchController</jndi-name>
		     ... 

	In other words, modify the **default-application-name** attribute value and the `<jndi-name>` element contents.

# Thoughts on Alternatives

Q. Why not do the ejbDeploy in the Maven build?

A. That would be nice.  If the RAD project isn't aware of them it might overwrite them with nothing, at least in the approach I headed down.   

Q. Could I use an EJB 3 JAR, and avoid the EJB deploy?

A. The PGCPackager will only generate an EJB-2.1 module, so you'd have to do some creative Ant manipulation.   But it might be worth it not to have to do the EJB deploy.

### Ideas

* Might be nice to show a variant if you have the EJBDeploy tool installed but not the EJB embeddable container (or if you don't how to get it from RAD). 

## A note on module group names

Because the **was\_public.jar** is the only one with an official POM provided, it is the only one which uses the **com.ibm.websphere.appserver** group name.

The batch runtime and EJB embeddable container JARs are official parts of the WebSphere Application Server product, but without official POMs provided.   So for these, we use a group name of **com.ibm.ws.scottkurz.waslib**.   That is, the POMs were written by Scott Kurz for this sample, but the JARs are the standard ones.

Finally, the sample archetype itself uses group name **com.ibm.ws.scottkurz.samples**, as this was created purely for this sample.

## Links

* Back to [main page](/README.md)
