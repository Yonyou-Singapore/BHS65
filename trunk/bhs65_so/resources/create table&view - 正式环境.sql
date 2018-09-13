--v_skill_category
drop view v_skill_category;
create view v_skill_category as
select a.pk_defdoc as id, a.code as code, a.name as name from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist 
where b.code= 'BHS09';

--v_certificates
drop view v_certificates;
create view v_certificates as
select a.pk_defdoc as id, a.code as code, a.name as name from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist 
where b.code= 'BHS10';

--v_users
drop view v_users;
create view v_users as
select pk_psndoc as user_id, b.code as user_code, b.name as user_name, r.code as role_code, glbdef27 as password
, r.name as role_name
,email as user_email, isnull(homephone, officephone) as user_phone_no
from bd_psndoc b inner join 
(select a.pk_defdoc as id, a.code as code, a.name as name from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist 
where b.code= 'BHS09') r on b.glbdef31 = r.id
where dr=0 and exists( select 1 from hi_psnjob j where b.pk_psndoc = j.pk_psndoc and j.dr=0 and j.poststat = 'Y');

--v_users update chenth 20180412
create view v_users as
select pk_psndoc as user_id, b.code as user_code, b.name as user_name, r.code as role_code, glbdef27 as password
, r.name as role_name
,email as user_email, isnull(homephone, officephone) as user_phone_no
from bd_psndoc b left join 
(select a.pk_defdoc as id, a.code as code, a.name as name from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist 
where b.code= 'BHS11') r on b.glbdef31 = r.id
where dr=0 and exists( select 1 from hi_psnjob j where b.pk_psndoc = j.pk_psndoc and j.dr=0 and j.poststat = 'Y')

--v_user_skills
drop view v_user_skills;
create view v_user_skills as
SELECT distinct pk_psndoc as user_id, glbdef1 as skill_category_id FROM  hi_psndoc_glbdef3 ;

--v_user_certificate
drop view v_user_certificate;
create view v_user_certificate as
SELECT pk_psndoc as user_id, glbdef1 as certificate_id, glbdef3 as expiredate FROM  hi_psndoc_glbdef4 ;


--v_customers
drop view v_customers;
create view v_customers as 
select a.pk_customer as id, a.code as code, a.name as name, a.def1 + ' ' + a.def2 as address, a.def2 as postcode
, tel1 as contact_phone_no, b.name as contact_person, b.phone as person_phone, b.cell as person_cellphone
 from bd_customer a left join (
select pk_customer, b.pk_linkman, code, name, phone, cell from bd_custlinkman b inner join bd_linkman c on b.pk_linkman = c.pk_linkman where b.isdefault = 'Y'
) b on a.pk_customer = b.pk_customer
;

--update 20171128
create view v_customers as 
select a.pk_customer as id, a.code as code, a.name as name, c.detailinfo + ' ' + c.postcode as address, c.postcode as postcode
, a.tel1 as contact_phone_no, l.name as contact_person, l.phone as person_phone, l.cell as person_cellphone
 from bd_customer a
 left join bd_custaddress b on a.pk_customer = b.pk_customer and b.isdefault = 'Y'
 left join bd_address c on b.pk_address=c.pk_address
left join (
select pk_customer, b.pk_linkman, code, name, phone, cell from bd_custlinkman b inner join bd_linkman c on b.pk_linkman = c.pk_linkman where b.isdefault = 'Y'
) l on a.pk_customer = l.pk_customer
where a.dr=0

--20180614 给GPS提供customer视图
GRANT SELECT ON v_customers TO bhsgps;
GRANT SELECT ON bd_customer TO bhsgps;
GRANT SELECT ON bd_custaddress TO bhsgps;
GRANT SELECT ON bd_address TO bhsgps;
GRANT SELECT ON bd_custlinkman TO bhsgps;
GRANT SELECT ON bd_linkman TO bhsgps;


--v_joborders
drop view v_joborders;
create view v_joborders as 
SELECT csaleorderid  as job_id, vbillcode as so_no, vdef17 as subject, vdef18 as requirement, ccustomerid as customer_id, startdate as start_date, enddate as end_date, c.colorhex
 FROM so_saleorder 
left join bd_psndoc p on p.pk_psndoc = cemployeeid
left join (
select a.pk_defdoc as id, a.code as colorhex from bd_defdoc a 
inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist where b.code= 'BHS12'
) c on p.glbdef33 = c.id
 ;

--update 20171128
create view v_joborders as 
SELECT mh.billid as job_id, mh.vbillno as so_no, mh.subject as subject, mh.def14 as requirement, so.ccustomerid as customer_id, mh.departuretime as start_date, mh.jobendtime as end_date, c.colorhex
 FROM bhs_somove_h mh
inner join so_saleorder so on mh.csaleorderid = so.csaleorderid
left join bd_psndoc p on p.pk_psndoc = so.cemployeeid
left join (
select a.pk_defdoc as id, a.code as colorhex from bd_defdoc a where pk_defdoclist='1001V81000000000DX3X'
) c on p.glbdef33 = c.id
where so.dr=0 and mh.dr=0
;
--update 20171128 new
create view v_joborders as 
SELECT mh.billid as job_id, mh.vbillno as so_no, mh.subject as subject, so.ccustomerid as customer_id, mh.departuretime as start_date, mh.jobendtime as end_date, c.colorhex
 ,mh.fromcorp as fromlocation, mh.fromaddress+ ' ' +mh.fromcode as fromaddress 
 ,mh.contactperson as fromcontact, mh.def3 as fromcontactphone 
 ,mh.tocorp as tolocation, mh.toaddress+ ' ' +mh.tocode as toaddress 
 ,mh.def4 as tocontact, mh.def5 as tocontactphone 
