package com.line.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.line.dto.CardDateStatisticsDto;
import com.line.dto.CardPointStatisticsDto;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @author gonglei
 * 
 */
public class ExportExcel {

	private String headerName;

	private WritableCellFormat headerFormat;
	private WritableCellFormat headerFormat2;
	private WritableCellFormat titleFormat;
	private WritableCellFormat normalFormat;
	private WritableCellFormat floatFormat;
	private WritableCellFormat noBorderLineFormat;

	public ExportExcel(String headerName) {
		super();
		this.headerName = headerName;
		WritableFont titlefont = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD, false);
		headerFormat = new WritableCellFormat(titlefont);
		headerFormat2 = new WritableCellFormat(titlefont);
		titleFormat = new WritableCellFormat(titlefont);
		try {
			headerFormat.setAlignment(jxl.format.Alignment.CENTRE);
			headerFormat2.setAlignment(jxl.format.Alignment.LEFT);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		try {
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			headerFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			titleFormat.setBackground(jxl.format.Colour.GRAY_25);
			// ����
			WritableFont normalfont = new WritableFont(WritableFont.ARIAL, 10);

			normalFormat = new WritableCellFormat(normalfont);
			normalFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			normalFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			NumberFormat fivedps = new NumberFormat("#.##");
			floatFormat = new WritableCellFormat(fivedps);
			floatFormat.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			noBorderLineFormat = new WritableCellFormat(normalfont);
			noBorderLineFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			noBorderLineFormat.setWrap(true);

			Calendar ca = Calendar.getInstance();
			ca.set(1, 1, 1);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param project
	 * @param profile
	 * @param testBatchs
	 * @param fileName
	 */
	public void exportConsisterLogReport(List<CardDateStatisticsDto> rects,
			String fileName, int size) {
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(fileName));
			WritableSheet sheet = book.createSheet("sheet", 0);
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			
			sheet.addCell(new Label(0, 0, "日期", titleFormat));
			sheet.addCell(new Label(1, 0, "检查产品数量", titleFormat));
			sheet.addCell(new Label(2, 0, "良品数量", titleFormat));
			sheet.addCell(new Label(3, 0, "不良品数量", titleFormat));
			sheet.addCell(new Label(4, 0, "直通率", titleFormat));

			int startRow = 1;// ��ʼ��
			for (int i = 0; i < rects.size(); i++) {
				CardDateStatisticsDto rect = rects.get(i);
				sheet.addCell(new Label(0, startRow, rect.getDate(),
						normalFormat));
				sheet.addCell(new Label(1, startRow, rect.getNum() + "",
						normalFormat));
				sheet.addCell(new Label(2, startRow, rect.getPass() + "",
						normalFormat));
				sheet.addCell(new Label(3, startRow, rect.getUnpass() + "",
						normalFormat));
				sheet.addCell(new Label(4, startRow, rect.getRate() + "",
						normalFormat));
				startRow++;
			}
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (book != null)
				try {
					book.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void exportConsisterLogReport1(List<CardPointStatisticsDto> rects,
			String fileName, int size) {
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(fileName));
			WritableSheet sheet = book.createSheet("sheet", 0);
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			
			sheet.addCell(new Label(0, 0, "检测点数", titleFormat));
			sheet.addCell(new Label(1, 0, "良品器件个数", titleFormat));
			sheet.addCell(new Label(2, 0, "不良器件个数", titleFormat));
			sheet.addCell(new Label(3, 0, "错料", titleFormat));
			sheet.addCell(new Label(4, 0, "漏件", titleFormat));
			sheet.addCell(new Label(5, 0, "反向", titleFormat));
			sheet.addCell(new Label(6, 0, "多件", titleFormat));
			sheet.addCell(new Label(7, 0, "不良率", titleFormat));

			int startRow = 1;
			for (int i = 0; i < rects.size(); i++) {
				CardPointStatisticsDto rect = rects.get(i);
				sheet.addCell(new Label(0, startRow, rect.getPoint() + "",
						normalFormat));
				sheet.addCell(new Label(1, startRow, rect.getPass() + "",
						normalFormat));
				sheet.addCell(new Label(2, startRow, rect.getUnpass() + "",
						normalFormat));
				sheet.addCell(new Label(3, startRow, rect.getError() + "",
						normalFormat));
				sheet.addCell(new Label(4, startRow, rect.getMiss() + "",
						normalFormat));
				sheet.addCell(new Label(5, startRow, rect.getContrary() + "",
						normalFormat));
				sheet.addCell(new Label(6, startRow, rect.getMore() + "",
						normalFormat));
				sheet.addCell(new Label(7, startRow, rect.getRate() + "",
						normalFormat));
				startRow++;
			}
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (book != null)
				try {
					book.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	

}
