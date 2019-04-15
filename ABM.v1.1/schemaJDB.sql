use abmjdb;


select *

from alumno;


insert into alumno
(dni,apyn,fechaNac,fechaIngr,sexo,cantMatAprob,promedio,carrera,estado)



values

(3,'Flores, Paula','1980-05-10','1995-06-30','F',12,6.7,'MED','A');



update alumno

set estado = "A"

where dni = 132;



start transaction;


delete

from alumno

where dni = 12123456;



commit;

rollback;