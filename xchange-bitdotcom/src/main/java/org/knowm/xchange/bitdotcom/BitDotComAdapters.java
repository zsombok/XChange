package org.knowm.xchange.bitdotcom;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.bitdotcom.dto.account.BitDotComAccounts;
import org.knowm.xchange.bitdotcom.dto.account.CurrencyLevelDetail;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.account.Wallet.Builder;

public class BitDotComAdapters {

  public static AccountInfo adaptAccountInfo(BitDotComAccounts bitDotComAccounts) {
    List<Balance> balances =
        bitDotComAccounts.getDetails().stream()
            .map(BitDotComAdapters::adaptBalance)
            .collect(Collectors.toList());
    List<Wallet> wallets = Collections.singletonList(Builder.from(balances).build());
    return new AccountInfo(
        String.valueOf(bitDotComAccounts.getUserId()),
        null,
        wallets,
        new Date(bitDotComAccounts.getCreatedAt()));
  }

  private static Balance adaptBalance(CurrencyLevelDetail currencyLevelDetail) {
    // TODO: business check
    return new Balance(
        new Currency(currencyLevelDetail.getCurrency()),
        currencyLevelDetail.getEquity(),
        currencyLevelDetail.getAvailableBalance());
  }
}
