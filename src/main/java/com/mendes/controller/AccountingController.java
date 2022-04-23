package com.mendes.controller;

import com.mendes.model.Accounting;
import com.mendes.model.ResultModel;
import com.mendes.service.AccountingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mendes
 */

@Api(value = "accounting")
@RestController
@RequestMapping("/accounting")
public class AccountingController {

    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @ApiOperation(value = "save")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/save")
    public ResponseEntity<ResultModel> save(@RequestBody Accounting model) {
        ResultModel resultModel = accountingService.save(model);
        if (resultModel.isError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultModel);
        }
        return ResponseEntity.ok(resultModel);
    }

    @ApiOperation(value = "list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/list")
    public ResponseEntity<List<Accounting>> list() {
        return ResponseEntity.ok(accountingService.list());
    }
}
