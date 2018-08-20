INSERT INTO bd_billtype (ts, iseditableproperty , pk_billtypecode , ncbrcode , parentbilltype , canextendtransaction , istransaction , isbizflowbill , datafinderclz , isaccount , referclassname , pk_org , component , isroot , billtypename , emendenumclass , billcoderule , dr , nodecode , isenablebutton , pk_billtypeid , systemcode , classname , checkclassname , accountclass , forwardbilltype , islock , billtypename2 , billtypename3 , billtypename4 , transtype_class , billtypename5 , pk_group , billtypename6 , webnodecode , billstyle , def3 , isapprovebill , isenabletranstypebcr , def2 , wherestring , def1 ) VALUES ('2017-04-09 11:53:53', null , 'QUAL' , '~' , '~' , 'Y' , 'N' , null , null , null , null , 'GLOBLE00000000000000' , 'bhsquality' , null , 'quality' , null , '~' , null , 'H1H1H107' , null , '0001V810000000004NQY' , 'bhs' , null , null , null , null , null , null , null , null , null , null , '~' , null , '~' , null , null , 'Y' , null , null , null , null );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'N' , 'N' , '0001V810000000004NQZ' , null , 'SAVE' , null , null , '送审' , null , null , 10 , 'N' , '1' , null , null , 'QUAL' );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'N' , 'N' , '0001V810000000004NR0' , null , 'APPROVE' , null , null , '审核' , null , null , 11 , 'N' , '2' , null , null , 'QUAL' );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'Y' , 'Y' , '0001V810000000004NR1' , null , 'UNSAVEBILL' , null , null , '收回' , null , null , 13 , 'N' , '3' , null , null , 'QUAL' );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'N' , 'Y' , '0001V810000000004NR2' , null , 'UNAPPROVE' , null , null , '弃审' , null , null , 12 , 'N' , '3' , null , null , 'QUAL' );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'N' , 'N' , '0001V810000000004NR3' , null , 'DELETE' , null , null , '删除' , null , null , 30 , 'N' , '3' , null , null , 'QUAL' );
INSERT INTO pub_billaction (ts, actionstyleremark , pushflag , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote5 , actionnote , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , dr , pk_billtype ) VALUES ('2017-04-09 11:53:55', null , null , '0001V810000000004NQY' , 'N' , 'N' , '0001V810000000004NR4' , null , 'SAVEBASE' , null , null , '保存' , null , null , 31 , 'Y' , '1' , null , null , 'QUAL' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_SAVE' , 'N' , 'SAVE' , '~' , 0 , 'QUAL' , '0001V810000000004NR5' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_APPROVE' , 'N' , 'APPROVE' , '~' , 0 , 'QUAL' , '0001V810000000004NR6' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_UNSAVEBILL' , 'N' , 'UNSAVEBILL' , '~' , 0 , 'QUAL' , '0001V810000000004NR7' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_UNAPPROVE' , 'N' , 'UNAPPROVE' , '~' , 0 , 'QUAL' , '0001V810000000004NR8' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_DELETE' , 'N' , 'DELETE' , '~' , 0 , 'QUAL' , '0001V810000000004NR9' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-04-09 11:53:55', '0001V810000000004NQY' , '~' , 'N_QUAL_SAVEBASE' , 'N' , 'SAVEBASE' , '~' , 0 , 'QUAL' , '0001V810000000004NRA' );
