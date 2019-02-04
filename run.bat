set ProjectPath=C:\Users\mayur.choudhary\workspace\EmailTemplate
echo %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\Lib\*
echo %classpath%
java org.testng.TestNG %ProjectPath%\testsuite.xml