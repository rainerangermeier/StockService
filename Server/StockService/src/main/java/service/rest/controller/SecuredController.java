package service.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.data.TransactionResponse;
import service.servicelayer.AppUserService;
import service.servicelayer.TransactionService;
import service.servicelayer.YQLService;

@RestController
@RequestMapping("/secured")
@Secured("ROLE_LOGGEDIN")
public class SecuredController {

	@Autowired
	private TransactionService securedService;

	@Autowired
	private AppUserService appUserSerive;

	@Autowired
	private YQLService yqlService;

	@RequestMapping(value = "/finance/transactions", method = RequestMethod.POST)
	@Transactional
	public TransactionResponse addTransaction(
			@RequestParam(value = "stocksymbol", required = true) String symbol,
			@RequestParam(value = "amount", required = true) int amount,
			@RequestParam(value = "type", required = true) boolean isSell) {

		return securedService.addTransaction(symbol, amount, isSell);
	}

	@Transactional
	@RequestMapping(value = "/finance/transactions", method = RequestMethod.GET)
	public TransactionResponse getUserTransactions() {
		return appUserSerive.getAuthenticatedUserTransactions();
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
	public TransactionResponse updateUser(
			@RequestParam(value = "pw", required = true) String password) {
		
		return appUserSerive.setUserPassword(password);
	}
}