,concat('S/O NO.: ',mh.vbillno
, ' <br/>Make:', mh.machinemake, ' Model:', mh.machinemodel, ' Submodel:', mh.machinesubmodel
, ' <br/>Total # of Pkgs:', cast(mh.totalofpkgs as int)
, ' <br/>Longest (CM):', cast(mh.lengthcm as int), ' Crate #:', cast(mh.crate1 as int)
, ' <br/>Widest (CM):', cast(mh.widthcm as int), ' Crate #:', cast(mh.crate2 as int)
, ' <br/>Highest (CM):', cast(mh.heightcm as int), ' Crate #:', cast(mh.crate3 as int)
, ' <br/>Heaviest (KG):', cast(mh.largestweight as int), ' Crate #:', cast(mh.kcrate as int)
) as requirement
 FROM bhs_somove_h mh
inner join so_saleorder so on mh.csaleorderid = so.csaleorderid
left join bd_psndoc p on p.pk_psndoc = so.cemployeeid
left join (
select a.pk_defdoc as id, a.code as colorhex from bd_defdoc a where pk_defdoclist='1001V81000000000DX3X'
) c on p.glbdef33 = c.id
where so.dr=0 and mh.dr=0
 ;


--update 20180408 new
create view v_joborders as 
SELECT mh.billid as job_id, mh.vbillno as so_no, mh.subject as subject, so.ccustomerid as customer_id, mh.departuretime as start_date, mh.jobendtime as end_date, c.colorhex
 ,d.name as fromlocation, mh.fromaddress+ ' ' +mh.fromcode as fromaddress 
 ,mh.contactperson as fromcontact, mh.def3 as fromcontactphone 
 ,e.name as tolocation, mh.toaddress+ ' ' +mh.tocode as toaddress 
 ,mh.def4 as tocontact, mh.def5 as tocontactphone 
,concat('S/O NO.: ',mh.vbillno
, ' <br/>Make:', mh.machinemake, ' Model:', mh.machinemodel, ' Submodel:', mh.machinesubmodel
, ' <br/>Total # of Pkgs:', cast(mh.totalofpkgs as int)
, ' <br/>Longest (CM):', cast(mh.lengthcm as int), ' Crate #:', cast(mh.crate1 as int)
, ' <br/>Widest (CM):', cast(mh.widthcm as int), ' Crate #:', cast(mh.crate2 as int)
, ' <br/>Highest (CM):', cast(mh.heightcm as int), ' Crate #:', cast(mh.crate3 as int)
, ' <br/>Heaviest (KG):', cast(mh.largestweight as int), ' Crate #:', cast(mh.kcrate as int)
) as requirement
 FROM bhs_somove_h mh
inner join so_saleorder so on mh.csaleorderid = so.csaleorderid
left join bd_psndoc p on p.pk_psndoc = so.cemployeeid
left join (
select a.pk_defdoc as id, a.code as colorhex from bd_defdoc a where pk_defdoclist='1001V81000000000DX3X'
) c on p.glbdef33 = c.id
left join bd_addressdoc d on mh.fromcorp = d.pk_addressdoc
left join bd_addressdoc e on mh.tocorp = e.pk_addressdoc
where so.dr=0 and mh.dr=0
 ;


--v_skill_category_cnt
drop view v_skill_category_cnt;
create view v_skill_category_cnt as
select billid_b as id, csaleorderid as job_id, skill as skill_category_id, case certificate when '~' then null else certificate end  as certificate_id, numberofpeople as count,
v.name as vehicleno
 from so_saleorder_bblack b
left join (
select a.pk_defdoc as id, a.code as code, a.name as name
from bd_defdoc a inner join  bd_defdoclist b on a.pk_defdoclist = b.pk_defdoclist  
where b.code= 'BHS50' 
) v on b.def1 = v.id
 where isnull(b.dr,0) = 0

--update 20171128
create view v_skill_category_cnt as
select billid_b as id, billid as job_id, skill as skill_category_id, case certificate when '~' then null else certificate end  as certificate_id, numberofpeople as count,
v.name as vehicleno
 from bhs_somove_box b
left join (
select a.pk_defdoc as id, a.code as code, a.name as name
from bd_defdoc a where a.pk_defdoclist = '1001V81000000000BKW4'
) v on b.def1 = v.id
 where isnull(b.dr,0) = 0
;


--oz_skillcategory_onsiteform
drop table oz_skillcategory_onsiteform;
create table oz_skillcategory_onsiteform(
skill_category_id varchar(20),
onsite_checklist_form varchar(100),
);

