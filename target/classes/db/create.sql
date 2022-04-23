CREATE TABLE PUBLIC."USER" (
                               USERNAME VARCHAR_IGNORECASE,
                               PASSWORD VARCHAR_IGNORECASE,
                               ID BIGINT AUTO_INCREMENT,
                               CONSTRAINT USER_PK PRIMARY KEY (ID)
);

CREATE TABLE PUBLIC.EMPLOYEES (
                                  ID INTEGER AUTO_INCREMENT,
                                  EMPLOYEE_NUMBER INTEGER,
                                  FIRST_NAME CHARACTER VARYING(100),
                                  LAST_NAME CHARACTER VARYING(100),
                                  CONTACT_NUMBER CHARACTER VARYING(20),
                                  BIRTHDAY DATE,
                                  ADDRESS CHARACTER VARYING(200),
                                  CONSTRAINT EMPLOYEES_PK PRIMARY KEY (ID)
);