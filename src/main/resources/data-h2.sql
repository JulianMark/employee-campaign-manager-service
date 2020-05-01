--TIPOS DE EMPLEADOS
INSERT INTO EMPLOYEE_TYPES (TYPE_EMPLOYEE)
VALUES ('FACER');
INSERT INTO EMPLOYEE_TYPES (TYPE_EMPLOYEE)
VALUES ('TEAM LEADER');
INSERT INTO EMPLOYEE_TYPES (TYPE_EMPLOYEE)
VALUES ('COACH');

--EMPLEADOS
INSERT INTO EMPLOYEES (NAME, LASTNAME, DNI, NICKNAME, PASSWORD)
VALUES ('JULIAN', 'MARK', '31646189', 'JMARK', '123');
INSERT INTO EMPLOYEES (NAME, LASTNAME, DNI, NICKNAME, PASSWORD)
VALUES ('DANIELA', 'SANZ', '35898878', 'DSANZ', '123');
INSERT INTO EMPLOYEES (NAME, LASTNAME, DNI, NICKNAME, PASSWORD)
VALUES ('JUAN', 'BLANDO', '35898875', 'JBLANDI', '123');

--ORGANIZACIONES SOCIALES Y CIVILES
INSERT INTO OSC (DESCRIPTION)
VALUES ('AFULIC');
INSERT INTO OSC (DESCRIPTION)
VALUES ('SOLES');

--lOCALIZACION DE LAS CAMPANIAS
INSERT INTO LOCATIONS (DESCRIPTION)
VALUES ('CORDOBA');

--TIPOS DE CAMPANIAS
INSERT INTO CAMPAIGN_TYPES (DESCRIPTION)
VALUES ('ITINERANCIA');
INSERT INTO CAMPAIGN_TYPES (DESCRIPTION)
VALUES ('VIA PUBLICA');
INSERT INTO CAMPAIGN_TYPES (DESCRIPTION)
VALUES ('EVENTOS');

--CAMPANIAS
INSERT INTO CAMPAIGNS (NAME, ID_LOC, ID_OSC, ID_TYPE_CAMPAIGN)
VALUES ('RIO CUARTO',1,1,1);
INSERT INTO CAMPAIGNS (NAME, ID_LOC, ID_OSC, ID_TYPE_CAMPAIGN)
VALUES ('VILLA ALLENDE',1,2,1);
INSERT INTO CAMPAIGNS (NAME, ID_LOC, ID_OSC, ID_TYPE_CAMPAIGN)
VALUES ('MERLO',1,2,1);

--EMPLEADO ID 1
INSERT INTO ACTIVE_CAMPAIGNS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE)
VALUES(1, 3, 1);
--EMPLEADO ID 2
INSERT INTO ACTIVE_CAMPAIGNS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE)
VALUES(2, 3, 2);

--EQUIPOS
--EMPLEADO JULIAN ID 1 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (1, 1, 1, SYSDATE, NULL);
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (1, 2, 1, SYSDATE, NULL);
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (1, 3, 1, SYSDATE, NULL);
--EMPLEADO DANIELA ID 2 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (2, 1, 2, SYSDATE, NULL);
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (2, 2, 2, SYSDATE, NULL);
INSERT INTO TEAMS (ID_EMPLOYEE, ID_CAMPAIGN, ID_TYPE_EMPLOYEE, INITIAL_DATE, FINAL_DATE)
VALUES (2, 3, 2, SYSDATE, SYSDATE);

--TIPO DE PAGOS
INSERT INTO PAY_TYPES (DESCRIPTION)
VALUES ('CREDITO');
INSERT INTO PAY_TYPES (DESCRIPTION)
VALUES ('DEBITO');

--DONACIONES EMPLEADO JULIAN ID 1 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (300, 1, 1, 1,'31646181','JUANA','PEREZ',35,'F', SYSDATE);
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (500, 2, 1, 1,'31646182','JUAN','GOMEZ',25,'M', SYSDATE);
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (400, 1, 1, 1,'31646183','MARIA','SUAREZ',28,'I', SYSDATE);
--FIN DONACIONES EMPLEADO JULIAN ID 1 CAMPAÑA 1 OSC AFULIC ID 1

--DONACIONES EMPLEADO DANIELA ID 2 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (350, 1, 2, 1,'23646181','SAMANTA','ROMERO',45,'F', SYSDATE);
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (300, 2, 2, 1,'24646182','EDUARDO','GONZALES',44,'M', SYSDATE);
INSERT INTO DONATIONS (AMOUNT, ID_TYPE_PAY, ID_EMPLOYEE, ID_CAMPAIGN,
                       DNI, NAME, LASTNAME, AGE, GENDER, CREATE_DATE)
VALUES (400, 1, 2, 1,'24646183','MARLON','SUZUKI',88,'I', SYSDATE);
--FIN DONACIONES EMPLEADO DANIELA ID 2 CAMPAÑA 1 OSC AFULIC ID 1

--REGISTRO DIARIO EMPLEADO JULIAN ID 1 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO DAILY_RECORDS (ID_EMPLOYEE, ID_CAMPAIGN, PRODUCTIVE_HOURS, NON_PRODUCTIVE_HOURS, CREATE_DATE)
VALUES (1, 1, 3, 1, TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
--FIN REGISTRO DIARIO EMPLEADO JULIAN ID 1 CAMPAÑA 1 OSC AFULIC ID 1

--REGISTRO DIARIO EMPLEADO DANIELA ID 2 CAMPAÑA 1 OSC AFULIC ID 1
INSERT INTO DAILY_RECORDS (ID_EMPLOYEE, ID_CAMPAIGN, PRODUCTIVE_HOURS, NON_PRODUCTIVE_HOURS, CREATE_DATE)
VALUES (2, 1, 5, 3, TO_DATE('2019/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));
--FIN REGISTRO DIARIO EMPLEADO DANIELA ID 2 CAMPAÑA 1 OSC AFULIC ID 1

