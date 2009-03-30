{webdsl, fetchsvn}:

webdsl.mkDerivation {
  name = "webdslorg";
  src = fetchsvn {
    url = https://svn.strategoxt.org/repos/WebDSL/webdslorg/trunk;
    sha256 = "6fdf9f705024c6ac845e9972db30793baa0ab7d7b39078d578b61c5dabfe572e";
    rev = 2587;
  };
  databaseServer = "localhost";
  databaseName = "webdslorg";
  databaseUser = "root";
  databasePassword = "";
  databaseMode = "update";
}
