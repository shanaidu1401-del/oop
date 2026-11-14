FLEET DISPATCH SYSTEM — INDIVIDUAL ASSIGNMENT
Requires JDK 17 or later. No external libraries.
Open a terminal in this project folder.
Compile: javac -d out src/Bus.java src/FleetDispatchApp.java src/BusTest.java
Run: java -cp out FleetDispatchApp
Test: java -cp out BusTest
Windows and Linux use the same commands above.

Bus.java is the chosen object class. FleetDispatchApp.java is the main class.
BusTest.java contains optional verification checks and is not a second student's object.
The sample data is fictional. Data is kept in memory and resets after closing.
The sample-output.txt file records a real run using menu choices:
1, 2 (5 passengers), 3 (30 km), 1, 4, 4, 2 (1 passenger), 0.
Tested with OpenJDK 21. All 19 checks passed.