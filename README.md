# OTP
🔑📲 💬 one time password SMS library for bulkSMS gateway

this library will help you to send SMS and one time passwords for authentication uses in your application throught bulkSMS gateway

1. open an account on BulkSMS website https://www.bulksms.com/account/#!/registration

2. download the otp.jar file 

3. add the jar file to your project libraries

4. BulkSMS.authenticate("username", "password");

5. BulkSMS.generateOTP(digits, alphanomeric);  
  * digits = the length of the otp (1,2,3,.....) .
  * alphanomeric = otp including alphanomeric letters or not (True ,False) .
  
6. BulkSMS.sendSms("country code", "phone number", "sms body : " + otp);

7. feel free to use and have fun 😊


