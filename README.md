# sanjira
The project consists of 2 parts. Backend and Mobile application as a frontend.

The backend (sanjyrabackend) is responsible for the server. 
It is installed on the server that it purchased from Subutai.( subutai.io ) 
The backend builds a database and works as a redirector. 
It receives data from the Mobile application and transfers it to the database. 
And vice versa, transfers data from the database to the application using RestTemplate.

The mobile application itself is located in the Kyrgyzpedigree folder. 
The entire project is located there and it is ready to be launched.


------------<
sudo apt-get update
sudo apt install default-jdk
sudo apt install maven    
sudo apt install mysql-server
sudo apt-get install git   
git clone https://github.com/nurik23/sanjira.git 
cd sanjira/  
cd sanjyrabackend/ 
--rm -rf target/
mvn clean package
cd target/
nohup java -jar sanjyra-backend-1.0.jar &
cat nohup.out
------->
