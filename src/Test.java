

import me.lumpchen.util.TablizeMessage;
import me.lumpchen.util.TablizeMessage.Align;

public class Test {
	public static void main(String[] args) {
		test_3();
	}
	
	static void test_1() {
		String[] columns = new String[]{"Category", "Rule", "Status", "Explain"};
		int[] numCharsInColumns = new int[]{20, 20, 20, 60};
		boolean gridding = true;
		
		TablizeMessage ts = new TablizeMessage(columns, numCharsInColumns, gridding);
		
		String[] row = new String[]{"Document", "Primary language", "Failed", 
				"Setting the document language in a PDF enables some screen readers to switch to the appropriate language."};
		ts.addRow(row);
		ts.addRow(row);
		ts.addRow(row);
		
		System.out.println(ts.getTablizedString());
	}
	
	static void test_2() {
		String[] columns = new String[]{"Category", "Rule", "Status", "Explain"};
		int[] numCharsInColumns = new int[]{20, 20, 20, 60};
		boolean gridding = false;
		
		TablizeMessage ts = new TablizeMessage(columns, numCharsInColumns, gridding);
		
		String[] row = new String[]{"Document", "Primary language", "Failed", 
				"Setting the document language in a PDF enables some screen readers to switch to the appropriate language."};
		ts.addRow(row);
		ts.addRow(row);
		ts.addRow(row);
		
		System.out.println(ts.getTablizedString());
	}
	
	static void test_3() {
		String[] columns = new String[]{"Category", "Rule", "Status", "Explain"};
		int[] numCharsInColumns = new int[]{20, 20, 20, 60};
		boolean gridding = true;
		Align[] alignment = new Align[]{Align.center, Align.center, Align.center, Align.right};
		
		TablizeMessage ts = new TablizeMessage(columns, numCharsInColumns, alignment, gridding);
		
		String[] row = new String[]{"Document", "Primary language", "Failed", 
				"Setting the document language in a PDF enables some screen readers to switch to the appropriate language."};
		ts.addRow(row);
		ts.addRow(row);
		ts.addRow(row);
		
		System.out.println(ts.getTablizedString());
	}
	
}
