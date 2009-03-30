{webdsl, fetchsvn}:

webdsl.mkDerivation {
  name = "associations";
  src = fetchsvn {
    url = https://svn.strategoxt.org/repos/WebDSL/test_apps/associations;
    sha256 = "a335eaf7a3d943b5e018683087187fdd226be0b88cc52a9e50b9f27eadf0a72f";
    rev = 2416;
  };
  databaseServer = "localhost";
  databaseName = "associations";
  databaseUser = "root";
  databasePassword = "";
  databaseMode = "update";
}
