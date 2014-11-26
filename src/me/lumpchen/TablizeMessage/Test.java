package me.lumpchen.TablizeMessage;

public class Test {
	public static void main(String[] args) {
		String[] columns = new String[]{"Category", "Rule", "Status", "Explain"};
		int[] numCharsInColumns = new int[]{20, 20, 20, 60};
		boolean gridding = true;
		TablizeMessage ts = new TablizeMessage(columns, numCharsInColumns, gridding);
		
		String[] row = new String[]{"vCategory vCategory wCategory", "vRule", "vStatus", 
				"Explain,Category,Category,Category,wwCategory,CategoryExplain,Category," +
				"Category,Category,wwCategory,CategoryExplain,Category,Category,Category,wwCategory,Category"};
		ts.addRow(row);
		ts.addRow(row);
		ts.addRow(row);
		
		System.out.println(ts.getTablizedString());
	}
}
