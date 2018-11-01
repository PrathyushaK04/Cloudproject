# Cloudproject
University Name: http://www.sjsu.edu/  

Course: [Cloud Technologies ](http://info.sjsu.edu/web-dbgen/catalog/courses/CMPE281.html)

Professor: [Sanjay Garje ](https://www.linkedin.com/in/sanjaygarje/)

ISA: [Anushri Srinath Aithal ](https://www.linkedin.com/in/anushri-aithal/)

Student: [Prathyusha Kasibhatta](https://www.linkedin.com/in/prathyusha-kasibhatta-5b39ab169/)

### Introduction: 

It is a 3 Tier web application which is highly available, scalable and hosted on AWS cloud and is accessible to users through public internet. This application uses various AWS services such as Route 53, Elastic Beanstalk, S3, CloudFront, Cloud Trail, CloudWatch, Lambda, Code Pipeline, SNS and MySQL database. 

### Features List: 

SignUp: User can create an account with their emailid for using this website. The signUp form does not accept null values and emailid should be in correct format. User cannot signup using that emailid again. 

Login: After creating an account, user can login for managing files. 

File Upload: User can upload files and they will be stored in both s3 bucket and database. 

File Update: To update object, same name should be given or else another object will be created. 

File Size restriction: User cannot upload a file which is more than 10mb. An alert will be given if user tried to upload. 

File Download: User can download already uploaded files from S3. 

File Delete: User can delete files and the files will be deleted both in S3 and database. 

Admin Login: Admin can login and manage files created by all users. Admin can download and delete the files created by users. 

Google API: User can login using google account and access the features of this website without creating another account. 

 

### AWS Services: 

Provided S3 storage service for the users which is highly available and scalable with low latency. 

Enabled Versioning and Transfer acceleration for the S3 bucket. 

The objects will be moved to Standard IA after 75 days and to Amazon Glacier after 365 days which reduces cost. 

After one more year in Amazon Glacier, objects will be deleted. 

Created Cross-Region Replication for the S3 bucket which helps in disaster recovery and faster delivery. 

Created CloudFront Distribution for the S3 bucket and blacklisted Cubans so that they couldn’t access my S3 bucket. Edited TTL to 60 seconds so that for every 60sec the cache will be refreshed. 

Used CloudTrails which is an API log monitoring service which sends events configured to CloudWatch log group. So continuous monitoring will be there for S3. So whenever user uploads objects, CloudWatch invokes lambda function and the logs are stored in S3. 

Created two environments Development and Production using Elastic Bean Stalk and deployed code to it.  

Used Code Pipeline for Continuous Integration and Continuous Delivery. Whenever a commit is made to git repo, it releases the changes to Development environment. 

For changes to move to Production environment, manual approval is required. Because any changes that causes production environment down are not allowed. So, development environment is helpful in testing. 

For manual approvals, I have used SNS to send mails so that admin will be notified whenever there is an update. 

I have implemented Google API, so that user can login using their google account without creating an account. 

### Screenshots: 

SignUp page:
![alt text](https://github.com/PrathyushaK04/Cloudproject/blob/master/WebContent/styles/Signup.png)

Login Page:
![alt text](https://github.com/PrathyushaK04/Cloudproject/blob/master/WebContent/styles/Login.png)
User Dashboard:
![alt text](https://github.com/PrathyushaK04/Cloudproject/blob/master/WebContent/styles/Dashboard.png)
Admin Dashboard:
![alt text](https://github.com/PrathyushaK04/Cloudproject/blob/master/WebContent/styles/admindashboard.png)
