package io.phanisment.ansicolor;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;

public final class ANSITag {
	private static final Map<String, Character> style = Collections.unmodifiableMap(Map.<String, Character>of(
		"reset",			'0',
		"bold",				'1',
		"dim",				'2',
		"italic",			'3',
		"underline",	'4'
	));
	private final ANSITagLexer lexer = new ANSITagLexer();

	public static void f(final String text, final PrintStream out) {
		new ANSITag().parse(text, out);
	}

	public void parse(final String text, final PrintStream out) {
		parse(text.toCharArray(), out);
	}

	public void parse(final char[] buffer, final PrintStream out) {
		lexer.reset();
		while (lexer.next(buffer)) {
			if (lexer.in_tag) processTag(buffer, lexer.start, lexer.last, out);
			else out.print(new String(buffer, lexer.start, lexer.last));
		}
		out.print(ANSIColor.RESET);
		}

	public void processTag(final char[] buffer, final int start, final int last, final PrintStream out) {
	if (last <= 0) return;
	
	if (buffer[start] == '/' || isResetTag(buffer, start, last)) {
		out.print(ANSIColor.RESET);
		return;
	}

	out.print(ANSIColor.ESC);
	int buffer_start = start;
	boolean first = true;
	int color_in_tag_count = 0;
	for (int i = start; i <= start + last; i++) {
		if (i == start + last || buffer[i] == ' ') {
			int buffer_length = i - buffer_start;
			if (buffer_length > 0) {
				String arg = new String(buffer, buffer_start, buffer_length).toLowerCase();
				Character styleCode = style.get(arg);
				if (styleCode != null) {
					if (!first) out.print(';');
					out.print(styleCode);
					first = false;
				} else {
					try {
						var color = ANSIColor.valueOf(arg.toUpperCase());
						if (!first) out.print(';');
						if (color_in_tag_count == 0) {
							out.print(color.foreground_code);
							color_in_tag_count++;
						} else out.print(color.background_code);
						first = false;
					} catch (IllegalArgumentException ignored) {}
				}
			}
			buffer_start = i + 1;
		}
	}
	out.print("m");
}

	private boolean isResetTag(char[] buffer, int start, int last) {
		if (last != 5) return false;
		return buffer[start] == 'r' && buffer[start+1] == 'e' && buffer[start+2] == 's' && buffer[start+3] == 'e' && buffer[start+4] == 't';
	}
}