delete oz_skillcategory_onsiteform;
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('01', 'safetychecklist-teamleaderrole-ppe-ladder-electricalequipment-aircaster');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('02', 'safetychecklist-teamleaderrole-ppe-ladder-electricalequipment-aircaster');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('03', '');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('04', '');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('05', 'safetychecklist-forkliftoperatorrole-daily-forklift');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('06', 'safetychecklist-forkliftoperatorrole-daily-forklift');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('07', 'safetychecklist-driverrole-daily-lorry');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('08', 'safetychecklist-driverrole-daily-lorry');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('09', 'safetychecklist-scissorliftoperatorrole-scissor-boom-lift');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('10', 'safetychecklist-wahsupervisorrole-monthly-safety-harness-inspection');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('11', '');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('12', 'safetychecklist-liftingsupervisorrole-liftingsupervisor-crane');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('13', 'safetychecklist-signalmanriggerrole-lifting-gear');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('14', 'safetychecklist-signalmanriggerrole-lifting-gear');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('15', 'safetychecklist-firstaiderrole-first-aidbox');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('16', 'safetychecklist-coordinatorrole-daily-inspection');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('17', 'safetychecklist-coordinatorrole-daily-inspection');
insert into oz_skillcategory_onsiteform(skill_category_id,onsite_checklist_form) values('18', 'safetychecklist-coordinatorrole-daily-inspection');

--oz_user_devices
drop table oz_user_devices;
create table oz_user_devices(
--id int PRIMARY KEY NOT NULL,
user_id varchar(20),
device_model varchar(45),
device_uuid varchar(100),
gcm_token varchar(200),
created_date char(19)
);

--oz_survey_type
drop table oz_survey_type;
create table oz_survey_type(
id varchar(2) PRIMARY KEY NOT NULL,
name varchar(45),
report_form varchar(100),
);

--oz_survey_type
delete oz_survey_type;
insert into oz_survey_type(id,name,report_form) values('LP','Lifting Plan','report-lifting-plan');
insert into oz_survey_type(id,name,report_form) values('P1','Permit: Hot Work','permit-hot-work-gascutting-welding');
insert into oz_survey_type(id,name,report_form) values('P2','Permit: Work At Height','permit-work-at-height_en');
insert into oz_survey_type(id,name,report_form) values('P3','Permit: Lifting Operation','permit-lifting-operation');
insert into oz_survey_type(id,name,report_form) values('SS','Site Survey','report-site-survey');

--oz_joborder_survey_status
drop table oz_joborder_survey_status;
create table oz_joborder_survey_status(
id varchar(2) PRIMARY KEY NOT NULL,
name varchar(45),
);
delete oz_joborder_survey_status;
insert into oz_joborder_survey_status(id,name) values('AS', 'Assigned');
insert into oz_joborder_survey_status(id,name) values('CP', 'Completed');
insert into oz_joborder_survey_status(id,name) values('D', 'Deleted');
insert into oz_joborder_survey_status(id,name) values('W', 'Working');
 
--oz_joborder_user_status
drop table oz_joborder_user_status;
create table oz_joborder_user_status(
id varchar(2) PRIMARY KEY NOT NULL,
name varchar(45),
);

delete oz_joborder_user_status;
insert into oz_joborder_user_status(id,name) values('AK', 'Acknowledged');
insert into oz_joborder_user_status(id,name) values('AS', 'Assigned');
insert into oz_joborder_user_status(id,name) values('CL', 'Confirmed Late');
insert into oz_joborder_user_status(id,name) values('CP', 'Completed');
insert into oz_joborder_user_status(id,name) values('NC', 'Not Coming');
insert into oz_joborder_user_status(id,name) values('TS', 'Temporary Save');
 
--oz_status
drop table oz_status;
create table oz_status(
id varchar(2) PRIMARY KEY NOT NULL,
name varchar(45),
);

--oz_joborder_status
drop table oz_joborder_status;
create table oz_joborder_status(
job_id varchar(20),
status_id varchar(2),
);

--oz_joborder_user
drop table oz_joborder_user;
create table oz_joborder_user(
id int identity(1,1) PRIMARY KEY NOT NULL,
job_id varchar(20),
assigned_user_id varchar(20),
skill_category_id varchar(20),
job_user_status_id varchar(20),
is_locked varchar(1),
confirmed_late_mins varchar(45),
not_comming_informed_date char(19),
checkedin_time char(19),
checkedout_time char(19),
onsite_form_file varchar(100),
created_date char(19),
updated_date char(19),
created_user_id varchar(20),
updated_user_id varchar(20),
checkin_confirmed varchar(5),
audit_rate varchar(20)
);

--oz_joborder_survey
drop table oz_joborder_survey;
create table oz_joborder_survey(
id int identity(1,1) PRIMARY KEY NOT NULL,
survey_type_id varchar(20),
appointment_date char(19),
customer_name varchar(200),
customer_address varchar(200),
contact_name varchar(100),
contact_phone_no varchar(50),
surveyor_id varchar(20),
status_id varchar(2),
customer_id varchar(20),
file_name varchar(100),
created_date char(19),
updated_date char(19),
created_user_id varchar(20),
updated_user_id varchar(20),
po_costcenter_no varchar(45),
machine_id varchar(45),
micap_no varchar(45),
location_from varchar(500),
location_to varchar(500),
sublocation_from varchar(500),
sublocation_to varchar(500),
machine_make varchar(45),
machine_model varchar(45),
machine_submodel varchar(45),
work_natures varchar(500),
estimated_job_date varchar(19),
estimated_hours varchar(45),
lid_no varchar(100)
);


drop view v_user_leaves;
create view v_user_leaves as 
SELECT pk_psndoc as user_id, leavebegintime, leaveendtime FROM tbm_leavereg 
union 
select assigned_user_id as user_id, substring(not_comming_informed_date,0,11) + ' 00:00:00' as leavebegintime, substring(not_comming_informed_date,0,11) + ' 23:59:59' as leaveendtime 
from oz_joborder_user where job_user_status_id = 'NC'

