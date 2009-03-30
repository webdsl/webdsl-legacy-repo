{webdsl, fetchsvn}:

webdsl.mkDerivation {
  name = "researchr";
  # Location of the subversion checkout for researchr
  src = /tmp/researchr/trunk;
  databaseServer = "localhost";
  databaseName = "researchr";
  databaseUser = "root";
  databasePassword = "";
  databaseMode = "update";
}
