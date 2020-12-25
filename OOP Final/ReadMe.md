# Instructions to run the CLI and GUI 🔴

1. Firstly, we have to compile all the java files with in the CLI folder

2. Before that go into the GUI folder and run the following command on a separate terminal so that we can start up the GUI servers for both front and backend

        sbt run

3. Run the following code so that the necessary java files get compiled
    
        javac -d . *.java

4. Running the Console Application using the Terminal
        
        java console.ConsoleApplication

5. Now running the JUnit Test file 

6. Cut everything present inside the "tests" folder and paste it inside the CLI root folder

7. Run the following code to compile the test class

        javac -d . -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." PremierLeagueTester.java 

8. Run the following code to run the test java file

        java -cp "junit-4.8.1.jar; hamcrest-2.2.jar;." org.junit.runner.JUnitCore tests.PremierLeagueTester
