/* 
 * ITSS4380.501, GROUP11.SQL
 * Milestone 3 and 4
 * Script file for ORACLE DBMS
 *
 * This script file creates the following
 * Database Schema: dbo
 * VW_ALL_EMPLOYEES,
 * SP_PART_REPORT, SP_EMP_ADD, SP_EMP_GET
 * trg_print_capacity_change
 */

-- VIEWS ----------------------------------------------------
/* vw_employee_address */
CREATE OR REPLACE VIEW dbo.VW_ALL_EMPLOYEES AS
    -- This view is to denormalize employee name and address,
    -- and show their information as a single table.
    SELECT en.EMPLOYEE_NUMBER AS EMPLOYEE, FIRST_NAME, MIDDLE_NAME, LAST_NAME,
           '(' || EMP_PHONE_AREA || ') ' || EMP_PHONE_NUMBER AS PHONE,
	   EMP_ADDRESS_STNUM || ' ' || EMP_ADDRESS_STNAME AS STREET, 
           EMP_ADDRESS_CITY CITY, EMP_ADDRESS_STATE STATE, EMP_ADDRESS_ZIP ZIP
    FROM EMP_NAME en, EMP_ADDRESS ea, EMP_PHONE ep
    WHERE en.EMPLOYEE_NUMBER = ea.EMPLOYEE_NUMBER AND
          en.EMPLOYEE_NUMBER = ep.EMPLOYEE_NUMBER;
/

-- Stored Procedures ----------------------------------------------------
/* sp_PART_REPORT */
CREATE Or REPLACE Procedure dbo.sp_PART_REPORT (partnum in VARCHAR)
AS
-- This procedure creates a report that tells the locations of specified parts and how much room is left in the Bins
	partnumber VARCHAR(5) := UPPER(partnum);
	warehouseid VARCHAR(4);
	binid NUMBER;
	remcapacity NUMBER;
	countbin NUMBER := 0;

CURSOR capacity_cursor IS
	SELECT B.WAREHOUSE_ID, B.BIN_NUMBER, SUM(BIN_CAPACITY - BATCH_SIZE) AS "REMAINING_CAPACITY"
	FROM BATCH B, BIN
	WHERE B.BIN_NUMBER = BIN.BIN_NUMBER and part_number = partnumber
	GROUP BY B.BIN_NUMBER, B.WAREHOUSE_ID, part_number;
BEGIN
	partnumber :=UPPER(partnumber);

	DBMS_OUTPUT.PUT_LINE ('     	Locations of Part ' || partnumber);
	DBMS_OUTPUT.PUT_LINE ('==============================================');
	DBMS_OUTPUT.PUT_LINE ('Warehouse ID  	Bin Number 	Remaning Capacity');
	DBMS_OUTPUT.PUT_LINE ('==============================================');

OPEN capacity_cursor;
LOOP
	fetch capacity_cursor into warehouseid, binid, remcapacity;
	exit when capacity_cursor%notfound;
	countbin := countbin + 1;
	DBMS_OUTPUT.PUT_LINE (warehouseid || '   	---->  	' || binid || '  	---->  	' || remcapacity);
END LOOP;

	DBMS_OUTPUT.PUT_LINE ('==============================================');
	DBMS_OUTPUT.PUT_LINE ('   	Part ' || partnumber || ' is in ' || countbin || ' Locations');
CLOSE capacity_cursor;
End;
/

/* SP_EMP_ADD */
CREATE OR REPLACE PROCEDURE dbo.SP_EMP_ADD
	(W_EMP_NUM IN NUMBER, W_FN IN VARCHAR2, W_LN IN VARCHAR2, W_MN IN VARCHAR2, W_AC IN VARCHAR2, W_PN IN VARCHAR2, 
	 W_SNUM IN NUMBER, W_SNAME IN VARCHAR2, W_CITY IN VARCHAR2, W_STATE IN VARCHAR2, W_ZIP IN NUMBER)
AS 

