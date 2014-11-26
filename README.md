TablizeMessage
==============

Make log message tablized. The API can make easy to layout message like instert cell data into a table.

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

Example output:
---------------------------------------------------------------------------------------------------------------------------------------
|      Category      |        Rule        |       Status       |        Page&Tip Text         |                Explain                 |
---------------------------------------------------------------------------------------------------------------------------------------
|Document            |Primary language    |Failed              |@Page 0                       |Setting the document language in a PDF  |
|                    |                    |                    |                              | enables some screen readers to switch  |
|                    |                    |                    |                              | to the appropriate language.           |
---------------------------------------------------------------------------------------------------------------------------------------
|Alternate Text      |Link alternate text |Failed              |@Page 1 nearby "Go to         |Link alternate text                     |
|                    |                    |                    | xPression!"                  |                                        |
---------------------------------------------------------------------------------------------------------------------------------------
|Page Content        |Logical reading     |Needs manual check  |@Page 2                       |Logical reading order                   |
|                    | order              |                    |                              |                                        |
---------------------------------------------------------------------------------------------------------------------------------------



