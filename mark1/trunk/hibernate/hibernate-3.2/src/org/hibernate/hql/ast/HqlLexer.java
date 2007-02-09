// $Id: HqlLexer.java 7511 2005-07-16 17:28:40Z epbernard $
package org.hibernate.hql.ast;

import java.io.InputStream;
import java.io.Reader;

import antlr.Token;
import org.hibernate.QueryException;
import org.hibernate.hql.antlr.HqlBaseLexer;

/**
 * Custom lexer for the HQL grammar.  Extends the base lexer generated by ANTLR
 * in order to keep the grammar source file clean.
 */
class HqlLexer extends HqlBaseLexer {
	/**
	 * A logger for this class. *
	 */
	private boolean possibleID = false;

	public HqlLexer(InputStream in) {
		super( in );
	}

    public HqlLexer(Reader in) {
        super(in);
    }

	public void setTokenObjectClass(String cl) {
		// Ignore the token class name parameter, and use a specific token class.
		super.setTokenObjectClass( HqlToken.class.getName() );
	}

	protected void setPossibleID(boolean possibleID) {
		this.possibleID = possibleID;
	}

	protected Token makeToken(int i) {
		HqlToken token = ( HqlToken ) super.makeToken( i );
		token.setPossibleID( possibleID );
		possibleID = false;
		return token;
	}

	public int testLiteralsTable(int i) {
		int ttype = super.testLiteralsTable( i );
		return ttype;
	}

	public void panic() {
		//overriden to avoid System.exit
		panic("CharScanner: panic");
	}

	public void panic(String s) {
		//overriden to avoid System.exit
		throw new QueryException(s);
	}
}
