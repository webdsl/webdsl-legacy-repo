all : twiki-to-markdown extract-fields extract-user 

twiki-to-markdown : twiki-to-markdown.str site-specific.str xml-lib.str
	strc -i twiki-to-markdown.str \
		-la stratego-lib 

extract-fields : extract-fields.str
	strc -i extract-fields.str \
		-la stratego-lib 

extract-user : extract-user.str xml-lib.str
	strc -i extract-user.str \
		-la stratego-lib 

test :
	parse-xml-info -i foo.xhtml | ./twiki-to-markdown