create database Employee;
use Employee;
create table Employee (
    id int primary key auto_increment,
    name varchar(100),
    phone int,
    address varchar(100),
    birthday DATE,
    sex boolean default true,
    salary float
);

insert into Employee(name, phone, address, birthday,sex, salary)
values ('Tuan', 0787656397,'Thanh Hóa', '1994-1-10',true, 2000);
insert into Employee(name, phone, address, birthday,sex, salary)
values ('Linh', 0989111651,'Da Nang', '1992-2-12',false, 1000);
insert into Employee(name, phone, address, birthday,sex, salary)
values ('Hai', 0987654321,'Hai Phong', '1993-4-11',true, 2100);

DELIMITER //

CREATE PROCEDURE ADD_EMPLOYEE(
    IN p_name VARCHAR(100),
    IN p_phone INT,
    IN p_address VARCHAR(100),
    IN p_birthday DATE,
    IN p_sex BOOLEAN,
    IN p_salary FLOAT
)
BEGIN
    INSERT INTO Employee ( name, phone, address, birthday, sex, salary)
    VALUES ( p_name, p_phone, p_address, p_birthday, p_sex, p_salary);
END //

DELIMITER ;
# Xoa
DELIMITER //

CREATE PROCEDURE DELETE_EMPLOYEE(
    IN p_id INT
)
BEGIN
    DELETE FROM Employee WHERE id = p_id;
END //

DELIMITER ;

DELIMITER //

DELIMITER //

CREATE PROCEDURE FIND_BY_NAME(
    IN p_keyword VARCHAR(100)
)
BEGIN
    SELECT *
    FROM Employee
    WHERE
            name LIKE CONCAT('%', p_keyword, '%')

    ORDER BY id;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE UPDATE_EMPLOYEE(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_phone INT,
    IN p_address VARCHAR(100),
    IN p_birthday DATE,
    IN p_sex BOOLEAN,
    IN p_salary FLOAT
)
BEGIN
    UPDATE Employee
    SET
        name = p_name,
        phone = p_phone,
        address = p_address,
        birthday = p_birthday,
        sex = p_sex,
        salary = p_salary
    WHERE id = p_id;
END //

DELIMITER ;

#  update
DELIMITER //

CREATE PROCEDURE UPDATE_EMPLOYEE(
    IN p_id INT,
    IN p_name VARCHAR(100),
    IN p_phone INT,
    IN p_address VARCHAR(100),
    IN p_birthday DATE,
    IN p_sex BOOLEAN,
    IN p_salary FLOAT
)
BEGIN
    UPDATE Employee
    SET
        id = p_id,
        name = p_name,
        phone = p_phone,
        address = p_address,
        birthday = p_birthday,
        sex = p_sex,
        salary = p_salary
    WHERE id = p_id;
END //

DELIMITER ;
# finbyId
DELIMITER //

CREATE PROCEDURE FIND_BY_ID(
    IN p_id INT
)
BEGIN
    SELECT *
    FROM Employee
    WHERE id = p_id;
END //

DELIMITER ;


# danh sách nhân viên
DELIMITER //
create procedure FINDALL()
begin
    select * from Employee;
end//
DELIMITER
call FINDALL()




