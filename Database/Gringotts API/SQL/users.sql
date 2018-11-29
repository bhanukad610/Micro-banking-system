use id7910250_centralserver;
CREATE USER 'admin' @'localhost' IDENTIFIED BY 'admin';
CREATE USER 'accountant' @'localhost' IDENTIFIED BY 'accountant';
CREATE USER 'customer' @'localhost' IDENTIFIED BY 'customer';

GRANT SELECT on id7910250_centralserver.accounts_with_customer_details to 'admin';
GRANT SELECT on id7910250_centralserver.account_details_for_agents to 'admin';
GRANT SELECT on id7910250_centralserver.account_details_for_customers to 'admin';
GRANT SELECT on id7910250_centralserver.agent_details_for_customers to 'admin';
GRANT SELECT on id7910250_centralserver.fixed_accounts_with_customer_details to 'admin';
GRANT SELECT on id7910250_centralserver.interests to 'admin';

GRANT SELECT on id7910250_centralserver.accounts_with_customer_details to 'accountant';
GRANT SELECT on id7910250_centralserver.fixed_accounts_with_customer_details to 'accountant';
GRANT SELECT on id7910250_centralserver.interests to 'accountant';

GRANT SELECT on id7910250_centralserver.account_details_for_customers to 'customer';
GRANT SELECT on id7910250_centralserver.agent_details_for_customers to 'customer';
