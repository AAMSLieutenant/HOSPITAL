DROP TABLE patient;
DROP TABLE address;
DROP TABLE appointment;
DROP TABLE diagnosis;
DROP TABLE operation;
DROP TABLE medicine;
DROP TABLE proced;
DROP TABLE employee;
DROP TABLE position;
DROP TABLE diag_oper;
DROP TABLE diag_med;
DROP TABLE diag_proc;
DROP TABLE uuser;

CREATE TABLE patient(
	card_id number(11) NOT NULL PRIMARY KEY,
	p_name varchar(30) NOT NULL,
	p_surname varchar(30) NOT NULL,
	p_patronymic varchar(30) NOT NULL,
	p_age number(3) NOT NULL,
	p_birth_date Date NOT NULL,
	p_sex varchar(10) NOT NULL,
	p_arrival_date Date NOT NULL,
	p_discharge_date Date
);

CREATE TABLE address(
	card_id number(11),
	city varchar(30) NOT NULL,
	street varchar(30) NOT NULL,
	house_number number(4) NOT NULL,
	flat_number number(4) NOT NULL
);

CREATE TABLE appointment(
	app_id number(11) NOT NULL PRIMARY KEY,
	app_date Date NOT NULL,
	app_value number(10,4) NOT NULL,
	app_complaint varchar(300) NOT NULL,
	doc_id number(11) NOT NULL,
	card_id number(11) NOT NULL
);

CREATE TABLE diagnosis(
	diag_id number(11) NOT NULL PRIMARY KEY,
	diag_name varchar(300) NOT NULL,
	card_id number(11)
);

CREATE TABLE operation(
	oper_id number(11) NOT NULL PRIMARY KEY,
	oper_name varchar(130) NOT NULL,
	oper_value number(10,4) NOT NULL,
	oper_length number(4) NOT NULL,
	doctor_id number(11)
);

CREATE TABLE medicine(
	med_id number(11) NOT NULL PRIMARY KEY,
	med_name varchar(130) NOT NULL,
	med_value number(10,4) NOT NULL,
	emp_id number(11)
);

CREATE TABLE proced(
	proc_id number(11) NOT NULL PRIMARY KEY,
	proc_name varchar(130) NOT NULL,
	proc_value number(10,4) NOT NULL,
	emp_id number(11)
);

CREATE TABLE employee(
	emp_id number(11) NOT NULL PRIMARY KEY,
	emp_name varchar(30) NOT NULL,
	emp_surname varchar(30) NOT NULL,
	emp_patronymic varchar(30) NOT NULL,
	emp_age number(3) NOT NULL,
	emp_hire_date Date NOT NULL,
	emp_pos_id number(11) NOT NULL
);

CREATE TABLE uuser(
	user_id number(11) UNIQUE,
	login varchar(30) NOT NULL UNIQUE,
	password varchar(30) NOT NULL
);

CREATE TABLE position(
	pos_id number(11) NOT NULL PRIMARY KEY,
	pos_name varchar(30) NOT NULL,
	pos_salary number(10,4) NOT NULL,
	is_md number(1) NOT NULL
);

CREATE TABLE diag_oper(
	do_id number(11) UNIQUE,
	diag_id number(11),
	oper_date Date NOT NULL,
	oper_done number(1) NOT NULL,
	oper_id number(11) NOT NULL
);

CREATE TABLE diag_med(
	dm_id number(11) UNIQUE,
	diag_id number(11),
	med_start Date NOT NULL,
	med_end Date NOT NULL,
	med_done number(1) NOT NULL,
	med_id number(11) NOT NULL
);

CREATE TABLE diag_proc(
	dp_id number(11) UNIQUE,
	diag_id number(11),
	proc_date Date NOT NULL,
	proc_done number(1) NOT NULL,
	proc_id number(11) NOT NULL
);


ALTER TABLE address DROP CONSTRAINT add_fk_pat;
ALTER TABLE appointment DROP CONSTRAINT app_fk_pat;
ALTER TABLE appointment DROP CONSTRAINT app_fk_emp;
ALTER TABLE diagnosis DROP CONSTRAINT diag_fk_pat;
ALTER TABLE employee DROP CONSTRAINT emp_fk_pos;
ALTER TABLE uuser DROP CONSTRAINT uuser_fk_emp;
ALTER TABLE operation DROP CONSTRAINT oper_fk_emp;
ALTER TABLE medicine DROP CONSTRAINT med_fk_emp;
ALTER TABLE proced DROP CONSTRAINT proc_fk_emp;
ALTER TABLE diag_oper DROP CONSTRAINT diag_oper_fk_diag;
ALTER TABLE diag_oper DROP CONSTRAINT diag_oper_fk_oper;
ALTER TABLE diag_med DROP CONSTRAINT diag_med_fk_diag;
ALTER TABLE diag_med DROP CONSTRAINT diag_med_fk_med;
ALTER TABLE diag_proc DROP CONSTRAINT diag_proc_fk_diag; 
ALTER TABLE diag_proc DROP CONSTRAINT diag_proc_fk_proc; 



ALTER TABLE address ADD CONSTRAINT add_fk_pat FOREIGN KEY(card_id) REFERENCES patient(card_id) ON DELETE CASCADE;

ALTER TABLE appointment ADD CONSTRAINT app_fk_pat FOREIGN KEY(card_id) REFERENCES patient(card_id) ON DELETE CASCADE;
ALTER TABLE appointment ADD CONSTRAINT app_fk_emp FOREIGN KEY(doc_id) REFERENCES employee(emp_id) ON DELETE CASCADE;

ALTER TABLE diagnosis ADD CONSTRAINT diag_fk_pat FOREIGN KEY(card_id) REFERENCES patient(card_id) ON DELETE CASCADE;

ALTER TABLE employee ADD CONSTRAINT emp_fk_pos FOREIGN KEY(emp_pos_id) REFERENCES position(pos_id) ON DELETE CASCADE;

ALTER TABLE uuser ADD CONSTRAINT uuser_fk_emp FOREIGN KEY(user_id) REFERENCES employee(emp_id) ON DELETE CASCADE;

ALTER TABLE operation ADD CONSTRAINT oper_fk_emp FOREIGN KEY(doctor_id) REFERENCES employee(emp_id) ON DELETE CASCADE;

ALTER TABLE medicine ADD CONSTRAINT med_fk_emp FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE;

ALTER TABLE proced ADD CONSTRAINT proc_fk_emp FOREIGN KEY(emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE;


ALTER TABLE diag_oper ADD CONSTRAINT diag_oper_fk_diag FOREIGN KEY(diag_id) REFERENCES diagnosis(diag_id) ON DELETE CASCADE;
ALTER TABLE diag_oper ADD CONSTRAINT diag_oper_fk_oper FOREIGN KEY(oper_id) REFERENCES operation(oper_id) ON DELETE CASCADE;

ALTER TABLE diag_med ADD CONSTRAINT diag_med_fk_diag FOREIGN KEY(diag_id) REFERENCES diagnosis(diag_id) ON DELETE CASCADE;
ALTER TABLE diag_med ADD CONSTRAINT diag_med_fk_med FOREIGN KEY(med_id) REFERENCES medicine(med_id) ON DELETE CASCADE;

ALTER TABLE diag_proc ADD CONSTRAINT diag_proc_fk_diag FOREIGN KEY(diag_id) REFERENCES diagnosis(diag_id) ON DELETE CASCADE;
ALTER TABLE diag_proc ADD CONSTRAINT diag_proc_fk_proc FOREIGN KEY(proc_id) REFERENCES proced(proc_id) ON DELETE CASCADE;



