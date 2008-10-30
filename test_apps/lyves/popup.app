module popup

section templates


define popup(header : String) {
	#[[class:=popupbg, style := "position: absolute; top: 0px; left:0px;"] 
		#[[class:=popup]
			#[[class:=popupheader]
				table {
					<| [width:= 600px]	output(header) 
					 | actionLink("X")[onclick:= close(), class:= right]
					>
				}
			]
			#[ popupBody() ]
			--
			#[ [class:=popupfooter]
				table[height:= 25px] {
					<| [class := right, width:= 600px] popupFooter() 
					 | action("Cancel", close())
					>
				}
			]
		]
		action close() {
			visibility this << hide;
		}
	]
}

define popupBody() {
	"<no contents to display>"	
}

define popupFooter() {

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

.popupfooter {
	text-align := Align.right;
}

.right {
	text-align := Align.right;
}
