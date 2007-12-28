all : twiki-to-markdown extract-fields

twiki-to-markdown : twiki-to-markdown.str
	strc -i twiki-to-markdown.str \
		-la stratego-lib 

extract-fields : extract-fields.str
	strc -i extract-fields.str \
		-la stratego-lib 

test :
	parse-xml-info -i foo.xhtml | ./twiki-to-markdown