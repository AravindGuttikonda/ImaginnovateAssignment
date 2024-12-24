This is a basic SpringBoot application containing 2 REST API end points
One endpoint takes Employee details and saves it to DB
Another endpoint will calculate the annual tax for the particular employee

Tax is calculated based on the following rules:

Tax slabs on yearly salary:

No Tax for <=250000

5% Tax for >250000 and <=500000

10% Tax for >500000 and <=1000000

20% Tax for >1000000

Consider the DOJ while calculating total salary(If the employee joined on May 16th, we should not include April month salary and May month's 15 days salary in total salary)

Collect additional 2% cess for the amount more than 2500000 (ex: yearly salary is 2800000 then collect 2% cess for 300000)


Details of first endpoint:

It is REST POST method

Data to be given for Employee in JSON format

Fields(All fields are mandatory):

  Employee ID
  
  FirstName
  
  LastName
  
  Email

  PhoneNumber(May have multiple phone numbers)
  
  DOJ
  
  Salary(per month)
  

Details of second endpoint:

It is REST GET method which takes Employee ID as input

It will return employees' tax deduction for the current financial year(April to March) in the format

  Employee ID
  
  FirstName 
  
  LastName
  
  YearlySalary
  
  TaxAmount
  
  CessAmount
  

 

