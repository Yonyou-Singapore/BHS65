INSERT INTO bd_billtype (ts, iseditableproperty , pk_billtypecode , ncbrcode , parentbilltype , canextendtransaction , istransaction , isbizflowbill , datafinderclz , isaccount , referclassname , pk_org , isroot , component , billtypename , billcoderule , emendenumclass , dr , nodecode , isenablebutton , pk_billtypeid , systemcode , classname , checkclassname , accountclass , islock , forwardbilltype , billtypename2 , billtypename3 , billtypename4 , transtype_class , billtypename5 , pk_group , billtypename6 , webnodecode , billstyle , def3 , isapprovebill , def2 , isenabletranstypebcr , wherestring , def1 ) VALUES ('2017-03-05 15:45:00', null , 'PACK' , '~' , '~' , 'Y' , 'N' , null , null , null , null , 'GLOBLE00000000000000' , null , 'pack' , 'PACK' , '~' , null , null , 'H1H1H102' , null , '0001V810000000003ITX' , 'bhs' , null , null , null , null , null , null , null , null , null , null , '~' , null , '~' , null , null , 'Y' , null , null , null , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'N' , 'N' , '0001V810000000003ITY' , null , 'SAVE' , null , '送审' , null , null , null , 10 , 'N' , '1' , null , 'PACK' , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'N' , 'N' , '0001V810000000003ITZ' , null , 'APPROVE' , null , '审核' , null , null , null , 11 , 'N' , '2' , null , 'PACK' , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'Y' , 'Y' , '0001V810000000003IU0' , null , 'UNSAVEBILL' , null , '收回' , null , null , null , 13 , 'N' , '3' , null , 'PACK' , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'N' , 'Y' , '0001V810000000003IU1' , null , 'UNAPPROVE' , null , '弃审' , null , null , null , 12 , 'N' , '3' , null , 'PACK' , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'N' , 'N' , '0001V810000000003IU2' , null , 'DELETE' , null , '删除' , null , null , null , 30 , 'N' , '3' , null , 'PACK' , null );
INSERT INTO pub_billaction (ts, pushflag , actionstyleremark , pk_billtypeid , controlflag , finishflag , pk_billaction , actionnote6 , actiontype , actionnote4 , actionnote , actionnote5 , actionnote2 , actionnote3 , action_type , constrictflag , actionstyle , showhint , pk_billtype , dr ) VALUES ('2017-03-05 15:45:01', null , null , '0001V810000000003ITX' , 'N' , 'N' , '0001V810000000003IU3' , null , 'SAVEBASE' , null , '保存' , null , null , null , 31 , 'Y' , '1' , null , 'PACK' , null );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_SAVE' , 'N' , 'SAVE' , '~' , 0 , 'PACK' , '0001V810000000003IU4' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_APPROVE' , 'N' , 'APPROVE' , '~' , 0 , 'PACK' , '0001V810000000003IU5' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_UNSAVEBILL' , 'N' , 'UNSAVEBILL' , '~' , 0 , 'PACK' , '0001V810000000003IU6' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_UNAPPROVE' , 'N' , 'UNAPPROVE' , '~' , 0 , 'PACK' , '0001V810000000003IU7' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_DELETE' , 'N' , 'DELETE' , '~' , 0 , 'PACK' , '0001V810000000003IU8' );
INSERT INTO pub_busiclass (ts, pk_billtypeid , pk_businesstype , classname , isbefore , actiontype , pk_group , dr , pk_billtype , pk_busiclass ) VALUES ('2017-03-05 15:45:01', '0001V810000000003ITX' , '~' , 'N_PACK_SAVEBASE' , 'N' , 'SAVEBASE' , '~' , 0 , 'PACK' , '0001V810000000003IU9' );
