TablizeMessage
==============

Make log message tablized. The API can make easy to layout message like instert cell data into a table.

		String[] columns = new String[]{"Category", "Rule", "Status", "Explain"};
		int[] numCharsInColumns = new int[]{20, 20, 20, 60};
		boolean gridding = true;
		TablizeMessage ts = new TablizeMessage(columns, numCharsInColumns, gridding);
		
		String[] row = new String[]{"Document", "Primary language", "Failed", 
				"Setting the document language in a PDF enables some screen readers to switch to the appropriate language."};
		ts.addRow(row);
		System.out.println(ts.getTablizedString());

Example output: 
----------------
![Example of TablizeMessage](https://raw.githubusercontent.com/lumpchen/TablizeMessage/master/src/sample.png)