--update 20171128 包含Survey的，Survey的人不参与排班
create view v_user_leaves as 
SELECT pk_psndoc as user_id, leavebegintime, leaveendtime, b.timeitemname as type FROM tbm_leavereg a 
left join tbm_timeitem b on a.pk_leavetype = b.pk_timeitem
union 
select assigned_user_id as user_id, substring(not_comming_informed_date,0,11) + ' 00:00:00' as leavebegintime, substring(not_comming_informed_date,0,11) + ' 23:59:59' as leaveendtime, 'Not Coming' as type
from oz_joborder_user where job_user_status_id = 'NC'
union 
SELECT surveyor_id as user_id, substring(appointment_date,0,11) + ' 00:00:00' as leavebegintime, substring(appointment_date,0,11) + ' 23:59:59' as leaveendtime, 'Survey' as type
 FROM oz_joborder_survey 
;

INSERT INTO pub_pluginitem (dr,industrytype,iorder,localtype,pk_pluginitem,ts,vcomponentname,vdescription,veventtype,vextendmodule,vextendpointname,vextendtype,vmodulename,vruleclass,vtargetclass)
 VALUES (0,'~',102,'~','1001BHS20170510PSAVE','2017-05-10 23:30:00','m30','SO check enough skill users','before','scmpub','nc.vo.so.m30.plugin.BPPlugInPoint.Insert','addBefore','so','nc.bs.bhs.rule.BhsCheckReqBeforeRule',null)
;
INSERT INTO pub_pluginitem (dr,industrytype,iorder,localtype,pk_pluginitem,ts,vcomponentname,vdescription,veventtype,vextendmodule,vextendpointname,vextendtype,vmodulename,vruleclass,vtargetclass)
 VALUES (0,'~',102,'~','1001BHS20170510USAVE','2017-05-10 23:30:00','m30','SO check enough skill users','before','scmpub','nc.vo.so.m30.plugin.BPPlugInPoint.Update','addBefore','so','nc.bs.bhs.rule.BhsCheckReqBeforeRule',null)
;

drop view v_user_attendance;
create view v_user_attendance
as select u.job_id, u.assigned_user_id, u.checkedin_time as attendance_time
from so_saleorder s inner join oz_joborder_user u on s.csaleorderid = u.job_id



---提供给考勤系统的视图
drop view v_bhs_staff;
create view v_bhs_staff as
 select a.code, a.name,p.name as deptname,c.begindate, d.enddate, case poststat when 'Y' then 0 else 1 end as poststat
 from bd_psndoc  a
 left join hi_psnjob b on (a.pk_psndoc=b.pk_psndoc and b.poststat = 'Y')
 left join org_dept p on b.pk_dept = p.pk_dept
 left join hi_psnorg c on a.pk_psndoc = c.pk_psndoc
 left join hi_psndoc_psnchg d on (a.pk_psndoc = d.pk_psndoc and d.lastflag = 'Y')

--create view v_bhs_staff as
-- select a.code, a.name,c.begindate, d.enddate, case poststat when 'Y' then 0 else 1 end as poststat
-- from bd_psndoc  a
-- left join ( select distinct pk_psndoc, poststat from hi_psnjob where dr=0 ) b on a.pk_psndoc = b.pk_psndoc
-- left join hi_psnorg c on a.pk_psndoc = c.pk_psndoc
-- left join hi_psndoc_psnchg d on (a.pk_psndoc = d.pk_psndoc and d.lastflag = 'Y')
--update 20180830
create view v_bhs_staff as
 select a.code, a.name,p.name as deptname --,b.pk_hrgroup, c.pk_group, b.begindate
,c.begindate, c.enddate, case c.endflag when 'N' then 0 else 1 end as poststat
 from bd_psndoc  a
 left join (select distinct pk_psndoc,pk_dept from hi_psnjob where lastflag = 'Y') b on a.pk_psndoc=b.pk_psndoc
 left join hi_psnorg c on (a.pk_psndoc = c.pk_psndoc and c.lastflag = 'Y')
 left join org_dept p on b.pk_dept = p.pk_dept

drop view v_bhs_user_leaves;
 create view v_bhs_user_leaves as 
select p.code, leavebegintime, leaveendtime FROM 
bd_psndoc p inner join tbm_leavereg l on p.pk_psndoc = l.pk_psndoc
where isnull(l.dr,0) = 0;

--
 create view v_bhs_user_leaves as 
select p.code,p.name as psnname, d.name as deptname, leavebegintime, leaveendtime FROM 
bd_psndoc p inner join tbm_leavereg l on p.pk_psndoc = l.pk_psndoc
inner join hi_psnjob j on (p.pk_psndoc=j.pk_psndoc and poststat = 'Y')
inner join org_dept d on j.pk_dept = d.pk_dept
where isnull(l.dr,0) = 0

 --biosstar数据库
create view v_bhs_user_leaves as
select * from BHSK.dbo.v_bhs_user_leaves
create view v_bhs_staff as
select * from BHSK.dbo.v_bhs_staff

