This is a selenium test on firefox
code in Main.java is meanless.The real code is in MainTest.java
Read id and email from info.csv, then visit http://www.ncfxy.com and login with id.
Then get email from page and check if the email is the same as the email from info.csv.
Case the infomation from info.csv is private, info.csv will not be uploaded.

If two emails are the same , 'email is ***** 'will be printed.
If two emails are different,'fail in id ***** where email should be **** but email list is *******'will be printed.