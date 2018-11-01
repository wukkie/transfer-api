# Transfer API
Standalone Money Transfer API server.

## Details
- Libs: Dropwizard, h2, hibernate, lombok, maven

## Business requirements
- Transfer money from one account to another.
- User like to see forecast of transfer before operation.
- Request data have to be validated.
- Account can have different currency with different exchange rate.  
- Fees depend on business case. 

## Run
```
$ mvn clean install
```

``` 
$ java -jar transfer-api-0.0.1-SNAPSHOT-shaded.jar server AppConfiguration.yml
```

## Model 
Schema is loaded on start form file schema.sql

## API:	
Detailed Api Description stored in file swagger.yml (run in https://editor.swagger.io).
```sh 
$ http://127.0.0.1:9090/transfer/forecast/?from=AAAA&to=990099A&amount=34&feeStrategy=STANDARD_FEE
``` 

```sh
$ http://127.0.0.1:9090/transfer/?from=AAAA&to=990099A&amount=34&feeStrategy=STANDARD_FEE
```

