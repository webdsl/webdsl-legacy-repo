module news


section entities

entity NewsItem {
	name 		:: String
	text 		:: String
	author  -> User
	public 	:: Bool
	datetime:: DateTime
}

section pages

define newsitems() {
	// filtering
	var items : List<NewsItem> := select n from NewsItem as n;
	init {
		if (!securityContext.loggedIn) {
	 			items := select n from NewsItem as n
	 							 where (n._public = 1);
		}
	}
	// display
	for(news : NewsItem in items) {
		container[class:= bold]{
			// bewerk knopjes
			if (securityContext.loggedIn && (
						securityContext.principal.moderator == true ||
						securityContext.principal == news.author)
					) {
					newsitemEditButtons(news)
			}					
			output(news.name)[class:= small]
			container[class:= [gray,verysmall]]{ "(posted by " output(news.author.nick) " at "  output(news.datetime) ")"}
		}
		#{ output(news.text)}
	}  separated-by { -- }
	// toevoeg knopje
	if (securityContext.loggedIn && securityContext.principal.moderator == true) {
		--
		action("add newsitem", addNewsItem())
	}
	action addNewsItem() {
		var newnews : NewsItem := NewsItem{
			author := securityContext.principal
			datetime := now()
		};
		replace popuptarget << editNewsItem(newnews);			
	}
}

define newsitemEditButtons(news: NewsItem) {
	action("E")[onclick := { replace popuptarget << editNewsItem(news); }]
	action("X",{ news.delete(); relocate << home(); })
}

define editNewsItem(news: NewsItem) {
	popup("Edit newsitem "+news.name)
	define popupBody() {
		form {
			group {
				< label("name:") { input(news.name) } >
				< label("text:") { inputText(news.text) } >
				< label("public:") { input(news.public) } >
			}
			--
			#[class:= right] {
				action("Save",  {
					news.save();
					relocate << home();					
				})
				action("Cancel", { visibility this << hide; })
			}
		}
	}
}

