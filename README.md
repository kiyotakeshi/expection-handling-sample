# exception handling sample for REST API

post article: https://zenn.dev/bm_sms/articles/aec9aeca1fee34

## commands

generate model from OpenAPI spec

```shell
./gradlew openApiGenerate
```

format code

```shell
./gradlew detekt
```

run

```shell
./gradlew bootRun
```

## Postman collection

- [Postman collection](./postman/)

## API schema

run nginx container with ReDoc CDN

```shell
docker compose up -d
```

access: localhost:3000
