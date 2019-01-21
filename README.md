# Postman Orchestrator

This is a Java project to help Postman and Git users.
When working on a test project with Postman, after a while, the collection file can become quite large and complex.
This complexity can be a problem when several people work on the same collection and version control it (https://community.getpostman.com/t/better-version-controlling-mechanism-for-postman-tests/2052).

The idea of this tool and its method is to work with small collection with few test cases and then use the tool to combine them in a unified collection that can be used with Newman (Postman CLI tool).
 This way, with small collection file, commit review is easier and work can be shared.

## Table of contents

1. [Getting Started](#getting-started)
    1. [Prerequisites](#prerequisites)
    2. [Build](#build)
2. [Running the tests](#running-the-tests)
3. [Usage](#usage)
    1. [Orchestrator file](#orchestrator-file)
    2. [Scenario](#scenario)
    3. [Generate your scenario](#generate-your-scenario)
4. [Command Line Options](#command-line-options)
5. [Contributing](#contributing)
6. [Versioning](#versioning)
7. [Authors](#authors)
8. [License](#license)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To build and run the project you will need [Maven](https://maven.apache.org/) - Dependency Management and Java 8 version

### Build

To build the PostmanOrchestrator, use the following Maven command in the root folder of the project

The command will generate the *PostmanOrchestrator.jar* in Application\target

## Running the tests

The test cases will be ran automatically with the build command. You can run all test independently if you import the project on any Java IDE

## Usage

### Orchestrator file

To use the PostmanOrchestrator in your project, you first need to create a file name *orchestrator.json*.
This file should be in the root folder of your test project. 

Example 1:
```
Root
    |_ orchestrator.json
    |_ collection1.postman_collection.json
    |_ collection2.postman_collection.json
    |_ Folder 1
        |_ collection3.postman_collection.json
    |_ Folder 2
        |_ Folder 3
            |_ collection4.postman_collection.json
    |_ ...
```

This file is a json file that will contain the definition of all your scenario.

### Scenario

A scenario is a list of collections that you want to combine with the PostmanOrchestrator.
A scenario could be composed by a list of collections and/or a list of parent scenarios.

Example 2 based on Example 1 folder structure:
```
{
	"Scenario 1": {
	    "collections": [
	        "collection1.postman_collection.json",
	        "collection3.postman_collection.json"
	    ]
	},
	"Scenario 2": {
        "collections": [
            "collection2.postman_collection.json"
        ]
    },
    "Full Scenario": {
        "collections": [
            "collection4.postman_collection.json"
        ],
        "scenarios": [
            "Scenario 1",
            "Scenario 2"
        ]
    }
}
```

In this example, if you build the *Scenario 1*, you will generate a new collection file in the folder *Root* that will 
be named *Scenario_1.postman_collection.json*, in which you will have a collection folder
**collection1** with all requests and sub folders from *collection1.postman_collection.json* and another collection folder
**collection3** with all requests and sub folders from *collection3.postman_collection.json*.


In the same way, if you build the *Full Scenario*, the generated file will be *Full_Scenario.postman_collection.json* and will contain
the concatenation of all collections from *Scenario 1*, *Scenario 2* and the *collection4*. 

### Generate your scenario

To use the PostmanOrchestrator and generate your scenario, you need to run the following command

```
java -jar "PATH_TO_YOUR_JAR/PostmanOrchestrator.jar" -f "PATH_TO_THE_ROOT_FOLDER" -s "SCENARIO_NAME"
```

For the complete list of options, refer the [Command Line Options](#command-line-options) section below.

## Command Line Options

- `-h`, `--help`<br />
  Show command line help, including a list of options, and sample use cases.

- `-f <path>`, `--folder <path>`<br />
  Specify the base path where your collections can be found. In this folder, if the *orchestrator.json* file exists,
  the PostmanOrchestrator will use it to build your scenario, otherwise, it will concatenate all collections available 
  in the path and its sub-folders.

- `-s <name>`, `--scenario <name>`<br />
  Specify the name of the scenario you want to build. If the scenario name does not exist, an empty collection with your 
  scenario name will be created in the specified folder path.
  
- `-o <path>`, `--scenario <path>`<br />
  Specify a specific path and name for your generated collection. By default, the generated collection will be named 
  using the specified scenario name and will be available in folder specified using the `-f` option.
  
## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Anthony Todisco** - *Initial work* - [PostmanOrchestrator](https://github.com/PostmanOrchestrator)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
