DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = N'0001V810000000003NAS';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = N'MOVE' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = N'MOVE';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001V810000000003NAS';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001V810000000003NAT';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001V810000000003NAT';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NAU';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NAV';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NAW';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = N'0001V810000000003NAF';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = N'0001V810000000003NAF';
DELETE FROM pub_function WHERE pk_billtype = N'MOVE';
DELETE FROM pub_billaction WHERE pk_billtypeid = N'0001V810000000003NAF';
DELETE FROM pub_billactiongroup WHERE pk_billtype = N'MOVE';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001V810000000003NAF';
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAG';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAH';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAI';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAJ';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAK';
DELETE FROM pub_billaction WHERE pk_billaction = '0001V810000000003NAL';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAM';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAN';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAO';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAP';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAQ';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001V810000000003NAR';
DELETE FROM pub_systemplate_base where pk_systemplate = N'0001V810000000003NAE';
delete from pub_print_datasource where ctemplateid = N'0001V810000000003MLP';
delete from pub_print_cell where ctemplateid = N'0001V810000000003MLP';
delete from pub_print_line where ctemplateid = N'0001V810000000003MLP';
delete from pub_print_variable where ctemplateid = N'0001V810000000003MLP';
delete from pub_print_template where ctemplateid = N'0001V810000000003MLP';
DELETE FROM pub_systemplate_base where pk_systemplate = N'0001V810000000003MLO';
delete from pub_query_condition where pk_templet = '0001V810000000003MJJ';
delete from pub_query_templet where id = '0001V810000000003MJJ';
DELETE FROM pub_systemplate_base where pk_systemplate = N'0001V810000000003MJI';
delete from pub_billtemplet_b where pk_billtemplet = '0001V810000000003MFZ';
delete from pub_billtemplet where pk_billtemplet = '0001V810000000003MFZ';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = N'0001V810000000003MFZ';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = N'0001V810000000003MFY';
DELETE FROM sm_funcregister WHERE cfunid = N'0001V810000000003MFW';
DELETE FROM sm_paramregister WHERE pk_param = N'0001V810000000003MFX';
