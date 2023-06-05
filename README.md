
# Practice Test with Maven and JBehave

Implement a test case to search for a keyword on Google home page using jBehave (framework for Behaviour-Driven Development).

1. Create new Maven Project.
2. Add all required dependencies to project.
3. Create required JBehave story in the package.
4. Create required a Java class in the package.
5. Use at least the "Given, Then, When" annotations in story.
6. Write a scenario using JBehave.
7. Implement a test case to open google home page and search for keyword "JBehave".
8. If the results include string "What is JBehave?" , test case succeeded. And failed otherwise.

**_Note:_** You can configure chrome driver in order to open google page. You might also need more libraries to be configured.

**_Expected Output:_** Documentation of the exercise and link to github or project zip file.


# Deployment

You must ensure that both commands work for your non-root user:

```
java -version
mvn -version
```

In the path of your choice clone this GitHub repository:

`git clone git@github.com:guanchgonzalez/practice_jbehave.git`


## Chrome WebDriver

In webdriver/ path you can see the Chrome driver binary file for a Mac 64bits platform and latest Chrome release (114.0.5735.90). This is the interface between the installed browser into the host where you deploy the project and the Selenium driver managed by Maven.
For other environments (different platform and/or different Chrome version), you can substitute the file by the one you need from the [Chrome WebDriver official site](https://chromedriver.chromium.org/downloads).


## Maven libraries issue

To force the download of all the required libraries from the [Maven Repository](https://mvnrepository.com/) execute these commands:

```
mvn clean
mvn compile
mvn dependency:tree
mvn install
```

At this point you will have already noticed a compilation error before performing the JBehave scenario: a **NoSuchMethodError** when loading the _com.google.common.collect.ImmutableMap_ method. This is a known conflict between dependent libraries that remains unresolved on Maven v3.9.2, as you can check on this [StackOverFlow article](https://stackoverflow.com/questions/71095560/java-lang-nosuchmethoderror-com-google-common-collect-immutablemap-error-when).

The issue was fixed on com.google.guava:guava:[30.1-jre](https://mvnrepository.com/artifact/com.google.guava/guava/30.1-jre) library, which is the dependent version for the com.google.inject:guice:[5.0.0](https://mvnrepository.com/artifact/com.google.inject/guice/5.0.0) version and following, but the last JAR file available to download in the Maven repository (compatible with the latest available org.apache.maven:maven-core is the [3.6.0](https://repo1.maven.org/maven2/org/apache/maven/maven-core/3.6.0/) version by the way) is the [4.2.3-no_aop](https://repo1.maven.org/maven2/com/google/inject/guice/4.2.3/). The dependent version downloaded by `mvn` for the com.google.inject:guice is the com.google.guava:guava:[25.1-android](https://mvnrepository.com/artifact/com.google.guava/guava/25.1-android), which con.google.common.collect ImmutableMap class only accept up to 10 arguments, instead of the 12 required ones during the `mvn test` phase.

Until an official fix is released, you can use this workaround:

In your local Maven repository (by default, _$HOME/.m2/repository_) edit the **com/google/inject/guice/4.2.1/guice-4.2.1.pom** file and change the parent.version from `4.2.1` to `5.1.0`. Then perform again the `mvn install` phase and that's all!


# Test execution and report

Launch the specific `mvn integration-test` phase and check the output result in a HTML format in the [target/jbehave/view/rgl_practice.stories.JBehave.html](target/jbehave/view/rgl_practice.stories.JBehave.html) test report file.

