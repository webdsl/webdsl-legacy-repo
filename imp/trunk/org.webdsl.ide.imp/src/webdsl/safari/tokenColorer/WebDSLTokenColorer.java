package webdsl.safari.tokenColorer;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import webdsl.safari.parser.WebDSLParsersym;

import lpg.runtime.IToken;

public class WebDSLTokenColorer extends TokenColorerBase implements
		WebDSLParsersym, ITokenColorer {

	TextAttribute commentAttribute, keywordAttribute, stringAttribute,
			numberAttribute, doubleAttribute, identifierAttribute,
			sectionAttribute;

	public TextAttribute getColoring(IParseController controller, IToken token) {
		switch (token.getKind()) {
		
		/* UNDONE: More varied token kinds from LPG parser
		case TK_IDENTIFIER:
			return identifierAttribute;
		case TK_NUMBER:
			return numberAttribute;
		case TK_FLOAT:
			return doubleAttribute;
			// case TK_StringLiteral:
			// return stringAttribute;
		case TK_SECTIONCOMMENT:
			return sectionAttribute;
		case TK_DESCRIPTIONCOMMENT: case TK_NOTECOMMENT: case TK_SINGLE_LINE_COMMENT:
			return commentAttribute;
		case TK_define:
			return keywordAttribute;
		}
		*/
		
		case TK_KEYWORD:
			return keywordAttribute;
		case TK_IDENTIFIER:
			return identifierAttribute;

		default:
			return super.getColoring(controller, token);
		}
	}

	public WebDSLTokenColorer() {
		super();
		// TODO: Define text attributes for the various
		// token types that will have their text colored
		Display display = Display.getDefault();
		commentAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_CYAN), null, SWT.ITALIC);
		stringAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.NORMAL);
		identifierAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		doubleAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.NORMAL);
		sectionAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.BOLD);
		numberAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.NORMAL);
		keywordAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
	}

}
