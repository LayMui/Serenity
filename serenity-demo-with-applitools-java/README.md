# Getting started instructions
### The project directory structure
The project has build scripts for Maven and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                        Test runners and supporting code
    + resources
      + features                  Feature files
             TODOManagement.feature 
       + webdriver                 Bundled webdriver binaries
         + linux
         + mac
         + windows 
           chromedriver.exe       OS-specific Webdriver binaries 
           geckodriver.exe
```

## Executing the tests
Please ensure you have download the webdriver such as chromedriver, firefox into the path
See the serenity.conf 
```
drivers {
  windows {
    webdriver.chrome.driver = "src/test/resources/webdriver/windows/chromedriver.exe"
    webdriver.gecko.driver = "src/test/resources/webdriver/windows/geckodriver.exe"
    webdriver.ie.driver = "src/test/resources/webdriver/windows/IEDriverServer.exe"
  }
  mac {
    webdriver.chrome.driver = "/src/test/resources/webdriver/mac/chromedriver"
    webdriver.gecko.driver = "/src/test/resources/webdriver/mac/geckodriver"
  }
  linux {
    webdriver.chrome.driver = "src/test/resources/webdriver/linux/chromedriver"
    webdriver.gecko.driver = "src/test/resources/webdriver/linux/geckodriver"
  }
}
```
By default, the tests will run remotely on browserstack. You can run them it in local browser chrome by overriding the `driver` system property, e.g.
```json
$ mvn clean verify -Dwebdriver.driver=chrome 
```

### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```

The project also bundles some of the WebDriver binaries that you need to run Selenium tests in the `src/test/resources/webdriver` directories. These binaries are configured in the `drivers` section of the `serenity.conf` config file:
```json
drivers {
  windows {
    webdriver.chrome.driver = "src/test/resources/webdriver/windows/chromedriver.exe"
    webdriver.gecko.driver = "src/test/resources/webdriver/windows/geckodriver.exe"
  }
  mac {
    webdriver.chrome.driver = "src/test/resources/webdriver/mac/chromedriver"
    webdriver.gecko.driver = "src/test/resources/webdriver/mac/geckodriver"
  }
  linux {
    webdriver.chrome.driver = "src/test/resources/webdriver/linux/chromedriver"
    webdriver.gecko.driver = "src/test/resources/webdriver/linux/geckodriver"
  }
}
```
This configuration means that development machines and build servers do not need to have a particular version of the WebDriver drivers installed for the tests to run correctly.

### Environment-specific configurations
We can also configure environment-specific properties and options, so that the tests can be run in different environments. Here, we configure three environments, __dev__, _staging_ and _prod_, with different starting URLs for each:
```json
environments {
  default {
    webdriver.base.url = "http://todomvc.com/examples/vue/"
  }
  dev {
    webdriver.base.url = "http://todomvc.com/examples/vue/"
  }
  staging {
    webdriver.base.url = "http://todomvc.com/examples/vue/"
  }
  prod {
    webdriver.base.url = "http://todomvc.com/examples/vue/"
  }
}

```
  
You use the `environment` system property to determine which environment to run against. 
For example to run the tests in the staging environment, you could run:
```json
$ mvn clean verify -Denvironment=staging
```

For example to run the tests with specific tags, you could run:
```json
$ mvn clean verify -Dcucumber.options="--tags @smoke"
```
For example to run the tests in the staging environment and with specific tags, you could run:
```json
$ mvn clean verify -Denvironment=staging -Dcucumber.options="--tags @smoke"
```

For example to run the tests locally using specific browser say chrome
```json
$ mvn verify -Dwebdriver.driver=chrome -Dcucumber.options="--tags @smoke"
```
For example to run the tests on browserstack the configuration and capabilities
Change the serenity.conf

For example to run the tests on browserstack with different build say 2.5
```json
$ mvn clean verify -Dcucumber.options="--tags @smoke" -Dbstack_build=2.5
```

To force updating of snapshots and re-download all the dependencies
$ mvn clean install -U -Dmaven.test.failure.ignore=true 

The test results will be recorded in the `target/site/serenity` directory.
