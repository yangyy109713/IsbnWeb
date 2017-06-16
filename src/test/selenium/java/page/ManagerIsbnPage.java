package test.selenium.java.page;

public class ManagerIsbnPage {
	public final String ISBN_MANAGE = "ISBN����";
	public final String ISBN_MANAGER_XPATH = "//div[@id='main-menu-inner']/ul/li/ul/li/a/span";
	public final String ADD_ISBN = "add-isbn";
	
	public final String NEW_BARCODE_BARCODE_XPATH = "//form[@id='isbn_new_barcode_form']//input[@type='text']";
	public final String BARCODE = "2017061500003";
	public final String NEW_BARCODE_SUBMIT_XPATH = "//form[@id='isbn_new_barcode_form']//button[@type='submit']";
	
	public final String BARCOD_ID = "barcode";
	public final String BARCOD_SEARCH_ID = "search-btn";
	
	public final String DELETE_BOOK_CLASS = "isbn-delete-info";
	public final String DELETE_BOOK_CONFIRM = "ȷ��Ҫɾ���鱾��";
	public final String ADD_BOOK_XPATH = "(//table[@id='workflowIsbnTable']//div[@class='pull-left'])[1]/a/span";//when only a barcode
	public final String ADD_BOOK_CONFIRM = "ȷ����Ҫ�½���ҵ����";
	
	public final String EDIT_BOOK_LINKTEXT = "�༭";
	
	//public final String BOOK_NAME_XPATH = "(//div[@id='isbn-info-panel-0']//div[@class='row'])[1]/div/div/div/input";
	
	public final String BOOK_NAME_NAME = "name";
	public final String BOOK_NAME = "����õ��";
	
	public final String BOOK_TYPE = "type";
	public final String BOOK_TYPE_SELECT = "������ҵ";
	public final String BOOK_SUBJECT = "subject";
	public final String BOOK_SUBJECT_SELECT = "Ӣ��";
	public final String BOOK_GRADE = "grade";
	public final String BOOK_GRADE_SELECT = "���꼶";
	public final String BOOK_TERM = "term";
	public final String BOOK_TERM_SELECT = "�ϲ�";
	public final String BOOK_EDITION = "edition";
	public final String BOOK_EDITION_SELECT = "��̰�";
	
	//public final String BOOK_PUBLISH_XPATH = "(//div[@id='isbn-info-panel-0']//div[@class='row'])[3]/div/div/div/input";
	public final String BOOK_PUBLISH_NAME = "press";
	public final String BOOK_PUBLISH_CONTENT = "����õ�幤��";
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
	public final String HAS_ANSWER_SELECT = "��";
	public final String VALIDATION = "validation";
	public final String VALIDATION_SELECT = "δ������";
	public final String UPLOAD_IMG = "cover-file-input-0";
	public final String UPLOAD_IMG_PATH = "E:\\Photo\\��ҵ��2\\00.jpg";
	public final String SAVE_BOOK_CLASSNAME = "save-info";
	public final String SAVE_BOOK_CONFIRM = "��ȷ����Ҫ�������ݣ�";
	
	public final String HANDLE_CLASS = "open-workflow-modal";
	public final String HANDLE_O_ID = "workflowOp";
	public final String HANDLE_O_BUY = "�ύ����";
	public final String HANDLE_P_ID = "nextUserId";
	public final String HANDLE_P_M = "yangyyhandle";
	public final String HANDLE_E_ID = "workflowDesc";
	public final String HANDLE_E_CON = "auto test";
	public final String HANDLE_SUBMIT_CLASS = "do-commit-workflow";
	public final String HANDLE_SUBMIT_CONTENT = "��ȷ����Ҫ�ύ�ô�����";
}