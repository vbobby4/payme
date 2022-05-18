package bobko.donatservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bobko.donatservice.entities.FrontTransaction;
import bobko.donatservice.entities.Response;
import bobko.donatservice.entities.Transaction;
import bobko.donatservice.services.TransactionService;

@RestController
@CrossOrigin(allowedHeaders = "*")
public class TransactionController {
    private final TransactionService service;
    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping(value = "/api/addTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addTransaction(@RequestBody Response response){
        Transaction transaction = response.getData().getStatementItem();
        service.save(transaction);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/api/getLastTransaction")
    public ResponseEntity<Object> getLastTransaction(){
        Transaction lastTransaction = service.getLastTransaction();
        FrontTransaction transaction = service.getFrontTransactionFromDefault(lastTransaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
/*curl -i
        -H "Accept: application/json"
        -H "Content-Type:application/json"
        -X POST --data
        '{"username": "johnny", "password": "password"}' "https://localhost:8080/.../request"
curl -i -H "Accept:application/json" -H "Content-Type:application/json" -X POST --data '{"type":"StatementItem","data":{"account":"WiD7unOk1NUiRGG6O8NG2A","statementItem":{"id":"ZuHWzqkKGVo=","time":1554466347,"description":"Покупкащастя","mcc":7997,"hold":false,"amount":-95000,"operationAmount":-95000,"currencyCode":980,"commissionRate":0,"cashbackAmount":19000,"balance":10050000,"comment":"Закаву","receiptId":"XXXX-XXXX-XXXX-XXXX","counterEdrpou":"3096889974","counterIban":"UA898999980000355639201001404"}}}' "http://localhost:1489/addTransaction"
response :
{type: "StatementItem", data:
data:
{account: "WiD7unOk1NUiRGG6O8NG2A", statementItem:
statementItem:
{"id": "ZuHWzqkKGVo=",
"time": 1554466347,
"description": "Покупка щастя",
"mcc": 7997,
"hold": false,
"amount": -95000,
"operationAmount": -95000,
"currencyCode": 980,
"commissionRate": 0,
"cashbackAmount": 19000,
"balance": 10050000,
"comment": "За каву",
"receiptId": "XXXX-XXXX-XXXX-XXXX",
"counterEdrpou": "3096889974",
"counterIban": "UA898999980000355639201001404"
}:
}:
}:
 Hierarchy :
 Response
 Response
 StatementItem
 */