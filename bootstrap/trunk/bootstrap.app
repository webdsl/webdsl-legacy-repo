application bootstrap

imports examples/starter0
imports examples/starter1

section root page

	define page root(){ 
		list{
			listitem{ navigate starter0() { "Starter 0" } }
			listitem{ navigate starter1() { "Starter 1" } }
		}
	}