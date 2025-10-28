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
./run-tests.sh <browser> <environment>
```

### Running tests - Environment

To run the tests against an environment set the corresponding `host` environment property as specified under
`<env>.host.services` in the [application.conf](/src/test/resources/application.conf).

To run the UI performance smoke tests in staging environment, execute the script:

```bash
./run-tests.sh chrome staging
```

### ZAP tests

The `run_zap_tests.sh` script uses [dast-config-manager](https://github.com/hmrc/dast-config-manager) to run ZAP tests locally using the DAST Docker image.

To run the ZAP tests locally, execute the script:

```bash
./run_zap_tests.sh
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
