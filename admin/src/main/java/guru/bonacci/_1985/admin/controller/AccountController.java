package guru.bonacci._1985.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guru.bonacci._1985.admin.domain.AccountDetails;
import guru.bonacci._1985.admin.dto.AccountDto;
import guru.bonacci._1985.admin.dto.Mappers;
import guru.bonacci._1985.admin.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  
  @PostMapping("/pools/{poolId}/users/{userId}")
  public Optional<AccountDetails> create( @PathVariable("poolId") Long poolId, 
					                                @PathVariable("userId") Long userId, 
					                                @Valid @RequestBody AccountDto dto) { 
    var account = Mappers.mapAccount(dto);
    return accountService.createAccount(poolId, userId, account); 
  }

  @GetMapping("/{accountId}")
  public Optional<AccountDetails> retrieve(@PathVariable("accountId") Long accountId) {
    return accountService.getAccount(accountId); 
  }

  @GetMapping("/{poolId}/accounts")
  public List<AccountDetails> retrieveAccounts(@PathVariable("poolId") Long poolId) {
    return accountService.getAccountsByPoolId(poolId); 
  }
  
  @GetMapping("/{poolId}")
  public List<AccountDetails> search( @PathVariable("poolId") Long poolId, 
                                      @RequestParam Optional<String> q) {
    var accountName = q.orElseGet(() -> "*");
    return accountService.searchAccounts(poolId, accountName); 
  }

  @DeleteMapping("/{accountId}")
  public void delete(@PathVariable(value = "accountId") Long accountId) {
    accountService.deactivate(accountId); 
  }
}
