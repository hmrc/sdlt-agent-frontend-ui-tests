**This is the template README. Please update this with project specific content.**

# SDLT Agent Frontend UI Tests

UI journey tests suite for the [sdlt-agent-frontend](https://github.com/hmrc/sdlt-agent-frontend) using SeleniumWebdriver with ScalaTest.

## Pre-requisites

### Starting Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d -p 27017:27017 --name mongo percona/percona-server-mongodb:6.0
```

To start the required services via [service manager](https://github.com/hmrc/sm2), run:

```bash
sm2 --start SDLT_ALL
```

### Running tests

* Argument `<browser>` must be `chrome`, `edge`, or `firefox`.
* Argument `<environment>` must be `local`, `dev`, `qa` or `staging`.

To run the UI tests locally, execute the script:

```bash
./run_tests.sh <browser> <environment>
```

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