BEGIN
   INSERT INTO EMPLOYEE(EMPLOYEE_NUMBER)
          VALUES (W_EMP_NUM);
   INSERT INTO EMP_NAME(FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMPLOYEE_NUMBER)
          VALUES (W_FN, W_LN, W_MN, W_EMP_NUM);
   INSERT INTO EMP_PHONE(EMP_PHONE_AREA, EMP_PHONE_NUMBER, EMPLOYEE_NUMBER)
          VALUES (W_AC, W_PN, W_EMP_NUM);
   INSERT INTO EMP_ADDRESS(EMPLOYEE_NUMBER, EMP_ADDRESS_STNUM, EMP_ADDRESS_STNAME, EMP_ADDRESS_CITY, EMP_ADDRESS_STATE, EMP_ADDRESS_ZIP)
          VALUES (W_EMP_NUM, W_SNUM, W_SNAME, W_CITY, W_STATE, W_ZIP);
   DBMS_OUTPUT.PUT_LINE ('Employee ' || W_EMP_NUM || ' added.');
END;
/

/* SP_EMP_GET */
CREATE OR REPLACE PROCEDURE dbo.SP_EMP_GET IS
	W_EMPLOYEE_NUMBER	NUMBER;
	W_FIRST_NAME	        VARCHAR2(20);
	W_MIDDLE_NAME           VARCHAR2(20);
	W_LAST_NAME             VARCHAR2(20);
	W_PHONE                 VARCHAR2(20);
	W_EMP_ADDRESS_STREET    VARCHAR2(20);
	W_EMP_ADDRESS_CITY      VARCHAR2(20);
	W_EMP_ADDRESS_STATE     VARCHAR2(20);
	W_EMP_ADDRESS_ZIP	NUMBER;
	
CURSOR EMP_CURSOR IS 
     SELECT EMPLOYEE, FIRST_NAME, MIDDLE_NAME, LAST_NAME, PHONE, STREET, CITY, STATE, ZIP
     FROM VW_EMPLOYEE_ADDRESS;
	 
BEGIN
	DBMS_OUTPUT.PUT_LINE('======================================');

OPEN EMP_CURSOR;
LOOP
   FETCH EMP_CURSOR INTO W_EMPLOYEE_NUMBER, W_FIRST_NAME, W_MIDDLE_NAME, W_LAST_NAME, w_PHONE, W_EMP_ADDRESS_STREET, W_EMP_ADDRESS_CITY, W_EMP_ADDRESS_STATE, W_EMP_ADDRESS_ZIP;
   EXIT WHEN EMP_CURSOR%NOTFOUND;
   DBMS_OUTPUT.PUT_LINE(W_EMPLOYEE_NUMBER || W_FIRST_NAME || W_MIDDLE_NAME || W_LAST_NAME || W_PHONE || W_EMP_ADDRESS_STREET || W_EMP_ADDRESS_CITY || W_EMP_ADDRESS_STATE || W_EMP_ADDRESS_ZIP);
END LOOP;
	DBMS_OUTPUT.PUT_LINE('======================================');
	DBMS_OUTPUT.PUT_LINE('--- END OF REPORT ----');
CLOSE EMP_CURSOR;
END;
/

-- Add TRIGGERS ----------------------------------------------------
/* trg_print_capacity_change */
CREATE OR REPLACE TRIGGER dbo.trg_print_capacity_change
BEFORE DELETE OR INSERT OR UPDATE ON BIN
FOR EACH ROW
-- This trigger will show what the revised bin capacities
-- are when they get changed
DECLARE
    bin_inc NUMBER;
    inc_type VARCHAR(12);
BEGIN
    bin_inc := :NEW.BIN_CAPACITY - :OLD.BIN_CAPACITY;
    
    IF bin_inc > 0 THEN
        inc_type := 'increased ';
    ELSIF bin_inc < 0 THEN
        inc_type := 'decreased ';
    END IF;
    
    IF bin_inc <> 0 THEN
        DBMS_OUTPUT.PUT('WAREHOUSE ' || :NEW.WAREHOUSE_ID);
        DBMS_OUTPUT.PUT(', BIN ' || :NEW.BIN_NUMBER);
        DBMS_OUTPUT.PUT(' capacity has ' || inc_type);
        DBMS_OUTPUT.PUT(ABS(bin_inc) || ' to ');
        DBMS_OUTPUT.PUT_LINE(:NEW.BIN_CAPACITY || '.');
    END IF;
END;
/