GRANT SELECT ON [dbo].[v_bhs_user_leaves] TO [biosstar]
GRANT SELECT ON [dbo].[tbm_leavereg] TO [biosstar]
GRANT SELECT ON [dbo].[v_bhs_user_leaves] TO [biosstar]
GRANT SELECT ON [dbo].[hi_psnjob] TO [biosstar]
GRANT SELECT ON [dbo].[org_dept] TO [biosstar]


create view v_bhs_sameorder as
SELECT ccustomerid, startdate,enddate, count(*) as cnt FROM  so_saleorder 
where dr=0
group by ccustomerid, startdate,enddate


$Error->iif(vdef19=="Y" && vdef20==null,"[Mover Skill] cannot be empty.","");
exist->getcolvalue2(so_saleorder, vbillcode, ccustomerid,ccustomerid,startdate,startdate);
$Confirm->iif(length(exist)>0 && vbillcode<>exist,"There is a same customer and same day JobOrder. The exist JobOrder No. is:","bb")


update sm_user  set email = null from sm_user s inner join bd_psndoc p on s.pk_psndoc = p.pk_psndoc where p.email is not null

SELECT s.user_code,s.user_name,s.email,p.email FROM sm_user s inner join bd_psndoc p on s.pk_psndoc = p.pk_psndoc 
where p.email is not null



update oz_skillcategory_onsiteform set skill_category_id = sc.id from oz_skillcategory_onsiteform so inner join v_skill_category sc on so.skill_category_id = sc.code


Alter table bhs_somove_h alter column def14 varchar(800);


--20180202 给GPS提供的quotation视图
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate  
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
where a.fstatusflag=1

--201803082 给GPS提供的quotation视图 update
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, qb.vbdef2 as Description, so.vbillcode as SOno
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join so_saleorder_b sob on qb.pk_salequotation_b = sob.csrcbid 
left join so_saleorder so on sob.csaleorderid = so.csaleorderid
where a.fstatusflag=1

--20180404 给GPS提供的quotation视图 update
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join so_saleorder_b sob on qb.pk_salequotation_b = sob.csrcbid 
left join so_saleorder so on sob.csaleorderid = so.csaleorderid
where a.fstatusflag=1
order by b.code,a.vbillcode,m.code

--20180502 给GPS提供的quotation视图 update
drop view v_bhs_quotation
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join so_saleorder_b sob on qb.pk_salequotation_b = sob.csrcbid 
left join so_saleorder so on sob.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
where a.fstatusflag=1
order by b.code,a.vbillcode,m.code

----20180511 给GPS提供的quotation视图 update 关闭的quotation也需要显示出来
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join 
(select so.*,sob.csrcbid from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
where so.fstatusflag = 1) so on qb.pk_salequotation_b = so.csrcbid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
where a.fstatusflag in(1,4)

----20180530 给GPS提供的quotation视图 包含SO\Truck信息
create view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
,jo.*
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join (
select so.*,sob.csrcbid from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0
) so on qb.pk_salequotation_b = so.csrcbid
left join(
select th.csaleorderid, vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as NAR_AR_TC, d2.name as Open_Box, d3.name as Norn_LB_WLB, tb.def6 as Size, tb.def7 as isRearDoor
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from bhs_sotruck_h th 
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where th.dr=0 
) jo on jo.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
where a.fstatusflag in(1,4)

----20180530 支持查没有quotation的job
create  view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, sm_user.user_name as CreateBy
,jo.*
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join (
select so.*,sob.csrcbid from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0
) so on qb.pk_salequotation_b = so.csrcbid
left join(
select th.csaleorderid, vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as NAR_AR_TC, d2.name as Open_Box, d3.name as Norn_LB_WLB, tb.def6 as Size, tb.def7 as isRearDoor
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from bhs_sotruck_h th 
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where th.dr=0 
) jo on jo.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
left join sm_user on isnull(so.creator, a.creator) = sm_user.cuserid
where a.fstatusflag in(1,4)
union all
select b.code as customercode,b.name as customername, so.quotationno as quotationno, so.dbilldate as quotationdate, so.subject as subject, m.code as PartCode, m.name as PartName, sob.vbdef2 as Description
, sob.nastnum as QTY, sob.nqtorigprice as UnitPrice_ExclTax, sob.nqtorigtaxprice as UnitPrice_InclTax,sob.norigmny as Amount_ExclTax,sob.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, sm_user.user_name as CreateBy
,jo.*
from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
inner join bd_customer b on so.ccustomerid = b.pk_customer
inner join bd_material m on sob.cmaterialid = m.pk_material
left join(
select th.csaleorderid, vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as NAR_AR_TC, d2.name as Open_Box, d3.name as Norn_LB_WLB, tb.def6 as Size, tb.def7 as isRearDoor
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from bhs_sotruck_h th 
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where th.dr=0 
) jo on jo.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
left join sm_user on so.creator = sm_user.cuserid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0 and sob.csrcbid = '~'


