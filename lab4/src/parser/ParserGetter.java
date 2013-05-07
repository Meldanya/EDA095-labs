package parser;

import javax.swing.text.html.HTMLEditorKit;

public class ParserGetter extends HTMLEditorKit {
	private static final long serialVersionUID = 1L;

	public HTMLEditorKit.Parser getParser() {
		return super.getParser();
	}
}
