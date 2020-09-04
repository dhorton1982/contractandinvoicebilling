# contractandinvoicebilling

To compile and run the application, follow these steps:

Open a command line window or terminal. Navigate to the root directory of the project, where the pom.xml resides. Compile the project: mvn clean compile. Package the application: mvn package. Look in the target directory. You should see a file with the following or a similar name: contractandinvoicebilling-0.0.1-SNAPSHOT.jar. Change into the target directory. Execute the JAR: java -jar contractandinvoicebilling-0.0.1-SNAPSHOT.jar. 

Example POST URL: http://localhost:8080/contracts

Example JSON Request: 
{
     "description": "First contract",
     "contractValue": 1000.00
}

Example GET URL: http://localhost:8080/contracts/1?remainingBillableValue=true

Example JSON Reponse: 

{
    "id": 1,
    "description": "First contract",
    "contractValue": 1000.00,
    "remainingValue": 600.00,
    "clientUserId": 0,
    "vendorUserId": 0,
    "invoices": [
        {
            "id": 1,
            "invoiceValue": 100.00,
            "contractId": 1,
            "created": "2020-09-04T04:35:52.759+0000",
            "voidValue": false
        },
        {
            "id": 2,
            "invoiceValue": 100.00,
            "contractId": 1,
            "created": "2020-09-04T04:35:54.076+0000",
            "voidValue": false
        },
        {
            "id": 3,
            "invoiceValue": 100.00,
            "contractId": 1,
            "created": "2020-09-04T04:36:00.769+0000",
            "voidValue": true
        },
        {
            "id": 4,
            "invoiceValue": 100.00,
            "contractId": 1,
            "created": "2020-09-04T04:36:05.981+0000",
            "voidValue": false
        },
        {
            "id": 5,
            "invoiceValue": 100.00,
            "contractId": 1,
            "created": "2020-09-04T04:36:44.012+0000",
            "voidValue": false
        }
    ]
}

Example POST Request: http://localhost:8080/invoices

Example JSON Request:

{
    "invoiceValue": 100.00,
    "contractId": 1,
    "created": null,
    "voidValue": false
}

Example GET URL: http://localhost:8080/invoices

Example JSON Response:

[
    {
        "id": 1,
        "invoiceValue": 100.00,
        "contractId": 1,
        "created": "2020-09-04T02:06:39.450+0000",
        "voidValue": false
    },
    {
        "id": 2,
        "invoiceValue": 100.00,
        "contractId": 1,
        "created": "2020-09-04T02:06:40.924+0000",
        "voidValue": false
    },
    {
        "id": 3,
        "invoiceValue": 100.00,
        "contractId": 1,
        "created": "2020-09-04T02:06:48.300+0000",
        "voidValue": true
    }
]
