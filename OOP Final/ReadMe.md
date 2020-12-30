# Instructions to run the CLI and GUI 🔴

1. Firstly, go into the **'ui'** folder which is inside the **GUI** folder and open the terminal to this path and run the following command to install all the **node modules** for the front-end angular

        npm install

2. Once all the **node modules** are installed, then **go back to the GUI root folder** and run the following command on a separate terminal so that we can **start up the GUI servers for both front and backend**

        sbt run

3. Now, go into the CLI folder and run the following code using the terminal so that the necessary java files get **compiled**
    
        javac -d . *.java

4. **Running** the Console Application using the Terminal
        
        java console.ConsoleApplication

5. Now running the JUnit Test file 

6. Cut everything present inside the **'tests'** folder and paste it inside the CLI root folder

7. Run the following code to **compile** the test class

        javac -d . -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." PremierLeagueTester.java 

8. Run the following code to **run** the test java file

        java -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." org.junit.runner.JUnitCore tests.PremierLeagueTester
