
Java�������ԡ�
Java�ٷ��ĵ���http://docs.oracle.com/javase/8/docs/api/



���������
1.��װ����JDK���ο��ٶȾ���
http://jingyan.baidu.com/article/6dad5075d1dc40a123e36ea3.html

2.��װ�������ߣ���EclipseΪ�����ο��ٶȾ���
http://jingyan.baidu.com/article/d7130635194f1513fcf47557.html

3.�½�Java Project

4.���ز�����Selenium�Ķ���jar�ļ�������selenium-server-standalone-2.45.0.jar��

5.����JUnit jar�ļ�������junit-4.12.jar��

6.JUnit��ʹ��
6.1 ʹ��@Before @Test @After���������ע�⣬��Eclipse��ͨ��Run as -> 
	JUnit Test���в��Խű�
6.2 ʹ��Suite�����JUnit Test��һ��ִ�У��������������ʾ��
	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		LoginTest.class,
		IsbnBookTest.class,
		AddIsbnTest.class
	})
6.3 �����ɲο�JUnit�ٷ��ĵ�
    http://junit.org/junit4/javadoc/latest/index.html


Selenium���

1. Selenium 1.x����
1.1 Selenium 1.x��һ��������Web����ϵͳ
1.2 Selenium 1.x�����ű�¼�ƣ�Selenium IDE��,��д�����У�Selenium RC��


2.Selenium 2����
2.1 Selenium 2�ֱ���ΪWebDriver����Selenium 1.x ��WebDriver�ļ��ɣ��ϲ�ǰ�����ǵ�������Ŀ
2.2 Selenium 2�ܹ��ƹ�JSɳ�䣬��Ϊ�û��ṩ�����������Ϳ������Ե�֧��


3. Selenium 2��WebDriver��ʹ�÷�ʽ
3.1�ڴ�������ʾָ�������������·������������޷����������
3.2����ѡ����������½��������������driver�����ڲ���ҳ��Ԫ��


4. Selenium API�����﷨
4.1ͨ������driver.get(url)��ָ��������д���ַ��driver.quit()��driver.close()�ر�
4.1����Selenium API��ȡҳ��Ԫ�ط������磺element = driver.findElement(By.XX(����))
	���ҳ��Ԫ�أ�
	element.click()ʵ�ֵ������
	element.sendKeys(����)��������
4.3 Selenium �������ݿɲο�����ĵ�������Selenium_����API.pdf��
4.4 Selenium Java �ο��ĵ���Ӣ�İ棩������selenium_api_JAVADOC.rar��,
	��ѹ��򿪡�index.html�����ɲ鿴


5. Selenium IDE �ű�¼�ƹ���
5.1 Selenium��Firefox��һ�����������Firefox��ֱ��������
5.2 ��IDE��Firefox��������ַ����ʼ¼�ƣ�¼����ɣ��ɵ���java/python/C#
	�ȶ��ֿ�������֧�ֵĽű���





