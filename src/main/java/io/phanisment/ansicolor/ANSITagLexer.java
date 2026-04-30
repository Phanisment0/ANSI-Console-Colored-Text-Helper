package io.phanisment.ansicolor;

public final class ANSITagLexer {
	public int pos = 0;
	public boolean in_tag;

	public int start;
	public int last;

	public boolean next(final char[] buffer) {
		int length = buffer.length;
		if (pos >= length) return false;

		start = pos;
		if (buffer[pos] == '<') {
			in_tag = true;
			pos++; // Lewati '<'
			start = pos;
			while (pos < length && buffer[pos] != '>') pos++;
			last = pos - start;
			if (pos < length) pos++;
		} else {
			in_tag = false;
			while (pos < length && buffer[pos] != '<') pos++;
			last = pos - start;
		}
		return true;
	}

	public void reset() {
		this.pos = 0;
		this.in_tag = false;
	}

	@Override
	public String toString() {
		return "{ " + pos + ", " + in_tag + ", " + start + ", " + last + " }";
	}
}
