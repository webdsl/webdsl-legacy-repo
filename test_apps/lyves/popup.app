module popup

section templates


define popup(header : String) {
	#[class:=popupbg, style := "position: absolute; top: 0px; left:0px;"] {
		#[class:=popup] {
			#[class:=popupheader] {
				table {
					<| [width:= 600px]	output(header) 
					 | actionLink("X")[onclick:=  { visibility this << hide; }, class:= right]
					>
				}
			}
			#{ popupBody() }
		}
	}
}

define popupBody() {
	"<no contents to display>"	
}

style popupStyle

popup(header: String) {
	display := Display.block;
	align		:= Align.center;
}

.popupbg {
	height	:= 100%;
	width		:= 100%;
	image		:= url("images/popup_bg.png");
	image-repeat:= Repeat.both;
}

.popup {
	align				 := Align.center;
	background-color := #cccccc;
	border-style := BorderStyle.solid;
	border-width := 1px;
	border-color := Color.navy;
	padding			 := 2px;
	width				 := 640px;
	margin-top	 := 50px;
}

.popupheader {
	background-color := #e2eaff;	
	border-style := BorderStyle.inset;
	font-color := Color.White;
	font-style := FontStyle.bold;
}

.right {
	text-align := Align.right;
}
