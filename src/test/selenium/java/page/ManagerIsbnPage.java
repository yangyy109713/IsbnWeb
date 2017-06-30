package test.selenium.java.page;

public class ManagerIsbnPage {
	public final String ISBN_MANAGE = "ISBN管理";
	public final String ISBN_MANAGER_XPATH = "//div[@id='main-menu-inner']/ul/li/ul/li/a/span";
	public final String ADD_ISBN = "add-isbn";
	
	public final String NEW_BARCODE_BARCODE_XPATH = "//form[@id='isbn_new_barcode_form']//input[@type='text']";
	public final String BARCODE = "2017063015172";
	public final String NEW_BARCODE_SUBMIT_XPATH = "//form[@id='isbn_new_barcode_form']//button[@type='submit']";
	
	public final String BARCOD_ID = "barcode";
	public final String BARCOD_SEARCH_ID = "search-btn";
	
	public final String DELETE_BOOK_CLASS = "isbn-delete-info";
	public final String DELETE_BOOK_CONFIRM = "确定要删除书本？";
	public final String ADD_BOOK_XPATH = "(//table[@id='workflowIsbnTable']//div[@class='pull-left'])[1]/a/span";//when only a barcode
	public final String ADD_BOOK_CONFIRM = "确定需要新建作业本？";
	
	public final String EDIT_BOOK_LINKTEXT = "编辑";
	
	//public final String BOOK_NAME_XPATH = "(//div[@id='isbn-info-panel-0']//div[@class='row'])[1]/div/div/div/input";
	
	public final String BOOK_NAME_NAME = "name";
	public final String BOOK_NAME = "香槟玫瑰";
	
	public final String BOOK_TYPE = "type";
	public final String BOOK_TYPE_SELECT = "暑期作业";
	public final String BOOK_SUBJECT = "subject";
	public final String BOOK_SUBJECT_SELECT = "英语";
	public final String BOOK_GRADE = "grade";
	public final String BOOK_GRADE_SELECT = "八年级";
	public final String BOOK_TERM = "term";
	public final String BOOK_TERM_SELECT = "上册";
	public final String BOOK_EDITION = "edition";
	public final String BOOK_EDITION_SELECT = "浙教版";
	
	//public final String BOOK_PUBLISH_XPATH = "(//div[@id='isbn-info-panel-0']//div[@class='row'])[3]/div/div/div/input";
	public final String BOOK_PUBLISH_NAME = "press";
	public final String BOOK_PUBLISH_CONTENT = "香槟玫瑰工厂";
	public final String BOOK_PUBLISH_YEAR = "year";
	public final String BOOK_PUBLISH_YEAR_CONTENT = "2017";
	public final String BOOK_PUBLISH_MONTH = "month";
	public final String BOOK_PUBLISH_MONTH_CONTENT = "06";
	public final String BOOK_PUBLISH_REVISION = "revision";
	public final String BOOK_PUBLISH_REVISION_CONTENT = "10";
	public final String BOOK_PUBLISH_PRINTYEAR = "printYear";
	public final String BOOK_PUBLISH_PRINTYEAR_CONTENT = "2017";
	public final String BOOK_PUBLISH_PRINTMONTH = "printMonth";
	public final String BOOK_PUBLISH_PRINTMONTH_CONTENT = "06";
	public final String BOOK_PUBLISH_PRINTREVERSION = "printRevision";
	public final String BOOK_PUBLISH_PRINTREVERSION_CONTENT = "16";
	
	public final String HAS_ANSWER = "hasAnswer";
	public final String HAS_ANSWER_SELECT = "是";
	public final String VALIDATION = "validation";
	public final String VALIDATION_SELECT = "未经过审定";
	public final String UPLOAD_IMG = "cover-file-input-0";
	public final String UPLOAD_IMG_PATH = "E:\\Photo\\作业本2\\00.jpg";
	public final String SAVE_BOOK_CLASSNAME = "save-info";
	public final String SAVE_BOOK_CONFIRM = "你确定需要保存数据？";
	
	public final String HANDLE_CLASS = "open-workflow-modal";
	public final String HANDLE_O_ID = "workflowOp";
	public final String HANDLE_O_BUY = "提交购买";
	public final String HANDLE_P_ID = "nextUserId";
	public final String HANDLE_P_M = "yangyyhandle";
	public final String HANDLE_E_ID = "workflowDesc";
	public final String HANDLE_E_CON = "auto test";
	public final String HANDLE_SUBMIT_CLASS = "do-commit-workflow";
	public final String HANDLE_SUBMIT_CONTENT = "您确定需要提交该处理？";

	public final String BUY_XPATH = "(//div[@id='main-menu-inner']/ul/li/ul/li)[4]";
	public final String HANDLE_BUY = "提交上传";
	public final String HANDLE_M1 = "yangyyupload";
}
