DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = N'0001V810000000003NXX';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = N'STOR' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = N'STOR';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001V810000000003NXX';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001V810000000003NXY';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001V810000000003NXY';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NXZ';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NY0';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001V810000000003NY1';
