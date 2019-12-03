package com.life.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LoadWorkbook {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 *	ĐỌC FILE EXCEL XLSX
	 */
	public XSSFWorkbook getXSSFWorkbook(String name) {
		XSSFWorkbook xssfWorkbook = null;
		URL url = this.getClass().getClassLoader().getResource(name);
		if (url != null) {
			try {
				xssfWorkbook = new XSSFWorkbook(url.openStream());
			}
			catch (IOException ex) {
				this.logger.log(Level.WARNING, ex.getMessage());
				this.logger.log(Level.WARNING, ex.getStackTrace().toString());
			}
		}
		return xssfWorkbook;
	}

	/**
	 * 	ĐỌC FILE EXCEL XLS
	 */
	public HSSFWorkbook getHSSFWorkbook(String name) {
		HSSFWorkbook hssfWorkbook = null;
		URL url = this.getClass().getClassLoader().getResource(name);
		if(url != null) {
			try {
				hssfWorkbook = new HSSFWorkbook(url.openStream());
			}
			catch (IOException ex) {
				this.logger.log(Level.WARNING, ex.getMessage());
				this.logger.log(Level.WARNING, ex.getStackTrace().toString());
			}
		}
		return hssfWorkbook;
	}

}