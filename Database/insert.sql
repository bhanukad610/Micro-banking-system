#bymelonie
use centralServer;
insert into branches values('0004','Nugegoda');
insert into branches values('0005','Panadura');
insert into branches values('0006','Matara');
insert into branches values('0007','Jaffna');
insert into branches values('0008','Kandy');
insert into branches values('0009','Galle');

Insert into bankingAgents values('00004','121212121H','0771212121','Leo Messi','11,flower st,Nugegoda','details','0004');
Insert into bankingAgents values('00005','121212122H','0772212121','Chris Ronal','10,plant st,Panadura','details','0005');
Insert into bankingAgents values('00006','121212123H','0773212121','Virat Kohli','9,garden st,Matara','details','0006');
Insert into bankingAgents values('00007','121212124H','0774212121','Robert Nox','8,tree st,Jaffna','details','0007');
Insert into bankingAgents values('00008','121212125H','0775212121','Barak Trump','7,house st,Kandy','details','0008');
Insert into bankingAgents values('00009','121212126H','0776212121','Jeorge Clinton','6,school st,Galle','details','0009');

insert into customers values('111112222H','Mark Anthony','0711212121','1,forest road,Kohuwala','00004');
insert into customers values('111113333H','Alan Walker','0712212121','3,jungle road,Horana','00005');
insert into customers values('111114444H','Justin Beiber','0713212121','5,wood road,Matara','00006');
insert into customers values('111115555H','Ed sheeran','0714212121','7,cemetry road,Jaffna','00007');
insert into customers values('111116666H','Shayne Ward','0715212121','9,sea road,Badulla','00008');
insert into customers values('111117777H','Jennifer Lopez','0716212121','11,ocean road,Galle','00009');

insert into accounts values('160111', '111112222H','adult','ok', 15000.00, 'good', '0004');
insert into accounts values('160112', '111113333H','teen','ok', 20000.00, 'good', '0005');
insert into accounts values('160113', '111114444H','senior','ok', 150000.00, 'good', '0006');
insert into accounts values('160114', '111115555H','children','ok', 30000.00, 'good', '0007');
insert into accounts values('160115', '111116666H','adult','ok', 5000.00, 'good', '0008');
insert into accounts values('160116', '111117777H','adult','ok', 25000.00, 'good', '0009');
insert into accounts values('160117', '111117777H','adult','ok', 35000.00, 'good', '0009');

