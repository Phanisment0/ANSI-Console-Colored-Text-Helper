package io.phanisment.ansicolor;

public enum ANSIColor {
	BLACK(30, 40), RED(31, 41), GREEN(32, 42), YELLOW(33, 43), BLUE(34, 44), MAGENTA(35, 45), CYAN(36, 46), WHITE(37, 47), DEFAULT(39, 49),
	
	BRIGHT_BLACK(90, 100), BRIGHT_RED(91, 101), BRIGHT_GREEN(92, 102), BRIGHT_YELLOW(93, 103), BRIGHT_BLUE(94, 104), BRIGHT_MAGENTA(95, 105), BRIGHT_CYAN(96, 106), BRIGHT_WHITE(97, 107);

	public static final String ESC = "\u001B[";
	public static final String RESET = ESC + "0m";
	
	public final int foreground_code;
	public int background_code = -1;
	private int style = 0;
	private boolean use_background = false;

	ANSIColor(int fg, int bg) {
		this.foreground_code = fg;
		this.background_code = bg;
	}

	public ANSIColor bold() {
		this.style = 1;
		return this;
	}

	public ANSIColor dim() {
		this.style = 2;
		return this;
	}

	public ANSIColor italic() {
		this.style = 3;
		return this;
	}

	public ANSIColor underline() {
		this.style = 4;
		return this;
	}

	public ANSIColor background(ANSIColor color) {
		this.background_code = color.background_code;
		this.use_background = true;
		return this;
	}

	public String format(final String text) {
		return this.toString() + text + RESET;
	}

	@Override
	public String toString() {
		var s = new StringBuilder(ESC);
		if (style != -1) s.append(style).append(';');
		s.append(foreground_code);
		if (use_background) s.append(';').append(background_code);
		s.append('m');
		this.style = 0;
		this.use_background = false;
		return s.toString();
	}
}
