# Kong Gateway API Tests 
Test Automation framework to test APIs of Kong Gateway

**Tech Stack:**</br>
Java version "21.0.3"</br>
Gradle 8.7</br>
**Kong Gateway PROD (Im using the one-month trail version)**
****</br>

**Prerequisites/Setup:**
1. Java and Gradle installed</br>
2. Have a Kong Gateway API Key handy 

**How to Run: Created a github action** </br>

**How to Run: (on Local Machine, Non Docker )** </br>
(If you want to just run the tests locally, without using Docker) </br>

1. Clone this repo </br>
2. Navigate to root directory of the repo </br>
3. Run **export KONG_API_KEY to your KONG Gateway API Key**
4. Run **./gradlew test -i** </br>
5. Verify Test results in lib\build\reports\tests\test\index.html </br>

**How to Run (Docker way)** 
1. Clone this repo </br>
2. Navigate to root directory of the repo and set an env variable KONG_API_KEY to your KONG GATEWAY API Key</br>
3. Build the docker image with command ==> **docker build -t kong-api-tests:1.0 .** </br>
4. Verify docker image is built successfully using docker images command
5. Run the docker image in a container, which will run the tests and save the test reports locally, in the host machine </br>
   **docker run -e KONG_API_KEY=$KONG_API_KEY -v <TESTOUTPUT_PATH>:/opt/kong/lib/build/reports kong-api-tests:1.0 </br>**
    In Windows: **docker run -e KONG_API_KEY=%KONG_API_KEY% -v <TESTOUTPUT_PATH>:/opt/kong/lib/build/reports kong-api-tests:1.0 </br>**
   Change the TESTOUTPUT_PATH to your desired location to save test results
4. Verify Test results in your set location </br>
 
**What's happening behind the scenes:**
The Dockerfile uses a openjdk base image, which has java pre-built. </br>
There is a script called runtests.sh, which is the entrypoint for the Dockerfile. This script setsup an openjdk baseimage in the container and also fire the test execution using a gradle command </br> 
Our docker run command has a -v option, which creates a volume, and this is how we copy the test result reports to the host machine.Also it uses your host KONG_API_KEY environment veiable and pass it to docker container by -e option, which is needed to authenticate the kong API calls


**Libraries used:**</br>
Java HttpClient2</br>
junit </br>

<h3>**Test Details:**</h3>
The tests focus on Kong Gateway functionality. In a nutshell tests deal with creation, modification, deletion of Control Planes, Services and routes. We have tests covering some of the CRUD operations that the rest api offers. And the tests are accpetance tests integrating flows accross different operations of Kong Gateway.
The Tests cover 
<ul type="square">
<li>Create Control Plane</li>  
<li>List Control Planes</li> 
<li>Get Control Plane</li> 
<li>Delete Control Plane</li> 
<li>Create Service</li>  
<li>List Services</li> 
<li>Get Service</li> 
<li>Delete Service</li> 
<li>Create Route</li>  
<li>Get Routes</li> 
<li>Get GLobal configuration to fetch Proxy URL</li>  
<li>Access Proxy URL+ Route and verify the backend service api is called successfully</li>  
<li>Delete Route</li> 
  
</ul>**
****There is one end to end style API test, whiere I create a control plane, create a service, a route, and access the route on the proxy URL to successfully access the Service API****

</br>
For Service definition URL, I used a free (and apikey less) rest api service from the web, it's simple and lightweight. It's https://dummyjson.com/todos

**Successful Github Actions Run:** </br>
![image](https://github.com/user-attachments/assets/00d2eab9-7e08-4990-8aee-f5da5faa5736)


**Sample Test Reports:** </br>
</br>![image](https://github.com/user-attachments/assets/4b5e5356-e7ed-4bd0-a8fd-3994ef7d629d)


</br![image](https://github.com/user-attachments/assets/b97d2cce-da56-41db-ab73-64b60476a1e2)

</br> ![image](https://github.com/user-attachments/assets/00c2fac9-15a2-4afc-a7c1-10b0c8a1053b)

****Sample Logging**:**</br>
![image](https://github.com/user-attachments/assets/0fba4647-87da-402e-85f6-84db09f7f444)