--20180830 增加以下字段ToolID, MicapNO, Model, SubModel, DeliveryNo, SurveyNo, AttnTo
create  view v_bhs_quotation as 
select b.code as customercode,b.name as customername, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject, m.code as PartCode, m.name as PartName, qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, sm_user.user_name as CreateBy
, so.toolidlid as ToolID,so.micapno as MicapNO, so.machinemodel as Model, so.machinesubmodel as SubModel
, so.dono as DeliveryNo, so.surveyno as SurveyNo, so.attnto as AttnTo
,jo.*
from so_salequotation a inner join bd_customer b on a.pk_customer = b.pk_customer
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
inner join bd_material m on qb.pk_material_v = m.pk_material
left join (
select so.*,sob.csrcbid from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0
) so on qb.pk_salequotation_b = so.csrcbid
left join(
select th.csaleorderid, vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as NAR_AR_TC, d2.name as Open_Box, d3.name as Norn_LB_WLB, tb.def6 as Size, tb.def7 as isRearDoor
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from bhs_sotruck_h th 
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where th.dr=0 
) jo on jo.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
left join sm_user on isnull(so.creator, a.creator) = sm_user.cuserid
where a.fstatusflag in(1,4)
union all
select b.code as customercode,b.name as customername, so.quotationno as quotationno, so.dbilldate as quotationdate, so.subject as subject, m.code as PartCode, m.name as PartName, sob.vbdef2 as Description
, sob.nastnum as QTY, sob.nqtorigprice as UnitPrice_ExclTax, sob.nqtorigtaxprice as UnitPrice_InclTax,sob.norigmny as Amount_ExclTax,sob.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, fc.name as FromLocation, so.FromAddress, so.fromcode as FromPostCode
, tc.name as ToLocation,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, sm_user.user_name as CreateBy
, so.toolidlid as ToolID,so.micapno as MicapNO, so.machinemodel as Model, so.machinesubmodel as SubModel
, so.dono as DeliveryNo, so.surveyno as SurveyNo, so.attnto as AttnTo
,jo.*
from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
inner join bd_customer b on so.ccustomerid = b.pk_customer
inner join bd_material m on sob.cmaterialid = m.pk_material
left join(
select th.csaleorderid, vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as NAR_AR_TC, d2.name as Open_Box, d3.name as Norn_LB_WLB, tb.def6 as Size, tb.def7 as isRearDoor
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from bhs_sotruck_h th 
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where th.dr=0 
) jo on jo.csaleorderid = so.csaleorderid
left join bd_addressdoc fc on so.fromcorp = fc.pk_addressdoc
left join bd_addressdoc tc on so.tocorp = tc.pk_addressdoc 
left join sm_user on so.creator = sm_user.cuserid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0 and sob.csrcbid = '~'


GRANT SELECT ON v_bhs_quotation TO bhsgps;
GRANT SELECT ON bd_addressdoc TO bhsgps;

GRANT SELECT ON so_salequotation_b TO bhsgps;
GRANT SELECT ON bd_material TO bhsgps;
GRANT SELECT ON so_saleorder_b TO bhsgps;
GRANT SELECT ON so_saleorder TO bhsgps;

GRANT SELECT ON v_bhs_quotation TO bhsgps;
GRANT SELECT ON so_salequotation TO bhsgps;
GRANT SELECT ON bd_customer TO bhsgps;




--20180316 付款单和收款单表头汇总无税金额
create view v_bhs_gatherbillnotax as 
SELECT pk_gatherbill, sum(notax_cr) as notax_cr, sum(local_notax_cr) as local_notax_cr, sum(groupnotax_cre) as groupnotax_cre , sum(globalnotax_cre) as globalnotax_cre FROM ar_gatheritem  where dr=0 group by pk_gatherbill

create view v_bhs_paybillnotax as 
SELECT pk_paybill, sum(notax_de) as notax_de, sum(local_notax_de) as local_notax_de, sum(groupnotax_de) as groupnotax_de , sum(globalnotax_de) as globalnotax_de FROM ap_payitem  where dr=0 group by pk_paybill


--20180425 创建触发器 保存采购订单时会更新表体的源头单据id等信息，支持联查。
create trigger tgr_po_order_b_updatesrc
on po_order_b 
for insert,update
as 
update po_order_b set csourceid = so.csaleorderid,csourcetypecode='30',
vsourcecode=so.vbillcode,vsourcerowno='10',vsourcetrantype=so.ctrantypeid,cfirstid = so.csaleorderid,cfirsttypecode='30',vfirstcode=so.vbillcode,vfirstrowno='10',vfirsttrantype=so.ctrantypeid
from po_order_b inner join po_order on po_order_b.pk_order = po_order.pk_order 
inner join so_saleorder so on po_order.vdef20=so.vbillcode
where po_order_b.dr=0 and so.dr = 0 and po_order.vdef20 !='~' and po_order.vdef20 is not null 


--检查有哪些没有完全核销的收款单
select distinct billno from (
select billno, pk_item from arap_tally group by billno, pk_item having count(*)<2
) t where billno  in (
select billno from (
select billno, pk_item from arap_tally group by billno, pk_item having count(*)=2
) a
)


--20180515 创建触发器-销售订单参照报价单后，如果该报价单是长期有效，则自动打开
create trigger trigger_bhs_quotation_open
on so_salequotation
after update
as 
    declare @billid varchar(20);
    --更新前的数据
    select @billid = pk_salequotation from inserted;
update so_salequotation set fstatusflag = 1 where vdef20 = 'Y' and fstatusflag=4 and pk_salequotation=@billid  ;



