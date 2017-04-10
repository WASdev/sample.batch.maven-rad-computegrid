**Note**:  We don't include a POM to actually build the archetype.   But it's simply zipped/jarred up from the contents of archetype-resources. 


1. Zip up with:

		jar uvf textfile-io-archetype-1.0-SNAPSHOT.jar  archetype-resources/

1. Install the sample Maven archetype:

		cd archetype   # IMPORTANT !
		mvn install:install-file -Dfile=./textfile-io-archetype-1.0-SNAPSHOT.jar -DpomFile=./textfile-io-archetype-1.0-SNAPSHOT.pom

    **Note:** It's important to run from the **archetype** subdirectory, since that contains the **pom.xml** that governs the installation of the archetype!

1. Update your local catalog (optional, but makes creation step a bit easier).

		mvn -f textfile-io-archetype-1.0-SNAPSHOT.pom archetype:update-local-catalog
