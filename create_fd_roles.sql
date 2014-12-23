USE fd_schema;

#CREATE USER 'manager'@'localhost' IDENTIFIER BY '1234';
#CREATE USER 'customer'@'localhost' IDENTIFIER BY '1234';

GRANT SELECT ON fd_schema.* to 'customer'@'localhost' IDENTIFIED BY '1234';
GRANT EXECUTE ON fd_schema.* to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT SELECT, UPDATE, DELETE, INSERT ON fd_schema.* to 'manager'@'localhost' IDENTIFIED BY '1234';
GRANT EXECUTE ON fd_schema.* to 'manager'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.USERS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.ADDRESSES to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.TOWNS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.STREETS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.ORDERS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.ORDER_DETAILS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.ORDERED_WITH_CREDIT_CARD to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.CREDIT_CARDS to 'customer'@'localhost' IDENTIFIED BY '1234';

GRANT UPDATE, DELETE, INSERT ON fd_schema.PRODUCTS to 'customer'@'localhost' IDENTIFIED BY '1234';
