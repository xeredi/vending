ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER busdistance IDENTIFIED BY bus;

GRANT CREATE SESSION, CREATE ANY TABLE, CREATE ANY SEQUENCE, CREATE procedure, DROP ANY TABLE, INSERT ANY TABLE, UPDATE ANY TABLE, DELETE ANY TABLE TO busdistance;
ALTER USER busdistance QUOTA 1000M ON users;
