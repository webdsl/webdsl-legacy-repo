{stdenv, fetchurl}:

stdenv.mkDerivation {
  name = "aterm-2.5pre21238";
  src = fetchurl {
    url = http://releases.strategoxt.org/strategoxt-0.17/aterm/aterm-2.5pre21238-26ra85lr/aterm-2.5.tar.gz;
    sha256 = "21e409c97e29711539527a17a04f00a0e33b35b96d68af0ad69e5a6faf780495";
  };
}
