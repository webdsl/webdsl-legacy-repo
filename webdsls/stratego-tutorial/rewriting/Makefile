

JAVA_FRONT = /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front
JAVA_FRONT_CFLAGS =  
JAVA_FRONT_LIBS = /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front/lib/libjava-front.la  
JAVA_FRONT_STRCFLAGS = -I /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front/share/java-front-syntax -I /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front/share/java-front -la /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front/lib/libjava-front.la
JAVA_FRONT_XTC = /nix/store/iva6y84cyvp6y5syw7f2qbi78x9jm1wz-java-front/share/java-front/XTC


STRCFLAGS = \
        -la stratego-xtc \
        -la stratego-lib \
        -la stratego-gpp \
        -la stratego-tool-doc \
        -la stratego-sglr \
        -la $(JAVA_FRONT_LIBS) \
	-la ../NanoWebDSL/src/libwebdsl-front.la \
	-I ../NanoWebDSL/src/ \
        --format-check 0 -O 1


normalize : normalize.str
	strc -i normalize.str $(STRCFLAGS)



