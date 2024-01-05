# Java Sample Application for Self-Learning

## Abstract

## How to generate coverage report

Use JaCoCo(Java Code Coverage) library as test coverage tool.

Execute the following command to generate coverage reports.

```bash
mvn clean verify
```

Coverage reports are generated in `target/site/jacoco` directory.

```bash
ll target/site/jacoco/
-rw-r--r--   1 user group <size>  <time_stamp> index.html
drwxr-xr-x  22 user group <size>  <time_stamp> jacoco-resources
-rw-r--r--   1 user group <size>  <time_stamp> jacoco-sessions.html
-rw-r--r--   1 user group <size>  <time_stamp> jacoco.csv
-rw-r--r--   1 user group <size>  <time_stamp> jacoco.xml
```