----20180514 给GPS提供的Truck JobOrder视图
create view v_bhs_truck_gps as
SELECT so.quotationno, so.vbillcode as SOno, th.vbillno as JobOrderNo, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
, def.name as TruckAirType, th.taapickuptime as PickupDateTime, th.taaondocktime as DeliveryDateTime
, th.taawbno as AWB, def1.name as VehicleType1, def2.name as VehicleType2, def3.name as VehicleType3
FROM bhs_sotruck_h th
inner join (
select distinct so.vbillcode, qh.vbillcode as quotationno,so.csaleorderid 
from so_saleorder so inner join so_saleorder_b sob on so.csaleorderid = sob.csaleorderid
inner join so_salequotation_b qb on qb.pk_salequotation_b = sob.csrcbid
inner join so_salequotation qh on qh.pk_salequotation = qb.pk_salequotation
) so on th.csaleorderid = so.csaleorderid
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc def on th.def1=def.pk_defdoc
left join bd_defdoc def1 on tb.def1=def1.pk_defdoc
left join bd_defdoc def2 on tb.def2=def2.pk_defdoc
left join bd_defdoc def3 on tb.def3=def3.pk_defdoc
where th.dr=0 

--20180521 给GPS提供的Truck JobOrder视图
drop view v_bhs_truck_gps;
create view v_bhs_truck_gps as
select so.quotationno, csaleordercode as SOno,vbillno as JobOrderNo, def.name as TruckAirType, substring(th.dbilldate,1,10) as JobCreatedDate, substring(th.creationtime,12,8) as JobCreateTime
,taapickuptime as PickupDateTime,taaondocktime as DeliveryDateTime, th.taawbno as AWB, isnull(tsfeexpreferreddatetime, tspreferreddatetime) as CustomerPreferredDateTime 
,defvelsel.name as Vessel, defvoyage.name as Voyage,isnull(tsfeexportagent ,tsimportagent) as Agent
,isnull(tsfeexportpermitno ,tsimportpermitno) as Permit, isnull(tsfeexlinktocontractor, tslinktocontractor) as LinkToContractor,isnull(tsfeexporteta ,tsimporteta) as ETA
,tsfeemptycntrcollection as EmptyContainerCollection,tsfeportnetref as PortnetRef,tsfepickupref as PickupRef,tsfebookingref as BookingRef,tsfeportofdischarge as PortofDischarge
,tsportofloading as PortOfLoading, tsreturntoyard as ReturnToYard
,isnull(tsfefrom, tsfrom) as PortFrom, isnull(tsfeto, tsto) as PortTo
,isnull(th.def1, th.def3) as LooseCargoDeliverFrom, isnull(th.def2, th.def4) as LooseCargoDeliverTo
,th.taspecialinstruction as SpecialInstruction, th.tacommoninstruction CommonInstruction
,d1.name as VechicleType1, d2.name as VechicleType2, d3.name as VechicleType3
, case when tsunstuffing='Y' then 'Y' else tsfeunstuffing end as IsUnStuffing
, case when tsairride='Y' then 'Y' else tsfeairride end as IsAirRide
, case when tsloosecargodeliver='Y' then 'Y' else tsfeloosecargocollect end as IsLooseCargoDeliver
,isnull(tsfemoveinstruction ,tsmoveinstruction) as MoveInstruction, isnull(tsfejobinstruction, tsjobinstruction) as JobInstruction,isnull(tsfecscomment ,tscscomment) as CSComment
from so_saleorder so inner join 
bhs_sotruck_h th on th.csaleorderid = so.csaleorderid
left join bd_defdoc def on (th.def1=def.pk_defdoc and def.pk_defdoclist='1001V81000000002IN1V')
left join bhs_sotruckfone_b tb on (th.billid = tb.billid and tb.dr=0)
left join bd_defdoc d1 on (tb.def1 = d1.pk_defdoc and d1.pk_defdoclist='1001V81000000000HS5U')
left join bd_defdoc d2 on (tb.def2 = d2.pk_defdoc and d2.pk_defdoclist='1001V81000000000HS5V')
left join bd_defdoc d3 on (tb.def3 = d3.pk_defdoc and d3.pk_defdoclist='1001V81000000000HS5W')
left join bd_defdoc defvelsel on (isnull(tsfeexportvessel,tsimportvessel) = defvelsel.pk_defdoc and defvelsel.pk_defdoclist='1001V810000000002NPQ')
left join bd_defdoc defvoyage on (isnull(tsfeexportvoyage,tsimportvoyage) = defvoyage.pk_defdoc and defvoyage.pk_defdoclist='1001V810000000002NPP')
where so.dr=0 and th.dr=0 

GRANT SELECT ON v_bhs_truck_gps TO bhsgps;
GRANT SELECT ON bhs_sotruck_h TO bhsgps;
GRANT SELECT ON bhs_sotruckfone_b TO bhsgps;
GRANT SELECT ON bd_defdoc TO bhsgps;




