
#Задание
Система Больница. Врач определяет диагноз, делает назначение Пациенту 
(процедуры, лекарства, операции). Назначение может выполнить Медсестра 
(процедуры, лекарства) или Врач (любое назначение). Пациент может быть 
выписан из Больницы, при этом фиксируется окончательный диагноз.
#Для Установки :

    1) Скачать проект с github по ссылке: 
       git clone https://github.com/AAMSLieutenant/HOSPITAL.git
    2) Скачать и установить Apache Tomcat Server по ссылке:
       https://tomcat.apache.org/download-90.cgi
    2) Скачать и установить Oracle Database 11g:
        https://www.oracle.com/technetwork/products/express-edition/overview/index-100989.html
    
#Для Запуска :

    1) Настройте подключение к БД
    1) Запустите файлы : HOSPITAL/scripts/base.sql и data.sql
    2) Разверните Apache Server
    4) Соберите проект сборщиком типа Maven
    5) Перейти по стандартному адресу localhost:8089
