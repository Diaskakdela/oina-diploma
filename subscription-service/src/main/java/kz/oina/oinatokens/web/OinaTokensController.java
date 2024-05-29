package kz.oina.oinatokens.web;

import kz.oina.core.controller.model.ApiResponse;
import kz.oina.oinatokens.entity.UserTokens;
import kz.oina.oinatokens.exception.NotEnoughTokensException;
import kz.oina.oinatokens.exception.UserAccountAbsenceException;
import kz.oina.oinatokens.exception.WrongCreditTokenAmountException;
import kz.oina.oinatokens.model.TransactionDetails;
import kz.oina.oinatokens.service.TransactionService;
import kz.oina.oinatokens.service.UserTokensService;
import kz.oina.oinatokens.web.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("oina-tokens")
@RequiredArgsConstructor
public class OinaTokensController {
    private final UserTokensService userTokensService;
    private final TransactionService transactionService;

    @GetMapping("/{renterId}")
    public ResponseEntity<ApiResponse<AvailableTokensResponse>> findAvailableTokens(@PathVariable UUID renterId) {
        var response = userTokensService.findByUserId(renterId)
                .map(UserTokens::getTokenAmount)
                .map(BigDecimal::new)
                .map(AvailableTokensResponse::new)
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error(null, "Renter doesn't have any available tokens"));
        return ResponseEntity.ok(response);
    }

    @PostMapping("credit")
    public ResponseEntity<ApiResponse<CreditTokensResponse>> creditTokens(CreditTokensRequest creditTokensRequest) {
        try {
            var transaction = transactionService.createTransaction(
                    TransactionDetails.creditTransaction(creditTokensRequest.renterId(), creditTokensRequest.amount()));
            var response = new CreditTokensResponse(transaction.getId(), transaction.getTransactionType().name());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (UserAccountAbsenceException e) {
            return ResponseEntity.ok(ApiResponse.error("Not exists available tokens"));
        } catch (WrongCreditTokenAmountException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Wrong credit token amount"));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("debit")
    public ResponseEntity<ApiResponse<DebitTokensResponse>> debitTokens(DebitTokensRequest debitTokensRequest) {
        try {
            var transaction = transactionService.createTransaction(
                    TransactionDetails.creditTransaction(debitTokensRequest.renterId(), debitTokensRequest.amount()));
            var response = new DebitTokensResponse(transaction.getId(), transaction.getTransactionType().name());
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (UserAccountAbsenceException e) {
            return ResponseEntity.ok(ApiResponse.error("Not exists available tokens"));
        } catch (NotEnoughTokensException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Not enough available tokens"));
        } catch (WrongCreditTokenAmountException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Wrong credit token amount"));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage()));
        }
    }
}
