package de.cimt.talendcomp.tfileexcelpoi;
import static org.junit.Assert.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;


public class TestSpreadsheetFile {
	
	protected Workbook workbook;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testReadStreamingCheckContent() {
		String file = "/Volumes/Data/Talend/testdata/excel/test2/store_report_simple.xlsx";
		SpreadsheetFile sf = new SpreadsheetFile();
		sf.setCreateStreamingXMLWorkbook(true);
		try {
			sf.setInputFile(file);
			sf.initializeWorkbook();
		} catch (Exception e) {
			fail("Read file failed:" + e.getMessage());
		}
		SpreadsheetInput si = new SpreadsheetInput();
		si.setWorkbook(sf.getWorkbook());
		try {
			si.useSheet(0);
		} catch (Exception e) {
			fail("use sheet failed: " + e.getMessage());
		}
		Row row = si.getRow(0);
		if (row == null) {
			fail("Row 1 does not exists");
		}
		int rowNum = 0;
		while (si.readNextRow()) {
			rowNum++;
		}
		assertTrue("Not correct row num:" + rowNum, rowNum == 1001);
	}
	
	@Test
	public void testReadStreamingLastRowNum() {
		String file = "/Volumes/Data/Talend/testdata/excel/test2/store_report.xlsx";
		SpreadsheetFile sf = new SpreadsheetFile();
		sf.setCreateStreamingXMLWorkbook(true);
		try {
			sf.setInputFile(file);
			sf.initializeWorkbook();
			workbook = sf.getWorkbook();
		} catch (Exception e) {
			fail("Read file failed:" + e.getMessage());
		}
		SpreadsheetInput si = new SpreadsheetInput();
		si.setWorkbook(workbook);
		try {
			si.useSheet("Store 11");
		} catch (Exception e) {
			fail("use sheet failed: " + e.getMessage());
		}
		assertTrue("No rows found", si.getLastRowNum() > 0);
	}

}
