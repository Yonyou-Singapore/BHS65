DELETE FROM bd_billtype2 WHERE pk_billtypeid = N'0001V810000000003HJZ';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = N'0001V810000000003HJZ';
DELETE FROM pub_function WHERE pk_billtype = N'BUIL';
DELETE FROM pub_billaction WHERE pk_billtypeid = N'0001V810000000003HJZ';
DELETE FROM pub_billactiongroup WHERE pk_billtype = N'BUIL';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001V810000000003HJZ';
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK0';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK1';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK2';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK3';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK4';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003HK5';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HK6';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HK7';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HK8';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HK9';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HKA';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003HKB';
