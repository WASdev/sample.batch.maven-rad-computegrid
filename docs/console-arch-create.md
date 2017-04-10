    $  mvn archetype:generate -DarchetypeCatalog=local
    [INFO] Scanning for projects...
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] Building Maven Stub Project (No POM) 1
    [INFO] ------------------------------------------------------------------------
    [INFO]
    [INFO] >>> maven-archetype-plugin:2.2:generate (default-cli) > generate-sources @ standalone-pom >>>
    [INFO]
    [INFO] <<< maven-archetype-plugin:2.2:generate (default-cli) < generate-sources @ standalone-pom <<<
    [INFO]
    [INFO] --- maven-archetype-plugin:2.2:generate (default-cli) @ standalone-pom ---
    [INFO] Generating project in Interactive mode
    [INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
    Choose archetype:
    1: local -> com.ibm.ws.scottkurz.samples:textfile-io-archetype (textfile-io-archetype)
    Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
    Define value for property 'groupId': : com.xyz.radbatch
    Define value for property 'artifactId': : MyBatch-parent
    Define value for property 'version':  1.0-SNAPSHOT: :
    Define value for property 'package':  com.xyz.radbatch: :
    Confirm properties configuration:
    groupId: com.xyz.radbatch
    artifactId: MyBatch-parent
    version: 1.0-SNAPSHOT
    package: com.xyz.radbatch
     Y: :
    [INFO] ----------------------------------------------------------------------------
    [INFO] Using following parameters for creating project from Archetype: textfile-io-archetype:1.0-SNAPSHOT
    [INFO] ----------------------------------------------------------------------------
    [INFO] Parameter: groupId, Value: com.xyz.radbatch
    [INFO] Parameter: artifactId, Value: MyBatch-parent
    [INFO] Parameter: version, Value: 1.0-SNAPSHOT
    [INFO] Parameter: package, Value: com.xyz.radbatch
    [INFO] Parameter: packageInPathFormat, Value: com/xyz/radbatch
    [INFO] Parameter: package, Value: com.xyz.radbatch
    [INFO] Parameter: version, Value: 1.0-SNAPSHOT
    [INFO] Parameter: groupId, Value: com.xyz.radbatch
    [INFO] Parameter: artifactId, Value: MyBatch-parent
    [INFO] project created from Archetype in dir: C:\git\mvn.rad.doc\newproject\MyBatch-parent
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 55.146 s
    [INFO] Finished at: 2016-07-21T06:20:31-04:00
    [INFO] Final Memory: 14M/484M
    [INFO] ------------------------------------------------------------------------


## Links

* [Back](create-maven-app.md) to previous page.

