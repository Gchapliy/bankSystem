<%--BANK ACCOUNT MANAGE INTERFACE--%>
<section id="bankAccountManager">
    <div class="row center-lg center-md center-sm center-xs">
        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 title">
            ${managing}
        </div>
    </div>
    <div class="row center-lg center-md center-sm center-xs accountBtns">
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 accountBtn">
            <a href="/${link}">
                ${back}
            </a>
        </div>
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 accountBtn">
            <a href="/newBankAccount">
                ${createAccount}
            </a>
        </div>
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 accountBtn">
            <a href="/history?uuid=${bankAccount.accountUuid}&page=1">
                ${history}
            </a>
        </div>
        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 accountBtn">
            <a href="/paymentTransfers?uuid=${bankAccount.accountUuid}&page=1">
                ${paymentTransfers}
            </a>
        </div>
    </div>
</section>