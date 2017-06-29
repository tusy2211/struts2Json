package com.struts2.khanhbn.Banking.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.struts2.khanhbn.Banking.dao.AccountDao;
import com.struts2.khanhbn.Banking.dao.AccountDaoImpl;
import com.struts2.khanhbn.Banking.model.Account;
import com.struts2.khanhbn.Banking.util.CommonUtils;



public class AccountController extends SuperActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ELENCO_PUNTI_VENDITA = "Elenco Punti Vendita";
	public static final String IMAGE_PATH = "images";
	public static final String ICBPI_LOGO = "ICBPI_logo.jpg";
	public static final Logger logger = Logger.getLogger(AccountController.class);
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String username;
	private String password;
	private List<Account> data = new ArrayList<Account>();
	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private Account account = new Account();
	
	private int idAccount;
	
	private int _id;

	private String _username;

	private String _pass;

	private String _des;

	private String _image;
	
	private Date _birthday;
	
	private String contentType = null;
	
	private String fileName = null;
	
	private InputStream fileInputStream = null;
	
	protected HttpServletResponse servletResponse;
	
	private String listDataSelected;
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public String getListDataSelected() {
		return listDataSelected;
	}

	public void setListDataSelected(String listDataSelected) {
		this.listDataSelected = listDataSelected;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public Date get_birthday() {
		return _birthday;
	}

	public void set_birthday(Date _birthday) {
		this._birthday = _birthday;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_pass() {
		return _pass;
	}

	public void set_pass(String _pass) {
		this._pass = _pass;
	}

	public String get_des() {
		return _des;
	}

	public void set_des(String _des) {
		this._des = _des;
	}

	public String get_image() {
		return _image;
	}

	public void set_image(String _image) {
		this._image = _image;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Account> getData() {
		return data;
	}

	public void setData(List<Account> data) {
		this.data = data;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDaoImpl accountDao) {
		this.accountDao = accountDao;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpServletRequest getRequest() {
		return request;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String processLogin() throws Exception {

		if (accountDao.login(username, password)) {
			// this.lstAccounts = accountService.getAccounts();
//			this.session.put("username", username);
			getSession().put("username", username);
			return SUCCESS;
		}
		request.setAttribute("message", "Username or password is invalid!");
		return ERROR;
	}

	public String getList() throws Exception {
		this.data = accountDao.getAccounts();
		System.out.println("aa==== " + data);
		return SUCCESS;
	}

	// 3. Process logout.
	public String logout() throws Exception {
		if (this.session.containsKey("username")) {
			this.session.remove("username");
		}
		return SUCCESS;
	}

	// process add and update
	public String processAddNew() throws Exception {
//		int id = "".equals(_id) ? null : _id;
		account.setId(_id);
		account.setUsername(_username);
		account.setPassword(_pass);
		account.setDescription(_des);
		account.setImage(_image);
		account.setBirthday(_birthday);
		boolean isCheck = accountDao.addNew(account);
		if (isCheck) {
			this.data = accountDao.getAccounts();
		}
		return SUCCESS;
	}
	
	// delete
	public String delete() throws Exception {
		this.account = accountDao.getBookByID(this.idAccount);
		System.out.println("idAccount ===" + idAccount);
		boolean isCheck = accountDao.delete(this.account);
		if (isCheck) {
			this.data = accountDao.getAccounts();
		}
		return SUCCESS;
	}
	
	// export excel
	public String exportExcelElencoPuntiVendita() {
		contentType = "application/vnd.ms-excel";
		fileName = ELENCO_PUNTI_VENDITA + ".xlsx";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<Account> lstElencoPuntiVendita = objectMapper.readValue(listDataSelected,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Workbook workBook = new SXSSFWorkbook(100);
			String sheetName = ELENCO_PUNTI_VENDITA;
			Sheet sheet = workBook.createSheet(sheetName);
			CommonUtils commonUtils = CommonUtils.getInstance();
			CellStyle arial14Format = commonUtils.arial14Format(workBook);
			CellStyle arial10Format = commonUtils.arial10Format(workBook);
			CellStyle times10FormatBoldCenter = commonUtils.times10FormatBoldCenter(workBook);
			CellStyle times10formatCenter = commonUtils.times10FormatCenter(workBook);

			List<String> listColumnsHeader = new ArrayList<String>();
			listColumnsHeader.add("Account Name");
			listColumnsHeader.add("Password");
			listColumnsHeader.add("Description");
			listColumnsHeader.add("Image");
			listColumnsHeader.add("Birthday");

			// Set column width
			sheet.setColumnWidth(1, 7000);
			sheet.setColumnWidth(2, 14000);
			sheet.setColumnWidth(3, 7000);
			sheet.setColumnWidth(4, 7000);
			sheet.setColumnWidth(5, 7000);

			int rowNum = 0;
			rowNum = addAddtionalInfoToSheet(workBook, sheetName, arial10Format, fileName, arial14Format);

			// Create table

			Row row = commonUtils.sheetAddRow(sheet, rowNum++);
			for (int i = 0; i < listColumnsHeader.size(); i++) {
				commonUtils.sheetAddCell(row, i + 1, listColumnsHeader.get(i), times10FormatBoldCenter);
			}

			for (int i = 0; i < lstElencoPuntiVendita.size(); i++) {
				row = commonUtils.sheetAddRow(sheet, rowNum++);
				Account elencoPuntiVendita = lstElencoPuntiVendita.get(i);
				commonUtils.sheetAddCell(row, 1, elencoPuntiVendita.getUsername(), times10formatCenter);
				commonUtils.sheetAddCell(row, 2, elencoPuntiVendita.getPassword(), times10formatCenter);
				commonUtils.sheetAddCell(row, 3, elencoPuntiVendita.getDescription(), times10formatCenter);
				commonUtils.sheetAddCell(row, 4, elencoPuntiVendita.getImage(), times10formatCenter);
				commonUtils.sheetAddCell(row, 5, commonUtils.convertDateToString(elencoPuntiVendita.getBirthday()),
						times10formatCenter);
			}
			workBook.write(baos);
			workBook.close();
			fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			baos.close();
		} catch (Exception e) {
			logger.error(e);
			createCookie();
			return ERROR;
		}
		createCookie();
		logger.debug("exportExcelElencoPuntiVendita() - End method");
		return SUCCESS;
	}
	
	public int addAddtionalInfoToSheet(Workbook workBook, String sheetName, CellStyle arial10format, String fileName, CellStyle arial14format) {
		ServletContext servletContext = ServletActionContext.getServletContext();
		FileInputStream bannerImage;
		byte[] bytes = null;
		try {
			bannerImage = new FileInputStream(
					String.format(servletContext.getRealPath(IMAGE_PATH + File.separator + ICBPI_LOGO)));
			bytes = IOUtils.toByteArray(bannerImage);
		} catch (Exception e) {
			logger.error(e);
		}

		int pictureId = workBook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
		int rowNum = 1;
		Sheet sheet = workBook.getSheet(sheetName);
		Drawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = new XSSFClientAnchor();
		anchor.setAnchorType(AnchorType.DONT_MOVE_AND_RESIZE);
		anchor.setCol1(1);
		anchor.setRow1(rowNum);
		anchor.setRow2(rowNum + 3);
		anchor.setCol2(2);
		drawing.createPicture(anchor, pictureId);

		rowNum += 3;
		Row row = sheet.createRow(rowNum);

		CommonUtils.getInstance().sheetAddCell(row, 1, "Recupero Crediti Esercente", arial10format);
		rowNum += 2;

		String dataExport = CommonUtils.getInstance().getDateDDMMYYYToString();

		CommonUtils.getInstance().sheetAddCell(CommonUtils.getInstance().sheetAddRow(sheet, rowNum), 1, fileName.substring(0, fileName.length() - 5) + " " + dataExport, arial14format);
		rowNum += 2;
		return rowNum;
	}
	
	public void createCookie() {
		logger.debug("createCookie() - tokenRce:" + request.getParameter("tokenRce"));
		Cookie cookie = new Cookie("downloadRce", request.getParameter("tokenRce"));
		cookie.setPath("/");
		servletResponse.addCookie(cookie);
	}
}