select a.pk_customer, qb.pk_material, a.vbillcode as quotationno, a.dquotedate as quotationdate, a.vdef2 as subject,qb.vbdef2 as Description
, qb.nassistnum as QTY, qb.nqtorigprice as UnitPrice_ExclTax, qb.nqtorigtaxprice as UnitPrice_InclTax,qb.norigmny as Amount_ExclTax,qb.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB,so.fromcorp, so.FromAddress, so.fromcode as FromPostCode,so.tocorp,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, isnull(so.creator, a.creator) as creator
from so_salequotation a 
inner join so_salequotation_b qb on a.pk_salequotation = qb.pk_salequotation
left join (
select so.*,sob.csrcbid from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid
where so.fstatusflag = 1 and so.dr=0 and sob.dr=0
) so on qb.pk_salequotation_b = so.csrcbid
union all 
select so.ccustomerid as pk_customer,sob.cmaterialid as pk_material, so.quotationno as quotationno, so.dbilldate as quotationdate, so.subject as subject, sob.vbdef2 as Description
, sob.nastnum as QTY, sob.nqtorigprice as UnitPrice_ExclTax, sob.nqtorigtaxprice as UnitPrice_InclTax,sob.norigmny as Amount_ExclTax,sob.norigtaxmny as Amount_InclTax, so.vbillcode as SOno
, so.awb as AWB, so.fromcorp, so.FromAddress, so.fromcode as FromPostCode
, so.tocorp,so.ToAddress, so.tocode as ToPostCode
, so.pkgs as TotalPackges, so.LargestLength, so.LargestWidth, so.LargestHeight,so.LargestWeight,so.m3 as Volume,so.kg as GrossWeight,so.ChargeableWeight,so.NetWeight
, round(so.lcrate,0) as LargestLengthCrate, round(so.wcrate,0) as LargestWidthCrate, round(so.hcrate,0) as LargestHeightCrate, round(so.kcrate,0) as LargestWeightCrate
, so.vdef14 as isUncrated, so.vdef15 as isTightSpace, so.vdef16 as isTopple, so.vdef18 as isJackingLifting, so.vdef19 as isHoisting, so.vdef20 as Others
, so.creator as creator
from so_saleorder so
inner join so_saleorder_b sob on sob.csaleorderid = so.csaleorderid


--销售出库单录入负数，自动变为退货单，要想改回正数，需执行以下脚本
so_saleorder h
 inner join so_saleorder_b b on h.csaleorderid = b.csaleorderid where h.vbillcode = 'SO-180608-03875' 

update so_saleorder_b set fretexchange = 0 where csaleorderbid = '1001V81000000005QP35'



--20180711 Survey 信息存到表中，NC直接读取
create table oz_survery_machines (
id int PRIMARY KEY NOT NULL,
survey_no int not null, 
description varchar(500),
length varchar(500), 
width varchar(500), 
height varchar(500), 
weight varchar(500)
)

create table oz_survey_skills (
id int PRIMARY KEY NOT NULL,
survey_no int not null, 
skill_name varchar(500),
qty varchar(500)
)

create table oz_survey_tools (
id int PRIMARY KEY NOT NULL,
survey_no int not null, 
tool_name varchar(500),
qty varchar(500)
)

--ITS给yonyou提供的视图
drop view its_StockAvailable;
create view v_its_StockAvailable as 
select * from AssetMan.[dbo].[StockAvailable];

create view v_its_ItemHistory as 
select * from AssetMan.[dbo].[ItemHistory];

alter table bhs_sostore_b alter column def2 varchar(3000);
alter table bhs_sostore_b alter column def12 varchar(3000);


--20180830 store打印
create view v_bhs_store_b as 
select b.billid_b, b.billid, c.code as custcode, c.name as custname, h.def19 as toolid, h.def2 as totalpkgs from bhs_sostore_b b 
inner join bhs_sostore_h h on h.billid = b.billid
inner join so_saleorder s on h.csaleorderid = s.csaleorderid
inner join bd_customer c on s.ccustomerid = c.pk_customer


--20180903 store增加字段
alter table bhs_sostore_h
add 
summarym2 DECIMAL(28,8) NULL,
summarym3 DECIMAL(28,8) NULL,
summarykg DECIMAL(28,8) NULL,
summarypkgs DECIMAL(28,8) NULL,
noofshockwatches DECIMAL(28,8) NULL,
nooftiltwatches DECIMAL(28,8) NULL,
largestlength DECIMAL(28,8) NULL,
largestwidth DECIMAL(28,8) NULL,
largestheight DECIMAL(28,8) NULL,
largestweight DECIMAL(28,8) NULL,
micapno VARCHAR(50) NULL,
oemtoolno VARCHAR(50) NULL,
toolidlid VARCHAR(50) NULL,
machinemake VARCHAR(50) NULL,
machinemodel VARCHAR(50) NULL,
machinesubmodel VARCHAR(50) NULL,
suppliername VARCHAR(50) NULL,
lcrate VARCHAR(50) NULL,
wcrate VARCHAR(50) NULL,
hcrate VARCHAR(50) NULL,
kcrate VARCHAR(50) NULL,
istopplerisk CHAR(1) NULL,
toppleriskcrateno VARCHAR(50) NULL,
damagedcrateno VARCHAR(50) NULL,
cratestatus VARCHAR(50) NULL,
iscentralgrapoint CHAR(1) NULL ;


--20180912 store itemhistory view
drop view v_its_ItemHistory;
create view v_its_ItemHistory as 
select a.Asset_ID, a.Asset_No, a.Description, a.Tool_ID, a.Micap_No, a.Set_No, a.EPC_ID, a.Date_of_Purchase, a.customer, a.Location, a.Length, a.Width, a.Height, a.Space_m2, a.Cubic_Meter, a.project
, convert(char(19), a.Date, 120) as Date_IN, convert(char(19),b.Date, 120) as Date_OUT,  a.Qty_In, b.Qty_Out, a.Person_In_Charge, a.Remarks, a.Doc_No
from AssetMan.[dbo].[ItemHistory] a left join AssetMan.[dbo].[ItemHistory] b on (a.Asset_ID = b.Asset_ID and b.qty_out is not null)
where a.qty_in is not null