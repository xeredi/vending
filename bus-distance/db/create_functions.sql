CREATE OR REPLACE FUNCTION degree_to_radian(p_degree IN NUMBER)
   RETURN NUMBER IS
BEGIN
   RETURN p_degree / (180/ACOS(-1));
END degree_to_radian;


CREATE OR REPLACE FUNCTION distanciaKm(latOrig IN NUMBER, lonOrig IN NUMBER, latDest IN NUMBER, lonDest IN NUMBER)
   RETURN NUMBER IS
BEGIN
   RETURN (acos(sin(degree_to_radian(latOrig)) * sin(degree_to_radian(latDest)) +
        cos(degree_to_radian(latOrig)) * cos(degree_to_radian(latDest)) *
        cos(degree_to_radian(lonOrig) - degree_to_radian(lonDest))) * 6378)
    ;
END distanciaKm;

-- TEST
select distanciaKm(36.720139, -4.419422, 40.425797, -3.690462) from dual;
select distanciaKm(43.31819167, -8.365175, 43.31823667, -8.36503667) from dual;
