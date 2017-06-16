
Java基础：略。
Java官方文档：http://docs.oracle.com/javase/8/docs/api/



搭建开发环境
1.安装配置JDK，参考百度经验
http://jingyan.baidu.com/article/6dad5075d1dc40a123e36ea3.html

2.安装开发工具，以Eclipse为例，参考百度经验
http://jingyan.baidu.com/article/d7130635194f1513fcf47557.html

3.新建Java Project

4.下载并导入Selenium的独立jar文件，见“selenium-server-standalone-2.45.0.jar”

5.导入JUnit jar文件，见“junit-4.12.jar”

6.JUnit的使用
6.1 使用@Before @Test @After给方法添加注解，在Eclipse中通过Run as -> 
	JUnit Test运行测试脚本
6.2 使用Suite将多个JUnit Test类一起执行，代码具体如下所示：
	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		LoginTest.class,
		IsbnBookTest.class,
		AddIsbnTest.class
	})
6.3 其他可参考JUnit官方文档
    http://junit.org/junit4/javadoc/latest/index.html


Selenium简介

1. Selenium 1.x介绍
1.1 Selenium 1.x是一套完整的Web测试系统
1.2 Selenium 1.x包含脚本录制（Selenium IDE）,编写和运行（Selenium RC）


2.Selenium 2介绍
2.1 Selenium 2又被称为WebDriver，是Selenium 1.x 和WebDriver的集成，合并前两者是单独的项目
2.2 Selenium 2能够绕过JS沙箱，并为用户提供更多的浏览器和开发语言的支持


3. Selenium 2（WebDriver）使用方式
3.1在代码中显示指定浏览器驱动的路径，否则可能无法启动浏览器
3.2根据选择浏览器，新建浏览器驱动对象driver，用于操作页面元素


4. Selenium API基本语法
4.1通过方法driver.get(url)在指定浏览器中打开网址，driver.quit()或driver.close()关闭
4.1根据Selenium API获取页面元素方法，如：element = driver.findElement(By.XX(“”))
	获得页面元素；
	element.click()实现点击操作
	element.sendKeys(“”)输入内容
4.3 Selenium 基础内容可参考相关文档，见“Selenium_中文API.pdf”
4.4 Selenium Java 参考文档（英文版），见“selenium_api_JAVADOC.rar”,
	解压后打开“index.html”即可查看


5. Selenium IDE 脚本录制工具
5.1 Selenium是Firefox的一个插件，可在Firefox中直接添加组件
5.2 打开IDE，Firefox中输入网址，开始录制，录制完成，可导出java/python/C#
	等多种开发语言支持的脚本。





