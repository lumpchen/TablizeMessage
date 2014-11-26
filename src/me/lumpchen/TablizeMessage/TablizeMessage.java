package me.lumpchen.TablizeMessage;

import java.util.ArrayList;
import java.util.List;

public class TablizeMessage {
	
	private List<String> tablizedText = new ArrayList<String>();
	private String[] columns;
	private int[] numCharsInColumns;
	private boolean gridding;
	private int numOfChars = 0;
	private static final char DASH = '-';
	private static final char BAR = '|';
	
	private int[] barPos;
	public enum Align{center, left, right};
	private Align[] columnAlignment;
	
	public TablizeMessage(String[] columns, int[] numCharsInColumns, boolean gridding) {
		this(columns, numCharsInColumns, new Align[]{Align.left}, gridding);
	}
	
	public TablizeMessage(String[] columns, int[] numCharsInColumns, Align[] columnAlignment, boolean gridding) {
		if (columns.length != numCharsInColumns.length
				|| (columns.length != columnAlignment.length && columnAlignment.length != 1)) {
			throw new java.lang.IllegalArgumentException("Column attribute must be same count!");
		}
		
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			int nums = numCharsInColumns[i];
			
			if (nums < column.length()) {
				nums = column.length();
				numCharsInColumns[i] = nums;
			}
		}
		
		this.columns = columns;
		this.numCharsInColumns = numCharsInColumns;
		this.barPos = new int[this.columns.length];
		if (columnAlignment.length == 1) {
			this.columnAlignment = new Align[this.columns.length];
			for (int i = 0; i < this.columnAlignment.length; i++) {
				this.columnAlignment[i] = columnAlignment[0];
			}
		} else {
			this.columnAlignment = columnAlignment;				
		}
		
		this.gridding = gridding;
		
		for (int n : this.numCharsInColumns) {
			this.numOfChars += n;
		}
		
		if (this.gridding) {
			this.lineGrid();
		}
		
		this.lineHeader();
	}
	
	public void addRow(String[] rowData) {
		if (rowData == null || rowData.length != this.columns.length) {
			throw new java.lang.IllegalArgumentException("Row count must match column count!");
		}
		
		String[][] multiLineRow = new String[this.columns.length][];
		for (int i = 0; i < this.columns.length; i++) {
			String text = rowData[i];
			
			if (text != null) {
				text = text.trim();
				text = text.replaceAll("\r", "");
				text = text.replaceAll("\n", " ");					
			} else {
				text = "";
			}

			String[] splits = this.fillCell(text, this.numCharsInColumns[i], this.columnAlignment[i]);
			multiLineRow[i] = splits;
		}
		
		this.regularRow(multiLineRow);
		List<StringBuilder> lines = new ArrayList<StringBuilder>();
		for (int i = 0; i < this.columns.length; i++) {
			String[] splits = multiLineRow[i];
			for (int j = 0; j < splits.length; j++) {
				String split = splits[j];
				StringBuilder line = null;
				if (!lines.isEmpty() && lines.size() > j) {
					line = lines.get(j);
				}
				if (line == null) {
					line = new StringBuilder();
					lines.add(line);
				}

				if (i == 0 && this.gridding) {
					line.append(BAR);
				}
			
				line.append(split);
			
				if (this.gridding) {
					line.append(BAR);
				}
			}
		}
		
		for (StringBuilder buf : lines) {
			this.tablizedText.add(buf.toString());
		}
		
		if (this.gridding) {
			this.lineGrid();
		}
	}
	
	private void regularRow(String[][] multiLineRow) {
		int max = 0;
		for (int i = 0; i < multiLineRow.length; i++) {
			if (max < multiLineRow[i].length) {
				max = multiLineRow[i].length;
			}
		}
		
		for (int i = 0; i < multiLineRow.length; i++) {
			String[] cellLines = multiLineRow[i];
			if (max == cellLines.length) {
				continue;
			}
			
			String[] filledSplit = new String[max];
			for (int j = 0; j < max; j++) {
				if (j < cellLines.length) {
					filledSplit[j] = cellLines[j];
				} else {
					StringBuilder buf = new StringBuilder();
					this.repeatChar(' ', this.numCharsInColumns[i], buf);
					filledSplit[j] = buf.toString();						
				}
			}
			multiLineRow[i] = filledSplit;
		}
	}
	
	private void lineHeader() {
		StringBuilder line = new StringBuilder();
		int run = 0;
		for (int i = 0; i < this.columns.length; i++) {
			String column = this.columns[i];
			int len = this.numCharsInColumns[i];
			
			if (i == 0 && this.gridding) {
				line.append(BAR);
			}
			
			run += this.fillColumnHeader(column, len, line);
			this.barPos[i] = run; 
		}
		
		this.tablizedText.add(line.toString());
		if (this.gridding) {
			this.lineGrid();
		}
	}
	
	private int fillColumnHeader(String column, int len, StringBuilder line) {
		if (column.length() > len) {
			len = column.length();
		}
		String[] s = this.fillCell(column, len, Align.center);
		line.append(s[0]);
		int run = s[0].length(); 
		if (this.gridding) {
			line.append(BAR);
			run += 1;
		}
		return run;
	}
	
	private String[] fillCell(String s, int cellLen, Align align) {
		if (cellLen >= s.length()) {
			return new String[]{this.alignText(s, cellLen, align)};
		} else {
			int start = 0;
			String remain = s;
			List<String> splits = new ArrayList<String>();
			while (start < s.length()) {
				if (start > 0) {
					remain = s.substring(start);
				}
				String split = this.getNextSeg(remain, cellLen);
				start += split.length();
				
				split = this.alignText(split, cellLen, align);
				splits.add(split);
			}
			
			return splits.toArray(new String[splits.size()]);
		}
	}
	
	public String alignText(String s, int len, Align align) {
		if (s.length() >= len) {
			return s;
		}
		StringBuilder buf = new StringBuilder();
		if (Align.center == align) {
			int space = len - s.length();
			int before = space / 2;
			int after = space - before;
			
			while (before > 0) {
				buf.append(' ');
				before--;
			}
			
			buf.append(s);
			while (after > 0) {
				buf.append(' ');
				after--;
			}
			
		} else if (Align.left == align) {
			int space = len - s.length();
			buf.append(s);
			while (space > 0) {
				buf.append(' ');
				space--;
			}
		} else if (Align.right == align) {
			int space = len - s.length();
			while (space > 0) {
				buf.append(' ');
				space--;
			}
			buf.append(s);
		}
		
		return buf.toString();
	}
	
	private String getNextSeg(String s, int segNum) {
		if (s.length() <= segNum) {
			return s;
		}
		
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (Character.isWhitespace(c)) {
				if (i <= segNum) {
					return s.substring(0, i);
				}
			}
		}
		
		return s.substring(0, segNum);
	}
	
	public String getTablizedString() {
		StringBuilder line = new StringBuilder();
		for (String s : tablizedText) {
			line.append(s);
			line.append("\r\n");
		}
		return line.toString();
	}
	
	private void lineGrid() {
		StringBuilder line = new StringBuilder();
		this.repeatChar(DASH, this.numOfChars + this.columns.length, line);
		this.tablizedText.add(line.toString());
	}
	
	private void repeatChar(char c, int repeat, StringBuilder buf) {
		while (repeat > 0) {
			buf.append(c);
			repeat--;
		}
	}
}
