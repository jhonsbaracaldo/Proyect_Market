<img align="right" src="https://github.com/ada-school/module-template/blob/main/ada.png">


## CSV Reader with Java

Use Java to create a CSV Reader library.

**Learning Objectives**

- [ ]  Implement a CSV reader using Java.
- [ ]  Use a Maven to import a dependency to use an existing CSV reader library.



##  Detail Orientation 🤹🏽 

Crafting a library requires detail orientation. Make sure you consider all the edge cases of what can wrong eg:
- Empty fields passed on the CSV file.
- Fields with invalid input types (numbers as string)

**Main Topics**

* CSV
* Maven
* Libraries



## Codelab 🧪

🗣️ "I hear and I forget I see and I remember I do and I understand." Confucius



### Part 1: Implementing a CSV Reader with Java:

1. Download, import and run the code of this repository.
2. Open the TODO tab and implement the code required to transform each linea read in the file into a Product object.
3. Create a class called *ProductsService* and move you code there to store the list of products load from the CSV.
4. Override the *toString* method of the *Product* class and run your code to print your products and verify it works as excpected.
5. Create an interface to define how other users could use your library.
6. Change your code on the main method to use the interface and preview how your library works.

### Part 2: Using Maven to avoid re-inventing the wheel:

1. Read the following article to understand how to add *Maven* to the Java project
  * <a target="_blank" href="https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html#add_maven_support">Add Maven Support</a>

2. Once your project has the Maven include import the following library into your dependencies:

  * <a target="_blank" href="https://mvnrepository.com/artifact/com.opencsv/opencsv/5.7.0">MVN Repository Open CSV</a>

3. Read the Open CSV documentation to understand how to use the CSV library:
  * <a target="_blank" href="https://opencsv.sourceforge.net/index.html">Open CSV Documentation</a>
4. Modify your code to use the Open CSV library instead of reading and processing line by line.

### Advance Challenge: Export your library and create a Jar file

1. Search on the internet and learn how to use Maven to generate a Jar file, so you can port your library to other Java
   projects.

   ***Hint***: Use an existing Maven library. Do not re-inventing the wheel!
