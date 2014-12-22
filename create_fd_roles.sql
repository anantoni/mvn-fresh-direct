create user 'manager'@'localhost' identified by '';
create user 'customer'@'localhost' identified by '';

grant select on fresh_direct.* to 'customer'@'localhost' identified by '';
grant execute on fresh_direct.* to 'customer'@'localhost' identified by '';

grant select, update, delete, insert on fresh_direct.* to 'manager'@'localhost' identified by '';
grant execute on fresh_direct.* to 'manager'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.USERS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.ADDRESSES to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.TOWNS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.STREETS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.ORDERS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.ORDER_DETAILS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.ORDERED_WITH_CREDIT_CARD to 'customer'@'localhost' identifier by '';

grant update, delete, insert on fresh_direct.CREDIT_CARDS to 'customer'@'localhost' identified by '';

grant update, delete, insert on fresh_direct.PRODUCTS to 'customer'@'localhost' identified by '';
